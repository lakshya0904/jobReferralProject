package com.lakshya.referrerflow;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class ReferrerDetailsFragment extends Fragment {

    TextInputLayout nameTextInput;
    TextInputEditText nameEditText;
    TextInputLayout currentEmployerTextInput;
    TextInputEditText currentEmployerEditText;
    TextInputLayout designationTextInput;
    TextInputEditText designationEditText;
    TextInputLayout locationTextInput;
    TextInputEditText locationEditText;
    TextInputLayout workingSinceMonthTextInput;
    AutoCompleteTextView workingSinceMonthTextView;
    TextInputLayout workingSinceYearTextInput;
    AutoCompleteTextView workingSinceYearTextView;
    TextInputLayout workEmailTextInput;
    TextInputEditText workEmailEditText;
    RadioGroup genderRadioGroup;
    RadioButton femaleRadioButton;
    RadioButton maleRadioButton;
    RadioButton otherRadioButton;
    boolean firstError=true;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.referrer_details_fragment, container, false);

        nameTextInput=view.findViewById(R.id.name_text_input);
        nameEditText=view.findViewById(R.id.name_edit_text);
        currentEmployerTextInput = view.findViewById(R.id.current_employer_text_input);
        currentEmployerEditText = view.findViewById(R.id.current_employer_edit_text);
        designationTextInput=view.findViewById(R.id.designation_text_input);
        designationEditText=view.findViewById(R.id.designation_edit_text);
        locationTextInput=view.findViewById(R.id.location_text_input);
        locationEditText=view.findViewById(R.id.designation_edit_text);
        workingSinceMonthTextInput=view.findViewById(R.id.month_filled_exposed_text_input);
        workingSinceMonthTextView=view.findViewById(R.id.month_filled_exposed_dropdown);
        workingSinceYearTextInput=view.findViewById(R.id.year_filled_exposed_text_input);
        workingSinceYearTextView=view.findViewById(R.id.year_filled_exposed_dropdown);
        workEmailTextInput=view.findViewById(R.id.work_email_text_input);
        workEmailEditText=view.findViewById(R.id.work_email_edit_text);
        genderRadioGroup=view.findViewById(R.id.gender_group);
        femaleRadioButton=view.findViewById(R.id.radio_button_female);
        maleRadioButton=view.findViewById(R.id.radio_button_male);
        otherRadioButton=view.findViewById(R.id.radio_button_other);


        MaterialButton confirmButton = view.findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((NavigationHost) getActivity()).navigateTo(new MyOpenRolesNDFragment(), false); // Navigate to the next Fragment
//                ((NavigationHost) getActivity()).navigateTo(new MyOpenRolesGridFragment(), false);

                nameTextInput.setError(null);
                currentEmployerTextInput.setError(null);
                designationTextInput.setError(null);
                locationTextInput.setError(null);
                workingSinceMonthTextInput.setError(null);
                workingSinceYearTextInput.setError(null);
                workEmailTextInput.setError(null);

                if(!isValid(nameEditText.getText())) {
                    nameTextInput.setError(getString(R.string.error_name));
                    if(firstError) {
                        nameTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!isValid(designationEditText.getText())){
                    designationTextInput.setError(getString(R.string.error_designation));
                    if(firstError) {
                        designationTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!isValid(currentEmployerEditText.getText())){
                    currentEmployerTextInput.setError(getString(R.string.error_current_employer));
                    if(firstError) {
                        currentEmployerTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!isValid(locationEditText.getText())){
                    locationTextInput.setError(getString(R.string.error_location));
                    if(firstError) {
                        locationTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!isValid(workingSinceMonthTextView.getText())){
                    workingSinceMonthTextInput.setError(getString(R.string.error_month));
                    if(firstError) {
                        workingSinceMonthTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!isValid(workingSinceYearTextView.getText())) {
                    workingSinceYearTextInput.setError(getString(R.string.error_year));
                    if(firstError) {
                        workingSinceYearTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!isValid(workEmailEditText.getText())){
                    workEmailTextInput.setError(getString(R.string.error_work_email));
                    if(firstError) {
                        workEmailTextInput.requestFocus();
                        firstError=false;
                    }
                }if(!(femaleRadioButton.isChecked() || maleRadioButton.isChecked() || otherRadioButton.isChecked())){
                    Toast.makeText(getContext(),"Choose gender.",Toast.LENGTH_SHORT).show();
                }
                if(firstError) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
                    query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                    query.setLimit(1);
//                ParseUser pUser= ParseUser.getCurrentUser();
//                String objId= pUser.getObjectId();
                    //first getting the data, then updating info and then finally saving it in the same we did while creating it
                    String objId = null;
                    try {
                        objId = query.getFirst().getObjectId();
                    } catch (ParseException e) {
                        Log.i("Object id error", e.toString());
                        e.printStackTrace();
                    }
                    query.getInBackground(objId, new GetCallback<ParseObject>() {//"hVGmQEazjV"
                        @Override
                        public void done(final ParseObject object, ParseException e) {
                            if (e == null) {
                                object.put("name", nameEditText.getText().toString());
                                object.put("company", currentEmployerEditText.getText().toString());
                                object.put("designation", designationEditText.getText().toString());
                                object.put("location", locationEditText.getText().toString());
                                object.put("workingSinceMonth", workingSinceMonthTextView.getText().toString());
                                object.put("workingSinceYear", workingSinceYearTextView.getText().toString());
                                object.put("workEmail",workEmailEditText.getText().toString());
                                if (femaleRadioButton.isChecked())
                                    object.put("gender", "Female");
                                else if (maleRadioButton.isChecked())
                                    object.put("gender", "Male");
                                else
                                    object.put("gender", "Other");
                                object.saveInBackground(new SaveCallback() {
                                    public void done(ParseException ex) {
                                        if (ex == null) {
                                            Log.i("Referral details update", "Successful");
                                        } else {
                                            Log.i("Referrer details update", "Failed" + ex.toString());
                                        }
                                    }
                                });
                            } else {
                                Log.i("Referrer details update", "Failed" + e.toString());
                            }
                        }
                    });
//                query.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        if(e==null && objects!=null){
//                            for(final ParseObject object:objects){
//                                object.put("Name",nameEditText.getText().toString());
//                                object.saveInBackground(new SaveCallback() {
//                                    public void done(ParseException e) {
//
//                                        object.put("Name", "oldname");
//
//                                    }
//                                });
//                            }
//                        }
//                        else{
//                            Log.i("Referrer details update","Failed"+e.toString());
//                        }
//                    }
//                });
//                query.getInBackground("Faqc20RsKp", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if(e==null && object!=null){
//                            //Updating value
//                            object.put("score",200);
//                            object.saveInBackground();
//                            //we don't need to get object again because value is updated locally also
//                            //getting values
//                            Log.i("ObjectValue", object.getString("username"));
//                            Log.i("ObjectValue",Integer.toString(object.getInt("score")));
//                        }
//                    }
//                });
//                query.getInBackground(ParseUser.getCurrentUser().getObjectId(), new GetCallback<ParseUser>() {
//                    @Override
//                    public void done(ParseUser object, ParseException e) {
//                        if(e==null && object!=null){
//                            object.put("rc","c");
//                        }
//                    }
//                });
                    Intent i = new Intent(getActivity(), MyOpenRolesActivity.class);
                    startActivity(i);
                }
            }
        });
        String[] MONTHS = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        getContext(),
                        R.layout.dropdown_menu_popup_month,
                        MONTHS);

        AutoCompleteTextView editTextFilledExposedDropdown =
                view.findViewById(R.id.month_filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);

        String[] YEARS=new String[]{"1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984",
                "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996",
                "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
                "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
        ArrayAdapter<String> yearAdapter =
                new ArrayAdapter<String>(
                        getContext(),
                        R.layout.dropdown_menu_popup_month,
                        YEARS);

        AutoCompleteTextView yearEditTextFilledExposedDropdown =
                view.findViewById(R.id.year_filled_exposed_dropdown);
        yearEditTextFilledExposedDropdown.setAdapter(yearAdapter);
        return view;
    }


    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    /*
   In reality, this will have more complex logic including, but not limited to, actual
   authentication of the username and password.
*/
    private boolean isValid(@Nullable Editable text) {
        return text != null && text.length() >0;
    }
}
