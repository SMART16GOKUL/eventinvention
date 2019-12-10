package com.example.eventinvention.profilepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eventinvention.R;
import com.example.eventinvention.VolleySingleton;
import com.example.eventinvention.data.URLs;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.eventinvention.Activity.LoginActivity.MyPREFERENCES;

public class managerprofilepageActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    private commentRecyclerAdapter mAdapter;

    RecyclerView.LayoutManager layoutManager;

    List<commentdata> personUtilsList;
    ProgressBar progressBar;


    SharedPreferences sharedpreferences;

    TextView tname,tusertype,tcity,tstate,tdesc,tvedio,tcname,tcpname;
    ImageView  img,imggal1,imggal2,imggal3;
    EditText eincomment;
    String photo_url_str;
    Button sumbit;
    RatingBar trating,inrating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageruserspage);


        img =  findViewById(R.id.img);

        tname =  findViewById(R.id.tt1);
        tusertype =  findViewById(R.id.tt2);
        tcity =  findViewById(R.id.tt3);
        tstate =  findViewById(R.id.tt4);
        trating =  findViewById(R.id.tt5);
        tdesc =  findViewById(R.id.tt6);
        tvedio =  findViewById(R.id.tt7);

        tcname =  findViewById(R.id.ttcname);
        tcpname =  findViewById(R.id.ttcpname);


        inrating =  findViewById(R.id.rr1);
        eincomment =  findViewById(R.id.rr2);
        sumbit =  findViewById(R.id.button1);

        imggal1 =  findViewById(R.id.gal1);
        imggal2 =  findViewById(R.id.gal2);
        imggal3 =  findViewById(R.id.gal3);


        String managerprofileis = "";

        Intent intent = getIntent();
         managerprofileis = intent.getStringExtra("mess");

//        Toast.makeText(managerprofilepageActivity.this, "  VALUE"+managerprofileis, Toast.LENGTH_SHORT).show();

        assert managerprofileis != null;
        Log.d("resevedmanager",managerprofileis);

        reseveddata(managerprofileis);


        comment(managerprofileis);

        progressBar = findViewById(R.id.progressBar);

        recyclerView =  findViewById(R.id.recyler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        personUtilsList = new ArrayList<>();

    }



    private void reseveddata(final String reseved) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_MANGERPROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e("manageresponce1",response);



                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("manageresponce2",response);
                            JSONArray jsonArray=jsonObject.getJSONArray("message");
                            for(int i=0;i<jsonArray.length();i++){
                                final JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String   jcity=jsonObject1.getString("city");
                                String   jname=jsonObject1.getString("name");
                                final String   jid=jsonObject1.getString("id");
                                String   jusertype=jsonObject1.getString("usertype");
                                String   jstate=jsonObject1.getString("state");
                                String   jdescription=jsonObject1.getString("description");
                                String   jcname=jsonObject1.getString("company_name");
                                String   jcpname=jsonObject1.getString("contactperson_name");
                                final String   jvideo=jsonObject1.getString("video");
                                int jrating = jsonObject1.getInt("rating_count");
                                String   jphoto=jsonObject1.getString("photo");
                                final String   jgalphoto1 =jsonObject1.getString("photo1");
                                final String   jgalphoto2 =jsonObject1.getString("photo2");
                                final String   jgalphoto3 =jsonObject1.getString("photo3");





                                sumbit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {



                                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                                        String loginid = sharedpreferences.getString("uidKey",null);

                                        float a = inrating.getRating();
                                         int intrating = (int)Math.round(a);
                                        String comment = eincomment.getText().toString();


                                         if (intrating != 0){
                                             String inrating1 = String.valueOf(intrating);


                                             if (!inrating1.equals("") && !comment.equals("")){


                                                 submitvalues(jid,loginid,comment,inrating1);

                                                 assert loginid != null;
                                                 Log.d("allingnvalues",jid+loginid+comment+inrating1);



                                             }

                                             else {

                                                 if (inrating1.equals("")){

                                                     Toast.makeText(managerprofilepageActivity.this, "Rating  Bar is Empty!", Toast.LENGTH_SHORT).show();
                                                 }

                                                 if (comment.equals("")){

                                                     Toast.makeText(managerprofilepageActivity.this, " Comment  Feild  is  empty!", Toast.LENGTH_SHORT).show();

                                                 }

                                             }

                                         }


                                         else {


                                             Toast.makeText(managerprofilepageActivity.this, " Submit   Faild Give  Rating  to Submit", Toast.LENGTH_SHORT).show();

                                         }








                                    }
                                });


                                if (jvideo.equals("0")){

//                                    img.setBackgroundResource(R.drawable.nophotoimage);


//                                    tvedio.setVisibility(View.GONE);

                                }

                                else {

//                                    String ve = "Click  this  to  View  Vedio  : "+jvideo;

                                    tvedio.setText(jvideo);

                                    tvedio.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jvideo));
                                            intent.putExtra("VIDEO_ID", jvideo);
                                            startActivity(intent);

                                        }
                                    });

                                }




                                if (jphoto.equals("0")){

                                    img.setBackgroundResource(R.drawable.nophotoimage);


                                }

                                else {

                                    photo_url_str = "http://eventinvention.in/upload/"+jphoto;

                                    Picasso.get().load(photo_url_str).into(img);


                                }

                                if (jgalphoto1.equals("0")){

                                }

                                else {
                                    photo_url_str = "http://eventinvention.in/upload/1/"+jgalphoto1;
                                    Picasso.get().load(photo_url_str).into(imggal1);

                                    imggal1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            imggal1clickimage1(jgalphoto1);


                                        }
                                    });
                                }

                                if (jgalphoto2.equals("0")){

                                }

                                else {
                                    photo_url_str = "http://eventinvention.in/upload/2/"+jgalphoto2;
                                    Picasso.get().load(photo_url_str).into(imggal2);

                                    imggal2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            imggal1clickimage2(jgalphoto2);


                                        }
                                    });

                                }

                                if (jgalphoto3.equals("0")){

                                }

                                else {
                                    photo_url_str = "http://eventinvention.in/upload/3/"+jgalphoto3;
                                    Picasso.get().load(photo_url_str).into(imggal3);

                                    imggal3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            imggal1clickimage3(jgalphoto3);


                                        }
                                    });

                                }



                                Log.e("eventstrname",jcity);


                                tname.setText(jname);
                                tusertype.setText(jusertype);
                                tcity.setText(jcity);
                                tstate.setText(jstate);
                                trating.setRating(jrating);
                                tdesc.setText(jdescription);


                                tcname.setText(jcname);
                                tcpname.setText(jcpname);
//                                LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
//                                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

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
                params.put("id", reseved);
                Log.e("requstid",reseved);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void imggal1clickimage1(String jgalphoto1) {

        if (jgalphoto1.equals("0")){

        }

        else {


            final Dialog dialog = new Dialog(managerprofilepageActivity.this);
            dialog.setContentView(R.layout.customimagealert);
            dialog.setTitle("Title...");


            ImageView image = (ImageView) dialog.findViewById(R.id.image1);
            photo_url_str = "http://eventinvention.in/upload/1/"+jgalphoto1;
            Picasso.get().load(photo_url_str).into(image);
            Button dialogButton = (Button) dialog.findViewById(R.id.close);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();


        }

    }


    private void imggal1clickimage2(String jgalphoto2) {

        if (jgalphoto2.equals("0")){

        }

        else {


            final Dialog dialog = new Dialog(managerprofilepageActivity.this);
            dialog.setContentView(R.layout.customimagealert);
            dialog.setTitle("Title...");


            ImageView image = (ImageView) dialog.findViewById(R.id.image1);
            photo_url_str = "http://eventinvention.in/upload/2/"+jgalphoto2;
            Picasso.get().load(photo_url_str).into(image);
            Button dialogButton = (Button) dialog.findViewById(R.id.close);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();


        }

    }


    private void imggal1clickimage3(String jgalphoto3) {

        if (jgalphoto3.equals("0")){

        }

        else {


            final Dialog dialog = new Dialog(managerprofilepageActivity.this);
            dialog.setContentView(R.layout.customimagealert);
            dialog.setTitle("Title...");


            ImageView image = (ImageView) dialog.findViewById(R.id.image1);
            photo_url_str = "http://eventinvention.in/upload/3/"+jgalphoto3;
            Picasso.get().load(photo_url_str).into(image);
            Button dialogButton = (Button) dialog.findViewById(R.id.close);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();


        }

    }



    private void submitvalues(final String jid, final String loginid, final String comment, final String inrating1) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_ADDRATING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e("inrating1",response);



                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("inrating2",response);
//                            JSONArray jsonArray=jsonObject.getJSONArray("message");
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
//                                String   jcity=jsonObject1.getString("city");
//                                String   jname=jsonObject1.getString("name");
//                                String   jid=jsonObject1.getString("id");
//                                String   jusertype=jsonObject1.getString("usertype");
//                                String   jstate=jsonObject1.getString("state");
//                                String   jdescription=jsonObject1.getString("state");
//                                final String   jvideo=jsonObject1.getString("video");
//                                int jrating = jsonObject1.getInt("rating_count");
//                                String   jphoto=jsonObject1.getString("photo");
//
//
////                                Log.e("inratingresponce",jcity);
//
//
                                Toast.makeText(managerprofilepageActivity.this, "  INSERTING  VALUES  SUCCESS", Toast.LENGTH_SHORT).show();
//

                            finish();
                            startActivity(getIntent());
//
//                            }


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
                params.put("rating_to", jid);
                params.put("rating_by", loginid);
                params.put("comment", comment);
                params.put("rating_count", inrating1);

                Log.e("inratinginsidedata",jid+loginid+comment+inrating1);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void comment(final String stcity) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_VIEWCOMMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e("city1name",response);



                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("commentresponce",response);
                            JSONArray jsonArray=jsonObject.getJSONArray("message");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                commentdata dataman = new commentdata();
                                String   jname=jsonObject1.getString("comment");
//                                String   jid=jsonObject1.getString("id");
//                                String   jusertype=jsonObject1.getString("usertype");




                                dataman.setComment(jsonObject1.getString("comment"));
                                dataman.setCrating(jsonObject1.getInt("rating_count"));

//
                                personUtilsList.add(dataman);

                            }
//                            spinnnercity.setAdapter(new ArrayAdapter<String>(register.this, android.R.layout.simple_spinner_dropdown_item, CountryName2));

                            mAdapter = new commentRecyclerAdapter(managerprofilepageActivity.this, personUtilsList);
                            recyclerView.setAdapter(mAdapter);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setNestedScrollingEnabled(false);


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
                params.put("rating_to", stcity);
                Log.e("requstid",stcity);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}
