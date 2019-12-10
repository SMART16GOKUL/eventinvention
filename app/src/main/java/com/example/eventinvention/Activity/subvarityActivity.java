package com.example.eventinvention.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eventinvention.Adapter.subvarityCustomRecyclerAdapter;
import com.example.eventinvention.R;
import com.example.eventinvention.VolleySingleton;
import com.example.eventinvention.data.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class subvarityActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private subvarityCustomRecyclerAdapter mAdapter;

    RecyclerView.LayoutManager layoutManager;
    EditText autoedittext;

    String s = "";

        TextView subcaname;

        String stringsubcaname = "";
    List<subvaritydata> personUtilsList;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subvarity);
        progressBar = findViewById(R.id.progressBar);



        recyclerView =  findViewById(R.id.recyler);
        autoedittext = findViewById(R.id.auto_name);

        subcaname =  findViewById(R.id.subcaname);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        personUtilsList = new ArrayList<>();

//        String s = "Vendors";

        Intent intent = getIntent();

         s = intent.getStringExtra("subcatagoryid");
        stringsubcaname = intent.getStringExtra("subcaname");
        subcaname.setText(stringsubcaname);

        data1(s);


        autoedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

    }

    private void filter(String toString) {


        ArrayList<subvaritydata> filterdlistdata = new ArrayList<>();


        for (subvaritydata iteam : personUtilsList) {

            if (iteam.getDcity().toLowerCase().contains(toString.toLowerCase()) || iteam.getDname().toLowerCase().contains(toString.toLowerCase()) ||iteam.getDusertype().toLowerCase().contains(toString.toLowerCase()) || iteam.getDcatnam().toLowerCase().contains(toString.toLowerCase() )){



                filterdlistdata.add(iteam);

            }


        }

        mAdapter.filterlist(filterdlistdata);

    }



    private void data1(final String stcity) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_SUBCATAGORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e("city1name",response);



                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("cityres",response);
                            JSONArray jsonArray=jsonObject.getJSONArray("message");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                subvaritydata dataman = new subvaritydata();
                                String   jcity=jsonObject1.getString("city");
                                String   jname=jsonObject1.getString("name");
                                String   jid=jsonObject1.getString("id");
                                String   jusertype=jsonObject1.getString("usertype");

                                Log.e("eventstrname",jcity);


//                                dataman.dname= jsonObject1.getString("name");
//                                dataman.dcity= jsonObject1.getString("city");
//                                dataman.dusertype= jsonObject1.getString("usertype");

                                dataman.setDname(jsonObject1.getString("name"));
                                dataman.setDcity(jsonObject1.getString("city"));
                                dataman.setDusertype(jsonObject1.getString("company_name"));
                                dataman.setDcatnam(jsonObject1.getString("cat_names"));
                                dataman.setDrating(jsonObject1.getInt("rating_count"));
                                dataman.setDid(jsonObject1.getInt("id"));

//
                                personUtilsList.add(dataman);

                            }
//                            spinnnercity.setAdapter(new ArrayAdapter<String>(register.this, android.R.layout.simple_spinner_dropdown_item, CountryName2));

                            mAdapter = new subvarityCustomRecyclerAdapter(subvarityActivity.this, personUtilsList);
                            recyclerView.setAdapter(mAdapter);
                            recyclerView.setHasFixedSize(true);


                        }catch (JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cat_id", stcity);
                Log.e("requstid",stcity);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}

