package com.bebi.bebi_uas.database;

import com.bebi.bebi_uas.model.BarangListResponse;
import com.bebi.bebi_uas.model.BarangResponse;
import com.bebi.bebi_uas.model.LoginResponse;
import com.bebi.bebi_uas.model.RegisterResponse;
import com.bebi.bebi_uas.model.UsersModel;
import com.bebi.bebi_uas.model.UsersResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // Fungsi ini untuk memanggil API
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginRequest(@Field("username") String username,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> registerRequest(@Field("username") String username,
                                           @Field("password") String password,
                                           @Field("nama_lengkap") String nama_lengkap,
                                           @Field("email") String email,
                                           @Field("alamat") String alamat,
                                           @Field("jenis_kelamin") String jenis_kelamin,
                                           @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST("insertbarang.php")
    Call<RegisterResponse> insertbarang(
            @Field("nama_barang") String nama_barang,
            @Field("hargabeli_barang") String hargabeli_barang,
            @Field("jumlah") String jumlah,
            @Field("entry_by") String entry_by);

    @GET("readbarang.php")
    Call<BarangResponse> getbarang();

    @GET("readuser.php")
    Call<UsersResponse> getuser(
            @Query("id_user") Integer id_user
    );

}
