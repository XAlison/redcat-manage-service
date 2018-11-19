package com.ywsoftware.oa.modules.sys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywsoftware.oa.common.utils.AesHelper;
import com.ywsoftware.oa.common.utils.CookieHelper;
import com.ywsoftware.oa.common.utils.MD5;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.domain.login.LoginCookie;
import com.ywsoftware.oa.modules.sys.model.LoginModel;
import com.ywsoftware.oa.modules.sys.service.impl.AccountService;
import com.ywsoftware.oa.modules.sys.service.impl.LoginService;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends AbstractController {

    private final AccountService service;
    private final LoginService loginService;

    @Autowired
    public AccountController(AccountService service, LoginService loginService) {
        this.service = service;
        this.loginService = loginService;
    }

    @GetMapping
    ResponseEntity<List<Account>> findAll() {
        return ok(service.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginModel model, HttpServletRequest request)
            throws Exception {
        //登录
        Account account = loginService.login(model, request);
        // 登入成功 写入cookie
        LoginCookie cookie = new LoginCookie(account.getId(), account.getName(), account.getPhone(), account.getTenantId(), account.getManager());
        CookieHelper.addCookie("loginCookie", AesHelper.encrypt(new ObjectMapper().writeValueAsString(cookie)), false);
        return ok("登陆成功");
    }

    @GetMapping("/getAccountsByPhone")
    public ResponseEntity getAccountsByPhone(String phone, String loginIp, String cityId, String cityName, HttpServletRequest request) {
        return ok(loginService.getAccountsByPhone(phone, loginIp, cityId, cityName, request));
    }

    @PostMapping("/setManagePassword")
    public ResponseEntity setManagePassword(@RequestBody LoginModel loginModel) throws NoSuchAlgorithmException {
        loginService.setManagePassword(loginModel);
        return ok("设置管理员密码成功");
    }

    @GetMapping("/current")
    public ResponseEntity current() throws Exception {
        return ok(/*getLoginCookie()*/"ok");
    }

    @PostMapping("/loginOut")
    public ResponseEntity loginOut() {
        // 登出
        CookieHelper.clearCookie();
        return ok("登出成功");
    }

    @GetMapping(value = "/security/{id}")
    public ResponseEntity<?> useSecurity(@PathVariable String id) {
        return ok(service.haveSecurity(id));
    }

    @GetMapping(value = "/security/validator", params = {"id", "password"})
    public ResponseEntity<?> validatorSecurity(@RequestParam String id, @RequestParam String password) throws NoSuchAlgorithmException {
        boolean result = service.validatorSecurity(id, password);
        if (result) {
            // 创建 cook
            CookieHelper.addCookie("report" + id, MD5.md5(password), 60 * 60 * 12);
        }
        return ok(result);
    }

    @GetMapping(value = "/isReportPasswordEffective/{id}")
    public Boolean isReportPasswordEffective(@PathVariable String id) throws Exception {
        return StringUtils.isNotEmpty(CookieHelper.getCookie("report" + id));
    }
}
