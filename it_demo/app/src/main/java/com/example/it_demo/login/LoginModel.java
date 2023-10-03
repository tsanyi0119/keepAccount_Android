package com.example.it_demo.login;

import android.util.Log;

import com.example.it_demo.ApiService;
import com.example.it_demo.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.LoginModel{

    ApiService apiService= RetrofitManager.getInstance().getAPI();
    LoginContract.LoginPresenter loginPresenter;

    public LoginModel(LoginContract.LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void registerUser(String userName, String email, String password) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(userName, email, password);
        apiService.registerUser(registerUserRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<RegisterUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterUserResponse registerUserResponse) {
                        Log.e("LoginModel","註冊成功");
                        loginPresenter.showLoginPage();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LoginModel","註冊失敗" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loginUser(String email, String password) {
        LoginUserRequest loginUserRequest = new LoginUserRequest(email, password);
        apiService.loginUser(loginUserRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(new Observer<LoginUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginUserResponse loginUserResponse) {
                        Log.e("LoginActivity", "登入成功");
                        Log.e("LoginActivity", loginUserResponse.getStatus());
                        Log.e("LoginActivity", loginUserResponse.getToken());

                        loginPresenter.navigateToUserDateBrowseActivity(loginUserResponse.getToken());
                        RetrofitManager.getInstance().setToken(loginUserResponse.getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LoginActivity", "登入失敗");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

