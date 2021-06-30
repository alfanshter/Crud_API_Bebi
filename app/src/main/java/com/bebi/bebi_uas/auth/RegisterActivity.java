package com.bebi.bebi_uas.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bebi.bebi_uas.R;
import com.bebi.bebi_uas.database.ApiService;
import com.bebi.bebi_uas.database.UtilsApi;
import com.bebi.bebi_uas.databinding.ActivityRegisterBinding;
import com.bebi.bebi_uas.model.RegisterResponse;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    ApiService api;
    ProgressDialog loading;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.getLifecycleOwner();
        mContext = this;

        api = UtilsApi.getAPIService();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, getString(R.string.loading), true, false);

                String username = binding.edtUsername.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();
                String confirm_password = binding.edtCfrpassword.getText().toString().trim();
                String namalengkap = binding.edtFullname.getText().toString().trim();
                String email = binding.edtEmail.getText().toString().trim();
                String alamat = binding.edtAlamat.getText().toString().trim();
                String jeniskelamin = binding.edtGender.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty() && !confirm_password.isEmpty()
                        && !namalengkap.isEmpty() && !email.isEmpty() && !alamat.isEmpty() && !jeniskelamin.isEmpty()) {
                    if (confirm_password.equals(password)) {
                        loading.dismiss();
                        requestregister(username, password,confirm_password,namalengkap,email,alamat,jeniskelamin);

                    } else {
                        loading.dismiss();
                        Snackbar.make(v, R.string.confirmpassword, Snackbar.LENGTH_LONG).show();

                    }


                } else {
                    loading.dismiss();
                    Snackbar.make(v, R.string.kolom_kosong, Snackbar.LENGTH_LONG).show();

                }
            }

        });
    }

    private void requestregister(String username, String password, String confirm_password, String namalengkap, String email, String alamat, String jeniskelamin) {
            api.registerRequest(username,password,namalengkap,email,alamat,jeniskelamin,confirm_password).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body().getSuccess()==1){
                            loading.dismiss();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            loading.dismiss();
                            Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(mContext, response.message(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                }
            });
    }

}