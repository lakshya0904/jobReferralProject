package com.lakshya.referrerflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MyOpenRolesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationHost, MyRoleButtonListener {

    private Toolbar myOpenRolesToolbar;

    private DrawerLayout myOpenRolesDrawerLayout;
    private NavigationView myOpenRolesNavigationView;

    private List<MyOpenRolesEntry> myOpenRolesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_open_roles);

        myOpenRolesToolbar=findViewById(R.id.my_open_roles_toolbar);
        setSupportActionBar(myOpenRolesToolbar);

        myOpenRolesDrawerLayout=findViewById(R.id.my_open_roles_drawer_layout);
        myOpenRolesNavigationView=findViewById(R.id.my_open_roles_nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
                this,
                myOpenRolesDrawerLayout,
                myOpenRolesToolbar,
                R.string.my_open_roles_open_nav_drawer,
                R.string.my_open_roles_close_nav_drawer
        );

        myOpenRolesDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        myOpenRolesNavigationView.setNavigationItemSelectedListener(this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));

        //passing interface as argument->anonymous inner class thing
        //this can be replaced by following code(which is anonymous inner class thing):
        //also here this works bcoz activity implements interface and we have overridden its method.

//        new MyRoleButtonListener() {
//            @Override
//            public void OnViewRequestClickListener(int position) {
//                SelectedRoleFragment fragment=new SelectedRoleFragment();
//                Bundle args = new Bundle();
//                args.putParcelable("selectedRole", (Parcelable) myOpenRolesList.get(position));
////        args.putString("name",myOpenRolesList.get(position));
//                fragment.setArguments(args);
//                ((NavigationHost) fragment.getActivity()).navigateTo(fragment, false);
//            }
//        }
        MyOpenRolesCardRecyclerViewAdapter adapter = new MyOpenRolesCardRecyclerViewAdapter(
                MyOpenRolesEntry.initMyOpenRolesEntryList(getResources()), this);
        //
        myOpenRolesList=MyOpenRolesEntry.initMyOpenRolesEntryList(getResources());
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.my_open_roles_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.my_open_roles_grid_spacing_small);
        recyclerView.addItemDecoration(new MyOpenRolesGridItemDecoration(largePadding, smallPadding));

        FloatingActionButton addNewRoleButton = findViewById(R.id.add_new_open_roles_button);
        addNewRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.my_open_roles_drawer_layout, new AddNewRoleFragment())
                            .commit();
            }

        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //you can add switch statement here yo get the click event for the navigation item selected

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.my_open_roles_drawer_layout, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public void OnViewRequestClickListener(int position) {
//        SelectedRoleFragment fragment=new SelectedRoleFragment();
//        Bundle args = new Bundle();
////        Toast.makeText(this,"view request clicked",Toast.LENGTH_SHORT).show();
////        args.putParcelable("selectedRole", "(Parcelable) myOpenRolesList.get(position)");
////        error:MyOpenRolesEntry cannot be cast to android.os.Parcelable
//        //will required parceable implementation in MyOpenRolesEntry
//        args.putString("name","A name");
//        fragment.setArguments(args);
//        ((NavigationHost) this).navigateTo(fragment, false);

        Bundle bundle=new Bundle();
        bundle.putParcelable(getString(R.string.selected_role), (Parcelable) myOpenRolesList.get(position));
        SelectedRoleFragment selectedRoleFragment=new SelectedRoleFragment();
        selectedRoleFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.my_open_roles_drawer_layout, selectedRoleFragment)
                .commit();
    }
}
