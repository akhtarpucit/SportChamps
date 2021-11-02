package com.eiconix.sportchampsmemes.api;

import com.eiconix.sportchampsmemes.model.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemesApiResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
