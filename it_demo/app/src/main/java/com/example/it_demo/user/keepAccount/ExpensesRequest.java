package com.example.it_demo.user.keepAccount;

import com.google.gson.annotations.SerializedName;

public class ExpensesRequest {
    @SerializedName("expensesName")
    private String expensesName;

    @SerializedName("expensesValue")
    private int expensesValue;

    @SerializedName("expensesType")
    private String expensesType;

    @SerializedName("recorded_at")
    private String recordedAt;

    public ExpensesRequest(String expensesName, int expensesValue, String expensesType, String recordedAt) {
        this.expensesName = expensesName;
        this.expensesValue = expensesValue;
        this.expensesType = expensesType;
        this.recordedAt = recordedAt;
    }
}
