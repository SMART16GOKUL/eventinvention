package com.example.eventinvention.Activity.elaboratedprofile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.eventinvention.R;

public class elaborated extends AppCompatActivity {

    androidx.cardview.widget.CardView c1,c2,c3,c4,c5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_elaboratedprofile);


        c1  = findViewById(R.id.card1);
        c2  = findViewById(R.id.card2);
        c3  = findViewById(R.id.card3);
        c4  = findViewById(R.id.card4);
        c5  = findViewById(R.id.card5);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =  new Intent(elaborated.this,Pmyprofile.class);
                startActivity(i);

            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =  new Intent(elaborated.this,AboutUs.class);
                startActivity(i);

            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =  new Intent(elaborated.this,TermsAndCond.class);
                startActivity(i);

            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                Intent i =  new Intent(elaborated.this,ShareApp.class);
//                startActivity(i);


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "http://eventinvention.in/paynow.php";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });



        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =  new Intent(elaborated.this,JoindWithUs.class);
                startActivity(i);

            }
        });


    }

}
