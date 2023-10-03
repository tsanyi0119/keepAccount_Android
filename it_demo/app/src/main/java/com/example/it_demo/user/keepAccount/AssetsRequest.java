package com.example.it_demo.user.keepAccount;

import com.google.gson.annotations.SerializedName;

public class AssetsRequest {
    @SerializedName("assetsName")
    private String assetsName;

    @SerializedName("assetsValue")
    private int assetsValue;

    @SerializedName("assetsType")
    private String assetsType;

    @SerializedName("recorded_at")
    private String recordedAt;

    // 提供一個帶參數的建構子
    public AssetsRequest(String assetsName, int assetsValue, String assetsType, String recordedAt) {
        this.assetsName = assetsName;
        this.assetsValue = assetsValue;
        this.assetsType = assetsType;
        this.recordedAt = recordedAt;
    }
}
