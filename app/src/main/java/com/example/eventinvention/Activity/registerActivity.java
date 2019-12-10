package com.example.eventinvention.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class registerActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword,editTextphone,editTextstreetname,
            editTextlandmark,editTextpincode;
    String jid,jid2;
    TextView loginpage;
    ArrayList<String> CountryName;
    ArrayList<String> CountryName2;
    ArrayList<String> Countryid;
    ArrayList<String> Countryid2;
    Spinner spinnertstate,spinnnercity;
    ProgressBar progressBar;
    Button  b1,bvendorregistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = findViewById(R.id.progressBar);

        CountryName=new ArrayList<>();
        CountryName2=new ArrayList<>();
        Countryid =new ArrayList<>();
        Countryid2 =new ArrayList<>();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextphone = findViewById(R.id.editTextphone);
        editTextstreetname = findViewById(R.id.editTextstreetname);
        editTextlandmark = findViewById(R.id.editTextlandmark);
        spinnertstate = findViewById(R.id.editTextstate);
        spinnnercity = findViewById(R.id.editTextcity);
        editTextpincode = findViewById(R.id.editTextpincode);


           statespinner1();

        b1 = findViewById(R.id.buttonRegister);
        loginpage = findViewById(R.id.textViewLogin);
        bvendorregistration = findViewById(R.id.vendorsRegister);


        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i =  new Intent(registerActivity.this,LoginActivity.class);
                startActivity(i);


            }
        });

        bvendorregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(registerActivity.this,vendorregistrationActivity.class);
                startActivity(i);
            }
        });

        spinnertstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country=   spinnertstate.getItemAtPosition(spinnertstate.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();

                String stateName=CountryName.get(i).toString();
//                resetCity(stateName);
                Log.d("stateName",stateName);

                String testString = Integer.toString(i);
                Toast.makeText(registerActivity.this,testString , Toast.LENGTH_SHORT).show();
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


        spinnnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country=   spinnnercity.getItemAtPosition(spinnnercity.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();


                String testString = Integer.toString(i);
                Toast.makeText(registerActivity.this,testString , Toast.LENGTH_SHORT).show();
                jid2 = Countryid2.get(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registerUser(jid,jid2);


            }
        });


    }



    private void statespinner1() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLs.URL_STATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
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
                    spinnertstate.setAdapter(new ArrayAdapter<String>(registerActivity.this, android.R.layout.simple_spinner_dropdown_item, CountryName));
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
        //first getting the values


        //validating inputs

        //if everything is fineusername
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_CITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);


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
                            spinnnercity.setAdapter(new ArrayAdapter<String>(registerActivity.this, android.R.layout.simple_spinner_dropdown_item, CountryName2));

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



    private void registerUser(final String jid, final String jid2) {
            final String username = editTextUsername.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
            final String phone = editTextphone.getText().toString().trim();
            final String streetname = editTextstreetname.getText().toString().trim();
            final String landmark = editTextlandmark.getText().toString().trim();
//
            final String pincode = editTextpincode.getText().toString().trim();

            //first we will do the validations
            if (TextUtils.isEmpty(username)) {
                editTextUsername.setError("Please enter username");
                editTextUsername.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                editTextEmail.setError("Please enter your email");
                editTextEmail.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Enter a valid email");
                editTextEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Enter a password");
                editTextPassword.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                editTextphone.setError("Enter a password");
                editTextphone.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(streetname)) {
                editTextstreetname.setError("Enter a password");
                editTextstreetname.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(landmark)) {
                editTextlandmark.setError("Enter a password");
                editTextlandmark.requestFocus();
                return;
            }
//

            if (TextUtils.isEmpty(pincode)) {
                editTextpincode.setError("Enter a password");
                editTextpincode.requestFocus();
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            try {
                                //converting response to json object
                                JSONObject obj = new JSONObject(response);
                                //if no error in response
                                if (!obj.getBoolean("error")) {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


                                    editTextUsername.setText("");
                                    editTextEmail.setText("");
                                    editTextPassword.setText("");
                                    editTextphone.setText("");
                                    editTextstreetname.setText("");
                                    editTextlandmark.setText("");



                                    editTextpincode.setText("");

                                    finish();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));


                                } else {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"parram2"+ error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", username);
                    params.put("email", email);
                    params.put("password", password);
                    params.put("phone", phone);
                    params.put("streetname", streetname);
                    params.put("landmark", landmark);
                    params.put("state", jid);
                    params.put("city", jid2);
                    params.put("pincode", pincode);


                    Log.e("passingparam",jid+ jid2);
                    editTextUsername.setText("");
                    editTextEmail.setText("");
                    editTextPassword.setText("");
                    editTextphone.setText("");
                    editTextstreetname.setText("");
                    editTextlandmark.setText("");
//                    editTextstate.setText("");
//                    editTextcity.setText("");
                    editTextpincode.setText("");
                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
