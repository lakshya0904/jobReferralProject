package com.lakshya.referrerflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class FresherOrNotFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }   //to view search and filter button that are on right

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fresher_or_not_fragment, container, false);

        MaterialButton fresherButton = view.findViewById(R.id.fresher_button);
        MaterialButton notAFresherButton = view.findViewById(R.id.not_a_fresher_button);
        fresherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new FresherDetailsFragment(), true);
            }
        });
        notAFresherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(rorFragment.this, "Refer working....", Toast.LENGTH_SHORT);
                ((NavigationHost) getActivity()).navigateTo(new NotAFresherDetailsFragment(), true);
            }
        });

        return view;
    }
}
