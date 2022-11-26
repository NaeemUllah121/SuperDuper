package com.zasa.superduper;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.SharedPrefManager;

public class IntviteActivity extends AppCompatActivity {



    Context context;
    SharedPrefManager sharedPrefManager;

    ImageView btn_back;
    Button btn_InviteFriends,btn_ReferralCode;
    TextInputEditText et_ReferralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intvite);



        context = IntviteActivity.this;
        sharedPrefManager = new SharedPrefManager(context);

        btn_back=findViewById(R.id.btn_back);
        btn_InviteFriends=findViewById(R.id.btn_InviteFriends);
        btn_ReferralCode=findViewById(R.id.btn_ReferralCode);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, HomeActivity.class));
                finish();
            }
        });


        btn_InviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.shareApp(context);
            }
        });
        btn_ReferralCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(IntviteActivity.this,HomeActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}