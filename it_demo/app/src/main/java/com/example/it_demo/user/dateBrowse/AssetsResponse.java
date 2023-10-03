package com.example.it_demo.user.dateBrowse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssetsResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("assetsDataList")
    private List<AssetsData> assetsDataList;

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

    public List<AssetsData> getAssetsDataList() {
        return assetsDataList;
    }

    public void setAssetsDataList(List<AssetsData> assetsDataList) {
        this.assetsDataList = assetsDataList;
    }
}
