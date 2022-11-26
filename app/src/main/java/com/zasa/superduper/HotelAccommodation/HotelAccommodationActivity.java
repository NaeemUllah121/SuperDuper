package com.zasa.superduper.HotelAccommodation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.zasa.superduper.CompanyCategoryListTypeWiseS.CompanyCategoryListApi;
import com.zasa.superduper.CompanyCategoryListTypeWiseS.CompanyCategoryListModel;
import com.zasa.superduper.MembershipInfoActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
import com.zasa.superduper.RegisterUser.AreaListApi;
import com.zasa.superduper.RegisterUser.AreaListApiModel;
import com.zasa.superduper.RegisterUser.CityListApi;
import com.zasa.superduper.RegisterUser.CityListApiModel;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelAccommodationActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;
    String sp_getCityId, sp_getAreaId, sp_getCountryId;

    ///for glowing effect
    View lay_glowingBtn, lay_membership;
    int int_Member_Type;

    private SpinnerDialog companyCategorySpinnerDialog, citySpinnerDialog, areaSpinnerDialog;

    ArrayList<CompanyCategoryListModel> companyCategoryListModelArrayList;
    ArrayList<CityListApiModel> cityListApiModelArrayList;
    ArrayList<AreaListApiModel> areaListApiModelArrayList;

    String st_getCityId, st_getCountryId, st_getAreaId, st_getCompany_Category_Code;
    String st_getCountryName, st_getCityName, st_getAreaName, st_Company_Type_Code;


    AutoCompleteTextView tv_category, et_Area, et_City, et_Country, tv_CheckinDate, tv_CheckoutDate;
    TextInputLayout til_selectCity, til_selectArea, til_selectCategory/*, et_Adults, et_Children, et_Rooms*/;
    TextInputEditText et_Adults, et_Children, et_Rooms;

    CheckBox check_box;
    String st_check_boxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_accommodation);

        context = HotelAccommodationActivity.this;

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        st_Company_Type_Code = getIntent().getStringExtra("Company_Type_Code");

        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            int_Member_Type = memberDetailsApiModel.getMember_Type();
            st_getAreaId = memberDetailsApiModel.getArea_Id();
            st_getCityId = memberDetailsApiModel.getCity_Id();
            st_getCountryId = memberDetailsApiModel.getCountry_Id();
            //Toast.makeText(context, "if", Toast.LENGTH_SHORT).show();
        } else {
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            int_Member_Type = sharedPreferences.getInt("Member_Type", 0);
            st_getAreaId = sharedPreferences.getString("Area_Id", "");
            st_getCityId = sharedPreferences.getString("City_Id", "");
            st_getCountryId = sharedPreferences.getString("Country_Id", "");
            //Toast.makeText(context, "else  "+st_getAreaId, Toast.LENGTH_SHORT).show();
        }

        //int_Member_Type=1;
        lay_glowingBtn = findViewById(R.id.lay_glowingBtn);
        //Lay_noItemInCompanyList = findViewById(R.id.noItemInCompanyList);
        lay_membership = findViewById(R.id.lay_membership);
        if (int_Member_Type == 1) {
            lay_membership.setVisibility(View.VISIBLE);
        } else {
            lay_membership.setVisibility(View.GONE);
        }


        et_City = findViewById(R.id.selectCity);
        et_Area = findViewById(R.id.selectArea);
        tv_category = findViewById(R.id.selectCategory);


        et_Adults = findViewById(R.id.et_Adults);
        et_Children = findViewById(R.id.et_Children);
        et_Rooms = findViewById(R.id.et_Rooms);


        tv_CheckinDate = findViewById(R.id.tv_CheckinDate);
        tv_CheckinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SlideDateTime(tv_CheckinDate);
                tv_CheckinDate.clearFocus();
                tv_CheckinDate.setError(null);

            }
        });

        tv_CheckoutDate = findViewById(R.id.tv_CheckoutDate);
        tv_CheckoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SlideDateTime(tv_CheckoutDate);
                tv_CheckoutDate.clearFocus();
                tv_CheckoutDate.setError(null);

            }
        });


        check_box = findViewById(R.id.check_box);
        if (check_box.isChecked()) {
            st_check_boxValue= check_box.getText().toString();
        }

        glowButtonAnimator();
        searchCity(st_getCountryId);
        //searchArea(st_getCityId);
        //searchCategory();

//        et_Area.setEnabled(false);
//        tv_category.setEnabled(false);


    }

    ////Search city spinner Dialog function
    private void searchCity(String st_getCountryId) {

        progressDialog.show();
        progressDialog.setMessage("Loading cities...");
        final ArrayList<String> cityNameList = new ArrayList<>();

        Call<CityListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCityListApi(st_getCountryId);

        call.enqueue(new Callback<CityListApi>() {
            @Override
            public void onResponse(@NonNull Call<CityListApi> call, @NonNull Response<CityListApi> response) {
                CityListApi cityListApi = response.body();
                if (response.isSuccessful()) {

                    if (cityListApi != null) {

                        if (cityListApi.getStatus() == 1) {
                            //progressDialog.dismiss();

                            cityListApiModelArrayList = cityListApi.getCitylist();
                            for (int i = 0; i < cityListApiModelArrayList.size(); i++) {
                                String city = cityListApiModelArrayList.get(i).getCity_Title();
                                cityNameList.add(city);

                                if (cityListApiModelArrayList.get(i).getCity_Id().equals(st_getCityId)) {//get city name according to city code
                                    st_getCityName = cityListApiModelArrayList.get(i).getCity_Title();
                                    et_City.setText(st_getCityName);
                                }

                            }

                            searchArea(st_getCityId);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + cityListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<CityListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        citySpinnerDialog = new SpinnerDialog(this, cityNameList,
                "Select or Search City", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        citySpinnerDialog.setCancellable(true); // for cancellable
        citySpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        citySpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);
                st_getCityId = cityListApiModelArrayList.get(position).getCity_Id();
                // Toast.makeText(context, " "+st_getCityId, Toast.LENGTH_SHORT).show();

                et_City.setText(item);

                et_City.clearFocus();
                et_City.setError(null);

                et_Area.getText().clear();
                tv_category.getText().clear();


                searchArea(st_getCityId);

            }
        });

        et_City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                citySpinnerDialog.showSpinerDialog();
            }
        });

        til_selectCategory = findViewById(R.id.til_selectCategory);
        til_selectCategory.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                citySpinnerDialog.showSpinerDialog();
            }
        });

        til_selectCity = findViewById(R.id.til_selectCity);
        til_selectCity.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                citySpinnerDialog.showSpinerDialog();
            }
        });


    }

    ////Search area spinner Dialog function
    private void searchArea(String st_getCityId) {

        progressDialog.setMessage("Loading areas...");
        progressDialog.show();
        final ArrayList<String> areaNameList = new ArrayList<>();

        Call<AreaListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mAreaListApi(st_getCityId);

        call.enqueue(new Callback<AreaListApi>() {
            @Override
            public void onResponse(@NonNull Call<AreaListApi> call, @NonNull Response<AreaListApi> response) {
                AreaListApi areaListApi = response.body();
                if (response.isSuccessful()) {

                    if (areaListApi != null) {

                        if (areaListApi.getStatus() == 1) {

                            //progressDialog.dismiss();

                            areaListApiModelArrayList = areaListApi.getArealist();
                            for (int i = 0; i < areaListApiModelArrayList.size(); i++) {
                                String area = areaListApiModelArrayList.get(i).getArea_Title();
                                areaNameList.add(area);

                                if (areaListApiModelArrayList.get(i).getArea_Id().equals(st_getAreaId)) {//get area name according to area code
                                    st_getAreaName = areaListApiModelArrayList.get(i).getArea_Title();
                                    et_Area.setText(st_getAreaName);
                                }
                            }

                            searchCategory();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + areaListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<AreaListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        areaSpinnerDialog = new SpinnerDialog(this, areaNameList,
                "Select or Search Area", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        areaSpinnerDialog.setCancellable(true); // for cancellable
        areaSpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        areaSpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);
                st_getAreaId = areaListApiModelArrayList.get(position).getArea_Id();
                // Toast.makeText(context, ""+st_getAreaId, Toast.LENGTH_SHORT).show();
                et_Area.setText(item);

                et_Area.clearFocus();
                et_Area.setError(null);

                tv_category.getText().clear();

                searchCategory();
            }
        });

        et_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                areaSpinnerDialog.showSpinerDialog();
            }
        });

        til_selectArea = findViewById(R.id.til_selectArea);
        til_selectArea.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                areaSpinnerDialog.showSpinerDialog();
            }
        });
    }

    ////Search category spinner Dialog function
    private void searchCategory() {
        progressDialog.setMessage("Loading categories...");
        progressDialog.show();
        final ArrayList<String> categoryNameList = new ArrayList<>();

        Call<CompanyCategoryListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCompanyCategoryListApi(st_Company_Type_Code);

        call.enqueue(new Callback<CompanyCategoryListApi>() {
            @Override
            public void onResponse(@NonNull Call<CompanyCategoryListApi> call, @NonNull Response<CompanyCategoryListApi> response) {
                CompanyCategoryListApi areaListApi = response.body();
                if (response.isSuccessful()) {

                    if (areaListApi != null) {

                        if (areaListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            companyCategoryListModelArrayList = areaListApi.getCompanyCategoryList();
                            for (int i = 0; i < companyCategoryListModelArrayList.size(); i++) {
                                String name = companyCategoryListModelArrayList.get(i).getCompany_Category_Name();
                                categoryNameList.add(name);

                            /*    if (companyCategoryListModelArrayList.get(i).getArea_Id().equals(sp_getAreaId)) {//get area name according to area code

                                    st_getAreaName = companyCategoryListModelArrayList.get(i).getArea_Title();
                                    et_Area.setText(st_getAreaName);

                                }*/


                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + areaListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<CompanyCategoryListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        companyCategorySpinnerDialog = new SpinnerDialog(this, categoryNameList,
                "Select or Search Category", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        companyCategorySpinnerDialog.setCancellable(true); // for cancellable
        companyCategorySpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        companyCategorySpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);
                st_getCompany_Category_Code = companyCategoryListModelArrayList.get(position).getCompany_Category_Code();

                tv_category.setText(item);


                tv_category.clearFocus();
                tv_category.setError(null);
            }
        });

        tv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                companyCategorySpinnerDialog.showSpinerDialog();
            }
        });

        til_selectCategory = findViewById(R.id.til_selectCategory);
        til_selectCategory.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                companyCategorySpinnerDialog.showSpinerDialog();
            }
        });
    }


    public void btn_search(View view) {

        String st_City = et_City.getText().toString().trim();
        String st_Area = et_Area.getText().toString().trim();
        String st_category = tv_category.getText().toString().trim();

        String st_CheckinDate = tv_CheckinDate.getText().toString().trim();
        String st_CheckoutDate = tv_CheckoutDate.getText().toString().trim();
        String st_Adults = et_Adults.getText().toString().trim();
        String st_Children = et_Children.getText().toString().trim();
        String st_Rooms = et_Rooms.getText().toString().trim();

        if (TextUtils.isEmpty(st_City)) {
            et_City.requestFocus();
            et_City.setError("Please select your city!");
            return;
        }
        if (TextUtils.isEmpty(st_Area)) {
            et_Area.requestFocus();
            et_Area.setError("Please select your area!");
            return;
        }
        if (TextUtils.isEmpty(st_category)) {
            tv_category.requestFocus();
            tv_category.setError("Please select category!");
            return;
        }

        if (TextUtils.isEmpty(st_CheckinDate)) {
            tv_CheckinDate.requestFocus();
            tv_CheckinDate.setError("Please select Check-in date!");
            return;
        }
        if (TextUtils.isEmpty(st_CheckoutDate)) {
            tv_CheckoutDate.requestFocus();
            tv_CheckoutDate.setError("Please select Check-out date!");
            return;
        }
        if (TextUtils.isEmpty(st_Adults)) {
            et_Adults.requestFocus();
            et_Adults.setError("Please enter value!");
            return;
        }
        if (TextUtils.isEmpty(st_Children)) {
            et_Children.requestFocus();
            et_Children.setError("Please enter value!");
            return;
        }
        if (TextUtils.isEmpty(st_Rooms)) {
            et_Rooms.requestFocus();
            et_Rooms.setError("Please enter value!");
            return;
        }

        /*Intent intent = new Intent(context, CompanyListCategoryWiseActivity.class);
        intent.putExtra("st_getCityId", st_getCityId);
        intent.putExtra("st_getAreaId", st_getAreaId);
        intent.putExtra("st_getCompany_Category_Code", st_getCompany_Category_Code);
        intent.putExtra("st_Category_Name", tv_category.getText().toString());
        startActivity(intent);*/




    }


    public void SlideDateTime(TextView textView) {


        //SimpleDateFormat mFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        SlideDateTimeListener listener = new SlideDateTimeListener() {

            @Override
            public void onDateTimeSet(Date date) {


                textView.setText(mFormatter.format(date));

            }

            // Optional cancel listener
            @Override
            public void onDateTimeCancel() {

            }
        };



        new SlideDateTimePicker.Builder(getSupportFragmentManager())
                //new SlideDateTimePicker.Builder(getParentFragmentManager())
                .setListener(listener)
                .setInitialDate(new Date())
                .setMinDate(new Date())
                //.setMaxDate(maxDate)
                //.setIs24HourTime(true)
                .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                .setIndicatorColor(Color.parseColor("#990000"))
                .build()
                .show();


    }




    public void btn_back(View view) {
        finish();
    }

    public void lay_UpgradeMembership(View view) {

        startActivity(new Intent(context, MembershipInfoActivity.class));
    }

    private void glowButtonAnimator() {

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(lay_glowingBtn, "alpha", .5f, .1f);
        fadeOut.setDuration(300);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(lay_glowingBtn, "alpha", .1f, .5f);
        fadeIn.setDuration(300);

        AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });

        mAnimationSet.start();
    }
}