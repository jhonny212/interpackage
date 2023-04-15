package com.interpackage.users.auth.model;

public class AuthToken {
    private String authc;

    public AuthToken() {
    }

    public AuthToken(String authc) {
        this.authc = authc;
    }

    public String getAuthc() {
        return authc;
    }

    public void setAuthc(String authc) {
        this.authc = authc;
    }
}
