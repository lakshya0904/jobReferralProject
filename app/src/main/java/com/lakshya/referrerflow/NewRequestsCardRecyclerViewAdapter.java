package com.lakshya.referrerflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewRequestsCardRecyclerViewAdapter extends RecyclerView.Adapter<NewRequestsViewHolder> {
    private List<NewRequestsEntry> newRequestsEntryList;
    private CardButtonListener cardButtonListener;

    NewRequestsCardRecyclerViewAdapter(List<NewRequestsEntry> newRequestsEntryList,CardButtonListener cardButtonListener){
        this.newRequestsEntryList=newRequestsEntryList;
        this.cardButtonListener=cardButtonListener;
    }

    @NonNull
    @Override
    public NewRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_new_requests,parent,false);
        return new NewRequestsViewHolder(layoutView,cardButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewRequestsViewHolder holder, int position) {
        if(newRequestsEntryList!=null && position<newRequestsEntryList.size()){
            NewRequestsEntry newRequestsEntry=newRequestsEntryList.get(position);
            holder.name.setText(newRequestsEntry.name);
            holder.locationExperience.setText(newRequestsEntry.location+", "+newRequestsEntry.experience);
            holder.currentRole.setText(newRequestsEntry.currentRole);
            holder.qualificationCollege.setText(newRequestsEntry.qualification+", "+newRequestsEntry.college);
        }
    }

    @Override
    public int getItemCount() {
        return newRequestsEntryList.size();
    }
}
