package com.zasa.superduper.eLearn;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.YTRetrofitInstance;
import com.zasa.superduper.eLearn.YTModels.ResponseVideos;
import com.zasa.superduper.eLearn.YTModels.Video;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YTCoursePlaylistActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    RecyclerView YTRecyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Video> arrayListVideos;
    YTAdapter ytAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_course_playlist);



        YTRecyclerView = findViewById(R.id.playlist);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        YTRecyclerView.setLayoutManager(gridLayoutManager);

        arrayListVideos = new ArrayList<>();



        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        getVideo();

    }

    private void getVideo() {
        progressDialog.show();
        Call<ResponseVideos> call = YTRetrofitInstance
                .getInstance()
                .getApiInterface()
                .getAllVideos(200, "PLu0W_9lII9aiL0kysYlfSOUgY5rNlOhUd", "AIzaSyBSeQSZfgck6lMLkPxhoRd0FuM-mjhCYYg");

        call.enqueue(new Callback<ResponseVideos>() {
            @Override
            public void onResponse(@NonNull Call<ResponseVideos> call, @NonNull Response<ResponseVideos> response) {
                ResponseVideos responseVideos = response.body();

//                if (response.isSuccessful()) {
                if (responseVideos != null) {
                    if (responseVideos.items.size() > 0) {
                        progressDialog.dismiss();
                        for (int i = 0; i < responseVideos.items.size(); i++) {
                            arrayListVideos.add(responseVideos.items.get(i));
                        }
                        ytAdapter = new YTAdapter(arrayListVideos, YTCoursePlaylistActivity.this);
                        YTRecyclerView.setAdapter(ytAdapter);
                        ytAdapter.notifyDataSetChanged();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(YTCoursePlaylistActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(YTCoursePlaylistActivity.this, "null Response", Toast.LENGTH_SHORT).show();
                }
            }

            //  }

            @Override
            public void onFailure(Call<ResponseVideos> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(YTCoursePlaylistActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }


    public void btn_back(View view) {
        finish();
    }

    public void info_Btn(View view) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(YTCoursePlaylistActivity.this);
        final View dialogView = getLayoutInflater().inflate(R.layout.alertdialog_course_details, null);
        builder.setView(dialogView).setCancelable(true);

        builder.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        TextView tv_instructorId = dialogView.findViewById(R.id.tv_instructorId);
        TextView tv_Days = dialogView.findViewById(R.id.tv_Days);
        TextView tv_timing = dialogView.findViewById(R.id.tv_timing);
        TextView tv_nextCourseDate = dialogView.findViewById(R.id.tv_nextCourseDate);

        tv_instructorId.setText("7654321");
        tv_Days.setText("30-11-2020   29-09-2020");
        tv_timing.setText("12:00 a.m  1:00 p.m");
        tv_nextCourseDate.setText("01-06-2021");


        final AlertDialog alert = builder.create();
        alert.show();

    }



}