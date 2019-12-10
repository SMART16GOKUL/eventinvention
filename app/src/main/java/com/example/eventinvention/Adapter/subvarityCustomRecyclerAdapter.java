package com.example.eventinvention.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.eventinvention.Activity.subvaritydata;
import com.example.eventinvention.R;
import com.example.eventinvention.profilepage.subgendralprofilepageActivity;
import java.util.ArrayList;
import java.util.List;

public class subvarityCustomRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.eventinvention.Adapter.subvarityCustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<subvaritydata> personUtils;

    public subvarityCustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public com.example.eventinvention.Adapter.subvarityCustomRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subvarity_list, parent, false);
        com.example.eventinvention.Adapter.subvarityCustomRecyclerAdapter.ViewHolder viewHolder = new com.example.eventinvention.Adapter.subvarityCustomRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(com.example.eventinvention.Adapter.subvarityCustomRecyclerAdapter.ViewHolder holder, int position) {

        final int pos = position;
        holder.itemView.setTag(personUtils.get(position));

        subvaritydata pu = personUtils.get(position);

        holder.pname.setText(pu.getDname());
        holder.pcity.setText(pu.getDcity());
        holder.pcatname.setText(pu.getDcatnam());
        holder.pusertype.setText(pu.getDusertype());
        holder.pcity.setText(pu.getDcity());

//        holder.prating.setNumStars(pu.getDrating());
        holder.prating.setRating(pu.getDrating());
       String imagetext = "";
        Intent intent = ((Activity) context).getIntent();
        imagetext = intent.getStringExtra("imagetext");

//        Toast.makeText(context, " Passing  Intent "  + imagetext, Toast.LENGTH_SHORT).show();

        assert imagetext != null;
        Log.d("INTENTIMAGE",imagetext);

        if (imagetext.equals("Vendor List")){

            holder.intentimage.setBackgroundResource(R.drawable.eventvendor);


        }
        if (imagetext.equals("Artist List")){

            holder.intentimage.setBackgroundResource(R.drawable.eventartist);


        }
        if (imagetext.equals("Venue List")){

            holder.intentimage.setBackgroundResource(R.drawable.eventvenue);


        }

        else {


        }





        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int id = 0;
                id=personUtils.get(pos).getDid();

                String st  = "";

                st = String.valueOf(id);


//                Toast.makeText(context, st, Toast.LENGTH_LONG).show();
                Log.e(" getid", st);

                Intent i = new Intent(context, subgendralprofilepageActivity.class);
                i.putExtra("mess", st);
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public void filterlist(ArrayList<subvaritydata> filterdlistdata) {

        personUtils  = filterdlistdata;
        notifyDataSetChanged();

    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        public TextView pname;
        public TextView pcity;
        public TextView pcatname;
        public TextView pusertype;
        public RatingBar prating;
        public RelativeLayout rela;
        public ImageView  intentimage;



        public ViewHolder(View itemView) {
            super(itemView);

            pname = (TextView) itemView.findViewById(R.id.tname);
            pcity = (TextView) itemView.findViewById(R.id.tcity);
            pusertype = (TextView) itemView.findViewById(R.id.tusertype);
            pcatname = (TextView) itemView.findViewById(R.id.tcatname);
            prating = (RatingBar) itemView.findViewById(R.id.rratingBar);

            rela =  itemView.findViewById(R.id.rel);

            intentimage =itemView.findViewById(R.id.intentimage);

        }
    }

}

