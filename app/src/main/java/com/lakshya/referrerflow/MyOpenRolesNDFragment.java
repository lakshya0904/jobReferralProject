package com.lakshya.referrerflow;
// DUMPED
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MyOpenRolesNDFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, NavigationView.OnCapturedPointerListener {

    private Toolbar myOpenRolesToolbar;
    private DrawerLayout myOpenRolesDrawerLayout;
    private NavigationView myOpenRolesNavigationView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_open_roles_nd_fragment, container, false);

        myOpenRolesToolbar=view.findViewById(R.id.my_open_roles_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(myOpenRolesToolbar);
        }

        myOpenRolesDrawerLayout=view.findViewById(R.id.my_open_roles_drawer_layout);
        myOpenRolesNavigationView=view.findViewById(R.id.nav_drawer_view);

        //to add a side bar
        ActionBarDrawerToggle myOpenRolesActionBarDrawerToggle = new ActionBarDrawerToggle(
                activity,
                myOpenRolesDrawerLayout,
                myOpenRolesToolbar,
                R.string.my_open_roles_open_nav_drawer,
                R.string.my_open_roles_close_nav_drawer
        );

        myOpenRolesDrawerLayout.addDrawerListener(myOpenRolesActionBarDrawerToggle);
        myOpenRolesActionBarDrawerToggle.syncState();
        myOpenRolesNavigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) activity);

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        return false;
    }

    @Override
    public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        return false;
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture){
//
//    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    /*
   In reality, this will have more complex logic including, but not limited to, actual
   authentication of the username and password.
*/
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }


}
