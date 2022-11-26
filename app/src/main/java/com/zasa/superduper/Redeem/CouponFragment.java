package com.zasa.superduper.Redeem;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CouponFragment extends Fragment {


    public CouponFragment() {
        // Required empty public constructor
    }


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;
    String  st_sharedPref_MemberUnique;


    RecyclerView rcv_Coupon;
    LinearLayoutManager linearLayoutManager;
    CouponAdapter couponAdapter;
    ArrayList<CouponListModel> couponListModelArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_coupon, container, false);

        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);


        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            st_sharedPref_MemberUnique = memberDetailsApiModel.getMember_Unique();
        } else {
            //login shared pref
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            st_sharedPref_MemberUnique = sharedPreferences.getString("Member_Unique", "");
        }



        rcv_Coupon = view.findViewById(R.id.rcv_Coupon);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_Coupon.setLayoutManager(linearLayoutManager);

        CouponListApi( st_sharedPref_MemberUnique);


        return view;
    }

    public void CouponListApi(String st_sharedPref_MemberUnique) {

        progressDialog.show();
        Call<CouponListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCouponListApi(st_sharedPref_MemberUnique);

        call.enqueue(new Callback<CouponListApi>() {
            @Override
            public void onResponse(@NonNull Call<CouponListApi> call, @NonNull Response<CouponListApi> response) {

                CouponListApi couponListApi = response.body();

                if (response.isSuccessful()) {
                    if (couponListApi != null) {
                        if (couponListApi.getStatus() == 1) {



                            couponListModelArrayList = couponListApi.getCouponList();

                            couponAdapter = new CouponAdapter(couponListModelArrayList, context);
                            rcv_Coupon.setAdapter(couponAdapter);
                            couponAdapter.notifyItemInserted(couponListModelArrayList.size());
                            progressDialog.dismiss();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + couponListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<CouponListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}