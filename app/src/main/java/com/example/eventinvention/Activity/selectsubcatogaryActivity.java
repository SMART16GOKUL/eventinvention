package com.example.eventinvention.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eventinvention.Adapter.selectsubcatogaryAdapter;
import com.example.eventinvention.Adapter.subcatagorydata;
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

public class selectsubcatogaryActivity extends AppCompatActivity {


    androidx.recyclerview.widget.RecyclerView recyclerView;
    private selectsubcatogaryAdapter mAdapter;

    RecyclerView.LayoutManager layoutManager;
    String subcatagoryy = "";
    TextView theading;
    List<subcatagorydata> personUtilsList;
    EditText autoedittext;
    ProgressBar progressBar;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectsubcatogary);

        autoedittext = findViewById(R.id.auto_name);

        recyclerView = findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        personUtilsList = new ArrayList<>();



        Intent intent = getIntent();

        subcatagoryy = intent.getStringExtra("Sub_Catagoryy");

        String displayheading = intent.getStringExtra("DISPLAYTEXT");


        theading = findViewById(R.id.theading);

        theading.setText(displayheading);

//        Toast.makeText(this, "  Reseved  Value is : This "+subcatagoryy, Toast.LENGTH_SHORT).show();

        data1(subcatagoryy);


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


        ArrayList<subcatagorydata> filterdlistdata = new ArrayList<>();


        for (subcatagorydata iteam : personUtilsList) {

            if (iteam.getDname().toLowerCase().contains(toString.toLowerCase())) {


                filterdlistdata.add(iteam);

            }

        }

        mAdapter.filterlist(filterdlistdata);

    }

    private void data1(final String stcity) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.ROOT_URL1 + stcity,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("city1name", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("cityres", response);
                            JSONArray jsonArray = jsonObject.getJSONArray("events");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                subcatagorydata dataman = new subcatagorydata();
//                                    String   jcity=jsonObject1.getString("city");
                                String jname = jsonObject1.getString("cat_name");
                                String jid = jsonObject1.getString("id");
//                                    String   jusertype=jsonObject1.getString("usertype");

//                                    Log.e("eventstrname",jcity);


                                dataman.setDid(jsonObject1.getInt("id"));
                                dataman.setDname(jsonObject1.getString("cat_name"));


//
                                personUtilsList.add(dataman);

                            }

                            mAdapter = new selectsubcatogaryAdapter(selectsubcatogaryActivity.this, personUtilsList);
                            recyclerView.setAdapter(mAdapter);
                            recyclerView.setHasFixedSize(true);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
//                    params.put("usertype", stcity);
//                    Log.e("requstid",stcity);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}