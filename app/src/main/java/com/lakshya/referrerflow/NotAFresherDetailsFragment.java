package com.lakshya.referrerflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NotAFresherDetailsFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.not_a_fresher_details_fragment, container, false);
        final TextInputLayout nameTextInput = view.findViewById(R.id.name_text_input);
        final TextInputEditText nameEditText = view.findViewById(R.id.name_edit_text);
        MaterialButton confirmButton = view.findViewById(R.id.confirm_button);

        String[] MONTHS = new String[] {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
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
}
