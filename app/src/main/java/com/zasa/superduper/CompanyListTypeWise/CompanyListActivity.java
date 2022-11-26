package com.zasa.superduper.CompanyListTypeWise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.Profile.ProfileActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyListActivity extends AppCompatActivity {

    Context context;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;
    ///for glowing effect
    View lay_glowingBtn, lay_membership;
    int int_Member_Type;

    TextView tv_companyListTypeName;

    View SearchLay, HeaderLay, Lay_noItemInCompanyList;
    EditText et_SearchCompany;

    RecyclerView companyListRecycler;
    CompanyListRcvAdapter companyListRcvAdapter;
    ArrayList<CompanyListTypeWiseApiModel> companyListModelArrayListTypeWiseApi; ///use in CompanyListRecyclerView
    LinearLayoutManager linearLayoutManager;

    String st_CompanyTypeCode, st_CompanyTypeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        context = CompanyListActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        //put values from CompanyTypeListRcvAdapter
        st_CompanyTypeCode = getIntent().getStringExtra("companyCode");
        st_CompanyTypeName = getIntent().getStringExtra("companyName");
        // Toast.makeText(context, "" + st_CompanyTypeCode+"\n"+st_CompanyTypeName, Toast.LENGTH_SHORT).show();


        //MemberDetailsApi call on splash
        //get Member Details from shared Preference
        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            int_Member_Type = memberDetailsApiModel.getMember_Type();
            //Toast.makeText(context, "MemberDetails shared pref  call", Toast.LENGTH_SHORT).show();
        } else {
            //login shared pref
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            int_Member_Type = sharedPreferences.getInt("Member_Type", 0);
            //Toast.makeText(context, "login shared pref call", Toast.LENGTH_SHORT).show();
        }
        //int_Member_Type=1;
        lay_glowingBtn = findViewById(R.id.lay_glowingBtn);
        Lay_noItemInCompanyList = findViewById(R.id.noItemInCompanyList);

        lay_membership = findViewById(R.id.lay_membership);
        if (  int_Member_Type == 1) {
            lay_membership.setVisibility(View.VISIBLE);
        }else{
            lay_membership.setVisibility(View.GONE);
        }

        tv_companyListTypeName = findViewById(R.id.header);
        tv_companyListTypeName.setText(st_CompanyTypeName);

        companyListRecycler = findViewById(R.id.companyListRcv);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        companyListRecycler.setLayoutManager(linearLayoutManager);

        companyListTypeWiseApi();
        glowButtonAnimator();

        //////search able header//////////
        searchBar();


    }

    private void searchBar() {

        HeaderLay = findViewById(R.id.layHeader);
        SearchLay = findViewById(R.id.laySearch);
        et_SearchCompany = findViewById(R.id.SearchCompanyList);


        et_SearchCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//s is the text that enter in edittext

                try {
                    filter(s.toString());
                } catch (Exception e) {
                    Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });
    }

    private void filter(String text) {

        ArrayList<CompanyListTypeWiseApiModel> filteredList = new ArrayList<>();
        for (CompanyListTypeWiseApiModel item : companyListModelArrayListTypeWiseApi) {
            if (item.getCompany_Name().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        companyListRcvAdapter.filteredListMethod(filteredList);
    }




    private void companyListTypeWiseApi() {

        progressDialog.show();
        Call<CompanyListTypeWiseApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCompanyListTypeWiseApi(st_CompanyTypeCode);

        call.enqueue(new Callback<CompanyListTypeWiseApi>() {
            @Override
            public void onResponse(@NonNull Call<CompanyListTypeWiseApi> call, @NonNull Response<CompanyListTypeWiseApi> response) {
                CompanyListTypeWiseApi companyListTypeWiseApi = response.body();
                if (response.isSuccessful()) {
                    if (companyListTypeWiseApi != null) {
                        if (companyListTypeWiseApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            companyListModelArrayListTypeWiseApi = companyListTypeWiseApi.getCompanyList();

                            companyListRcvAdapter = new CompanyListRcvAdapter(companyListModelArrayListTypeWiseApi, context);
                            companyListRecycler.setAdapter(companyListRcvAdapter);
                            companyListRcvAdapter.notifyDataSetChanged();


                        } else {
                            progressDialog.dismiss();
                            //if dataList is empty
                            if (companyListTypeWiseApi.getStatus() == 2) {
                                Lay_noItemInCompanyList.setVisibility(View.VISIBLE);
                                companyListRecycler.setVisibility(View.GONE);
                            }

                            Toast.makeText(context, "" + companyListTypeWiseApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<CompanyListTypeWiseApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_back(View view) {
        finish();
    }

    public void btn_close(View view) {
        et_SearchCompany.getText().clear();
        et_SearchCompany.setText("");
    }

    public void btn_search(View view) {

        //set focus on edit text and open keyboard
        et_SearchCompany.requestFocus();
       /* InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et_SearchCompany, InputMethodManager.SHOW_IMPLICIT);

        HeaderLay.setVisibility(View.GONE);
        SearchLay.setVisibility(View.VISIBLE);

    }

    public void btn_SearchBack(View view) {

        et_SearchCompany.getText().clear();
        SearchLay.setVisibility(View.GONE);

        HeaderLay.setVisibility(View.VISIBLE);
    }

    public void btn_ContinueShopping(View view) {

        startActivity(new Intent(context, HomeActivity.class));
        finishAffinity();
    }

    private void glowButtonAnimator() {

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(lay_glowingBtn, "alpha", .5f, .1f);
        fadeOut.setDuration(300);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(lay_glowingBtn, "alpha", .1f, .5f);
        fadeIn.setDuration(300);

        AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });

        mAnimationSet.start();
    }

    public void lay_UpgradeMembership(View view) {
        startActivity(new Intent(context, ProfileActivity.class));
        finish();
    }
}