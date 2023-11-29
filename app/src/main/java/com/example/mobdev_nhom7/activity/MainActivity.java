package com.example.mobdev_nhom7.activity;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NAVIGATE_TO_TRIPS = "navigate_to_trips";
    ActivityMainBinding binding;
    int fragmentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserInfo();

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new StaysFragment());
        binding.bottomNav.setItemIconTintList(null);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id== R.id.stays && id != fragmentId) {
                fragmentId = id;
                replaceFragment(new StaysFragment());
            } else if (id==R.id.cities && id != fragmentId) {
                fragmentId = id;
                replaceFragment(new CityFragment());
            } else if (id==R.id.favourites && id != fragmentId) {
                fragmentId = id;
                replaceFragment(new FavouritesFragment());
            } else if (id==R.id.trips && id != fragmentId) {
                fragmentId = id;
                replaceFragment(new TripsFragment());
            } else if (id==R.id.settings && id != fragmentId) {
                fragmentId = id;
                replaceFragment(new SettingsFragment());
            }
            return true;
        });

        if (getIntent().getBooleanExtra(EXTRA_NAVIGATE_TO_TRIPS, false)) {
            // Navigate to TripsFragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new TripsFragment())
                    .commit();
        }
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

    private void getUserInfo() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.user_info), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.getEmail() != null) {
            editor.putString("email", user.getEmail());
        } else {
            user.updateEmail("examplemail@example.com")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d("Firebase Email Current User", "User email address updated.");
                        }
                    });
            editor.putString("email", "examplemail@example.com");
        }
        if (user.getPhoneNumber() != null) {
            editor.putString("phone", user.getPhoneNumber());
        } else {
            user.updateEmail("(+84) 012345678")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d("Firebase Phone Current User", "User phone address updated.");
                        }
                    });
            editor.putString("phone", "(+84) 012345678");
        }
        if (user.getDisplayName() != null) {
            editor.putString("name", user.getDisplayName());
        } else {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName("Dummy Name").build();
            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d("Firebase Current User", "User display name updated.");
                        }
                    });
            editor.putString("name", "Dummy Name");
        }
        editor.putString("provider", user.getProviderId());
        editor.apply();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (mAuth.getCurrentUser() != null) {
            String uid = mAuth.getCurrentUser().getUid();
            Log.d("uid", uid);
            SharedPreferences.Editor editor2 = preferences.edit();
            editor2.putString("user_id", uid);
            editor2.apply();
        }
        else {
            Log.d("uid", "empty");
        }
    }


}