package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.databinding.ActivityMainBinding;
import com.example.mobdev_nhom7.fragment.main_activity.CityFragment;
import com.example.mobdev_nhom7.fragment.main_activity.FavouritesFragment;
import com.example.mobdev_nhom7.fragment.main_activity.SettingsFragment;
import com.example.mobdev_nhom7.fragment.main_activity.StaysFragment;
import com.example.mobdev_nhom7.fragment.main_activity.TripsFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int fragmentId = 0;

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
                fragmentId = 4;
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

    public void switchToTripFragment() {
        this.binding.bottomNav.setSelectedItemId(R.id.trips);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
//        Log.d("a", "Asd ads");
//        switch (fragmentId) {
//            case 4:
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_ENTER:
//                        switch (SettingsFragment.editTextId) {
//                            case 0:
//                                EditText nameEditText = findViewById(R.id.dummy_name);
//                                nameEditText.setEnabled(false);
//
//                            case 1:
//                                EditText phoneEditText = findViewById(R.id.phone_edit_text);
//                                phoneEditText.setEnabled(false);
//
//                            case 2:
//                                EditText emailEditText = findViewById(R.id.email_edit_text);
//                                emailEditText.setEnabled(false);
//                                inputMethodManager.hideSoftInputFromWindow(emailEditText.getWindowToken(), 0);
//                        }
//                }
//                return super.onKeyUp(keyCode, event);
//        }
//        return true;
//    }
}