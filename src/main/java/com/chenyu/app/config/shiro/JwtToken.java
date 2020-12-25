package com.chenyu.app.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author chenyu
 * @date 2020-08-31
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
