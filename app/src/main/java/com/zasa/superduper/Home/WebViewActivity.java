package com.zasa.superduper.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zasa.superduper.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    SwipeRefreshLayout swipe;
    ProgressDialog progressDialog;
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//turn off night mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");


        webView = findViewById(R.id.webView);

        String intentUrl = getIntent().getStringExtra("URL");

    /*    if ( !intentUrl.contains("http://")||!intentUrl.contains("https://")) {

            Url="http://"+intentUrl;
        }
        else{
            Url=intentUrl;
        }*/
        Url=intentUrl;
        //Toast.makeText(this, "" + Url, Toast.LENGTH_SHORT).show();


        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

      /*  webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
       // webView.loadUrl(Url);
        webView.loadUrl("http://www.google.com");


        WebSettings webSettings = webView.getSettings();
       // webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
*/



        loadWebView();





    }



    private void loadWebView() {
        webView.setWebViewClient(new WebViewClient() {

            @Override
           // public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
                    //Toast.makeText(WebViewActivity.this, ""+description, Toast.LENGTH_SHORT).show();

            public void onReceivedError (WebView view, WebResourceRequest request, WebResourceError error){
                Toast.makeText(WebViewActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
                String webUrl = webView.getUrl();
                swipe.setRefreshing(false);
            }

        });

       //  webView.loadUrl("http://www.google.com");
        webView.loadUrl(Url);

        WebSettings webSettings = webView.getSettings();
        // webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        swipe.setRefreshing(true);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}