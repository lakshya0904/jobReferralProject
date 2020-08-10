package com.lakshya.referrerflow;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class NewRequestsViewHolder extends RecyclerView.ViewHolder{

    TextView name;
    TextView locationExperience;
    TextView currentRole;
    TextView qualificationCollege;
    Button rejectButton;
    Button acceptButton;
    CardButtonListener cardButtonListener;
    public NewRequestsViewHolder(@NonNull View itemView, final CardButtonListener cardButtonListener) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        locationExperience=itemView.findViewById(R.id.location_experience);
        currentRole=itemView.findViewById(R.id.current_role);
        qualificationCollege=itemView.findViewById(R.id.qualification_college);
        rejectButton=itemView.findViewById(R.id.reject_button);
        this.cardButtonListener=cardButtonListener;
        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardButtonListener.OnCardButtonClickListener(getAdapterPosition());
            }
        });
        acceptButton=itemView.findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardButtonListener.OnAcceptButtonClickListener(getAdapterPosition());
            }
        });
    }

}
