package com.example.eventinvention.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.eventinvention.R;
import com.example.eventinvention.profilepage.managerprofilepageActivity;

import java.util.ArrayList;
import java.util.List;

public class managerCustomRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<managerCustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Dataman1> personUtils;

    public managerCustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.emanagerdata_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final int pos = position;
        holder.itemView.setTag(personUtils.get(position));

        final Dataman1 pu = personUtils.get(position);

        holder.pname.setText(pu.getDname());
        holder.pcity.setText(pu.getDcity());
        holder.pusertype.setText(pu.getDusertype());
//        holder.prating.setNumStars(pu.getDrating());
        holder.prating.setRating(pu.getDrating());



        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int id = 0;
                 id=personUtils.get(pos).getDid();

                String st  = "";

                 st = String.valueOf(id);


//                Toast.makeText(context, st, Toast.LENGTH_LONG).show();
                Log.e(" getid", st);

                Intent i = new Intent(context, managerprofilepageActivity.class);
                i.putExtra("mess", st);
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public void filterlist(ArrayList<Dataman1> filterdlistdata) {

        personUtils  = filterdlistdata;
        notifyDataSetChanged();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        public TextView pname;
        public TextView pcity;
        public TextView pusertype;
        public RatingBar prating;
        public RelativeLayout rela;



        public ViewHolder(View itemView) {
            super(itemView);

            pname = (TextView) itemView.findViewById(R.id.tname);
            pcity = (TextView) itemView.findViewById(R.id.tcity);
            pusertype = (TextView) itemView.findViewById(R.id.tusertype);
            prating = (RatingBar) itemView.findViewById(R.id.rratingBar);

            rela =  itemView.findViewById(R.id.rel);

        }
    }

}
