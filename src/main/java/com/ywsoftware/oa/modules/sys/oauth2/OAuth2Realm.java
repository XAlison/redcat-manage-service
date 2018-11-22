package com.ywsoftware.oa.modules.sys.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywsoftware.oa.common.exception.ApplicationException;
import com.ywsoftware.oa.common.utils.AesHelper;
import com.ywsoftware.oa.common.utils.CookieHelper;
import com.ywsoftware.oa.modules.sys.domain.entity.SysUserTokenEntity;
import com.ywsoftware.oa.modules.sys.domain.login.LoginCookie;
import com.ywsoftware.oa.modules.sys.service.ShiroService;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 认证
 *
 * @author xiewl
 * @version 1.0
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Resource
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LoginCookie user = (LoginCookie)principals.getPrimaryPrincipal();
        String userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        // 根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        // token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        String loginCookie = CookieHelper.getCookie("loginCookie");
        if (loginCookie == null) {
            throw new ApplicationException("用户cookie失效");
        }
        LoginCookie user= null;
        try {
            user = new ObjectMapper().readValue(AesHelper.decrypt(loginCookie), LoginCookie.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }
}
