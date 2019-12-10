package com.example.eventinvention.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eventinvention.Activity.elaboratedprofile.elaborated;
import com.example.eventinvention.R;
import com.example.eventinvention.VolleySingleton;
import com.example.eventinvention.data.URLs;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.eventinvention.Activity.LoginActivity.MyPREFERENCES;

public class HomePage extends AppCompatActivity {

    SharedPreferences sharedpreferences;

   ImageView imgg1,imgg2,imgg3;
    TextView t1;
    String sviewvendors  = "viewvendors";
    String sviewartist  = "viewartist";
    String  sviewvenue   = "viewvenue";
    String svendors  = "Vendor List";
    String sartist  = "Artist List";
    String  svenue   = "Venue List";
    androidx.cardview.widget.CardView c1,c2,c3,c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        


        c1 = findViewById(R.id.card1);
        c2 = findViewById(R.id.card2);
        c3 = findViewById(R.id.card3);
        c4 = findViewById(R.id.card4);

        t1 = findViewById(R.id.t2);


        imgg1 = findViewById(R.id.addimg1);
        imgg2 = findViewById(R.id.addimg2);
        imgg3 = findViewById(R.id.addimg3);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        View view =getSupportActionBar().getCustomView();

        TextView logout= (TextView)view.findViewById(R.id.logout);
        ImageView profile= view.findViewById(R.id.img);

              logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

//                sharedpreferences = (SharedPreferences) getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
//                editor.putString("MyPrefs", "0");editor.apply();

                Intent i = new Intent(HomePage.this,LoginActivity.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this, elaborated.class);
                startActivity(i);
            }
        });



        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this,eventmanagerActivity.class);
                startActivity(i);

            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this,selectsubcatogaryActivity.class);
                i.putExtra("Sub_Catagoryy", sviewvendors);
                i.putExtra("DISPLAYTEXT", svendors);
                startActivity(i);

            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this,selectsubcatogaryActivity.class);
                i.putExtra("Sub_Catagoryy", sviewartist);
                i.putExtra("DISPLAYTEXT", sartist);
                startActivity(i);

            }
        });


        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this,selectsubcatogaryActivity.class);
                i.putExtra("Sub_Catagoryy", sviewvenue);
                i.putExtra("DISPLAYTEXT", svenue);
                startActivity(i);

            }
        });

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String name1 = sharedpreferences.getString("unameKey",null);
        String name2 = sharedpreferences.getString( "upassKey",null);

// set reference to the text view
// set the string from sp as text of the textview

        Log.d("sessionget",name1+name2);


        newsaddimagedata();

    }



    private void newsaddimagedata() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_VIEWNEWSADDS ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("city1name", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("events");

                            Log.d("cityres", String.valueOf(jsonArray));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                    String   jcity=jsonObject1.getString("city");
                                String jheadingname = jsonObject1.getString("title");
//                                String jid = jsonObject1.getString("id");
                                String   jaddimg1 = jsonObject1.getString("img1");
                                String   jaddimg2 = jsonObject1.getString("img2");
                                String   jaddimg3 = jsonObject1.getString("img3");

                                    Log.e("viewaddimage",jaddimg1+jheadingname);


                                    t1.setText(jheadingname);

                                if (jaddimg1.equals("0")){

//                                    img.setBackgroundResource(R.drawable.nophotoimage);

                                }

                                else {

                               String     photo_url_str = "http://eventinvention.in/"+jaddimg1;

                                    Picasso.get().load(photo_url_str).into(imgg1);


                                }


                                if (jaddimg2.equals("0")){
//                                    img.setBackgroundResource(R.drawable.nophotoimage);

                                }

                                else {

                                    String     photo_url_str = "http://eventinvention.in/"+jaddimg2;

                                    Picasso.get().load(photo_url_str).into(imgg2);


                                }

                                if (jaddimg3.equals("0")){

//                                    img.setBackgroundResource(R.drawable.nophotoimage);

                                }

                                else {

                                    String     photo_url_str = "http://eventinvention.in/"+jaddimg3;
//
                                    Picasso.get().load(photo_url_str).into(imgg3);


//                                    imgg3.setImageURI(Uri.fromFile(new File(photo_url_str)));

                                }


//                                imgg3.setBackgroundResource(R.drawable.imresizer);

                            }


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

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
