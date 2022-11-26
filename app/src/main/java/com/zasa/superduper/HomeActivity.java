package com.zasa.superduper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.zasa.superduper.Cart.CartActivity;
import com.zasa.superduper.FAQs.FAQsActivity;
import com.zasa.superduper.Home.HomeFragment;
import com.zasa.superduper.Home.WebViewActivity;
import com.zasa.superduper.Profile.MemberDetailsApi;
import com.zasa.superduper.Profile.ProfileActivity;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.SQLite.CartSQLiteDB;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.PrefsManager;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    ArrayList<Fragment> fragments = new ArrayList<>();
    NavigationView navigationView;
    TextView headerUsername, abUserName,cartTotalItemQty;
    DrawerLayout drawer;
    View header;
    Context context;
    FrameLayout btn_cart;
    CircleImageView userHeaderImage;
    SharedPrefManager sharedPrefManager;
    String Privacy_Policy_Url, Terms_Conditions_Url, Contact_Number, Support_Email, Website_Url;

    SharedPreferences sharedPreferences;
    public static String sp_memberID;
    String st_fName, st_lastName,
            st_Api_Member_Image_String;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupDrawer();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        floatingActionButton = findViewById(R.id.fab);
        header = navigationView.getHeaderView(0);
        bottomNavigationView.setBackground(null);
        headerUsername = header.findViewById(R.id.tv_haader_name);
        userHeaderImage = header.findViewById(R.id.ivUserHeaderImage);
        abUserName = findViewById(R.id.ab_username);
        cartTotalItemQty=findViewById(R.id.cartTotalItemQty);
        btn_cart=findViewById(R.id.btn_cart);



        getMemberDetailsApi();

        showUserImg();

        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        fragments.add(new HomeFragment());

//        fragments.add(new MoreFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragments.get(0)).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragments.get(0)).commit();
            } else if (item.getItemId() == R.id.invite) {
                startActivity(new Intent(HomeActivity.this, IntviteActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            } else if (item.getItemId() == R.id.notifications) {
                startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            } else if (item.getItemId() == R.id.more) {
                startActivity(new Intent(HomeActivity.this, MoreActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragments.get(1)).commit();
            }
            return true;
        });


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new HomeFragment());
        transaction.commit();


        context = HomeActivity.this;
        sharedPrefManager = new SharedPrefManager(context);
        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel != null) {
            Privacy_Policy_Url = appversionListModel.getPrivacy_Policy_Url();
            Contact_Number = appversionListModel.getContact_Number();
            Support_Email = appversionListModel.getSupport_Email();
            Website_Url = appversionListModel.getWebsite_Url();
            Terms_Conditions_Url = appversionListModel.getTerms_Conditions_Url();
        }

        getItemQtyFromSQLite();

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        });

    }

    public void setupDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
        } else if (id == R.id.nav_faqs) {
            Intent intent = new Intent(HomeActivity.this, FAQsActivity.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);

        } else if (id == R.id.nav_privacy_policy) {

            Intent intent1 = new Intent(context, WebViewActivity.class);
            intent1.putExtra("URL", Privacy_Policy_Url);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent1);
            finish();
        } else if (id == R.id.nav_terms_and_cond) {

            Intent intent1 = new Intent(context, WebViewActivity.class);
            intent1.putExtra("URL", Terms_Conditions_Url);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent1);
            finish();
        } else if (id == R.id.nav_cantactus) {
            Intent intent = new Intent(HomeActivity.this, ContactUsActivity.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    public void QrBtn(View view) {
//    }

//    @Override
//    public void onClick(View view) {
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        getItemQtyFromSQLite();
    }

    private void getMemberDetailsApi() {
        Call<MemberDetailsApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mMemberDetailsApi(PrefsManager.getMemberID());
        call.enqueue(new Callback<MemberDetailsApi>() {
            @Override
            public void onResponse(@NonNull Call<MemberDetailsApi> call, @NonNull Response<MemberDetailsApi> response) {

                MemberDetailsApi memberDetailsApi = response.body();
                if (response.isSuccessful()) {
                    if (memberDetailsApi != null) {
                        if (memberDetailsApi.getStatus() == 1) {

                            st_Api_Member_Image_String = memberDetailsApi.getMemberDetails().getMember_Image_String();
                            st_fName = memberDetailsApi.getMemberDetails().getMember_FName();
                            st_lastName = memberDetailsApi.getMemberDetails().getMember_LName();
                            st_Api_Member_Image_String = memberDetailsApi.getMemberDetails().getMember_Image_String();
                            showUserImg();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MemberDetailsApi> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showUserImg() {

        if (TextUtils.isEmpty(st_Api_Member_Image_String) || st_Api_Member_Image_String.length() < 200) {
            userHeaderImage.setImageResource(R.drawable.noimg);
            //iv_userImg.setBackgroundResource(R.drawable.noimg);

        } else {
            //Bitmap bitmap=  decodeToBase64(st_Api_Member_Image_String);
            Bitmap bitmap = Constant.decodeToBase64(st_Api_Member_Image_String, context);
            userHeaderImage.setImageBitmap(bitmap);
            headerUsername.setText(st_fName + " " + st_lastName);
            abUserName.setText(st_fName + " " + st_lastName);
        }
    }

    public void getItemQtyFromSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();
        if (cursor.getCount() == 0) {
            //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            //Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        int sql_Qty = 0;
        while (cursor.moveToNext()) {
            sql_Qty = sql_Qty + Integer.parseInt(cursor.getString(4));
        }

        //  Toast.makeText(this, "sql_Qty " + sql_Qty, Toast.LENGTH_SHORT).show();
        // tv_CartTotalItemQty = findViewById(R.id.cartTotalItemQty);

        if (sql_Qty > 0) {
            cartTotalItemQty.setVisibility(View.VISIBLE);
            cartTotalItemQty.setText("" + sql_Qty);
        } else {
            cartTotalItemQty.setVisibility(View.GONE);
        }


    }



}