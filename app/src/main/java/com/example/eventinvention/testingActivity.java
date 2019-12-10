package com.example.eventinvention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventinvention.Activity.HomePage;
import com.example.eventinvention.Activity.LoginActivity;

import static com.example.eventinvention.Activity.LoginActivity.MyPREFERENCES;

public class testingActivity extends AppCompatActivity {

    SharedPreferences prf;
    TextView t;
    String st1,st2,st3,st4 = "";
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        t = findViewById(R.id.te1);

        b =findViewById(R.id.b1);

        t.setText("");
        prf = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        st1 =prf.getString("unameKey",null);
        st2 =prf.getString("uemailKey",null);
        st3 =prf.getString("uidKey",null);
        st4 =prf.getString("uphoneKey",null);


        Toast.makeText(this, st1+st2+st3+st4, Toast.LENGTH_SHORT).show();

        t.setText(st1+st2+st3+st4);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prf = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.apply();


                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
