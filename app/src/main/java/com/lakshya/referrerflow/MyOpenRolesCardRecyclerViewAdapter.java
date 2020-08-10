package com.lakshya.referrerflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOpenRolesCardRecyclerViewAdapter  extends RecyclerView.Adapter<MyOpenRolesViewHolder> {
    private List<MyOpenRolesEntry> myOpenRolesList;
    private MyRoleButtonListener myRoleButtonListener;
//    for now no image is used and it is just working as header style in card
//    private ImageRequester imageRequester;

    MyOpenRolesCardRecyclerViewAdapter(List<MyOpenRolesEntry> myOpenRolesList, MyRoleButtonListener myRoleButtonListener) {
        this.myOpenRolesList = myOpenRolesList;
        this.myRoleButtonListener=myRoleButtonListener;
//        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public MyOpenRolesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_open_roles_card, parent, false);
        return new MyOpenRolesViewHolder(layoutView,myRoleButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOpenRolesViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102
        if (myOpenRolesList != null && position < myOpenRolesList.size()) {
            MyOpenRolesEntry myOpenRolesEntry = myOpenRolesList.get(position);
            holder.jobTitle.setText(myOpenRolesEntry.jobTitle);
            holder.postDate.setText("Posted "+myOpenRolesEntry.postDate+" ago");
            holder.requestsCount.setText("Requests: "+myOpenRolesEntry.requestsCount);//number of new entry thing is to added
            holder.referredCount.setText("Referred: "+myOpenRolesEntry.referredCount);
//            imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return myOpenRolesList.size();
    }
}
