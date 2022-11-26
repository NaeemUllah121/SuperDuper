package com.zasa.superduper;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zasa.superduper.FAQs.FAQsActivity;
import com.zasa.superduper.Home.WebViewActivity;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.Profile.ProfileActivity;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;

    View more_logout_btn, more_profile_btn, more_back_btn,
            more_Draw_btn, more_privacy_policy_btn, more_Terms_Conditions_btn, more_ContactUs_btn,more_FAQs_btn,more_LoyaltyBunchFamily_btn;
    TextView tv_versionName;
    String Privacy_Policy_Url,Terms_Conditions_Url,Contact_Number,Support_Email,Website_Url;
    // ImageView
    int int_Member_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        context = MoreActivity.this;
        sharedPrefManager = new SharedPrefManager(context);




        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel != null) {
            Privacy_Policy_Url= appversionListModel.getPrivacy_Policy_Url();
            Contact_Number= appversionListModel.getContact_Number();
            Support_Email= appversionListModel.getSupport_Email();
            Website_Url= appversionListModel.getWebsite_Url();
            Terms_Conditions_Url= appversionListModel.getTerms_Conditions_Url();
        }


        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            int_Member_Type = memberDetailsApiModel.getMember_Type();

        } else {
            //login shared pref
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            int_Member_Type = sharedPreferences.getInt("Member_Type", 0);

        }



        more_logout_btn = findViewById(R.id.more_logout_btn);
        more_logout_btn.setOnClickListener(this);

        more_profile_btn = findViewById(R.id.more_profile_btn);
        more_profile_btn.setOnClickListener(this);


        more_back_btn = findViewById(R.id.more_back_btn);
        more_back_btn.setOnClickListener(this);

        more_privacy_policy_btn = findViewById(R.id.more_privacy_policy_btn);
        more_privacy_policy_btn.setOnClickListener(this);

        more_Terms_Conditions_btn = findViewById(R.id.more_Terms_Conditions_btn);
        more_Terms_Conditions_btn.setOnClickListener(this);

        more_ContactUs_btn = findViewById(R.id.more_ContactUs_btn);
        more_ContactUs_btn.setOnClickListener(this);

        more_FAQs_btn = findViewById(R.id.more_FAQs_btn);
        more_FAQs_btn.setOnClickListener(this);


        more_LoyaltyBunchFamily_btn = findViewById(R.id.more_LoyaltyBunchFamily_btn);
        more_LoyaltyBunchFamily_btn.setOnClickListener(this);


        /*more_shareApp_btn = view.findViewById(R.id.more_shareApp_btn);
        more_shareApp_btn.setOnClickListener(this);*/


        tv_versionName = findViewById(R.id.tv_versionName);
        ///////set app version/////
        try {
            int versionCode = BuildConfig.VERSION_CODE;
            String versionName = BuildConfig.VERSION_NAME;
            tv_versionName.setText(versionCode + " (" + versionName + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (int_Member_Type == 3) {
            more_LoyaltyBunchFamily_btn.setVisibility(View.VISIBLE);
        }else {
            more_LoyaltyBunchFamily_btn.setVisibility(View.GONE);
        }

    }

    private void logout() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Alert!");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you want to logout?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPrefManager.logout();
                startActivity(new Intent(context, LoginActivity.class));

                finishAffinity();


            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.more_logout_btn:
                logout();
                break;

            case R.id.more_back_btn:
                startActivity(new Intent(context, HomeActivity.class));
                finishAffinity();
                break;

            case R.id.more_profile_btn:
                startActivity(new Intent(context, ProfileActivity.class));

                break;
                /*case R.id.more_shareApp_btn:
                    Constant.shareApp(context);
                    //shareApp();
                break; */
//                case R.id.more_TechClub_btn:
//                    requireActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.frame_layout, new TechClubFragment(), "Tag")
//                            //.addToBackStack("HomeFragment")
//                            .addToBackStack(null)
//                            .commit();
//                break;

//                case R.id.more_CourierService_btn:
//                    requireActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.frame_layout, new CourierServiceFragment(), "Tag")
//                            //.addToBackStack("HomeFragment")
//                            .addToBackStack(null)
//                            .commit();
//                break;

//                case R.id.more_LoyaltyBunchFamily_btn:
//                startActivity( new Intent(context, DonationVerificationActivity.class));
//                break;

            case R.id.more_FAQs_btn:
                startActivity( new Intent(context, FAQsActivity.class));
                break;
            case R.id.more_privacy_policy_btn:
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("URL",Privacy_Policy_Url);
                startActivity(intent);
                break;
            case R.id.more_Terms_Conditions_btn:
                Intent intent1 = new Intent(context, WebViewActivity.class);
                intent1.putExtra("URL",Terms_Conditions_Url);
                startActivity(intent1);
                break;
            case R.id.more_ContactUs_btn:
                startActivity(new Intent(context, ContactUsActivity.class));
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MoreActivity.this,HomeActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}