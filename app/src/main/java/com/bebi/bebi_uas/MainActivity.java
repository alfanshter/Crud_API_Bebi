package com.bebi.bebi_uas;

import android.os.Bundle;
import android.view.MenuItem;

import com.bebi.bebi_uas.ui.laporan.LaporanFragment;
import com.bebi.bebi_uas.ui.insert.InsertFragment;
import com.bebi.bebi_uas.ui.about.AboutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bebi.bebi_uas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  implements  BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // kita set default nya Home Fragment
        loadFragment(new LaporanFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_customer);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_customer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_laporan:
                fragment = new LaporanFragment();
                break;
            case R.id.navigation_home:
                fragment = new InsertFragment();
                break;
            case R.id.navigation_profil:
                fragment = new AboutFragment();
                break;
        }
        return loadFragment(fragment);
    }

}