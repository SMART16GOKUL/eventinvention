package com.example.eventinvention.Activity.elaboratedprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.eventinvention.R;

public class JoindWithUs extends AppCompatActivity {


    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_joind_with_us);


        WebView webView = (WebView) findViewById(R.id.web);
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        setContentView(webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://eventinvention.in/paynow.php");


    }
}
