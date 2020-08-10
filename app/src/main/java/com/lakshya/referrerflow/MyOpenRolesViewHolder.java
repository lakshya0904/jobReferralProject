package com.lakshya.referrerflow;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

public class MyOpenRolesViewHolder  extends RecyclerView.ViewHolder  {
     public NetworkImageView productImage;
    public TextView jobTitle;
    public TextView postDate;
    public TextView referredCount;
    public TextView requestsCount;
    public Button viewRequestsButton;
    MyRoleButtonListener myRoleButtonListener;
    //this one can be understood like myRoleButtonListener inside each View Holder

   public MyOpenRolesViewHolder(@NonNull View itemView, final MyRoleButtonListener myRoleButtonListener) {
       //how does the viewHolder know what is myRoleButtonListener?
       // ans-> declare it inside adapter

       super(itemView);
//       productImage = itemView.findViewById(R.id.product_image);
       jobTitle=itemView.findViewById(R.id.job_title);
       postDate=itemView.findViewById(R.id.post_date);
       requestsCount=itemView.findViewById(R.id.requests_count);
       referredCount=itemView.findViewById(R.id.referred_count);
       viewRequestsButton=itemView.findViewById(R.id.view_requests_button);
       this.myRoleButtonListener=myRoleButtonListener;
       viewRequestsButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                myRoleButtonListener.OnViewRequestClickListener(getAdapterPosition());
           }
       });
   }
}
