package com.lakshya.referrerflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseUser;

public class OTPFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.otp_fragment, container, false);
        ParseUser parseUser=ParseUser.getCurrentUser();

        final TextInputLayout OTPTextInput = view.findViewById(R.id.otp_text_input);
        final TextInputEditText OTPEditText = view.findViewById(R.id.otp_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new rorFragment(), true);
            }
        });
        return view;
    }
}
