package com.example.it_demo.user.dateBrowse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpensesResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("expensesDataList")
    private List<ExpensesData> expensesDataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ExpensesData> getExpensesDataList() {
        return expensesDataList;
    }

    public void setExpensesDataList(List<ExpensesData> expensesDataList) {
        this.expensesDataList = expensesDataList;
    }
}
