package com.zasa.superduper.Redeem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.SharedPrefManager;

public class RedeemHistoryActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;


    TabLayout tabLayout;
    ViewPager2 viewPager;
    TabLayoutAdapter tabLayoutAdapter;
    TextView tv_Notification_Badge;
    TabLayoutMediator tabLayoutMediator;
    public static BadgeDrawable badgeDrawable;




//    RecyclerView rcv_coinsHistory;
//    LinearLayoutManager linearLayoutManager;
//    CoinsHistoryAdapter coinsHistoryAdapter;
//    ArrayList<Model> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_history);


        context = RedeemHistoryActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);




        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.tab_view_pager);
        viewPager.setAdapter(new TabLayoutAdapter(this));

        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        tab.setText("Coins");
                        tab.setIcon(R.drawable.ic_coins);

                        badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        badgeDrawable.setVisible(true);
                        // badgeDrawable.setNumber(100);

                        break;


                    case 1:
                        tab.setText("Coupon");
                        tab.setIcon(R.drawable.ic_couponbtn);

                        badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        badgeDrawable.setVisible(true);
                        //badgeDrawable.setNumber(100);
                        //badgeDrawable.setMaxCharacterCount(3);

                        break;

                }


            }
        });
        tabLayoutMediator.attach();

        
//        rcv_coinsHistory = findViewById(R.id.rcv_coinsHistory);
//        rcv_coinsHistory.setNestedScrollingEnabled(true);
//
//        linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rcv_coinsHistory.setLayoutManager(linearLayoutManager);
//
//        coinsHistoryAdapter = new CoinsHistoryAdapter(models, context);
//        rcv_coinsHistory.setAdapter(coinsHistoryAdapter);
//
//
//
//        models.add(new Model("1000","0","12-2-22","124435"));
//        models.add(new Model("2000","0","12-2-22","124435"));
//        models.add(new Model("0","500","12-2-22","124435"));
//        models.add(new Model("100","0","12-2-22","124435"));
//        models.add(new Model("0","110","12-2-22","124435"));
//        models.add(new Model("0","500","12-2-22","124435"));
//        models.add(new Model("1000","0","12-2-22","124435"));
//        models.add(new Model("0","100","12-2-22","124435"));
//
//        coinsHistoryAdapter.notifyItemInserted(models.size());
    }

    public void btn_back(View view) {
        finish();
    }

    public void btn_generateCoupon(View view) {

       // startActivity(new Intent(context, CouponActivity.class));
    }
}