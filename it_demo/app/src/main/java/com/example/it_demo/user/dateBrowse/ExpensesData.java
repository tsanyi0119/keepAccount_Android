package com.example.it_demo.user.dateBrowse;

import com.google.gson.annotations.SerializedName;

public class ExpensesData {
    @SerializedName("id")
    private Long id;

    @SerializedName("expensesType")
    private String expensesType;

    @SerializedName("expensesName")
    private String expensesName;

    @SerializedName("expensesValue")
    private int expensesValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpensesName() {
        return expensesName;
    }

    public void setExpensesName(String expensesName) {
        this.expensesName = expensesName;
    }

    public int getExpensesValue() {
        return expensesValue;
    }

    public void setExpensesValue(int expensesValue) {
        this.expensesValue = expensesValue;
    }

    public String getExpensesType() {
        return expensesType;
    }

    public void setExpensesType(String expensesType) {
        this.expensesType = expensesType;
    }
}
