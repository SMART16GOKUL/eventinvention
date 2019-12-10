package com.example.eventinvention.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventinvention.Activity.subvarityActivity;
import com.example.eventinvention.R;

import java.util.ArrayList;
import java.util.List;

public class selectsubcatogaryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.eventinvention.Adapter.selectsubcatogaryAdapter.ViewHolder> {

    private Context context;
    private List<subcatagorydata> personUtils;

    public selectsubcatogaryAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public com.example.eventinvention.Adapter.selectsubcatogaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectcatagory_list, parent, false);
        com.example.eventinvention.Adapter.selectsubcatogaryAdapter.ViewHolder viewHolder = new com.example.eventinvention.Adapter.selectsubcatogaryAdapter.ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(com.example.eventinvention.Adapter.selectsubcatogaryAdapter.ViewHolder holder, int position) {

        final int pos = position;
        holder.itemView.setTag(personUtils.get(position));

        final subcatagorydata pu = personUtils.get(position);

        holder.pname.setText(pu.getDname());



        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subcatagoryy2 = "";
                String fullsubcaname = "";
                String subcaname = ""  ;

                int id = 0;
                id=personUtils.get(pos).getDid();
                Intent intent = ((Activity) context).getIntent();
                subcatagoryy2 = intent.getStringExtra("DISPLAYTEXT");
                subcaname = personUtils.get(pos).getDname();

                String st  = "";

                st = String.valueOf(id);

                fullsubcaname = subcatagoryy2+"-"+subcaname;

//                Toast.makeText(context, st, Toast.LENGTH_LONG).show();
                Log.e(" getid", st);

//                Toast.makeText(context, "Data"+fullsubcaname, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, subvarityActivity.class);
                i.putExtra("subcatagoryid", st);
                i.putExtra("subcaname", fullsubcaname);
                i.putExtra("imagetext", subcatagoryy2);
                context.startActivity(i);

//                Toast.makeText(context, subcatagoryy2, Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public void filterlist(ArrayList<subcatagorydata> filterdlistdata) {

        personUtils  = filterdlistdata;
        notifyDataSetChanged();


    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        public TextView pname;
        public RelativeLayout rela;



        public ViewHolder(View itemView) {
            super(itemView);

            pname = (TextView) itemView.findViewById(R.id.t1);
            rela =  itemView.findViewById(R.id.rel);

        }
    }

}

