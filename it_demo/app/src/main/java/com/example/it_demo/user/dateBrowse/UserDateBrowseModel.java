package com.example.it_demo.user.dateBrowse;

import android.util.Log;

import com.example.it_demo.ApiService;
import com.example.it_demo.RetrofitManager;
import com.example.it_demo.login.RegisterUserResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDateBrowseModel implements UserDateBrowseContract.UserDateBrowseModel{

    ApiService apiService= RetrofitManager.getInstance().getAPI();
    UserDateBrowseContract.UserDateBrowsePresenter presenter;

    public UserDateBrowseModel(UserDateBrowseContract.UserDateBrowsePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void deleteExpensesData(Long id) {
        apiService.deleteExpensesData(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new Observer<StatusResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(StatusResponse statusResponse) {
                                Log.e("deleteExpensesData", "刪除成功");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    @Override
    public void deleteAssetsData(Long id) {
        apiService.deleteAssetsData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<StatusResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusResponse statusResponse) {
                        Log.e("deleteAssetsData", "刪除成功");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadAssetsData(String date) {
        apiService.getAssetsData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<AssetsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AssetsResponse assetsResponse) {
                        presenter.showAssets(assetsResponse.getAssetsDataList());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadExpensesData(String date) {
        apiService.getExpensesData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ExpensesResponse>() {
                    @Override                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExpensesResponse expensesResponse) {
                        //取得支出相關資料
                        presenter.showExpenses(expensesResponse.getExpensesDataList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("loadExpensesData", e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
