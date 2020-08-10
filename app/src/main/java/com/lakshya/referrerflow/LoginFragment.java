package com.lakshya.referrerflow;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class LoginFragment extends Fragment implements View.OnKeyListener {
    TextInputLayout usernameTextInput;
    TextInputEditText usernameEditText;
    TextInputLayout passwordTextInput;
    TextInputEditText passwordEditText;
    MaterialButton logInButton;
    TextView signUpTextView;
//    MaterialButton signUpButton;
    Boolean signUpModeActive=false;
    LinearLayout backgroundLinearLayout;
    TextView appNameTextView;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        passwordTextInput = view.findViewById(R.id.password_text_input);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        usernameTextInput=view.findViewById(R.id.username_text_input);
        usernameEditText=view.findViewById(R.id.username_edit_text);
        logInButton = view.findViewById(R.id.log_in_button);
        signUpTextView=view.findViewById(R.id.sign_up_text_view);
//        signUpButton=view.findViewById(R.id.signup_button);
        backgroundLinearLayout=view.findViewById(R.id.background_linear_layout);
        appNameTextView=view.findViewById(R.id.app_name_text_view);

//        if(ParseUser.getCurrentUser()!=null){
//            Intent i=new Intent(getActivity(), MyOpenRolesActivity.class);
//            startActivity(i);
//        }
        //on click listener to hide keyboard as soon as pressed on background or textview
        backgroundLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });
        appNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });


        // Set an error if the password is less than 8 characters
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn(view);
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new SignUpFragment(), true);

                // tried to use both signp and login in same fragment
//                if(signUpModeActive){
//                    signUpModeActive=false;
//                    logInButton.setText(R.string.button_log_in);
//                    signUpTextView.setText(R.string.text_view_sign_up);
//                }
//                else {
//                    signUpModeActive=true;
//                    logInButton.setText(R.string.button_sign_up);
//                    signUpTextView.setText(R.string.text_view_log_in);
//                }
            }
        });


        passwordEditText.setOnKeyListener((View.OnKeyListener) this);
        // Snippet from "Navigate to the next Fragment" section goes here.

        return view;
    }

    public void logIn(View view){
        if (!isUsernameValid(usernameEditText.getText()) && !isPasswordValid(passwordEditText.getText())) {
            usernameTextInput.setError(getString(R.string.error_username));
            passwordTextInput.setError(getString(R.string.error_password));
            usernameTextInput.requestFocus();
        } else if (!isUsernameValid(usernameEditText.getText())) {
            passwordTextInput.setError(null);
            usernameTextInput.setError(getString(R.string.error_username));
            usernameTextInput.requestFocus();
        } else if (!isPasswordValid(passwordEditText.getText())) {
            usernameTextInput.setError(null);
            passwordTextInput.setError(getString(R.string.error_password));
            passwordTextInput.requestFocus();
        } else {
            passwordTextInput.setError(null); // Clear the error
            usernameTextInput.setError(null);

    //                    ParseObject userLoginInfo=new ParseObject("UserLoginInfo");

            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Intent i=new Intent(getActivity(), MyOpenRolesActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //tried to use same fragment for login and sign up
//        if(!signUpModeActive) {
//            if (!isUsernameValid(usernameEditText.getText()) && !isPasswordValid(passwordEditText.getText())) {
//                usernameTextInput.setError(getString(R.string.error_username));
//                passwordTextInput.setError(getString(R.string.error_password));
//                usernameTextInput.requestFocus();
//            } else if (!isUsernameValid(usernameEditText.getText())) {
//                passwordTextInput.setError(null);
//                usernameTextInput.setError(getString(R.string.error_username));
//                usernameTextInput.requestFocus();
//            } else if (!isPasswordValid(passwordEditText.getText())) {
//                usernameTextInput.setError(null);
//                passwordTextInput.setError(getString(R.string.error_password));
//                passwordTextInput.requestFocus();
//            } else {
//                passwordTextInput.setError(null); // Clear the error
//                usernameTextInput.setError(null);
//
////                    ParseObject userLoginInfo=new ParseObject("UserLoginInfo");
//
//                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
//                    @Override
//                    public void done(ParseUser user, ParseException e) {
//                        if (user != null) {
//                            Intent i=new Intent(getActivity(), MyOpenRolesActivity.class);
//                            startActivity(i);
//                        } else {
//                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        }
//        else{
//            if(!isUsernameValid(usernameEditText.getText()) && !isPasswordValid(passwordEditText.getText())){
//                usernameTextInput.setError(getString(R.string.error_username));
//                passwordTextInput.setError(getString(R.string.error_password));
//                usernameTextInput.requestFocus();
//            }
//            else if(!isUsernameValid(usernameEditText.getText())){
//                passwordTextInput.setError(null);
//                usernameTextInput.setError(getString(R.string.error_username));
//                usernameTextInput.requestFocus();
//            }
//            else if (!isPasswordValid(passwordEditText.getText())) {
//                usernameTextInput.setError(null);
//                passwordTextInput.setError(getString(R.string.error_password));
//                passwordTextInput.requestFocus();
//            } else {
//                passwordTextInput.setError(null); // Clear the error
//                usernameTextInput.setError(null);
//
//                ParseUser userLoginInfo= (ParseUser) new ParseUser();
//                userLoginInfo.setUsername(usernameEditText.getText().toString());
//                userLoginInfo.setPassword(passwordEditText.getText().toString());
//
//                userLoginInfo.signUpInBackground(new SignUpCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if(e==null) {
//                            Log.i("Sign up", "Successful!!");
//                            ((NavigationHost) getActivity()).navigateTo(new SignUpFragment(), false);
//                        }
//                        else {
//                            switch (e.getCode()) {
//                                case ParseException.USERNAME_TAKEN: {
//                                    usernameTextInput.setError(getString(R.string.error_username_taken));
//                                    Log.i("Sign up","Failed!! "+e.toString());
//                                    // report error
//                                    break;
//                                }
////                                    case ParseException.EMAIL_TAKEN: {    // for email address
////                                        // report error
////                                        break;
////                                    }
//                                default: {
//                                    Log.i("Sign up","Failed!! "+e.toString());
//                                    // Something else went wrong
//                                }
//                            }
//                        }
//                    }
//                });
//            }
//        }
    }
    private boolean isUsernameValid(Editable text) {
        return text!=null && text.length()>0;
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) { //called every time a key is pressed or lifted in password edit text
        //so for chking only press down && is added in if statement
        if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                logIn(view);
        }
        return false;
    }
    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager=(InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
    }
}
