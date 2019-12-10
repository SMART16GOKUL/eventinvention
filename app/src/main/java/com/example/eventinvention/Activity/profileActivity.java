package com.example.eventinvention.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eventinvention.R;
import com.example.eventinvention.VolleySingleton;
import com.example.eventinvention.data.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class profileActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    SharedPreferences.Editor editor;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String uname = "unameKey";
    public static final String uphone = "uphoneKey";
    EditText tname,tPassword,tPhone,tStreet,tLandMark,tPincode,talternatenumber;
    String jid,jid2;
    Spinner  tState,tCity;
    ArrayList<String> CountryName;
    ArrayList<String> CountryName2;
    ArrayList<String> Countryid;
    ArrayList<String> Countryid2;
    Button bback,bupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        CountryName=new ArrayList<>();
        CountryName2=new ArrayList<>();
        Countryid =new ArrayList<>();
        Countryid2 =new ArrayList<>();


        tname =  findViewById(R.id.tt1);
        tPassword =  findViewById(R.id.tt2);
        tState =  findViewById(R.id.tt3);
        tCity =  findViewById(R.id.tt4);
        tPhone =  findViewById(R.id.tt5);
        tStreet =  findViewById(R.id.tt6);
        tLandMark =  findViewById(R.id.tt7);
        tPincode =  findViewById(R.id.tt8);
        talternatenumber =  findViewById(R.id.tt9);

        bback =  findViewById(R.id.b1);
        bupdate =  findViewById(R.id.b2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String id = sharedpreferences.getString("uidKey",null);
        statespinner1();
        autodetails(id);

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(profileActivity.this,HomePage.class);
                startActivity(i);

            }
        });

        tState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country=   tState.getItemAtPosition(tState.getSelectedItemPosition()).toString();
//                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();

                String stateName=CountryName.get(i).toString();
//                resetCity(stateName);
                Log.d("stateName",stateName);

                String testString = Integer.toString(i);
//                Toast.makeText(profileActivity.this,testString , Toast.LENGTH_SHORT).show();
                jid = Countryid.get(i);
                Log.e("hjfhbjgf",jid);

                CountryName2.clear();
                Countryid2.clear();
                statespinner2( jid );

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        tCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country=   tCity.getItemAtPosition(tCity.getSelectedItemPosition()).toString();
//                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();


                String testString = Integer.toString(i);
//                Toast.makeText(profileActivity.this,testString , Toast.LENGTH_SHORT).show();
                jid2 = Countryid2.get(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updatedetails(jid,jid2);
                Log.d("registerUser1",jid+jid2);

            }
        });


    }




    private void statespinner1() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.URL_STATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);

                    Log.d("eventresponce",response);
                    JSONArray jsonArray=jsonObject.getJSONArray("events");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String   jstate=jsonObject1.getString("name");
                        String   jjid=jsonObject1.getString("id");

                        Log.e("eventstrname",jstate);



                        CountryName.add(jstate);
                        Countryid.add(jjid);


                    }
                    tState.setAdapter(new ArrayAdapter<String>(profileActivity.this, android.R.layout.simple_spinner_dropdown_item, CountryName));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void statespinner2(final String stcity) {

        //if everything is fineusername
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_CITY,
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
                                String   jcity=jsonObject1.getString("city");
                                String   jid=jsonObject1.getString("id");

                                Log.e("eventstrname",jcity);


//
                                CountryName2.add(jcity);
                                Countryid2.add(jid);


                            }
                            tCity.setAdapter(new ArrayAdapter<String>(profileActivity.this, android.R.layout.simple_spinner_dropdown_item, CountryName2));

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
                params.put("state_id", stcity);
                Log.e("requstid",stcity);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }




    private void autodetails(final String stcity) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_VIEWPROFILE,
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
                                String   jid=jsonObject1.getString("id");
                                String   jcity=jsonObject1.getString("city");
                                String   jname=jsonObject1.getString("name");
                                String   jpassword=jsonObject1.getString("password");
                                String   jphone=jsonObject1.getString("phone");
                                String   jstreet=jsonObject1.getString("streetname");
                                String   jlandmark=jsonObject1.getString("landmark");
                                String   jpincode=jsonObject1.getString("pincode");
                                String   jalternatenumber=jsonObject1.getString("alt_phone");


                                Log.e("eventstrname",jcity);

                                tname.setText(jname);
                                tPassword.setText(jpassword);
                                tPhone.setText(jphone);
                                tStreet.setText(jstreet);
                                tLandMark.setText(jlandmark);
                                tPincode.setText(jpincode);
                                talternatenumber.setText(jalternatenumber);

                                TextView textheading = findViewById(R.id.textheading);
                                textheading.setText(jname);

                            }

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
                params.put("id", stcity);
                Log.e("requstid",stcity);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void updatedetails( final String jid, final String jid2) {


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String id = sharedpreferences.getString("uidKey",null);

        final String username = tname.getText().toString().trim();
        final String password = tPassword.getText().toString().trim();
        final String phone = tPhone.getText().toString().trim();
        final String streetname = tStreet.getText().toString().trim();
        final String landmark = tLandMark.getText().toString().trim();
        final String pincode = tPincode.getText().toString().trim();
        final String alternatenumber = talternatenumber.getText().toString().trim();


//

        //first we will do the validations
        if (TextUtils.isEmpty(username)) {
            tname.setError("Please enter username");
            tname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            tPassword.setError("Please enter your email");
            tPassword.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(phone)) {
            tPhone.setError("Enter a password");
            tPhone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(streetname)) {
            tStreet.setError("Enter a password");
            tStreet.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(landmark)) {
            tLandMark.setError("Enter a password");
            tLandMark.requestFocus();
            return;
        }
//

        if (TextUtils.isEmpty(pincode)) {
            tPincode.setError("Enter a password");
            tPincode.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(alternatenumber)) {
            talternatenumber.setError("Enter  Alternate Number");
            talternatenumber.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_UPDATEUSERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(),"parram1"+ obj.getString("message"), Toast.LENGTH_SHORT).show();



                                finish();
                                startActivity(new Intent(getApplicationContext(), profileActivity.class));
                                //getting the user from the response



                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"parram2"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", username);
                params.put("password", password);
                params.put("phone", phone);
                params.put("streetname", streetname);
                params.put("landmark", landmark);
                params.put("state", jid);
                params.put("city", jid2);
                params.put("pincode", pincode);
                params.put("alt_phone", alternatenumber);



                Log.e("passingparam",id+username+ password+phone+streetname+landmark+jid+jid2+pincode);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



}
