package com.teksystems.model;


import javax.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank
    String username;
    @NotBlank
    String password;


    public LoginDTO() {
    }

    public LoginDTO(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

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
