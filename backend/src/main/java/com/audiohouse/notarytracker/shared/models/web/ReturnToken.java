package com.audiohouse.notarytracker.shared.models.web;

public class ReturnToken {

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "ReturnToken{" +
                "authToken='" + authToken + '\'' +
                '}';
    }
}
