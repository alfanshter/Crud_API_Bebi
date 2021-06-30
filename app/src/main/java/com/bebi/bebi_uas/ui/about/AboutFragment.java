package com.bebi.bebi_uas.ui.about;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bebi.bebi_uas.R;
import com.bebi.bebi_uas.adapter.BarangAdapter;
import com.bebi.bebi_uas.auth.LoginActivity;
import com.bebi.bebi_uas.auth.RegisterActivity;
import com.bebi.bebi_uas.database.ApiService;
import com.bebi.bebi_uas.database.UtilsApi;
import com.bebi.bebi_uas.databinding.FragmentAboutBinding;
import com.bebi.bebi_uas.model.BarangListResponse;
import com.bebi.bebi_uas.model.BarangResponse;
import com.bebi.bebi_uas.model.UsersModel;
import com.bebi.bebi_uas.model.UsersResponse;
import com.bebi.bebi_uas.session.Preferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutFragment extends Fragment {


    private FragmentAboutBinding binding;

    ApiService api;
    Context mContext;
    ProgressDialog loading;
    BarangAdapter recyclerAdapter;
    ArrayList<UsersModel> result;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
        binding.getLifecycleOwner();

        mContext = requireContext().getApplicationContext();

        api = UtilsApi.getAPIService();

        getdata();

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.setIsnama(mContext,"");
                Preferences.setIsLogin(mContext,false);
                Intent intent = new Intent(requireContext().getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    private void getdata() {
        api.getuser(Preferences.getid_user(mContext)).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()){
                    String username = response.body().getResult().get(0).getUsername();
                    String email = response.body().getResult().get(0).getEmail();
                    String nama_lengkap = response.body().getResult().get(0).getNama_lengkap();
                    String alamat = response.body().getResult().get(0).getAlamat();
                    String jenis_kelamin = response.body().getResult().get(0).getJenis_kelamin();

                    binding.txtUsername.setText(getString(R.string.username)+ ": " + username);
                    binding.txtEmail.setText(getString(R.string.email)+ ": " + email);
                    binding.textNamalengkap.setText(getString(R.string.fullname)+ ": " + nama_lengkap);
                    binding.txtAlamat.setText(getString(R.string.address)+ ": " + alamat);
                    binding.txtJeniskelamin.setText(getString(R.string.gender)+ ": " + jenis_kelamin);
                }

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }


}