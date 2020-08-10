package com.lakshya.referrerflow;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;


//import com.google.codelabs.mdc.java.shrine.network.ProductEntry;

public class MyOpenRolesGridFragment extends Fragment {

    private List<MyOpenRolesEntry> myOpenRolesList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.fragment_my_open_roles_grid, container, false);

        // Set up the toolbar
        setUpToolbar(view);

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        //change span count a/c to number of cards in a row
         myOpenRolesList = MyOpenRolesEntry.initMyOpenRolesEntryList(getResources());
        MyOpenRolesCardRecyclerViewAdapter adapter = new MyOpenRolesCardRecyclerViewAdapter(
                MyOpenRolesEntry.initMyOpenRolesEntryList(getResources()),new MyRoleButtonListener() {
            @Override
            public void OnViewRequestClickListener(int position) {
                SelectedRoleFragment fragment=new SelectedRoleFragment();
                Bundle args = new Bundle();
                args.putParcelable("selectedRole", (Parcelable) myOpenRolesList.get(position));
//        args.putString("name",myOpenRolesList.get(position));
                fragment.setArguments(args);
                ((NavigationHost) fragment.getActivity()).navigateTo(fragment, false);
            }
        });
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.my_open_roles_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.my_open_roles_grid_spacing_small);
        recyclerView.addItemDecoration(new MyOpenRolesGridItemDecoration(largePadding, smallPadding));

        return view;
    }

    private void setUpToolbar(View view) {
        androidx.appcompat.widget.Toolbar toolbar=view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        menuInflater.inflate(R.menu.nav_drawer_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

}