package com.example.it_demo.user.dateBrowse;

import com.google.gson.annotations.SerializedName;

public class AssetsData {
    @SerializedName("id")
    private Long id;

    @SerializedName("assetsType")
    private String assetsType;

    @SerializedName("assetsName")
    private String assetsName;

    @SerializedName("assetsValue")
    private int assetsValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public int getAssetsValue() {
        return assetsValue;
    }

    public void setAssetsValue(int assetsValue) {
        this.assetsValue = assetsValue;
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }
}
