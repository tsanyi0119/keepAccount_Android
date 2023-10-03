package com.example.it_demo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    // 以Singleton模式建立
    private static RetrofitManager mInstance = new RetrofitManager();

    private String token; // 用於儲存 JWT 令牌

    private ApiService apiService;
    private RetrofitManager() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            if (token != null) {
                requestBuilder.header("Authorization", "Bearer " + token);
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }).addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = httpClient.build();


        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.7:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public ApiService getAPI() {
        return apiService;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
