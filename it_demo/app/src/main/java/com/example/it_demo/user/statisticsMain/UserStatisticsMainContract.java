package com.example.it_demo.user.statisticsMain;

import com.example.it_demo.user.dateBrowse.ExpensesData;

import java.util.List;

public class UserStatisticsMainContract {
    interface UserStatisticsMainActivity{
        void showExpenses(List<ExpensesData> expensesDataList);
    }
    interface UserStatisticsMainPresenter{
        void loadExpensesData();
        void showExpenses(List<ExpensesData> expensesDataList);
    }
    interface UserStatisticsMainModel{
        void loadExpensesData();
    }
}
