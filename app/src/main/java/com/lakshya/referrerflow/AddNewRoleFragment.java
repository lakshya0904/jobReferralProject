package com.lakshya.referrerflow;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class AddNewRoleFragment extends Fragment {

    private ActionMode actionMode;
    private Button availabilityButton;
    private Button experienceButton;
    private Button collegesButton;
    private Button employersButton;
    private Button skillsButton;
    private Button locationButton;
    private Button expectationButton;
    private Button currentRoleButton;
    private Button genderButton;
    private Button ageButton;
    private Button qualificationButton;

//    private String [] COLLEGES;
    private boolean [] checkedColleges;
    ArrayList<Integer> userColleges;
    int ALL_TIER1_ENGINEERING=-1,ALL_TIER1_MBA=-1;

    View saveReferralCriteriaView;
    private Button saveButton;
    private Button cancelButton;
    private Button clearAllButton;

    LinearLayout filterOptions;

    String AVAILABILITY_OPTIONS[]=new String[]{"Immediate","1 Month", "2 Months", "3 Months"};
    String EXPERIENCE_OPTIONS[]=new String[]{"Less than 1 Year","1 Year - 3 Years","3 Years - 5 Years","5+ Years"};
    String COLLEGES[]=new String[]{"ALL TIER-1 ENGINEERING","BITS","IIT-B","IIT-KGP","IIT-M","ALL TIER-1 MBA", "IIM-A","IIM-B"};
    int TIER1_ENGINEERING_START;
    private int TIER1_ENGINEERING_END;
    int TIER1_MBA_START;
    int TIER1_MBA_END;

    boolean immediateAvailability=false, oneMonthAvailability=false, twoMonthAvailability=false, threeMonthAvailability=false;
    int experience=0;
    List<Integer> colleges=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
                startActivity(intent);
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_role, container, false);

        final LayoutInflater filterOptionsInflator=getLayoutInflater();

        filterOptions=view.findViewById(R.id.filter_options);

        availabilityButton=view.findViewById(R.id.availability_button);
        experienceButton=view.findViewById(R.id.experience_button);
        collegesButton=view.findViewById(R.id.colleges_button);
        employersButton=view.findViewById(R.id.employers_button);
        skillsButton=view.findViewById(R.id.skills_button);
        locationButton=view.findViewById(R.id.location_button);
        expectationButton=view.findViewById(R.id.expectation_button);
        currentRoleButton=view.findViewById(R.id.current_role);
        genderButton=view.findViewById(R.id.gender_button);
        ageButton=view.findViewById(R.id.age_button);
        qualificationButton=view.findViewById(R.id.qualification_button);

        filterOptions.removeAllViews();
        View vw1=filterOptionsInflator.inflate(R.layout.linear_layout_availability,filterOptions,true);
//
//        availabilityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                filterOptions.removeAllViews();
//                View vw=filterOptionsInflator.inflate(R.layout.linear_layout_availability,filterOptions,true);
//            }
//        });
        availabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filterOptions.removeAllViews();
                View vw=filterOptionsInflator.inflate(R.layout.linear_layout_availability,filterOptions,true);
                CheckBox immediateCheckBox=vw.findViewById(R.id.immediateCheckBox);
                CheckBox oneMonthCheckBox=vw.findViewById(R.id.oneMonthCheckBox);
                CheckBox twoMonthCheckBox=vw.findViewById(R.id.twoMonthCheckBox);
                CheckBox threeMonthCheckBox=vw.findViewById(R.id.threeMonthCheckBox);
                immediateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        immediateAvailability=b;
                    }
                });
                oneMonthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        oneMonthAvailability=b;
                    }
                });
                twoMonthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        twoMonthAvailability=b;
                    }
                });
                threeMonthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        threeMonthAvailability=b;
                    }
                });
//                if(((CheckBox)vw.findViewById(R.id.immediateCheckBox)).isChecked()){
//                    availability+=1;
//                }
//                if(((CheckBox)vw.findViewById(R.id.oneMonthCheckBox)).isChecked()){
//                    availability+=2;
//                }
//                if(((CheckBox)vw.findViewById(R.id.twoMonthCheckBox)).isChecked()){
//                    availability+=4;
//                }
//                if(((CheckBox)vw.findViewById(R.id.threeMonthCheckBox)).isChecked()){
//                    availability+=8;
//                }
            }
        });
        experienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filterOptions.removeAllViews();
                View vw=filterOptionsInflator.inflate(R.layout.linear_layout_experience,filterOptions,true);

            }
        });

        for(int i=0; i<COLLEGES.length; ++i){
            if(COLLEGES[i].equals("ALL TIER-1 ENGINEERING"))
                TIER1_ENGINEERING_START=i;
            else if(COLLEGES[i].equals("ALL TIER-1 MBA")) {
                TIER1_ENGINEERING_END = i - 1;
                TIER1_MBA_START = i;
            }
        }
        TIER1_MBA_END=COLLEGES.length-1;

        collegesButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                filterOptions.removeAllViews();
                View vw = filterOptionsInflator.inflate(R.layout.linear_layout_colleges, filterOptions, true);
                LinearLayout linearLayout=vw.findViewById(R.id.linear_layout_colleges);
                final ListView lView=vw.findViewById(R.id.colleges_list_view);
//                ListView lView=linearLayout.findViewById(R.id.colleges_list_view); //this also works fine
                //***********************************************************************************************************//
                // different lists can be made for tier1 engineering, tier1mba & so on.. it will make select all thing easier
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, COLLEGES);
                lView.setAdapter(arrayAdapter);
                lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//                lView.setItemChecked(1,true); // to set by default some option checked
//                lView.getChildAt(1).setBackgroundColor(Color.CYAN);
//                lView.getChildAt(0).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        lView.setItemChecked(1,true);
//                        lView.setItemChecked(2,true);
//                        lView.setItemChecked(3,true);
//                        lView.setItemChecked(4,true);
//                    }
//                });

                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                        View v;
                        int count = parent.getChildCount();
                        v = parent.getChildAt(position);
                        parent.requestChildFocus(v, view);
                        if(position==TIER1_ENGINEERING_START && lView.isItemChecked(position)){
                            for(int i=TIER1_ENGINEERING_START; i<=TIER1_ENGINEERING_END; ++i)
                                lView.setItemChecked(i,true);
                        }
//                        public void selectAll() {
//                            for (int i = 0; i < checkBoxState.length; i++) {
//                                checkBoxState[i] = true;
//                            }
//                            notifyDataSetChanged();
//                        } //function can be used to maintain the modularity
                        else if(position==TIER1_ENGINEERING_START && !lView.isItemChecked(position)){
                            for(int i=TIER1_ENGINEERING_START; i<=TIER1_ENGINEERING_END; ++i)
                                lView.setItemChecked(i,false);
                        }
                        else if(position==TIER1_MBA_START && lView.isItemChecked(position)){
                            for(int i=TIER1_MBA_START; i<=TIER1_MBA_END; ++i)
                                lView.setItemChecked(i,true);
                        }
                        else if(position==TIER1_MBA_START && !lView.isItemChecked(position)){
                            for(int i=TIER1_MBA_START; i<=TIER1_MBA_END; ++i)
                                lView.setItemChecked(i,false);
                        }

                        boolean allTier1Engineering=true;
                        for(int i=TIER1_ENGINEERING_START+1; i<=TIER1_ENGINEERING_END; ++i)
                            if(!lView.isItemChecked(i)){
                                allTier1Engineering=false;
                                break;
                            }
                        if(allTier1Engineering)
                            lView.setItemChecked(TIER1_ENGINEERING_START,true);
                        else
                            lView.setItemChecked(TIER1_ENGINEERING_START,false);

                        boolean allTier1MBA=true;
                        for(int i=TIER1_MBA_START+1; i<=TIER1_MBA_END; ++i)
                            if(!lView.isItemChecked(i)){
                                allTier1MBA=false;
                                break;
                            }
                        if(allTier1MBA)
                            lView.setItemChecked(TIER1_MBA_START,true);
                        else
                            lView.setItemChecked(TIER1_MBA_START,false);
//                        v.setBackground(res.getDrawable(R.drawable.transparent_button));
//                        for (int i = 0; i < count; i++) {
//                            if (i != position) {
//                                v = parent.getChildAt(i);
//                                v.setBackground(res.getDrawable(R.drawable.not_clicked));
//                            }
//                        }
                    }
                });

//                int position=0;
//                View wantedView = arrayAdapter.getView(position, null, lView);
//                if(wantedView==null)
//                    Toast.makeText(getContext(),"Attempt to invoke virtual method on a null object reference",Toast.LENGTH_SHORT).show();
//                View child=  lView.getChildAt(0);//gives null
//                CheckBox ch= (CheckBox) lView.getChildAt(0);//gives null
//                if(child==null)
//                    Toast.makeText(getContext(),"Attempt to invoke virtual method on a null object reference",Toast.LENGTH_SHORT).show();


//                ch.setTypeface(new Typeface((long)234), Typeface.BOLD);
//                for(int i=0; i<7; ++i) {
//                    CheckBox checkBox = new CheckBox(getContext());
//                    checkBox.setText("Hello");
//                    checkBox.setPadding(40, 40, 40, 40);
//                    ll.addView(checkBox);
//                    View line = new View(getContext());
////                    line.setMinimumHeight(1);
//                    line.setMinimumHeight(1);
//
//                    line.setBackgroundColor(R.color.toolbarIconColor);
//                    ll.addView(line);
//                }


//            }
            }
        });

//        if(actionMode!=null) {
//            actionMode = startSupportActionMode(actionModeCallback);
//        }

//        String[] MIN_EXP = new String[] {"or less","Fresher", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11","12","13","14","15","16","17","18","19","20"};
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(
//                        getContext(),
//                        R.layout.dropdown_menu_popup_month,
//                        MIN_EXP);
//
//        AutoCompleteTextView editTextFilledExposedDropdown =
//                view.findViewById(R.id.min_exp_filled_exposed_dropdown);
//        editTextFilledExposedDropdown.setAdapter(adapter);
//
//        String[] MAX_EXP=new String[]{"or more","Fresher", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11","12","13","14","15","16","17","18","19","20"};
//        ArrayAdapter<String> yearAdapter =
//                new ArrayAdapter<String>(
//                        getContext(),
//                        R.layout.dropdown_menu_popup_month,
//                        MAX_EXP);
//
//        AutoCompleteTextView yearEditTextFilledExposedDropdown =
//                view.findViewById(R.id.max_exp_filled_exposed_dropdown);
//        yearEditTextFilledExposedDropdown.setAdapter(yearAdapter);
//
////        final String[] COLLEGES=new String[]{"All Tier-1 Engineering","BITS","IIT-B","IIT-M","BHU",
////                "IIT-D","IIT-G","All Tier-1 MBA","IIM-A","IIM-B","IIM-K","FMS","YALE"};
//
//        COLLEGES=getResources().getStringArray(R.array.colleges);
//        checkedColleges=new boolean[COLLEGES.length];
//        userColleges=new ArrayList<>();
//
//
//        for(int i=1; i<COLLEGES.length; ++i){
//            if(COLLEGES[i].equals("All Tier-1 Engineering"))
//                ALL_TIER1_ENGINEERING=i;
//            else if(COLLEGES[i].equals("All Tier-1 MBA"))
//                ALL_TIER1_MBA=i;
//        }
//        collegesButton=(view).findViewById(R.id.colleges_button);
//        collegesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(getContext());
//                // getContext() for passing context, in case of activity-> activityName.this
//
//                builder.setMultiChoiceItems(COLLEGES, checkedColleges, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if(isChecked){
//                            if(userColleges==null || !userColleges.contains(position)){
//                                if(position==ALL_TIER1_ENGINEERING){
//                                    for(int i=ALL_TIER1_ENGINEERING; i<ALL_TIER1_MBA; ++i) {
//                                        if (!userColleges.contains(i))
//                                            userColleges.add(i);
//                                    }
//                                }else if(position==ALL_TIER1_MBA){
//                                    for(int i=ALL_TIER1_MBA; i<COLLEGES.length; ++i) {
//                                        if (!userColleges.contains(i))
//                                            userColleges.add(i);
//                                    }
//                                }else
//                                    userColleges.add(position);
//                            }else if(userColleges!=null){
//                                if(position==ALL_TIER1_ENGINEERING){
//                                    for(int i=ALL_TIER1_ENGINEERING; i<ALL_TIER1_MBA; ++i) {
//                                        if (userColleges.contains(i))
//                                            userColleges.remove(i);
//                                    }
//                                }else if(position==ALL_TIER1_MBA){
//                                    for(int i=ALL_TIER1_MBA; i<COLLEGES.length; ++i) {
//                                        if (userColleges.contains(i))
//                                            userColleges.remove(i);
//                                    }
//                                }else
//                                    userColleges.remove(position);
//                            }
//                        }
//                    }
//                })
//                        .setCancelable(false)
//                        .setTitle("Colleges")
//                        .setIcon(R.drawable.ic_clear_all_white_18dp)
//                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                do something here
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                //do something here also
//                                dialogInterface.dismiss();
//                            }
//                        })
//                        .setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int which) {
//                                //do sth here
//                                for(int i=0; i<checkedColleges.length; ++i){
//                                    checkedColleges[i]=false;
//                                }
//                                if(userColleges!=null)
//                                    userColleges.clear();
//                                builder.show();
//                                //try to find a better method instead of using this .show() thing
//                            }
//                        })
//                        .show();
//            }
//        });
//
        saveButton=view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(getContext());

                saveReferralCriteriaView=getLayoutInflater().inflate(R.layout.dialog_save_referral_criteria,container,false);
                builder.setView(saveReferralCriteriaView)
                        .setTitle(R.string.save_referral_criteria)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                ((NavigationHost) getActivity()).navigateTo(new AddNewRoleFragment(), false);
                                ParseObject job=new ParseObject("Jobs");
                                job.put("referrerId", ParseUser.getCurrentUser().getUsername());
                                job.put("immediateAvailability",immediateAvailability);
                                job.put("oneMonthAvailability",oneMonthAvailability);
                                job.put("twoMonthAvailability",twoMonthAvailability);
                                job.put("threeMonthAvailability",threeMonthAvailability);
                                job.put("experience",12);
                                job.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e==null)
                                            Log.i("Job creation","Successful!!");
                                        else
                                            Log.i("Job Creation", "Failed. Error:" +e.toString());
                                    }
                                });

                                Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("Make Changes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });

        cancelButton=view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
                startActivity(intent);
            }
        });
        clearAllButton=view.findViewById(R.id.clear_all_button);
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
                startActivity(intent);
            }
        });

//        LinearLayout linearLayout=view.findViewById(R.id.add_new_role_linear_layout);
//                if (view != null) {
//                    ViewGroup parent = (ViewGroup) view.getParent();
//                    if (parent != null) {
//                        parent.removeAllViews();
//                    }
//                }

        androidx.appcompat.widget.Toolbar toolbar=view.findViewById(R.id.referral_criteria_app_bar);
//        toolbar.setNavigationIcon(R.drawable.shr_close_menu);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
//                startActivity(intent);
//            }
//        });

//        toolbar.setCollapseIcon(R.drawable.ic_clear_all_black_18dp);
        //above collapse is not working

//        toolbar.setNavigationOnClickListener();

        //for converting toolbar into contextual action bar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return view;
    }

    //this is for icons on right side of action bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.referral_criteria_app_bar, menu);
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
            case R.id.clear_all:
                ((NavigationHost) getActivity()).navigateTo(new AddNewRoleFragment(), false);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        Intent intent=new Intent(getActivity(), MyOpenRolesActivity.class);
        startActivity(intent);
    }

    //this job is done by onCreateOptionsMenu() and setSupportActionBar().
    //This was useful when "actionMode = startSupportActionMode(actionModeCallback);" was used but it doesn't work like this in case of fragment.
//    private ActionMode.Callback actionModeCallback =new ActionMode.Callback() {
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.getMenuInflater().inflate(R.menuadd_new_role_menu,menu);
//            mode.setTitle("Add new role");
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            switch(item.getItemId()){
//                case R.id.clear_all_icon:
//                    Toast.makeText(getContext(),"Clear All Selected", Toast.LENGTH_SHORT).show();
//                    mode.finish();
//                    return true;
//                default: return false;
//            }
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            actionMode=null;
//        }
//    };
}
