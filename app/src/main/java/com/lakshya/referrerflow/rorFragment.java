package com.lakshya.referrerflow;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class rorFragment extends Fragment {
    //ror->Refer Or Referral
    MaterialButton jobButton;
    MaterialButton referButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }   //to view search and filter button that are on right

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ror_fragment, container, false);
        jobButton = view.findViewById(R.id.job_button);
        referButton = view.findViewById(R.id.refer_button);
//        ParseUser parseUser=ParseUser.getCurrentUser();
//        ParseObject parseObject=ParseUser.getCurrentUser();
        final ParseQuery<ParseUser> query=ParseQuery.getQuery("User");

        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseObject userInfo=new ParseObject("UserInfo");
                userInfo.put("username",ParseUser.getCurrentUser().getUsername());
                userInfo.put("rc","c");

                userInfo.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException ex) {
                        if (ex == null) {
                            Log.i("rc update", "Successful!");
                        } else {
                            Log.i("rc update", "Failed" + ex.toString());
                        }
                    }
                });
                ((NavigationHost) getActivity()).navigateTo(new FresherOrNotFragment(), true);
            }
        });
        referButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(rorFragment.this, "Refer working....", Toast.LENGTH_SHORT);

                ParseObject userInfo=new ParseObject("UserInfo");
                userInfo.put("username",ParseUser.getCurrentUser().getUsername());
                userInfo.put("rc","r");

                userInfo.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException ex) {
                        if (ex == null) {
                            Log.i("rc update", "Successful!");
                        } else {
                            Log.i("rc update", "Failed" + ex.toString());
                        }
                    }
                });
//                query.whereEqualTo("email",ParseUser.getCurrentUser().getEmail());
//                query.setLimit(1);
//                query.findInBackground(new FindCallback<ParseUser>() {
//                    @Override
//                    public void done(List<ParseUser> objects, ParseException e) {
//                        if(e==null){
//                            Log.i("findInBackground","Retrieved "+objects.size()+" objects");
//                            if(objects.size()>0){
//                                for(ParseObject object: objects){
//                                    object.put("rc","c");
//                                }
//                            }
//                        }
//                        else{
//                            Log.i("rc","Failed!!"+e.toString());
//                        }
//                    }
//                });
//                query.getInBackground(ParseUser.getCurrentUser().getObjectId(), new GetCallback<ParseUser>() {
//                    @Override
//                    public void done(ParseUser object, ParseException e) {
//                        if(e==null && object!=null){
//                            object.put("rc","r");
//                        }
//                        else{
//                            Log.i("rc","Failed!!"+e.toString());
//                        }
//                    }
//
//                });
                ((NavigationHost) getActivity()).navigateTo(new ReferrerDetailsFragment(), true);
            }
        });

        return view;
    }

}