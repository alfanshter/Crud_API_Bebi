package com.bebi.bebi_uas.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bebi.bebi_uas.MainActivity;
import com.bebi.bebi_uas.R;
import com.bebi.bebi_uas.database.ApiService;
import com.bebi.bebi_uas.database.UtilsApi;
import com.bebi.bebi_uas.databinding.ActivityLoginBinding;
import com.bebi.bebi_uas.model.LoginResponse;
import com.bebi.bebi_uas.session.Preferences;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    ApiService api;
    Context mContext;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.getLifecycleOwner();
        mContext = this;

        api = UtilsApi.getAPIService();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.usernameInput.getText().toString().trim();
                String password = binding.pass.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    loading = ProgressDialog.show(mContext, null, getString(R.string.loading), true, false);
                    requestlogin(username, password);


                } else {
                    Snackbar.make(v, R.string.kolom_kosong, Snackbar.LENGTH_LONG).show();

                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }


    private void requestlogin(String username, String password) {
        Log.v("dinda", username);
        api.loginRequest(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().getSuccess() == 1) {
                        Integer user_id = Integer.parseInt(response.body().getId());
                        Preferences.setid_user(getBaseContext(),user_id);
                        Preferences.setIsnama(getBaseContext(),response.body().getUsername());
                        Preferences.setLoggedInUser(getBaseContext(), Preferences.getRegisteredUser(getBaseContext()));
                        Preferences.setLoggedInStatus(getBaseContext(), true);
                        Preferences.setIsLogin(getBaseContext(), true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(mContext, getString(R.string.passwor_salah), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, getString(R.string.networkerror), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getIsLogin(getBaseContext())){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}