package com.zasa.superduper.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.InstallStatus;

import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.Profile.MemberDetailsApi;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.DeleteAppCache;
import com.zasa.superduper.Utils.PrefsManager;
import com.zasa.superduper.Utils.SharedPrefManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    /*    float v = 1;
        private static int SPLASH_TIMER = 4000; //4sec*/
    private static String TAG = "checkkk";
    AppUpdateManager appUpdateManager;
    public static final int RC_APP_UPDATEE = 104;

    Context context;
    ImageView splashImage;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;
    String st_Member_Unique;

    public static String client_code = "2022026";
    public static String company_code = "2022026001";
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //off dark mode

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // To hide Status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context = SplashActivity.this;
        sharedPrefManager = new SharedPrefManager(this);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sharedPref", MODE_PRIVATE);
        st_Member_Unique = sharedPreferences.getString("Member_Unique", "");
        // Toast.makeText(context, ""+st_Member_Unique,Toast.LENGTH_SHORT).show();

        progressBar = findViewById(R.id.progressbar);
        splashImage = findViewById(R.id.splashLogo);


//        ///////app update 1///////
//        appUpdateManager = AppUpdateManagerFactory.create(this);
//        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
//            @Override
//            public void onSuccess(AppUpdateInfo result) {
//                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                        && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                    try {
//                        appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE,
//                                SplashActivity.this, RC_APP_UPDATEE);
//                    } catch (IntentSender.SendIntentException e) {
//                        Toast.makeText(context, "update error", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                }else {
//                    appVersionApi();
//                   //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        //////////////////


        appVersionApi();

        companyDetails();


    }

    ///////app update 2////
    private InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(@NonNull InstallState state) {
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                showCompletedUpdate();
            }
        }
    };

    private void showCompletedUpdate() {

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "New App is ready!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /////////update app condition
        if (requestCode == RC_APP_UPDATEE && resultCode != RESULT_OK) {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        //  appUpdateManager = AppUpdateManagerFactory.create(this);
//        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
//            @Override
//            public void onSuccess(AppUpdateInfo result) {
//                if (result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
//                    try {
//                        appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, SplashActivity.this, RC_APP_UPDATEE);
//                    } catch (IntentSender.SendIntentException e) {
//                        Toast.makeText(context, "2 error", Toast.LENGTH_SHORT).show();
//
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
//    ////////////////////////////app update 2 end////////////
//

    private void companyDetails() {

        if (!Constant.isNetConnected(context)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "You are not connected with internet!", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        else {

            Call<CompanyDetailsApi> call = RetrofitInstance.getInstance().getApiInterface().mCompanyDetail("2022026001");
            call.enqueue(new Callback<CompanyDetailsApi>() {
                @Override
                public void onResponse(Call<CompanyDetailsApi> call, Response<CompanyDetailsApi> response) {

                    CompanyDetailsApi companyDetailsApi=response.body();
                    if (response.isSuccessful()){
                        if (companyDetailsApi!=null){
                            if (companyDetailsApi.getStatus()==1){
                                progressBar.setVisibility(View.GONE);
                                PrefsManager.setCompanyCode(companyDetailsApi.getCompanySingle().getCompany_Code());

                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<CompanyDetailsApi> call, Throwable t) {

                    progressBar.setVisibility(View.GONE);
                    onBackPressed();
                    Toast.makeText(SplashActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


    private void appVersionApi() {

        if (!Constant.isNetConnected(context)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "You are not connected with internet!", Toast.LENGTH_LONG).show();
            onBackPressed();

        } else {
            Call<AppVersionApi> call = RetrofitInstance
                    .getInstance()
                    .getApiInterface()
                    .mAppVersionApi("LBP");

            call.enqueue(new Callback<AppVersionApi>() {

                @Override
                public void onResponse(@NonNull Call<AppVersionApi> call, @NonNull Response<AppVersionApi> response) {
                    AppVersionApi appVersionApi = response.body();
                    if (response.isSuccessful()) {
                        if (appVersionApi != null) {
                            if (appVersionApi.getStatus() == 1) {

                                sharedPrefManager.saveAppVersionModel(appVersionApi.getAppversionList());
                                String appId = appVersionApi.getAppversionList().getApp_ID();
                                float appVersion = appVersionApi.getAppversionList().getApp_Version();
                                String Web_App_Static = appVersionApi.getAppversionList().getWeb_App_Static();
                                String Msg_Link = appVersionApi.getAppversionList().getMsg_Link();
                                String Msg_Flag = appVersionApi.getAppversionList().getMsg_Flag();
                                String Msg_Title = appVersionApi.getAppversionList().getMsg_Title();
                                String Msg_Desc = appVersionApi.getAppversionList().getMsg_Desc();
                                String Msg_Imag_Link = appVersionApi.getAppversionList().getMsg_Imag_Link();
                                String Cache_Clear_Flag = appVersionApi.getAppversionList().getCache_Clear_Flag();
                                String Contact_Number = appVersionApi.getAppversionList().getContact_Number();
                                String Support_Email = appVersionApi.getAppversionList().getSupport_Email();
                                String Website_Url = appVersionApi.getAppversionList().getWebsite_Url();
                                String Privacy_Policy_Url = appVersionApi.getAppversionList().getPrivacy_Policy_Url();
                                String Terms_Conditions_Url = appVersionApi.getAppversionList().getTerms_Conditions_Url();
                                int Hit_Counts = appVersionApi.getAppversionList().getHit_Counts();


                                Log.d(TAG, "onResponse: " + appVersionApi.getAppversionList().toString());

                                // progressBar.setVisibility(View.GONE);
                                if (TextUtils.isEmpty(Cache_Clear_Flag)) {
                                    Toast.makeText(context, "Cache not clear!", Toast.LENGTH_SHORT).show();

                                } else {
                                    if (Cache_Clear_Flag.equals("Y")) {
                                        DeleteAppCache.deleteCache(context);
                                        //Toast.makeText(context, "Cache cleared", Toast.LENGTH_SHORT).show();

                                    }

                                }

                                if (sharedPrefManager.isLoggedIn()) {

                                    callMemberDetailsApi();//call MemberDetails Api if already login
                                    // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();


                                } else {
                                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                    finish();
                                }


                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SplashActivity.this, "" + appVersionApi.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SplashActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SplashActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(@NonNull Call<AppVersionApi> call, @NonNull Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    onBackPressed();
                    Toast.makeText(SplashActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    private void callMemberDetailsApi() {


        Call<MemberDetailsApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mMemberDetailsApi(st_Member_Unique);

        call.enqueue(new Callback<MemberDetailsApi>() {
            @Override
            public void onResponse(@NonNull Call<MemberDetailsApi> call, @NonNull Response<MemberDetailsApi> response) {

                MemberDetailsApi memberDetailsApi = response.body();

                if (response.isSuccessful()) {
                    if (memberDetailsApi != null) {
                        if (memberDetailsApi.getStatus() == 1) {

                            sharedPrefManager.saveMemberDetails(memberDetailsApi.getMemberDetails());
                            progressBar.setVisibility(View.GONE);

                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();

                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(context, "" + memberDetailsApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(@NonNull Call<MemberDetailsApi> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                onBackPressed();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}