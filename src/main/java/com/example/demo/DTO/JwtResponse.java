package com.example.demo.DTO;

public class JwtResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public JwtResponse(String token) {
        this.token = token;
    }
}
