package com.highSchool.utils;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public class JwtToken implements AuthenticationToken {
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
