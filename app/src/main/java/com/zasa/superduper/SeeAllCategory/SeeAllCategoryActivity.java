package com.zasa.superduper.SeeAllCategory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zasa.superduper.Home.CompanyTypeListApi;
import com.zasa.superduper.Home.CompanyTypeListModel;
import com.zasa.superduper.Home.TopBrandListApi;
import com.zasa.superduper.Home.TopBrandListModel;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllCategoryActivity extends AppCompatActivity {

    Context context;
    ProgressDialog progressDialog;

    RecyclerView seeAllRecycler;
    SeeAllCategoryRcvAdapter seeAllAdapter;
    SeeAllTopBrandCategoryRcvAdapter seeAllTopBrandCategoryRcvAdapter;
    ArrayList<CompanyTypeListModel> companyTypeListModelArrayList;
    ArrayList<TopBrandListModel> topBrandListModelArrayList = new ArrayList<>();
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_category);

        context = SeeAllCategoryActivity.this;

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");


        seeAllRecycler = findViewById(R.id.seeAll_category);
        gridLayoutManager = new GridLayoutManager(context, 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        seeAllRecycler.setLayoutManager(gridLayoutManager);

        String seeAllType = getIntent().getStringExtra("seeAllType");

        if (seeAllType.equals("tv_SeeAllBtn")) {
            companyTypeListApi();
        }else {
            topBrandListApi();
        }


    }


    private void companyTypeListApi() {

        progressDialog.show();
        Call<CompanyTypeListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCompanyTypeListApi();

        call.enqueue(new Callback<CompanyTypeListApi>() {
            @Override
            public void onResponse(@NonNull Call<CompanyTypeListApi> call, @NonNull Response<CompanyTypeListApi> response) {
                CompanyTypeListApi companyTypeListApi = response.body();
                if (response.isSuccessful()) {
                    if (companyTypeListApi != null) {
                        if (companyTypeListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            companyTypeListModelArrayList = companyTypeListApi.getCompanyTypeList();
                            seeAllAdapter = new SeeAllCategoryRcvAdapter(companyTypeListModelArrayList, context);
                            seeAllRecycler.setAdapter(seeAllAdapter);
                            seeAllAdapter.notifyItemInserted(companyTypeListModelArrayList.size());


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + companyTypeListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<CompanyTypeListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_back(View view) {
        finish();
    }

    private void topBrandListApi() {

        progressDialog.show();
        Call<TopBrandListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mTopBrandListApi();

        call.enqueue(new Callback<TopBrandListApi>() {
            @Override
            public void onResponse(@NonNull Call<TopBrandListApi> call, @NonNull Response<TopBrandListApi> response) {
                TopBrandListApi topBrandListApi = response.body();
                if (response.isSuccessful()) {
                    if (topBrandListApi != null) {
                        if (topBrandListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            topBrandListModelArrayList = topBrandListApi.getTopBrandList();
                            seeAllTopBrandCategoryRcvAdapter = new SeeAllTopBrandCategoryRcvAdapter(topBrandListModelArrayList, context);
                            seeAllRecycler.setAdapter(seeAllTopBrandCategoryRcvAdapter);
                            seeAllTopBrandCategoryRcvAdapter.notifyItemInserted(topBrandListModelArrayList.size());


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + topBrandListApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<TopBrandListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}