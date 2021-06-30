package com.bebi.bebi_uas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BarangListResponse {

    @SerializedName("id_barang")
    @Expose
    private String idBarang;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("hargabeli_barang")
    @Expose
    private String hargabeliBarang;
    @SerializedName("hargajual_barang")
    @Expose
    private String hargajualBarang;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("entry_by")
    @Expose
    private String entryBy;

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getHargabeliBarang() {
        return hargabeliBarang;
    }

    public void setHargabeliBarang(String hargabeliBarang) {
        this.hargabeliBarang = hargabeliBarang;
    }

    public String getHargajualBarang() {
        return hargajualBarang;
    }

    public void setHargajualBarang(String hargajualBarang) {
        this.hargajualBarang = hargajualBarang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }
}
