package com.bebi.bebi_uas.ui.laporan;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bebi.bebi_uas.R;
import com.bebi.bebi_uas.adapter.BarangAdapter;
import com.bebi.bebi_uas.database.ApiService;
import com.bebi.bebi_uas.database.UtilsApi;
import com.bebi.bebi_uas.databinding.FragmentLaporanBinding;
import com.bebi.bebi_uas.model.BarangListResponse;
import com.bebi.bebi_uas.model.BarangResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanFragment extends Fragment {

    private FragmentLaporanBinding binding;

    ApiService api;
    Context mContext;
    BarangAdapter recyclerAdapter;

    List<BarangResponse> list;
    private AlertDialog.Builder dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_laporan,container,false);
        binding.getLifecycleOwner();

        mContext = requireContext().getApplicationContext();

        api = UtilsApi.getAPIService();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.rvBarang.setLayoutManager(layoutManager);
        binding.rvBarang.setAdapter(recyclerAdapter);
        getdata();

        return binding.getRoot();
    }

    private void getdata() {
       api.getbarang().enqueue(new Callback<BarangResponse>() {
           @Override
           public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
               if (response.isSuccessful()){
                   BarangAdapter adapter = new BarangAdapter(mContext,response.body().getResult());
                   adapter.notifyDataSetChanged();
                   binding.rvBarang.setAdapter(adapter);
               }
           }

           @Override
           public void onFailure(Call<BarangResponse> call, Throwable t) {
                t.printStackTrace();
           }
       });
    }


}