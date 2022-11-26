package com.zasa.superduper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zasa.superduper.Home.WebViewActivity;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.net.URLEncoder;

public class ContactUsActivity extends AppCompatActivity {

    TextView tv_contactNo, tv_Support_Email, tv_Website_Url, tv_versionName;
    SharedPrefManager sharedPrefManager;
    String Privacy_Policy_Url, Terms_Conditions_Url, Contact_Number, Support_Email, Website_Url;
    View more_contactNo_btn, more_Support_Email_btn, more_Website_Url_btn;
    Context context;

    String fbURL = "https://www.facebook.com/smileservicespakistan/";
    String instaURL = "https://www.instagram.com/smileservicespakistan/";
    String youtubeURL = "https://www.youtube.com/channel/UCDNiSu1idqgudE0TAq13DVw";
    String linkedinURL = "https://www.linkedin.com/in/smile-services-pakistan-48a591231/";
    String twitterURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        context = ContactUsActivity.this;

        sharedPrefManager = new SharedPrefManager(ContactUsActivity.this);
        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel != null) {
            Privacy_Policy_Url = appversionListModel.getPrivacy_Policy_Url();
            Contact_Number = appversionListModel.getContact_Number();
            Support_Email = appversionListModel.getSupport_Email();
            Website_Url = appversionListModel.getWebsite_Url();
            Terms_Conditions_Url = appversionListModel.getTerms_Conditions_Url();
        }

        more_contactNo_btn = findViewById(R.id.more_contactNo_btn);
        more_Support_Email_btn = findViewById(R.id.more_Support_Email_btn);
        more_Website_Url_btn = findViewById(R.id.more_Website_Url_btn);

        tv_contactNo = findViewById(R.id.tv_contactNo);
        tv_Support_Email = findViewById(R.id.tv_Support_Email);
        tv_Website_Url = findViewById(R.id.tv_Website_Url);
        tv_versionName = findViewById(R.id.tv_versionName);

        /*tv_contactNo.setText(Contact_Number);
        tv_Support_Email.setText(Support_Email);
        tv_Website_Url.setText(Website_Url); */

        tv_contactNo.setText("03218777839");
        tv_Support_Email.setText("smileservices786@gmail.com");
        tv_Website_Url.setText("www.smileservices.pk");


        ///////set app version/////
        try {
            int versionCode = BuildConfig.VERSION_CODE;
            String versionName = BuildConfig.VERSION_NAME;
            tv_versionName.setText(versionCode + " (" + versionName + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void btn_back(View view) {
        finish();
    }


    public void more_Website_Url_btn(View view) {

        String webUrl = tv_Website_Url.getText().toString().trim();

        openWebView(webUrl);
    }

    public void openWebView(String webUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        // intent.putExtra("URL",Website_Url);
        intent.putExtra("URL", webUrl);
        startActivity(intent);
    }

    public void more_Support_Email_btn(View view) {

        String Email = tv_Support_Email.getText().toString().trim();
        try {
            //Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + Support_Email));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + Email));
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(Intent.EXTRA_TEXT, "msg");
            startActivity(intent);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void more_contactNo_btn(View view) {
        String phone = tv_contactNo.getText().toString().trim();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                "tel", phone/*Contact_Number*/, null));
        try {
            startActivity(phoneIntent);
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    public void btn_whatapp(View view) {

        String msg = "Hi, There...\n";
        String phone = tv_contactNo.getText().toString().trim();

        try {
            PackageManager packageManager = context.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone=" + "+92" + phone.substring(1) + "&text=" + URLEncoder.encode(msg, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i);
            } else {
                Toast.makeText(context, "App Not Found!", Toast.LENGTH_SHORT).show();
                //KToast.errorToast(getActivity(), getString(R.string.no_whatsapp), Gravity.BOTTOM, KToast.LENGTH_SHORT);
            }
        } catch (Exception e) {
            Log.e("ERROR WHATSAPP", e.toString());
            Toast.makeText(context, "App Not Found!", Toast.LENGTH_SHORT).show();

        }
    }

    public void btn_fb(View view) {
        openWebView(fbURL);
    }

    public void btn_instagram(View view) {
        openWebView(instaURL);

    }

    public void btn_twitter(View view) {
        openWebView(twitterURL);
    }

    public void btn_linkedin(View view) {
        openWebView(linkedinURL);
    }

    public void btn_youtube(View view) {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(youtubeURL));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //openWebView(youtubeURL);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(youtubeURL));
            startActivity(intent);
        }
    }
}