package com.example.it_demo.user.dateBrowse;

import com.google.gson.annotations.SerializedName;

public class StatusResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
