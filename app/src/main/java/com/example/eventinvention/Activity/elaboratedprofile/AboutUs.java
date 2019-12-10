package com.example.eventinvention.Activity.elaboratedprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.eventinvention.R;

public class AboutUs extends AppCompatActivity {

    TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        t2  =  findViewById(R.id.t2);

        t2.setText("  of robots,[5] as well as computer systems for their control, sensory feedback, and information processing is robotics. These technologies deal with automated machines that can take the place of humans in dangerous environments or manufacturing processes, or resemble humans in appearance, behavior, or cognition. Many of today's robots are inspired by nature contributing to the field of bio-inspired robotics. These robots have also created a newer branch of robotics: soft robotics ");

    }
}
