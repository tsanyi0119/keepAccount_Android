package com.example.it_demo;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginSharedPreferences {
    private SharedPreferences sharedPreferences;
    public LoginSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("LoginToken",Context.MODE_PRIVATE);
    }
    public void setToken(String token){
        sharedPreferences.edit().putString("token",token).apply();
    }
    public String getToken(){
        return sharedPreferences.getString("token","");
    }

    public void setEmail(String email){
        sharedPreferences.edit().putString("email",email).apply();
    }
    public String getEmail(){
        return sharedPreferences.getString("email","");
    }
}
