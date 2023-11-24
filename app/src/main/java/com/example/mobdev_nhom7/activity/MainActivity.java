package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.databinding.ActivityMainBinding;
import com.example.mobdev_nhom7.fragment.main_activity.CityFragment;
import com.example.mobdev_nhom7.fragment.main_activity.FavouritesFragment;
import com.example.mobdev_nhom7.fragment.main_activity.SettingsFragment;
import com.example.mobdev_nhom7.fragment.main_activity.StaysFragment;
import com.example.mobdev_nhom7.fragment.main_activity.TripsFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new StaysFragment());
        binding.bottomNav.setItemIconTintList(null);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id== R.id.stays) {
                replaceFragment(new StaysFragment());
            } else if (id==R.id.cities) {
                replaceFragment(new CityFragment());
            } else if (id==R.id.favourites) {
                replaceFragment(new FavouritesFragment());
            } else if (id==R.id.trips) {
                replaceFragment(new TripsFragment());
            } else if (id==R.id.settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (mAuth.getCurrentUser() != null) {
            String uid = mAuth.getCurrentUser().getUid();
            Log.d("uid", uid);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user_id", uid);
            editor.apply();
            Map<String,?> keys = preferences.getAll();

            for(Map.Entry<String,?> entry : keys.entrySet()){
                Log.d("map values",entry.getKey() + ": " +
                                   entry.getValue().toString());
            }
        }
        else {
            Log.d("uid", "empty");
        }
    }

    private void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}