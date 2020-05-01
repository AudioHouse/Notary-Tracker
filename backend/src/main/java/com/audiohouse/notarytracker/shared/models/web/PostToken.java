package com.audiohouse.notarytracker.shared.models.web;

public class PostToken {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PostToken{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
