package com.example.mobdev_nhom7.fragment.main_activity;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.Login;
import com.example.mobdev_nhom7.activity.MainActivity;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityName;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityNameResponseData;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.TimeUnit;



import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mVerificationId;
    String oldName = "";
    String oldPhone = "";
    String oldEmail = "";
    EditText nameText;
    EditText phoneText;
    EditText emailText;
    private boolean resendEnabled = false;
    private int resendTime = 60;
    TextView resendTimeCountText;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(getString(R.string.user_info), MODE_PRIVATE);

        TextView profileName = view.findViewById(R.id.profile_name);
        profileName.setText(sharedPreferences.getString("name", ""));

        TextView profileEmail = view.findViewById(R.id.profile_email);
        profileEmail.setText(sharedPreferences.getString("email", ""));

        nameText = view.findViewById(R.id.dummy_name);
        nameText.setText(sharedPreferences.getString("name", ""));
        ImageView nameEditImage = view.findViewById(R.id.name_edit_image);

        phoneText = view.findViewById(R.id.phone_edit_text);
        phoneText.setText(sharedPreferences.getString("phone", ""));
        ImageView phoneEditImage = view.findViewById(R.id.phone_edit_image);

        emailText = view.findViewById(R.id.email_edit_text);
        emailText.setText(sharedPreferences.getString("email", ""));
        ImageView emailEditImage = view.findViewById(R.id.email_edit_image);

        oldName = nameText.getText().toString();
        oldPhone = phoneText.getText().toString();
        oldEmail = emailText.getText().toString();

        LinearLayout saveButton = view.findViewById(R.id.save_button);

        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);

        RelativeLayout nameBlock = view.findViewById(R.id.name_block);
        nameBlock.setOnClickListener(v -> {
            nameEditImage.setImageResource(R.drawable.edit_off_icon);
            phoneEditImage.setImageResource(R.drawable.edit_icon);
            emailEditImage.setImageResource(R.drawable.edit_icon);
            nameText.setEnabled(true);
            nameText.requestFocus();
            nameText.setSelection(nameText.getText().length());
            inputMethodManager.showSoftInput(nameText, InputMethodManager.SHOW_FORCED);

        });

        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!nameText.getText().toString().equals(oldName)) {
                    saveButton.setEnabled(true);
                    savable();
                } else if (phoneText.getText().toString().equals(oldPhone)
                        && emailText.getText().toString().equals(oldEmail)) {
                    saveButton.setEnabled(false);
                    notSavable();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        nameText.setOnEditorActionListener((v1, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputMethodManager.hideSoftInputFromWindow(nameText.getWindowToken(), 0);
                nameText.clearFocus();
                nameEditImage.setImageResource(R.drawable.edit_icon);
                return true;
            }
            return false;
        });

        RelativeLayout phoneBlock = view.findViewById(R.id.phone_block);
        phoneBlock.setOnClickListener(v -> {
            phoneEditImage.setImageResource(R.drawable.edit_off_icon);
            nameEditImage.setImageResource(R.drawable.edit_icon);
            emailEditImage.setImageResource(R.drawable.edit_icon);
            phoneText.setEnabled(true);
            phoneText.requestFocus();
            phoneText.setSelection(phoneText.getText().length());
            inputMethodManager.showSoftInput(phoneText, InputMethodManager.SHOW_FORCED);
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!phoneText.getText().toString().equals(oldPhone)) {
                    saveButton.setEnabled(true);
                    savable();
                } else if (nameText.getText().toString().equals(oldName)
                        && emailText.getText().toString().equals(oldEmail)) {
                    saveButton.setEnabled(false);
                    notSavable();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        phoneText.setOnEditorActionListener((v1, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputMethodManager.hideSoftInputFromWindow(phoneText.getWindowToken(), 0);
                phoneText.clearFocus();
                phoneEditImage.setImageResource(R.drawable.edit_icon);
                return true;
            }
            return false;
        });

        RelativeLayout emailBlock = view.findViewById(R.id.email_block);
        emailBlock.setOnClickListener(v -> {
            emailEditImage.setImageResource(R.drawable.edit_off_icon);
            phoneEditImage.setImageResource(R.drawable.edit_icon);
            nameEditImage.setImageResource(R.drawable.edit_icon);
            emailText.setEnabled(true);
            emailText.requestFocus();
            emailText.setSelection(emailText.getText().length());
            inputMethodManager.showSoftInput(emailText, InputMethodManager.SHOW_FORCED);
        });
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!emailText.getText().toString().equals(oldEmail)) {
                    saveButton.setEnabled(true);
                    savable();
                } else if (nameText.getText().toString().equals(oldName)
                        && phoneText.getText().toString().equals(oldPhone)) {
                    saveButton.setEnabled(false);
                    notSavable();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        emailText.setOnEditorActionListener((v1, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputMethodManager.hideSoftInputFromWindow(emailText.getWindowToken(), 0);
                emailText.clearFocus();
                emailEditImage.setImageResource(R.drawable.edit_icon);
                return true;
            }
            return false;
        });

        saveButton.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if (!Objects.equals(oldEmail, emailText.getText().toString())) {
                oldEmail = emailText.getText().toString();
                user.updateEmail(oldEmail)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d("Firebase Email Current User", "User email address updated.");
                            }
                        });
            }
            if (!Objects.equals(oldName, nameText.getText().toString())) {
                oldName = nameText.getText().toString();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(oldName).build();
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d("Firebase Current User", "User display name updated.");
                            }
                        });
            }
            if (!Objects.equals(oldPhone, phoneText.getText().toString())) {
                oldPhone = phoneText.getText().toString();

                resendTimeCountText = view.findViewById(R.id.resend_time_count_text);
                TextView resendButton = view.findViewById(R.id.resend_button);
                resendButton.setPaintFlags(resendButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                resendButton.setOnClickListener(v2 -> {
                    if (resendEnabled) {
                        this.requestPhoneNumberVerification(cleanPhoneNumber(oldPhone));
                        startCountDownTimer();
                    }
                });

                this.requestPhoneNumberVerification(cleanPhoneNumber(oldPhone));
                startCountDownTimer();
                Toast.makeText(view.getContext(), "We sent a smsCode to your phone", Toast.LENGTH_SHORT).show();

                LinearLayout verification_section = view.findViewById(R.id.verification_section);
                verification_section.setVisibility(View.VISIBLE);
                Button verifyButton = view.findViewById(R.id.button_verify_code);
                EditText smsInput = view.findViewById(R.id.edit_text_verification_code);
                verifyButton.setOnClickListener(v1 -> {
                    if (smsInput.getText().toString().length() < 6) {
                        Toast.makeText(view.getContext(), "SmsCode must have 6 letters", Toast.LENGTH_SHORT);
                    } else {
                        this.updatePhoneNumber(mVerificationId, smsInput.getText().toString());
                        verification_section.setVisibility(View.GONE);
                    }
                });
            }
            Toast.makeText(view.getContext(), "User information updated successfully", Toast.LENGTH_SHORT);

            ImageView saveImage = getView().findViewById(R.id.save_image);
            int color = ContextCompat.getColor(requireContext(), R.color.DarkGrey);
            ColorStateList colorStateList = ColorStateList.valueOf(color);
            ViewCompat.setBackgroundTintList(saveImage, colorStateList);
            TextView saveText = getView().findViewById(R.id.save_text);
            saveText.setTextColor(color);

            TextView avaName = view.findViewById(R.id.profile_name);
            avaName.setText(oldName);
            TextView avaEmail = view.findViewById(R.id.profile_email);
            avaEmail.setText(oldEmail);
        });

        ConstraintLayout signOutButton = view.findViewById(R.id.sign_out_tag);
        signOutButton.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent intent = new Intent(requireContext(), Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        });

        Button pastButton = view.findViewById(R.id.past_btn);
        pastButton.setOnClickListener(v -> {
            TripsFragment.initialPosition = 1;
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchToTripFragment();
            }
        });

        Button activeButton = view.findViewById(R.id.active_btn);
        activeButton.setOnClickListener(v -> {
            TripsFragment.initialPosition = 0;
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchToTripFragment();
            }
        });

        Button cancelledButton = view.findViewById(R.id.cancelled_btn);
        cancelledButton.setOnClickListener(v -> {
            TripsFragment.initialPosition = 2;
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchToTripFragment();
            }
        });

        return view;
    }

    public void savable() {
        ImageView saveImage = getView().findViewById(R.id.save_image);
        int color = ContextCompat.getColor(requireContext(), R.color.ButtonColor);
        ColorStateList colorStateList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(saveImage, colorStateList);
        TextView saveText = getView().findViewById(R.id.save_text);
        saveText.setTextColor(color);
    }

    public void notSavable() {
        ImageView saveImage = getView().findViewById(R.id.save_image);
        int color = ContextCompat.getColor(requireContext(), R.color.DarkGrey);
        ColorStateList colorStateList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(saveImage, colorStateList);
        TextView saveText = getView().findViewById(R.id.save_text);
        saveText.setTextColor(color);
    }

    public void requestPhoneNumberVerification(String phoneNumber) {
        Activity mActivity = getActivity();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,          // Phone number to verify
                60,                   // Timeout duration in seconds
                TimeUnit.SECONDS,     // Unit of timeout
                mActivity,            // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(mActivity, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                        mVerificationId = verificationId;
                    }
                });
    }

    // Method to update the user's phone number
    private void updatePhoneNumber(String verificationId, String smsCode) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Activity mActivity = getActivity();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            user.updatePhoneNumber(PhoneAuthProvider.getCredential(verificationId, smsCode))
                    .addOnCompleteListener(mActivity, task -> {
                        if (task.isSuccessful()) {
                            // Phone number updated successfully
                            Toast.makeText(mActivity, "Phone number updated.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If updating fails, display a message to the user.
                            Toast.makeText(mActivity, "Failed to update phone number.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(mActivity, "User not signed in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCountDownTimer() {
        resendEnabled = false;
        new CountDownTimer(resendTime * 1000L, 1000) {
            @Override
            public void onTick(long sendsUntilFinished) {
                resendTimeCountText.setText("Resend in: " + String.valueOf(sendsUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                resendEnabled = true;
            }
        }.start();
    }

    private static String cleanPhoneNumber(String phoneNumber) {
        String cleanedNumber = phoneNumber.replaceAll("[^+0-9]", "");

        Log.d("asd", cleanedNumber);
        return cleanedNumber;
    }
}