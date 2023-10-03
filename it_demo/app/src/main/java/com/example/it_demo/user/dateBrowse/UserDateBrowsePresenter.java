package com.example.it_demo.user.dateBrowse;

import android.util.Log;

import java.util.List;

public class UserDateBrowsePresenter implements UserDateBrowseContract.UserDateBrowsePresenter{
    UserDateBrowseContract.UserDateBrowseModel model;
    UserDateBrowseContract.UserDateBrowseActivity activity;
    public UserDateBrowsePresenter(UserDateBrowseContract.UserDateBrowseActivity activity) {
        model = new UserDateBrowseModel(this);
        this.activity = activity;
    }

    @Override
    public void loadExpensesData(String date) {
        model.loadExpensesData(date);
    }

    @Override
    public void loadAssetsData(String date) {
        model.loadAssetsData(date);
    }

    @Override
    public void deleteExpensesData(Long id) {
        model.deleteExpensesData(id);
    }

    @Override
    public void deleteAssetsData(Long id) {
        model.deleteAssetsData(id);
    }

    @Override
    public void showExpenses(List<ExpensesData> expensesDataList) {
        activity.showExpenses(expensesDataList);
    }

    @Override
    public void showAssets(List<AssetsData> assetsDataList) {
        activity.showAssets(assetsDataList);
    }


}
