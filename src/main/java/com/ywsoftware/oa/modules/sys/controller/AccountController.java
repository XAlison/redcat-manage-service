package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.modules.sys.entity.Account;
import com.ywsoftware.oa.modules.sys.model.LoginModel;
import com.ywsoftware.oa.modules.sys.service.impl.AccountService;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class AccountController extends  AbstractController {

    private final AccountService service;


    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public  ResponseEntity<List<Account>> findAll() {
        return ok(service.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginModel model, HttpServletRequest request)
            throws Exception {
       return ok("'登陆成功");
    }

    @GetMapping("/getAccountsByPhone")
    public ResponseEntity getAccountsByPhone(String phone, String loginIp, String cityId, String cityName, HttpServletRequest request) {
        return ok("");
    }

    @PostMapping("/setManagePassword")
    public ResponseEntity setManagePassword(@RequestBody LoginModel loginModel) throws NoSuchAlgorithmException {

        return ok("设置管理员密码成功");
    }

    @GetMapping("/current")
    public ResponseEntity current() throws Exception {

        return ok("设置管理员密码成功");
    }

    @PostMapping("/loginOut")
    public ResponseEntity loginOut() {

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

        }
        return ok(result);
    }

    @GetMapping(value = "/isReportPasswordEffective/{id}")
    public Boolean isReportPasswordEffective(@PathVariable String id) throws Exception {

        return true;
    }
}
