package com.example.mobdev_nhom7.fragment.main_activity;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.TimeUnit;


import java.util.Objects;

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
    String oldEmail = "";
    EditText nameText;
    EditText emailText;
    private boolean resendEnabled = false;
    private int resendTime = 60;
    TextView resendTimeCountText;
    private ImageView profilePic;
    Bitmap results, maskBitmap;

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
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        profilePic = view.findViewById(R.id.pfp);

        Bitmap finalMasking = maskingProcess();
        sharedPreferences= view.getContext().getSharedPreferences(getString(R.string.user_info), MODE_PRIVATE);

        TextView profileName = view.findViewById(R.id.profile_name);
        profileName.setText(sharedPreferences.getString("name", ""));

        TextView profileEmail = view.findViewById(R.id.profile_email);
        profileEmail.setText(sharedPreferences.getString("email", ""));

        nameText = view.findViewById(R.id.dummy_name);
        nameText.setText(sharedPreferences.getString("name", ""));
        ImageView nameEditImage = view.findViewById(R.id.name_edit_image);

        emailText = view.findViewById(R.id.email_edit_text);
        emailText.setText(sharedPreferences.getString("email", ""));
        ImageView emailEditImage = view.findViewById(R.id.email_edit_image);

        oldName = nameText.getText().toString();
        oldEmail = emailText.getText().toString();

        LinearLayout saveButton = view.findViewById(R.id.save_button);

        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);

        RelativeLayout nameBlock = view.findViewById(R.id.name_block);
        nameBlock.setOnClickListener(v -> {
            nameEditImage.setImageResource(R.drawable.edit_off_icon);
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
                } else if (emailText.getText().toString().equals(oldEmail)) {
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


        RelativeLayout emailBlock = view.findViewById(R.id.email_block);
        emailBlock.setOnClickListener(v -> {
            emailEditImage.setImageResource(R.drawable.edit_off_icon);

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
                } else if (nameText.getText().toString().equals(oldName)) {
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
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.d("name when save", nameText.getText().toString());
            editor.putString("name", nameText.getText().toString());
            editor.apply();
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
                                Log.d("Firebase Name Current User", "User display name updated.");
                            }
                        });
            }

            Toast.makeText(view.getContext(), "User information updated successfully", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onResume() {
        super.onResume();
        Log.d("name resume", sharedPreferences.getString("name", ""));
        nameText.setText(sharedPreferences.getString("name", ""));
    }

    public void notSavable() {
        ImageView saveImage = getView().findViewById(R.id.save_image);
        int color = ContextCompat.getColor(requireContext(), R.color.DarkGrey);
        ColorStateList colorStateList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(saveImage, colorStateList);
        TextView saveText = getView().findViewById(R.id.save_text);
        saveText.setTextColor(color);
    }

    private Bitmap maskingProcess() {
        try {
            Bitmap original, mask;
            original = BitmapFactory.decodeResource(getResources(), R.drawable.demo_pfp);
            mask = BitmapFactory.decodeResource(getResources(), R.drawable.circle_mask);

            if (original != null) {
                int width = original.getWidth();
                int height = original.getHeight();
                results = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                maskBitmap = Bitmap.createScaledBitmap(mask, width, height, true);

                Canvas canvas = new Canvas(results);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

                canvas.drawBitmap(original, 0, 0, null);
                canvas.drawBitmap(maskBitmap,0,0,paint);
                paint.setXfermode(null);
                paint.setStyle(Paint.Style.STROKE);
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }

        profilePic.setImageBitmap(results);
        return results;
    }
}