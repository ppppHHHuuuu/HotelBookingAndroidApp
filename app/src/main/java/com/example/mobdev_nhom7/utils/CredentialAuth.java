package com.example.mobdev_nhom7.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CredentialAuth {
    public static boolean checkCurrentUser(Context context, String newProvider) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.user_info), Context.MODE_PRIVATE);
        String currentUid = sharedPreferences.getString("uid", "-1");
        String currentProvider = sharedPreferences.getString("provider", "-1");
        if (!currentUid.equals("-1") && !newProvider.equals(currentProvider)) {
            mAuth.signInWithCustomToken(currentUid);
            return true;
        }
        return false;
    }
    public static void linkAuthProviderWithCurrentAccount(Activity activity, AuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        Log.d("Login Successfully", "linkWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();
                    } else {
                        Log.w("Login failed", "linkWithCredential:failure", task.getException());
                        Toast.makeText(activity, "Đã có lỗi không xác định",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
