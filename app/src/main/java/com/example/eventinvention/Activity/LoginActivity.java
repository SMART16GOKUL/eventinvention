package com.example.eventinvention.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import me.anwarshahriar.calligrapher.Calligrapher;

public class LoginActivity extends AppCompatActivity {

    Button b1,bvendorlogin;
    String  username,password;
    TextView treg,tforgot;
    EditText  e1,e2;
    ProgressBar progressBar;
    SharedPreferences.Editor editor;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String uname = "unameKey";
    public static final String uemail = "uemailKey";
    public static final String uid = "uidKey";
    public static final String uphone = "uphoneKey";
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        Calligrapher  calligrapher = new Calligrapher(this);
//        calligrapher.setFont(this,"varelaround.ttf",true);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String name1 = sharedpreferences.getString("uemailKey",null);


//        Toast.makeText(this,name1 , Toast.LENGTH_SHORT).show();

//        Log.d("mainseesget",name1);
        assert name1 != null;
        if(name1 != null){
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
        }

        else {

        }
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);

        b1 = findViewById(R.id.b1);
        bvendorlogin = findViewById(R.id.b2);
        treg = findViewById(R.id.t5);


        tforgot = findViewById(R.id.t3);



        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userLogin();


            }
        });

        treg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(LoginActivity.this,registerActivity.class);
                startActivity(i);

            }
        });

        bvendorlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this,vendorloginActivity.class);
                startActivity(i);

            }
        });

        tforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i   =  new Intent(LoginActivity.this,forgotpasswordActivity.class);
                startActivity(i);
            }
        });

    }

    private void userLogin() {
        //first getting the values
        final String username = e1.getText().toString();
        final String password = e2.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            e1.setError("Please enter your username");
            e1.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            e2.setError("Please enter your password");
            e2.requestFocus();
            return;
        }

        //if everything is fineusername
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                    Log.d("logresp",response);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
//                                User user = new User(
//                                        userJson.getInt("id"),
//                                        userJson.getString("name"),
//                                        userJson.getString("email"),
//                                        userJson.getString("phone")
//                                );



                                String reid = userJson.getString("id");
                                String rename = userJson.getString("name");
                                String reemail = userJson.getString("email");
                                String rephone = userJson.getString("phone");
                                Log.e("loginrsstrings",reid+rename+reemail+rephone);

                                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                                editor = sharedpreferences.edit();

                                editor.putString(uname, rename);
                                editor.putString(uemail, reemail);
                                editor.putString(uid, reid);
                                editor.putString(uphone, rephone);
                                editor.commit();

                                Log.e("passinside",reemail+reid);

                                //storing the user in shared preferences
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(LoginActivity.this, HomePage.class));
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
                params.put("email", username);
                params.put("password", password);
                Log.e("requstparram",username+password);


                return params;


            }
        };
        Log.d("sessionput",username);

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
                Toast.makeText(LoginActivity.this, "2ndclick", Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        }, 2000);

    }
}
