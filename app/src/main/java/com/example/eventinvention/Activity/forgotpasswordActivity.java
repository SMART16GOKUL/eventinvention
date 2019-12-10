package com.example.eventinvention.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eventinvention.R;
import com.example.eventinvention.VolleySingleton;
import com.example.eventinvention.data.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class forgotpasswordActivity extends AppCompatActivity {


    EditText eforgot;
    Button  bforgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        eforgot =  findViewById(R.id.eforgot);
        bforgot = findViewById(R.id.bforgotbutton);



        bforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uforgotpassword();

            }
        });


    }

    private void uforgotpassword() {
        //first getting the values
        final String forgotpassword = eforgot.getText().toString().trim();

        //validating inputs

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(forgotpassword).matches()) {
            eforgot.setError("Enter a valid email");
            eforgot.requestFocus();
            return;
        }

        //if everything is fineusername
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_FORGOTPASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("logresp",response);

                        try {
                            //converting response to json object

                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {


                                Toast.makeText(forgotpasswordActivity.this, " SUCESS CHECK  YOU  MAIL", Toast.LENGTH_LONG).show();
                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");



                                finish();
                                startActivity(new Intent(forgotpasswordActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("exeption", e.toString());
//                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", forgotpassword);
                Log.e("requstparram",forgotpassword);


                return params;


            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onBackPressed() {

        Intent i  = new Intent(forgotpasswordActivity.this,LoginActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
