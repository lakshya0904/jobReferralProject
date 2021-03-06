package com.lakshya.referrerflow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewRequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewRequestsFragment extends Fragment implements CardButtonListener{
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public NewRequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewRequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static NewRequestsFragment newInstance(String param1, String param2) {
//        NewRequestsFragment fragment = new NewRequestsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    private List<NewRequestsEntry> newRequestsEntryList;
    View rejectView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }
    ViewGroup container;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container=container;   //for using it in OnViewRequestClickListener method
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_requests, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false));

        NewRequestsCardRecyclerViewAdapter adapter=new NewRequestsCardRecyclerViewAdapter(NewRequestsEntry.initNewRequestsEntryList(getResources()),this);
        newRequestsEntryList=NewRequestsEntry.initNewRequestsEntryList(getResources());
        recyclerView.setAdapter(adapter);

        int largePadding = getResources().getDimensionPixelSize(R.dimen.new_requests_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.new_requests_grid_spacing_small);
        recyclerView.addItemDecoration(new NewRequestsGridItemDecoration(largePadding,smallPadding));

        return view;
    }

    @Override
    public void OnCardButtonClickListener(int position) {
//        ((NavigationHost) getActivity()).navigateTo(new NewRequestsFragment(), false); // Navigate to the next Fragment
        newRequestsEntryList.get(position); //reference to node which was selected

//        dumped bcoz dialod box turned out better as you need Parcelable to get back to selected role and there is no such issue with dialog box
//        Bundle bundle=new Bundle();
//        bundle.putParcelable(getString(R.string.applicant_to_reject), (Parcelable) newRequestsEntryList.get(position));
//
//        RejectRoleFragment rejectRoleFragment=new RejectRoleFragment();
//        rejectRoleFragment.setArguments(bundle);
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.my_open_roles_drawer_layout, rejectRoleFragment)
//                .commit();
        final MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(getContext());
        rejectView=getLayoutInflater().inflate(R.layout.dialog_reject,container,false);
        builder.setView(rejectView)
                .setTitle("Reject "+newRequestsEntryList.get(position).name)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    @Override
    public void OnAcceptButtonClickListener(int position) {

        newRequestsEntryList.get(position); //reference to node which was selected
        final MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(getContext());
        rejectView=getLayoutInflater().inflate(R.layout.dialog_accept,container,false);
        builder.setView(rejectView)
                .setTitle("Refer "+newRequestsEntryList.get(position).name)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    @Override
    public void OnNameClickListener(int position) {

    }

}
