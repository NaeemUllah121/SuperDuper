package com.zasa.superduper.DonationVerification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationVerificationActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;

    RecyclerView rcv_donationVerify;
    DonationVerificationAdapter donationVerificationAdapter;
    ArrayList<UnVerifyFamilyOrderModel> unVerifyFamilyOrderModelArrayList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;


    String st_FName, st_LName, st_LB_Points, st_sharedPref_MemberUnique;
    int int_Member_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_verification);

        context = DonationVerificationActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);




        //MemberDetailsApi call on splash
        //get Member Details from shared Preference
        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            int_Member_Type = memberDetailsApiModel.getMember_Type();
            st_LB_Points = memberDetailsApiModel.getLB_Points();
            st_sharedPref_MemberUnique = memberDetailsApiModel.getMember_Unique();
            st_FName = memberDetailsApiModel.getMember_FName();
            st_LName = memberDetailsApiModel.getMember_LName();
            //Toast.makeText(context, "MemberDetails shared pref  call", Toast.LENGTH_SHORT).show();
        } else {
            //login shared pref
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            st_LB_Points = sharedPreferences.getString("LB_Points", "");
            int_Member_Type = sharedPreferences.getInt("Member_Type", 0);
            st_FName = sharedPreferences.getString("Member_FName", "");
            st_LName = sharedPreferences.getString("Member_LName", "");
            st_sharedPref_MemberUnique = sharedPreferences.getString("Member_Unique", "");
            //Toast.makeText(context, "login shared pref call", Toast.LENGTH_SHORT).show();

        }


        rcv_donationVerify = findViewById(R.id.rcv_donationVerify);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_donationVerify.setLayoutManager(linearLayoutManager);

        unVerifyFamilyOrderApiCall();

//        donationVerificationAdapter = new DonationVerificationAdapter(unVerifyFamilyOrderModelArrayList, context);
//        rcv_donationVerify.setAdapter(donationVerificationAdapter);
//        donationVerificationAdapter.notifyItemInserted(unVerifyFamilyOrderModelArrayList.size());

    }


    private void unVerifyFamilyOrderApiCall() {

        progressDialog.show();
        Call<UnVerifyFamilyOrderApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mUnVerifyFamilyOrderApi(st_sharedPref_MemberUnique);

        call.enqueue(new Callback<UnVerifyFamilyOrderApi>() {
            @Override
            public void onResponse(@NonNull Call<UnVerifyFamilyOrderApi> call, @NonNull Response<UnVerifyFamilyOrderApi> response) {

                UnVerifyFamilyOrderApi unVerifyFamilyOrderApi = response.body();

                if (response.isSuccessful()) {
                    if (unVerifyFamilyOrderApi != null) {
                        if (unVerifyFamilyOrderApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            unVerifyFamilyOrderModelArrayList = unVerifyFamilyOrderApi.getFOHList();


                            donationVerificationAdapter = new DonationVerificationAdapter(unVerifyFamilyOrderModelArrayList, context);
                            rcv_donationVerify.setAdapter(donationVerificationAdapter);
                            donationVerificationAdapter.notifyItemInserted(unVerifyFamilyOrderModelArrayList.size());

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + unVerifyFamilyOrderApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<UnVerifyFamilyOrderApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void VerifyFamilyOrderApiCall(String st_sharedPref_MemberUnique, String OrderId) {

        progressDialog.show();
        Call<VerifyFOrderApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mVerifyFOrderApi(st_sharedPref_MemberUnique, OrderId);

        call.enqueue(new Callback<VerifyFOrderApi>() {
            @Override
            public void onResponse(@NonNull Call<VerifyFOrderApi> call, @NonNull Response<VerifyFOrderApi> response) {

                VerifyFOrderApi verifyFOrderApi = response.body();

                if (response.isSuccessful()) {
                    if (verifyFOrderApi != null) {
                        if (verifyFOrderApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            Toast.makeText(context, "" + verifyFOrderApi.getMessage(), Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + verifyFOrderApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<VerifyFOrderApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_back(View view) {
        finish();
    }
}