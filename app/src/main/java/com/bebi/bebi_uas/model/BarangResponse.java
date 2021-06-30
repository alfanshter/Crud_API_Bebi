package com.bebi.bebi_uas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BarangResponse {

    @SerializedName("result")
    ArrayList<BarangListResponse> result = null;

    public ArrayList<BarangListResponse> getResult() {
        return result;
    }

    public void setResult(ArrayList<BarangListResponse> result) {
        this.result = result;
    }

}
