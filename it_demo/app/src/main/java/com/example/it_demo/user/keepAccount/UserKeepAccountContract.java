package com.example.it_demo.user.keepAccount;

public interface UserKeepAccountContract {
    interface UserKeepAccountActivity{
        void navigateToUserDateBrowseActivity();
    }
    interface UserKeepAccountPresenter{
        void insertAssets(AssetsRequest assetsRequest);
        void insertExpenses(ExpensesRequest expensesRequest);
        void navigateToUserDateBrowseActivity();
    }
    interface UserKeepAccountModel{
        void insertAssets(AssetsRequest assetsRequest);
        void insertExpenses(ExpensesRequest expensesRequest);
    }
}
