package com.zasa.superduper.CourierService;

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

import com.zasa.superduper.CourierService.Models.DeliveryRequestHistoryApi;
import com.zasa.superduper.CourierService.Models.DeliveryRequestHistoryModel;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryRequestHistoryActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;

    //String st_Vendor_Unique="20211200000001";
    SharedPreferences sharedPreferences;


    RecyclerView rcv_dRequestHistory;
    ArrayList<DeliveryRequestHistoryModel> deliveryRequestHistoryModelArrayList = new ArrayList<>();
    DeliveryRequestHistoryRcvAdapter deliveryRequestHistoryRcvAdapter;
    LinearLayoutManager linearLayoutManager;
    String Vendor_Unique, Vendor_Title, Vendor_Full_Name, Vendor_Mobile,st_Member_Unique;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_request_history);

        context = DeliveryRequestHistoryActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");


        //////these value are coming from Dakika Activity
        Vendor_Unique = getIntent().getStringExtra("Vendor_Unique");
        Vendor_Title = getIntent().getStringExtra("Vendor_Title");
        Vendor_Full_Name = getIntent().getStringExtra("Vendor_Full_Name");
        Vendor_Mobile = getIntent().getStringExtra("Vendor_Mobile");
        st_Member_Unique = getIntent().getStringExtra("st_Member_Unique");


        rcv_dRequestHistory=findViewById(R.id.rcv_dRequestHistory);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_dRequestHistory.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);//set rcv reverse order


        DRHistoryApiCall();

    }

    private void DRHistoryApiCall() {

        progressDialog.show();
        Call<DeliveryRequestHistoryApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mDeliveryRequestHistoryApi(Vendor_Unique,st_Member_Unique);

        call.enqueue(new Callback<DeliveryRequestHistoryApi>() {
            @Override
            public void onResponse(@NonNull Call<DeliveryRequestHistoryApi> call, @NonNull Response<DeliveryRequestHistoryApi> response) {

                DeliveryRequestHistoryApi deliveryRequestHistoryApi = response.body();

                if (response.isSuccessful()) {
                    if (deliveryRequestHistoryApi != null) {
                        if (deliveryRequestHistoryApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            deliveryRequestHistoryModelArrayList = deliveryRequestHistoryApi.getDHList();


                            deliveryRequestHistoryRcvAdapter = new DeliveryRequestHistoryRcvAdapter(deliveryRequestHistoryModelArrayList, context);
                            rcv_dRequestHistory.setAdapter(deliveryRequestHistoryRcvAdapter);
                            //deliveryRequestHistoryRcvAdapter.notifyDataSetChanged();
                            deliveryRequestHistoryRcvAdapter.notifyItemInserted(deliveryRequestHistoryModelArrayList.size());

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + deliveryRequestHistoryApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<DeliveryRequestHistoryApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_back(View view) {
        finish();
    }
}