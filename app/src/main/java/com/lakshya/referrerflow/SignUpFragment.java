package com.lakshya.referrerflow;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class SignUpFragment extends Fragment implements View.OnKeyListener  {
    LinearLayout backgroundLinearLayout;
    TextView signUpTextView;
    TextInputLayout nameTextInput;
    TextInputEditText nameEditText;
    TextInputLayout usernameTextInput;
    TextInputEditText usernameEditText;
    TextInputLayout mobileTextInput;
    TextInputEditText mobileEditText;
    TextInputLayout emailTextInput;
    TextInputEditText emailEditText;
    TextInputLayout passwordTextInput;
    TextInputEditText passwordEditText;
    TextInputLayout confirmPasswordTextInput;
    TextInputEditText confirmPasswordEditInput;
    MaterialButton nextButton;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        backgroundLinearLayout=view.findViewById(R.id.background_linear_layout);
        signUpTextView=view.findViewById(R.id.sign_up_text_view);
//        nameTextInput = view.findViewById(R.id.name_text_input);
//        nameEditText = view.findViewById(R.id.name_edit_text);
        usernameTextInput = view.findViewById(R.id.username_text_input);
        usernameEditText = view.findViewById(R.id.username_edit_text);
        mobileTextInput=view.findViewById(R.id.mobile_number_text_input);
        mobileEditText=view.findViewById(R.id.mobile_number_edit_text);
        emailTextInput=view.findViewById(R.id.personal_email_id_text_input);
        emailEditText=view.findViewById(R.id.personal_email_id_edit_text);
        passwordTextInput=view.findViewById(R.id.password_text_input);
        passwordEditText=view.findViewById(R.id.password_edit_text);
        confirmPasswordTextInput=view.findViewById(R.id.confirm_password_text_input);
        confirmPasswordEditInput=view.findViewById(R.id.confirm_password_edit_text);
        nextButton = view.findViewById(R.id.next_button);

        confirmPasswordEditInput.setOnKeyListener(this);

        backgroundLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(view);
            }
        });

        return view;
    }

    public void next(View view){
        boolean firstRequestFocus=false;
//        nameTextInput.setError(null);
        usernameTextInput.setError(null);
        mobileTextInput.setError(null);
        emailTextInput.setError(null);
        passwordTextInput.setError(null);
        confirmPasswordTextInput.setError(null);

//        if(!isNameValid(nameEditText.getText())){
//            nameTextInput.setError(getString(R.string.error_name));
//            if(!firstRequestFocus) {
//                nameTextInput.requestFocus();
//                firstRequestFocus=true;
//            }
//        }
        if(!isUsernameValid(usernameEditText.getText())){
            usernameTextInput.setError(getString(R.string.error_username));
            if(!firstRequestFocus) {
                usernameTextInput.requestFocus();
                firstRequestFocus=true;
            }
        }
        if(!(isEmailValid(emailEditText.getText()) || isMobileValid(mobileEditText.getText()) )){
            mobileTextInput.setError(getString(R.string.error_mobile_email));
            emailTextInput.setError(getString(R.string.error_mobile_email));
            if(!firstRequestFocus) {
                mobileTextInput.requestFocus();
                firstRequestFocus=true;
            }
        }
        if (!isPasswordValid(passwordEditText.getText())) {
            passwordTextInput.setError(getString(R.string.error_password));
            if(!firstRequestFocus) {
                passwordTextInput.requestFocus();
                firstRequestFocus=true;
            }
        }
        if(!passwordEditText.getText().toString().equals(confirmPasswordEditInput.getText().toString())){
            passwordTextInput.setError(getString(R.string.error_confirm_password));
            if(!firstRequestFocus) {
                confirmPasswordTextInput.requestFocus();
                firstRequestFocus=true;
            }
        }
        if(!firstRequestFocus){
            passwordTextInput.setError(null); // Clear the error
            usernameTextInput.setError(null);

            ParseUser userLoginInfo= (ParseUser) new ParseUser();
            userLoginInfo.setUsername(usernameEditText.getText().toString());
            userLoginInfo.setPassword(passwordEditText.getText().toString());
            if(isEmailValid(emailEditText.getText())){
                userLoginInfo.setEmail(emailEditText.getText().toString());
            }
            userLoginInfo.put("mobile",mobileEditText.getText().toString());

            userLoginInfo.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null) {
                        Log.i("Sign up", "Successful!!");
                        ((NavigationHost) getActivity()).navigateTo(new OTPFragment(), true);
                    }
                    else {
                        switch (e.getCode()) {
                            case ParseException.USERNAME_TAKEN: {
                                usernameTextInput.setError(getString(R.string.error_username_taken));
                                Log.i("Sign up","Failed!! "+e.toString());
                                // report error
                                break;
                            }
                            case ParseException.EMAIL_TAKEN: {    // for email address
                                emailTextInput.setError(getString(R.string.error_email_taken));
                                Log.i("Sign up","Failed!! "+e.toString());
                                break;
                            }
                            case ParseException.INVALID_EMAIL_ADDRESS:{
                                emailTextInput.setError(getString(R.string.error_email_format));
                                Log.i("Sign up","Failed!! "+e.toString());
                                break;
                            }
                            default: {
                                Log.i("Sign up","Failed!! "+e.toString());
                                // Something else went wrong
                            }
                        }
                    }
                }
            });
        }
    }
    private boolean isNameValid(Editable text) {
        return text!=null && text.length()>0;
    }

    private boolean isUsernameValid(Editable text) {
        return text!=null && text.length()>0;
    }

    private boolean isMobileValid(Editable text) {
        return text!=null && text.length()>0;
    }

    private boolean isEmailValid(Editable text) {
        return text!=null && text.length()>0;
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            next(view);
        }
        return false;
    }
    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager=(InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
    }
}
