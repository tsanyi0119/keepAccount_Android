package com.example.it_demo.user.statisticsMain;

import com.example.it_demo.user.dateBrowse.ExpensesData;

import java.util.List;

public class UserStatisticsMainPresenter implements UserStatisticsMainContract.UserStatisticsMainPresenter{
    UserStatisticsMainContract.UserStatisticsMainActivity activity;
    UserStatisticsMainContract.UserStatisticsMainModel model;
    public UserStatisticsMainPresenter(UserStatisticsMainContract.UserStatisticsMainActivity activity) {
        this.activity = activity;
        model = new UserStatisticsMainModel(this);
    }

    @Override
    public void loadExpensesData() {
        model.loadExpensesData();
    }

    @Override
    public void showExpenses(List<ExpensesData> expensesDataList) {
        activity.showExpenses(expensesDataList);
    }
}
