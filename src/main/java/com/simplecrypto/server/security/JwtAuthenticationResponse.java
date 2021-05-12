package com.simplecrypto.server.security;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String username;
    private String email;

    public JwtAuthenticationResponse(String username,String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return email;
    }

    public void setAuthorities(String email) {
        this.email = email;
    }
}