package com.example.it_demo.user.keepAccount;

import com.example.it_demo.ApiService;
import com.example.it_demo.RetrofitManager;
import com.example.it_demo.user.dateBrowse.AssetsResponse;
import com.example.it_demo.user.dateBrowse.StatusResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserKeepAccountModel implements UserKeepAccountContract.UserKeepAccountModel{
    ApiService apiService= RetrofitManager.getInstance().getAPI();
    UserKeepAccountContract.UserKeepAccountPresenter presenter;

    public UserKeepAccountModel(UserKeepAccountContract.UserKeepAccountPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void insertAssets(AssetsRequest assetsRequest) {
        apiService.insertAssets(assetsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<StatusResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusResponse statusResponse) {
                        presenter.navigateToUserDateBrowseActivity();
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
    public void insertExpenses(ExpensesRequest expensesRequest) {
        apiService.insertExpenses(expensesRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<StatusResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusResponse statusResponse) {
                        presenter.navigateToUserDateBrowseActivity();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
