package com.zasa.superduper.CourierService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zasa.superduper.CourierService.Models.CourierListApi;
import com.zasa.superduper.CourierService.Models.CourierListModel;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourierServiceFragment extends Fragment {


    public CourierServiceFragment() {
        // Required empty public constructor
    }

    Context context;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;

    ArrayList<CourierListModel> courierListModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    CourierServiceRcvAdapter courierServiceRcvAdapter;
    ImageView iv_courierGif,btn_back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courier_service, container, false);

        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        recyclerView = view.findViewById(R.id.rcv_courier);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        iv_courierGif = view.findViewById(R.id.iv_courierGif);


        btn_back = view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomeActivity.class));
                requireActivity().finishAffinity();
            }
        });

        loadGif();
        courierListApi();


        return view;
    }

    private void loadGif() {
        // Glide.with(this).load(R.drawable.courierorgg) .dontAnimate().into(iv_courierGif);
        Glide.with(context)
                .asGif()
                .load(R.drawable.deliveryaddress) // Replace with a valid url
                .addListener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        resource.setLoopCount(1); // Place your loop count here.
                        return false;
                    }
                })
                .into(iv_courierGif); // Replace with your ImageView id.

    }

    private void courierListApi() {

        progressDialog.show();
        Call<CourierListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCourierListApi();

        call.enqueue(new Callback<CourierListApi>() {
            @Override
            public void onResponse(@NonNull Call<CourierListApi> call, @NonNull Response<CourierListApi> response) {
                CourierListApi courierListApi = response.body();
                if (response.isSuccessful()) {
                    if (courierListApi != null) {
                        if (courierListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            courierListModelArrayList = courierListApi.getCourierlist();
                            courierServiceRcvAdapter = new CourierServiceRcvAdapter(courierListModelArrayList, context);
                            recyclerView.setAdapter(courierServiceRcvAdapter);
                            //courierServiceRcvAdapter.notifyDataSetChanged();
                            courierServiceRcvAdapter.notifyItemInserted(courierListModelArrayList.size());


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "" + courierListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CourierListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}