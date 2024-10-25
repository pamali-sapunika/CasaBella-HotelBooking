package com.casabella.backend.security;

public class AuthRequest {

    private String username;
    private String password;

    //Setters Getters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
