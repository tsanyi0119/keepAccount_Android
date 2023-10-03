package com.example.it_demo.login;

public interface LoginContract {
    interface LoginActivity{
        void showLoginPage();
        void navigateToUserDateBrowseActivity(String token);
    }
    interface LoginPresenter{
        void registerUser(String userName,String email,String password);
        void loginUser(String email,String password);
        void showLoginPage();
        void navigateToUserDateBrowseActivity(String token);
    }
    interface LoginModel{
        void registerUser(String userName,String email,String password);
        void loginUser(String email,String password);
    }
}
