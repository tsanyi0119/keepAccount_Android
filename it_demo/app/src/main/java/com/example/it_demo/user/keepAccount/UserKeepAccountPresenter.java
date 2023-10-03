package com.example.it_demo.user.keepAccount;

public class UserKeepAccountPresenter implements UserKeepAccountContract.UserKeepAccountPresenter{
    UserKeepAccountContract.UserKeepAccountActivity activity;
    UserKeepAccountContract.UserKeepAccountModel model;
    public UserKeepAccountPresenter(UserKeepAccountContract.UserKeepAccountActivity activity) {
        this.activity = activity;
        model = new UserKeepAccountModel(this);
    }

    @Override
    public void insertAssets(AssetsRequest assetsRequest) {
        model.insertAssets(assetsRequest);
    }

    @Override
    public void insertExpenses(ExpensesRequest expensesRequest) {
        model.insertExpenses(expensesRequest);
    }

    @Override
    public void navigateToUserDateBrowseActivity() {
        activity.navigateToUserDateBrowseActivity();
    }
}
