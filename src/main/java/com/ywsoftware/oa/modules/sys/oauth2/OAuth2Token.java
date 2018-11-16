package com.ywsoftware.oa.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author xiewl
 * @version 1.0
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
