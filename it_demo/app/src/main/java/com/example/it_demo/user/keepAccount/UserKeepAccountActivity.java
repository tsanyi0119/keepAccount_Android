package com.example.it_demo.user.keepAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.it_demo.R;
import com.example.it_demo.user.dateBrowse.UserDateBrowseActivity;
import com.example.it_demo.user.dateBrowse.UserDateBrowseContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserKeepAccountActivity extends AppCompatActivity implements UserKeepAccountContract.UserKeepAccountActivity{

    Button btn_keep_expenses,btn_keep_assets,btn_summit;
    LinearLayout linearLayout_keep_assets , linearLayout_keep_expenses;
    ImageView img_food,img_traffic,img_medical,img_pet,img_3c,img_life,img_deposit,img_wallet,img_storedvalue;
    TextView tv_keep_type;
    String type = "food";
    String selectedDateStr="";
    String date = "";
    String status = "expenses";
    EditText edit_name , edit_price;
    UserKeepAccountContract.UserKeepAccountPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_keep_account);
        init();
    }
    private void init(){
        presenter = new UserKeepAccountPresenter(this);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        btn_keep_expenses = findViewById(R.id.btn_keep_expenses);
        btn_keep_assets = findViewById(R.id.btn_keep_assets);
        btn_summit = findViewById(R.id.btn_summit);
        edit_name = findViewById(R.id.edit_name);
        edit_price = findViewById(R.id.edit_price);
        linearLayout_keep_assets = findViewById(R.id.linearLayout_keep_assets);
        linearLayout_keep_expenses = findViewById(R.id.linearLayout_keep_expenses);
        img_food = findViewById(R.id.img_food);
        img_traffic = findViewById(R.id.img_traffic);
        img_medical = findViewById(R.id.img_medical);
        img_pet = findViewById(R.id.img_pet);
        img_3c = findViewById(R.id.img_3c);
        img_life = findViewById(R.id.img_life);
        img_deposit = findViewById(R.id.img_deposit);
        img_wallet = findViewById(R.id.img_wallet);
        img_storedvalue = findViewById(R.id.img_storedvalue);
        tv_keep_type = findViewById(R.id.tv_keep_type);
        setupUI();
    }
    private void setupUI(){
        imgOnClick();
        btn_keep_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "expenses";
                setExpensesView();
            }
        });
        btn_keep_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "assets";
                setAssetsView();
            }
        });
        btn_summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.equals("assets")){
                    AssetsRequest assetsRequest = new AssetsRequest(
                            edit_name.getText().toString(),Integer.parseInt(edit_price.getText().toString()),type,date
                    );
                    presenter.insertAssets(assetsRequest);
                }
                if(status.equals("expenses")){
                    ExpensesRequest expensesRequest = new ExpensesRequest(
                        edit_name.getText().toString(),Integer.parseInt(edit_price.getText().toString()),type,date
                    );
                    presenter.insertExpenses(expensesRequest);
                }
            }
        });
        btn_keep_expenses.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAD689")));
        btn_keep_assets.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFB")));

    }

    private void setAssetsView() {
        linearLayout_keep_assets.setVisibility(View.VISIBLE);
        linearLayout_keep_expenses.setVisibility(View.GONE);
        btn_keep_assets.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAD689")));
        btn_keep_expenses.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFB")));
        tv_keep_type.setText("存款");
        type = "deposit";
        img_deposit.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_light));
        img_wallet.setImageDrawable(getResources().getDrawable(R.drawable.ic_wallet_preset));
        img_storedvalue.setImageDrawable(getResources().getDrawable(R.drawable.ic_storedvalue_preseet));

    }

    private void setExpensesView() {
        linearLayout_keep_assets.setVisibility(View.GONE);
        linearLayout_keep_expenses.setVisibility(View.VISIBLE);
        btn_keep_assets.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFB")));
        btn_keep_expenses.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAD689")));
        tv_keep_type.setText("飲食");
        type = "food";
        img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_light));
        img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
        img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
        img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
        img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
        img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));
    }

    private void imgOnClick() {
        img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_light));
        img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
        img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
        img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
        img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
        img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));

        img_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("飲食");
                type = "food";
                img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_light));
                img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
                img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
                img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
                img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
                img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));
            }
        });

        img_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("交通");
                type = "traffic";
                img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_preseet));
                img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_light));
                img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
                img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
                img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
                img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));
            }
        });

        img_medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("醫療");
                type = "medical";
                img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_preseet));
                img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
                img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_light));
                img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
                img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
                img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));
            }
        });

        img_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("寵物");
                type = "pie";
                img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_preseet));
                img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
                img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
                img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
                img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
                img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));
            }
        });

        img_3c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("3C");
                type = "electronic";
                img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_preseet));
                img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
                img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
                img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
                img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_light));
                img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_preset));
            }
        });

        img_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("生活");
                type = "life";
                img_food.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_preseet));
                img_traffic.setImageDrawable(getResources().getDrawable(R.drawable.ic_traffic_preset));
                img_medical.setImageDrawable(getResources().getDrawable(R.drawable.ic_medical_preset));
                img_pet.setImageDrawable(getResources().getDrawable(R.drawable.ic_pet_preset));
                img_3c.setImageDrawable(getResources().getDrawable(R.drawable.ic_digit_preseet));
                img_life.setImageDrawable(getResources().getDrawable(R.drawable.ic_life_light));
            }
        });

        img_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("存款");
                type = "deposit";
                img_deposit.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_light));
                img_wallet.setImageDrawable(getResources().getDrawable(R.drawable.ic_wallet_preset));
                img_storedvalue.setImageDrawable(getResources().getDrawable(R.drawable.ic_storedvalue_preseet));
            }
        });

        img_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("錢包");
                type = "wallet";
                img_deposit.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_preset));
                img_wallet.setImageDrawable(getResources().getDrawable(R.drawable.ic_wallet_light));
                img_storedvalue.setImageDrawable(getResources().getDrawable(R.drawable.ic_storedvalue_preseet));
            }
        });

        img_storedvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_keep_type.setText("儲值");
                type = "storedvalue";
                img_deposit.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_preset));
                img_wallet.setImageDrawable(getResources().getDrawable(R.drawable.ic_wallet_preset));
                img_storedvalue.setImageDrawable(getResources().getDrawable(R.drawable.ic_storedvalue_light));
            }
        });

    }

    @Override
    public void navigateToUserDateBrowseActivity() {
        Intent intent = new Intent(UserKeepAccountActivity.this, UserDateBrowseActivity.class);
        startActivity(intent);
        finish();
    }
}