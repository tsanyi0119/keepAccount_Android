package com.example.it_demo;

import com.example.it_demo.login.LoginUserRequest;
import com.example.it_demo.login.LoginUserResponse;
import com.example.it_demo.login.RegisterUserRequest;
import com.example.it_demo.login.RegisterUserResponse;
import com.example.it_demo.user.dateBrowse.AssetsResponse;
import com.example.it_demo.user.dateBrowse.ExpensesResponse;
import com.example.it_demo.user.dateBrowse.StatusResponse;
import com.example.it_demo.user.keepAccount.AssetsRequest;
import com.example.it_demo.user.keepAccount.ExpensesRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("register")
    Observable<RegisterUserResponse> registerUser(@Body RegisterUserRequest registerUserRequest);

    @POST("auth")
    Observable<LoginUserResponse> loginUser(@Body LoginUserRequest loginUserRequest);

    @GET("user/expenses")
    Observable<ExpensesResponse> getExpensesData(@Query("date") String date);

    @GET("user/expenses/all")
    Observable<ExpensesResponse> getAllExpensesData();

    @GET("user/assets")
    Observable<AssetsResponse> getAssetsData(@Query("date") String date);

    @DELETE("user/expenses")
    Observable<StatusResponse> deleteExpensesData(@Query("id") Long id);

    @DELETE("user/assets")
    Observable<StatusResponse> deleteAssetsData(@Query("id") Long id);

    @POST("user/assets")
    Observable<StatusResponse> insertAssets(@Body AssetsRequest assetsRequest);

    @POST("user/expenses")
    Observable<StatusResponse> insertExpenses(@Body ExpensesRequest expensesRequest);



}
