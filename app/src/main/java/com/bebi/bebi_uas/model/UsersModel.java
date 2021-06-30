package com.bebi.bebi_uas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersModel {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nama_lengkap")
    @Expose
    private String nama_lengkap;

    @SerializedName("email")
    @Expose
    private  String email;

    @SerializedName("alamat")
    @Expose
    private  String alamat;

    @SerializedName("jenis_kelamin")
    @Expose
    private  String jenis_kelamin;

    @SerializedName("confirm_password")
    @Expose
    private  String confirm_password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
