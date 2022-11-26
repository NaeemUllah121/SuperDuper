package com.zasa.superduper.WasteMaterialRequestHistory;

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

import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialRequestHistoryActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;

    String st_Member_Unique;
    SharedPreferences sharedPreferences;


    RecyclerView MRH_Rcv;
    ArrayList<MOHListModel> mrHistoryArrayList;
    MaterialRequestHistoryRcvAdapter MRH_Adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_request_history);

        context = MaterialRequestHistoryActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        st_Member_Unique = sharedPreferences.getString("Member_Unique", "");


        MRH_Rcv=findViewById(R.id.rcv_mRequestHistory);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        MRH_Rcv.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);//set rcv reverse order

        mrHistoryArrayList = new ArrayList<>();

        MRHistoryApiCall();


    }


    private void MRHistoryApiCall() {

        progressDialog.show();
        Call<MRHistoryApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mMRHistoryApi(st_Member_Unique);

        call.enqueue(new Callback<MRHistoryApi>() {
            @Override
            public void onResponse(@NonNull Call<MRHistoryApi> call, @NonNull Response<MRHistoryApi> response) {

                MRHistoryApi mrHistoryApi = response.body();

                if (response.isSuccessful()) {
                    if (mrHistoryApi != null) {
                        if (mrHistoryApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            mrHistoryArrayList = mrHistoryApi.getMOHList();


                            MRH_Adapter = new MaterialRequestHistoryRcvAdapter(mrHistoryArrayList, context);
                            MRH_Rcv.setAdapter(MRH_Adapter);
                            MRH_Adapter.notifyDataSetChanged();


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + mrHistoryApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<MRHistoryApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void btn_back(View view) {
        finish();
    }
}