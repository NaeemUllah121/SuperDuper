package com.zasa.superduper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.Utils.SharedPrefManager;

public class MembershipInfoActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;

    ImageView iv_memshipGif;
    View lay_pending,lay_main;
    String Payment_Verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_info);

        context = MembershipInfoActivity.this;

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        iv_memshipGif=findViewById(R.id.iv_memshipGif);
        lay_pending=findViewById(R.id.lay_pending);
        lay_main=findViewById(R.id.lay_main);


        loadGif();


        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            Payment_Verify = memberDetailsApiModel.getPayment_Verify();
        } else {
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            Payment_Verify = sharedPreferences.getString("Payment_Verify", "");

        }

        if (!TextUtils.isEmpty(Payment_Verify)&& Payment_Verify.equals("N")) {
            lay_main.setVisibility(View.GONE);
            lay_pending.setVisibility(View.VISIBLE);
        }





    }

    private void loadGif() {
        // Glide.with(this).load(R.drawable.courierorgg) .dontAnimate().into(iv_courierGif);
        Glide.with(context)
                .asGif()
                .load(R.drawable.memship) // Replace with a valid url
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
                .into(iv_memshipGif); // Replace with your ImageView id.

    }

    public void Upgrade(View view) {
        startActivity(new Intent(context, MembershipUpgradeActivity.class));
    }

    public void btn_back(View view) {
        finish();
    }

    public void btn_BackTOHome(View view) {
        startActivity(new Intent(context, HomeActivity.class));
        finishAffinity();
    }

}