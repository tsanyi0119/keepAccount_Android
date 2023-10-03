package com.example.it_demo.login;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.it_demo.LoginSharedPreferences;
import com.example.it_demo.R;
import com.example.it_demo.user.dateBrowse.UserDateBrowseActivity;
import com.example.it_demo.user.statisticsMain.UserStatisticsMainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginActivity {

    LoginContract.LoginPresenter presenter;
    private LoginSharedPreferences loginSharedPreferences;

    View login_layout,register_layout;
    Button btn_login_register , btn_login ,btn_register;
    EditText edit_register_userName,edit_register_email,edit_register_password,edit_login_email,edit_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        setupUI();

    }

    private void init(){

        loginSharedPreferences = new LoginSharedPreferences(this);

        presenter = new LoginPresenter(this);
        edit_register_userName = findViewById(R.id.edit_register_userName);
        edit_register_email = findViewById(R.id.edit_register_email);
        edit_register_password = findViewById(R.id.edit_register_password);
        edit_login_email = findViewById(R.id.edit_login_email);
        edit_login_password = findViewById(R.id.edit_login_password);
        login_layout = findViewById(R.id.login_layout);
        register_layout = findViewById(R.id.register_layout);
        btn_register = findViewById(R.id.btn_register);
        btn_login_register = findViewById(R.id.btn_login_register);
        btn_login = findViewById(R.id.btn_login);

    }
    private void setupUI() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登入
                presenter.loginUser(
                        edit_login_email.getText().toString(),
                        edit_login_password.getText().toString()
                );
            }
        });
        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //立刻註冊
                startFloatUpAnimation(login_layout,register_layout);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //開始註冊
                presenter.registerUser(
                        edit_register_userName.getText().toString(),
                        edit_register_email.getText().toString(),
                        edit_register_password.getText().toString()
                );
            }
        });
        register_layout.setVisibility(View.GONE);
        startFloatUpAnimation(register_layout,login_layout);
    }
    private void startFloatUpAnimation(View disappearView,View displayView){
        disappearView.setVisibility(View.GONE);
        displayView.setVisibility(View.VISIBLE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(displayView, "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(displayView, "scaleY", 0.2f, 1.0f);

        scaleX.setDuration(1000);
        scaleY.setDuration(1000);

        scaleX.start();
        scaleY.start();
    }

    @Override
    public void showLoginPage() {
        startFloatUpAnimation(register_layout,login_layout);
    }

    @Override
    public void navigateToUserDateBrowseActivity(String token) {
        loginSharedPreferences.setToken(token);
        Intent intent = new Intent(LoginActivity.this, UserStatisticsMainActivity.class);
        startActivity(intent);
        finish();
    }
}