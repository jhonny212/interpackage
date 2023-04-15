package com.interpackage.users.auth.model;

import java.io.Serializable;

public class TokenCache implements Serializable {
    private String token;
    private long expirationTime = 3600;

    public TokenCache(String token, long expirationTime){
        this.expirationTime = expirationTime;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
}
