package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.common.exception.InvalidParameterAppException;
import com.ywsoftware.oa.common.utils.CookieHelper;
import com.ywsoftware.oa.common.utils.MD5;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.domain.login.LoginCookie;
import com.ywsoftware.oa.modules.sys.model.LoginModel;
import com.ywsoftware.oa.modules.sys.service.mapper.AccountMapper;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yx
 */
@Service
public class ManagerService {

    private final Logger logger = LoggerFactory.getLogger(ManagerService.class);

    private final AccountMapper accountMapper;
    private final SecurityService securityService;

    @Autowired
    public ManagerService(AccountMapper accountMapper, SecurityService securityService) {
        this.accountMapper = accountMapper;
        this.securityService = securityService;
    }

    @Transactional
    public void transferManager(LoginModel model, String userId) throws NoSuchAlgorithmException {
        logger.debug(model.getId());
        logger.debug(userId);
        //校验短信
        securityService.verPhoneCode("4", model.getPhoneCode(), model.getPhone());
        //将现有账户管理员账号密码置空
        accountMapper.delManager(userId);
        //设置当前管理员
        int updateCount = accountMapper.setManager(model.getId());
        if (updateCount == 0) {
            throw new InvalidParameterAppException("更换管理员失败，请选择有效用户");
        }
        //清除cookie
        CookieHelper.clearCookie();
    }

    /**
     * 校验当前用户
     *
     * @param model       含验证码信息
     * @param loginCookie 当前cookie
     */
    public void checkCurrentManager(LoginModel model, LoginCookie loginCookie) throws NoSuchAlgorithmException {
        //短信校验
        securityService.verPhoneCode("3", model.getPhoneCode(), loginCookie.getPhone());
        Account account = new Account();
        account.setId(loginCookie.getUserId());
        account.setManagePasswordHash(MD5.md5(model.getPassword()));
        account = accountMapper.getAccountByManagePwd(account);
        if (account == null) {
            throw new InvalidParameterAppException("管理员密码输入错误");
        }
    }
}
