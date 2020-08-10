package com.lakshya.referrerflow;
//DUMPED!!
//bcoz dialod box turned out better as you need Parcelable to get back to selected role and there is no such issue with dialog box
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class RejectRoleFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reject_role, container, false);

        androidx.appcompat.widget.Toolbar toolbar=view.findViewById(R.id.reject_app_bar);
        //for converting toolbar into contextual action bar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();
        Bundle bundle=getArguments();
        NewRequestsEntry applicantToReject=bundle.getParcelable(getString(R.string.applicant_to_reject));


        return view;
    }

    //this is for icons on right side of action bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.reject_role_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

}
