package com.lakshya.referrerflow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

//public class SelectedRoleFragment extends Fragment {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_selected_role, container, false);
////        Toast.makeText(getActivity(),"view request clicked",Toast.LENGTH_SHORT).show();
//        return view;
//    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        menuInflater.inflate(R.menu.referral_criteria_app_bar, menu);
//        super.onCreateOptionsMenu(menu, menuInflater);
//    }
public class SelectedRoleFragment extends Fragment{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private NewRequestsFragment newRequestsFragment;
    private HistoryRequestsFragment historyRequestsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_role, container, false);

        androidx.appcompat.widget.Toolbar toolbar=view.findViewById(R.id.referral_criteria_app_bar);
        //for converting toolbar into contextual action bar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle=getArguments();
        MyOpenRolesEntry selectedRole=bundle.getParcelable(getString(R.string.selected_role));
        viewPager=view.findViewById(R.id.selected_role_view_pager);
        tabLayout=view.findViewById(R.id.tab_layout);

        newRequestsFragment=new NewRequestsFragment();
        historyRequestsFragment=new HistoryRequestsFragment();

        tabLayout.setupWithViewPager(viewPager);

        SelectedRoleViewPagerAdapter selectedRoleViewPagerAdapter=new SelectedRoleViewPagerAdapter(getActivity().getSupportFragmentManager(),0);
        selectedRoleViewPagerAdapter.addFragment(newRequestsFragment,"New");
        selectedRoleViewPagerAdapter.addFragment(historyRequestsFragment,"History");
        viewPager.setAdapter(selectedRoleViewPagerAdapter);

//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_fiber_new_24dp);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_history_24dp);

        //not working!!
//        BadgeDrawable badgeDrawableNew=tabLayout.getTabAt(0).getOrCreateBadge();
//        badgeDrawableNew.setVisible(true,true);

        return view;
    }

    //this is for icons on right side of action bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.selected_role_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    //below 2 functions are for working of back button at top left
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.filter:
//                ((NavigationHost) getActivity()).navigateTo(new AddNewRoleFragment(), false);
//                may be add some dialog box
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
        startActivity(intent);
    }
}
