package com.example.eventinvention.profilepage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eventinvention.R;

import java.util.List;

public class commentRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<commentRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<commentdata> personUtils;

    public commentRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final int pos = position;
        holder.itemView.setTag(personUtils.get(position));

        final commentdata pu = personUtils.get(position);

        holder.pname.setText(pu.getComment());
        holder.crating.setRating(pu.getCrating());


    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        public TextView pname;
        public RatingBar crating;



        public ViewHolder(View itemView) {
            super(itemView);

            pname = (TextView) itemView.findViewById(R.id.tcomment);
            crating =  itemView.findViewById(R.id.rating2);


        }
    }

}
