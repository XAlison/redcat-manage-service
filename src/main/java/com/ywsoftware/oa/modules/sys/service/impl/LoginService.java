package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.common.exception.InvalidParameterAppException;
import com.ywsoftware.oa.common.utils.MD5;
import com.ywsoftware.oa.common.utils.PasswordIntensityChecker;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.domain.entity.AccountLogs;
import com.ywsoftware.oa.modules.sys.model.LoginModel;
import com.ywsoftware.oa.modules.sys.model.TenantAccountModel;
import com.ywsoftware.oa.modules.sys.service.mapper.AccountMapper;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author yx
 */
@Service
public class LoginService {

    private final AccountMapper accountMapper;
    private final SecurityService securityService;
    private final AccountLogsService accountLogsService;

    @Autowired
    public LoginService(
            AccountMapper accountMapper,
            SecurityService securityService, AccountLogsService accountLogsService) {
        this.accountMapper = accountMapper;
        this.securityService = securityService;
        this.accountLogsService = accountLogsService;
    }

    public List<TenantAccountModel> getAccountsByPhone(String phone, String loginIp, String cityId, String cityName, HttpServletRequest request) {
        if (StringUtils.isEmpty(phone)) {
            throw new InvalidParameterAppException("手机号不可为空.");
        }

        List<Account> accounts = accountMapper.findByPhone(phone);
        List<TenantAccountModel> tenants = accountMapper.findTenantAccounts(phone);

        List<TenantAccountModel> models = new ArrayList<>();
        // 校验二次验证信息
        int multiValidate = 0;
        if (accounts != null && accounts.size() > 0) {
            multiValidate = secondValidate(phone, loginIp, cityId, cityName, request);
        }
        for (Account account : accounts) {
            // 过滤非管理员用户
            if (!account.getManager()) {
                continue;
            }
            TenantAccountModel model = tenants.stream()
                    .filter(m -> account.getId().equals(m.getAccountId())).findAny().orElse(null);
            if (model == null) {
                continue;
            }
            model.setFirstLogin(account.getManagePasswordHash() == null);
            model.setMultiValidate(multiValidate);
            models.add(model);
        }
        //如果是空列表
        if (models.size() == 0) {
            throw new InvalidParameterAppException("用户不存在.");
        }

        return models;
    }

    /**
     * 账号登录方法
     *
     * @param loginModel 电话 密码 验证码
     * @return
     */
    public Account login(LoginModel loginModel, HttpServletRequest request) throws Exception {
        // 非二次校验不启用验证码
        if (loginModel.getMultiValidate() >= 3 || !"".equals(loginModel.getPhoneCode())) {
            securityService.verPhoneCode("1", loginModel.getPhoneCode(), loginModel.getPhone());
        }
        //将密码md5加密
        loginModel.setPassword(MD5.md5(loginModel.getPassword()));
        Account userAccount = accountMapper.findUserByPhoneAndPassword(loginModel);
        // 账号登录日志记录
        accountLogsService.saveUserLog(loginModel.getPhone(),
                userAccount == null ? "账号或密码错误" : "正常登录",
                loginModel.getLoginIp(), loginModel.getCityId(),
                loginModel.getCityName(), request);
        if (userAccount == null) {
            throw new InvalidParameterAppException("账号或密码错误");
        }
        return userAccount;
    }

    /**
     * 设置管理员密码方法
     *
     * @param loginModel 电话 密码 验证码
     */
    public void setManagePassword(LoginModel loginModel) throws NoSuchAlgorithmException {
        securityService.verPhoneCode("2", loginModel.getPhoneCode(), loginModel.getPhone());
        //密码强度校验
        if (!PasswordIntensityChecker.includeNumChar(loginModel.getPassword())) {
            throw new InvalidParameterAppException("密码必须数字、大写字母、小写字母组合，长度不小于6位");
        }
        //密码md5加密
        loginModel.setPassword(MD5.md5(loginModel.getPassword()));
        accountMapper.setManagePassword(loginModel);
    }

    /**
     * @Description: 用户二次校验
     * @Date: 2018/11/5 10:45
     * @Version 1.0
     */
    public int secondValidate(String userId, String loginIp, String cityId, String cityName, HttpServletRequest request) {
        AccountLogs accountLogs = accountLogsService.getUserLoginLog(userId, "验证登录", loginIp, cityId, cityName, request);
        return accountLogsService.secondValidate(accountLogs);
    }

}
