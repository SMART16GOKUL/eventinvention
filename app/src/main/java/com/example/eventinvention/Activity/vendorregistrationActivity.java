package com.example.eventinvention.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.eventinvention.R;

public class vendorregistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorregistration);

        WebView webView = findViewById(R.id.webView1);
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        setContentView(webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://eventinvention.in/signup.php");
    }
}
