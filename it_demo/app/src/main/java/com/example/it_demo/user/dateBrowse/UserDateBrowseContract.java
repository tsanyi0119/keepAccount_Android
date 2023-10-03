package com.example.it_demo.user.dateBrowse;

import java.util.List;

public interface UserDateBrowseContract {
    interface UserDateBrowseActivity{
        void showExpenses(List<ExpensesData> expensesDataList);
        void showAssets(List<AssetsData> assetsDataList);
    }
    interface UserDateBrowsePresenter{
        void loadExpensesData(String date);
        void loadAssetsData(String date);
        void deleteExpensesData(Long id);
        void deleteAssetsData(Long id);
        void showExpenses(List<ExpensesData> expensesDataList);
        void showAssets(List<AssetsData> assetsDataList);
    }
    interface UserDateBrowseModel{
        void deleteExpensesData(Long id);
        void deleteAssetsData(Long id);
        void loadAssetsData(String date);
        void loadExpensesData(String date);
    }
}
