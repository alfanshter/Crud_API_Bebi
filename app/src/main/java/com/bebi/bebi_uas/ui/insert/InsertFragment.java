package com.bebi.bebi_uas.ui.insert;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bebi.bebi_uas.R;
import com.bebi.bebi_uas.adapter.BarangAdapter;
import com.bebi.bebi_uas.auth.LoginActivity;
import com.bebi.bebi_uas.auth.RegisterActivity;
import com.bebi.bebi_uas.database.ApiService;
import com.bebi.bebi_uas.database.UtilsApi;
import com.bebi.bebi_uas.databinding.FragmentInsertBinding;
import com.bebi.bebi_uas.model.RegisterResponse;
import com.bebi.bebi_uas.session.Preferences;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFragment extends Fragment {

    private FragmentInsertBinding binding;

    ApiService api;
    Context mContext;
    ProgressDialog loading;
    BarangAdapter recyclerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert, container, false);
        binding.getLifecycleOwner();

        mContext = requireContext().getApplicationContext();

        api = UtilsApi.getAPIService();

        binding.btnInsertitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(getActivity(), null, getString(R.string.loading), true, false);

                String namabarang = binding.edtNamabarang.getText().toString().trim();
                String hargabeli = binding.edtHargabeli.getText().toString().trim();
                String jumlah = binding.edtJumlah.getText().toString().trim();

                if (!namabarang.isEmpty() && !hargabeli.isEmpty() && !jumlah.isEmpty()){
                    insertbarang(namabarang,hargabeli,jumlah);

                }else {
                    Toast.makeText(mContext, getString(R.string.kolom_kosong), Toast.LENGTH_SHORT).show();
                    loading.dismiss();

                }
            }
        });
        return binding.getRoot();

    }

    private void insertbarang(String namabarang, String hargabeli, String jumlah) {

        api.insertbarang(namabarang,hargabeli,jumlah, Preferences.getIsnama(requireActivity().getBaseContext())).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess() == 1){
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }else {
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }else {
                    Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }

}