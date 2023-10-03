package com.example.it_demo.login;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginModel loginModel;
    private LoginContract.LoginActivity loginActivity;

    public LoginPresenter(LoginContract.LoginActivity loginActivity) {
        loginModel = new LoginModel(this);
        this.loginActivity = loginActivity;
    }

    @Override
    public void registerUser(String userName, String email, String password) {
        loginModel.registerUser(userName,email,password);
    }

    @Override
    public void loginUser(String email, String password) {
        loginModel.loginUser(email,password);
    }

    @Override
    public void showLoginPage() {
        loginActivity.showLoginPage();
    }

    @Override
    public void navigateToUserDateBrowseActivity(String token) {
        loginActivity.navigateToUserDateBrowseActivity(token);
    }
}
