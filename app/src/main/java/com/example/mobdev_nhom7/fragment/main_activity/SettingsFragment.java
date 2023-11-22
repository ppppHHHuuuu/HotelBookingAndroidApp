package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

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

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.Login;
import com.example.mobdev_nhom7.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String oldName = "";
    String oldPhone = "";
    String oldEmail = "";

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
        EditText nameText = view.findViewById(R.id.dummy_name);
        ImageView nameEditImage = view.findViewById(R.id.name_edit_image);
        EditText phoneText = view.findViewById(R.id.phone_edit_text);
        ImageView phoneEditImage = view.findViewById(R.id.phone_edit_image);
        EditText emailText = view.findViewById(R.id.email_edit_text);
        ImageView emailEditImage = view.findViewById(R.id.email_edit_image);
        oldName = nameText.getText().toString();
        oldPhone = phoneText.getText().toString();
        oldEmail = emailText.getText().toString();

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
                    savable();
                } else if (phoneText.getText().toString().equals(oldPhone)
                        && emailText.getText().toString().equals(oldEmail)) {
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
                    savable();
                } else if (nameText.getText().toString().equals(oldName)
                        && emailText.getText().toString().equals(oldEmail)) {
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
                    savable();
                } else if (nameText.getText().toString().equals(oldName)
                        && phoneText.getText().toString().equals(oldPhone)) {
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
        LinearLayout saveButton = view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            oldName = nameText.getText().toString();
            oldPhone = phoneText.getText().toString();
            oldEmail = emailText.getText().toString();
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
}