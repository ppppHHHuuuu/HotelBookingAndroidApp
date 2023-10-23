package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.databinding.ActivityMainBinding;
import com.example.mobdev_nhom7.fragment.main_activity.FavouritesFragment;
import com.example.mobdev_nhom7.fragment.main_activity.SettingsFragment;
import com.example.mobdev_nhom7.fragment.main_activity.StaysFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new StaysFragment());
        binding.bottomNav.setItemIconTintList(null);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.stays) {
                replaceFragment(new StaysFragment());
            } else if (id==R.id.favourites) {
                replaceFragment(new FavouritesFragment());
            } else if (id==R.id.settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }

    private void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}