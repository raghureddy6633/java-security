package com.security.spring_security.dto;

public class AuthResponse {

    private String accessToken;
    private Long expiratioTime;

    public AuthResponse(String accessToken, Long expiratioTime) {
        this.accessToken = accessToken;
        this.expiratioTime = expiratioTime;
    }
    public AuthResponse(){

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiratioTime() {
        return expiratioTime;
    }

    public void setExpiratioTime(Long expiratioTime) {
        this.expiratioTime = expiratioTime;
    }
}
