package com.audiohouse.notarytracker.shared.models.web;

public class PostToken {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PostToken{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
