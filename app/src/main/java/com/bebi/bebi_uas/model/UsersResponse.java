package com.bebi.bebi_uas.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsersResponse {

    @SerializedName("result")
    ArrayList<UsersModel> result = null;

    public ArrayList<UsersModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<UsersModel> result) {
        this.result = result;
    }

}
