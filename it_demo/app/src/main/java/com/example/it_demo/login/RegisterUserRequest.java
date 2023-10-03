package com.example.it_demo.login;

import com.google.gson.annotations.SerializedName;

public class RegisterUserRequest {
    @SerializedName("userName")
    private String userName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public RegisterUserRequest(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
