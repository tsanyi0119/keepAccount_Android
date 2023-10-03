package com.example.it_demo.user.statisticsMain;

import com.example.it_demo.ApiService;
import com.example.it_demo.RetrofitManager;
import com.example.it_demo.user.dateBrowse.AssetsResponse;
import com.example.it_demo.user.dateBrowse.ExpensesResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserStatisticsMainModel implements UserStatisticsMainContract.UserStatisticsMainModel{
    private UserStatisticsMainContract.UserStatisticsMainPresenter presenter;
    ApiService apiService= RetrofitManager.getInstance().getAPI();

    public UserStatisticsMainModel(UserStatisticsMainContract.UserStatisticsMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadExpensesData() {
        apiService.getAllExpensesData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ExpensesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExpensesResponse expensesResponse) {
                        presenter.showExpenses(expensesResponse.getExpensesDataList());
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
