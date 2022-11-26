package com.zasa.superduper.FAQs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQsActivity extends AppCompatActivity {
    private static final String TAG = "checkkk";
    ProgressDialog progressDialog;
    Context context;
    SharedPreferences sharedPreferences;

    RecyclerView rcv_Faqs;
    ArrayList<FaqListModel> faqListModelArrayList = new ArrayList<>();
    FAQRcvAdapter FAQRcvAdapter;
    LinearLayoutManager linearLayoutManager;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;


   // ArrayList<CityListApiModel> cityListApiModelArrayList = new ArrayList<>();
    private int numOfIteration = 0;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);


        context = FAQsActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");


        progressBar = findViewById(R.id.progressbar);

        rcv_Faqs = findViewById(R.id.rcv_Faqs);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       // rcv_Faqs.setHasFixedSize(true);
        rcv_Faqs.setLayoutManager(linearLayoutManager);
        //FAQRcvAdapter = new FAQRcvAdapter(cityListApiModelArrayList, context);
        FAQRcvAdapter = new FAQRcvAdapter(faqListModelArrayList, context);
        rcv_Faqs.setAdapter(FAQRcvAdapter);


        pagination();

        //searchCity();

        mFAQsListApi();


    }

    private void pagination() {

        rcv_Faqs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                }
                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    //searchCity();
                    mFAQsListApi();
                }
            }
        });
    }



    private void mFAQsListApi() {

        progressDialog.show();


        Call<FaqListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mFaqListApi();


        call.enqueue(new Callback<FaqListApi>() {
            @Override
            public void onResponse(@NonNull Call<FaqListApi> call, @NonNull Response<FaqListApi> response) {

                FaqListApi faqListApi = response.body();

                if (response.isSuccessful()) {
                    if (faqListApi != null) {
                        if (faqListApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            faqListModelArrayList = faqListApi.getFaqList();

                            FAQRcvAdapter = new FAQRcvAdapter(faqListModelArrayList, context);
                            rcv_Faqs.setAdapter(FAQRcvAdapter);
                            FAQRcvAdapter.notifyItemInserted(faqListModelArrayList.size());

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + faqListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<FaqListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

/*    ////Search city spinner Dialog function
    private void searchCity() {

        //progressDialog.show();
        progressBar.setVisibility(View.VISIBLE);

        Call<CityListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCityListApi("092");

        call.enqueue(new Callback<CityListApi>() {
            @Override
            public void onResponse(@NonNull Call<CityListApi> call, @NonNull Response<CityListApi> response) {

                CityListApi cityListApi = response.body();
                if (response.isSuccessful() && cityListApi != null) {

                    if (cityListApi.getStatus() == 1) {

                       // progressDialog.dismiss();
                        // cityListApiModelArrayList = cityListApi.getCitylist();

                        for (int i = numOfIteration; i < cityListApi.getCitylist().size(); i++) {
                            cityListApiModelArrayList.add(cityListApi.getCitylist().get(i));

                            numOfIteration++;

                            if (numOfIteration % 20 == 0) {
                                //Toast.makeText(context, ""+numOfIteration, Toast.LENGTH_SHORT).show();
                                break;
                            }

                        }

                        //FAQRcvAdapter.notifyDataSetChanged();
                        FAQRcvAdapter.notifyItemInserted(cityListApiModelArrayList.size());
                        progressBar.setVisibility(View.GONE);

                    } else {
                       // progressDialog.dismiss();
                        Toast.makeText(context, "" + cityListApi.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                } else {
                   // progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<CityListApi> call, @NonNull Throwable t) {
               // progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }*/

    public void btn_back(View view) {
        finish();
    }
}