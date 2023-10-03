package com.example.it_demo.login;

import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("token")
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
