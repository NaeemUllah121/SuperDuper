package com.zasa.superduper.CourierService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.zasa.superduper.CourierService.Models.DeliveryTypeListApi;
import com.zasa.superduper.CourierService.Models.DeliveryTypeListModel;
import com.zasa.superduper.CourierService.Models.ParcelTypeListApi;
import com.zasa.superduper.CourierService.Models.ParcelTypelistModel;
import com.zasa.superduper.CourierService.Models.SubmitDeliveryRequestApi;
import com.zasa.superduper.CourierService.Models.SubmitDeliveryRequestList;
import com.zasa.superduper.CourierService.Models.SubmitDeliveryRequestListModel;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
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

public class DakiyaActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;

    TextInputEditText et_SenderName, et_SenderMobile, et_SenderCNIC, et_SenderAddress,
            et_RecipientName, et_RecipientMobile, et_RecipientAddress, et_NOofparcel, et_remarks;

    private SpinnerDialog ParcelType_SpinnerDialog, deliveryType_SpinnerDialog;
    ArrayList<ParcelTypelistModel> parcelTypelistModelArrayList = new ArrayList<>();
    ArrayList<DeliveryTypeListModel> deliveryTypeListModelArrayList = new ArrayList<>();
    ArrayList<SubmitDeliveryRequestListModel> submitDeliveryRequestListModelArrayList = new ArrayList<>();


    TextView  tv_ParcelDeliverDate, tv_header_detail;
    AutoCompleteTextView tv_ParcelPickupDate, tv_Parcel_Type, tv_delivery_Type;

    String st_LB_Points, st_Member_Unique, st_FName, st_LName, st_Member_LB_Card_Id, st_mobile, st_Member_CNIC, currentDateTime;
    int int_getDelivery_Type, int_getParcel_Type;
    Button btn_submitDeliveryReq;
    ImageView btn_back, btn_deliveryHistory;
    String Vendor_Unique, Vendor_Title, Vendor_Full_Name, Vendor_Mobile;

    private AdView mAdView;

    RadioGroup RG_DonationStatus;
    RadioButton RB_DonationStatus;
    String st_SelectedMethod;
    TextInputEditText et_CNICDonation, et_VoucherIdDonation;
    TextInputLayout lay_CNICDonation, lay_VoucherIdDonation;

    View lay_donation, lay_Parceldelivery;
    CardView cardView;
    ImageView iv_expandIcon;
    View lay_expandableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dakiya);


        context = DakiyaActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        currentDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date());

        ////banners ads///
        mAdView = findViewById(R.id.adView);
        bannerADAdMod();


        ///DonationStatusView();


        //get Member Details from shared Preference
        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            st_LB_Points = memberDetailsApiModel.getLB_Points();
            st_Member_Unique = memberDetailsApiModel.getMember_Unique();
            st_FName = memberDetailsApiModel.getMember_FName();
            st_LName = memberDetailsApiModel.getMember_LName();
            st_mobile = memberDetailsApiModel.getMember_Mobile();
            st_Member_CNIC = memberDetailsApiModel.getMember_CNIC();
            st_Member_LB_Card_Id = memberDetailsApiModel.getMember_LB_Card_Id();
            if (TextUtils.isEmpty(st_mobile)) {
                st_mobile = memberDetailsApiModel.getMember_LoginID();

            }
        } else {
            //////login shared pref
            SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            st_LB_Points = sharedPreferences.getString("LB_Points", "");
            st_FName = sharedPreferences.getString("Member_FName", "");
            st_LName = sharedPreferences.getString("Member_LName", "");
            st_Member_Unique = sharedPreferences.getString("Member_Unique", "");
            st_mobile = sharedPreferences.getString("Member_Mobile", "");
            st_Member_CNIC = sharedPreferences.getString("Member_CNIC", "");

        }

        //////these value are coming from CourierServiceRcvAdapter
        Vendor_Unique = getIntent().getStringExtra("Vendor_Unique");
        Vendor_Title = getIntent().getStringExtra("Vendor_Title");
        Vendor_Full_Name = getIntent().getStringExtra("Vendor_Full_Name");
        Vendor_Mobile = getIntent().getStringExtra("Vendor_Mobile");


        et_SenderName = findViewById(R.id.SenderName);
        et_SenderMobile = findViewById(R.id.SenderMobile);
        et_SenderCNIC = findViewById(R.id.SenderCNIC);
        et_SenderAddress = findViewById(R.id.SenderAddress);
        et_RecipientName = findViewById(R.id.RecipientName);
        et_RecipientMobile = findViewById(R.id.RecipientMobile);
        et_RecipientAddress = findViewById(R.id.RecipientAddress);

        tv_Parcel_Type = findViewById(R.id.tv_Parcel_Type);
        tv_delivery_Type = findViewById(R.id.tv_delivery_Type);
        et_NOofparcel = findViewById(R.id.et_NOofparcel);
        et_remarks = findViewById(R.id.et_remarks);
        tv_ParcelPickupDate = findViewById(R.id.tv_ParcelPickupDate);
        tv_header_detail = findViewById(R.id.tv_header_detail);

        et_SenderName.setText(st_FName + " " + st_LName);
        et_SenderMobile.setText(st_mobile);
        et_SenderCNIC.setText(st_Member_CNIC);
        tv_ParcelPickupDate.setText(currentDateTime);//
        tv_header_detail.setText(Vendor_Title);


        cnicMasking();
        deliveryTypeApi();
        parcelTypeApi();


        tv_ParcelPickupDate = findViewById(R.id.tv_ParcelPickupDate);
        tv_ParcelPickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideDateTime(tv_ParcelPickupDate);
                tv_ParcelPickupDate.clearFocus();
                tv_ParcelPickupDate.setError(null);
            }
        });

        /* set visibility gone in xml*/
        tv_ParcelDeliverDate = findViewById(R.id.tv_ParcelDeliverDate);
        tv_ParcelDeliverDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideDateTime(tv_ParcelDeliverDate);
                tv_ParcelDeliverDate.clearFocus();
                tv_ParcelDeliverDate.setError(null);
            }
        });


    }

//    private void DonationStatusView() {
//
//        lay_donation = findViewById(R.id.lay_donation);
//        lay_Parceldelivery = findViewById(R.id.lay_Parceldelivery);
//
//
//        RG_DonationStatus = findViewById(R.id.RG_DonationStatus);
//
//        lay_CNICDonation = findViewById(R.id.lay_CNICDonation);
//        et_CNICDonation = findViewById(R.id.et_CNICDonation);
//        lay_VoucherIdDonation = findViewById(R.id.lay_VoucherIdDonation);
//        et_VoucherIdDonation = findViewById(R.id.et_VoucherIdDonation);
//
//
//        RG_DonationStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                // int onlinePaymentId = RG_PaymentMethods.getCheckedRadioButtonId();
//                RB_DonationStatus = RG_DonationStatus.findViewById(checkedId);
//                st_SelectedMethod = RB_DonationStatus.getText().toString();
//
//                if (st_SelectedMethod.equals("CNIC")) {
//                    lay_VoucherIdDonation.setVisibility(View.GONE);
//                    lay_CNICDonation.setVisibility(View.VISIBLE);
//                } else {
//                    lay_VoucherIdDonation.setVisibility(View.VISIBLE);
//                    lay_CNICDonation.setVisibility(View.GONE);
//
//                }
//
//
//                // Toast.makeText(context, "" + st_OnlineSelectedMethod, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        final RadioRealButton button1 = findViewById(R.id.button1);
//        final RadioRealButton button2 = findViewById(R.id.button2);
//
//        RadioRealButtonGroup group = findViewById(R.id.group);
//        group.setOnClickedButtonPosition(new RadioRealButtonGroup.OnClickedButtonPosition() {
//            @Override
//            public void onClickedButtonPosition(int position) {
//                //Toast.makeText(DonationActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
//                //button1.getImageView().setImageResource(R.drawable.b2);
//                //button2.getImageView().setImageResource(R.drawable.b2);
//
//                if (position == 0) {
//                    lay_Parceldelivery.setVisibility(View.VISIBLE);
//                    lay_donation.setVisibility(View.GONE);
//
//                } else {
//                    lay_donation.setVisibility(View.VISIBLE);
//                    lay_Parceldelivery.setVisibility(View.GONE);
//
//                }
//            }
//        });
//
//    }

    public void btn_submitDeliveryReq(View view) {


        String st_SenderName = et_SenderName.getText().toString();
        String st_SenderMobile = et_SenderMobile.getText().toString().trim();
        String st_SenderCNIC = et_SenderCNIC.getText().toString().trim();
        String st_SenderAddress = et_SenderAddress.getText().toString();
        String st_RecipientName = et_RecipientName.getText().toString();
        String st_RecipientMobile = et_RecipientMobile.getText().toString().trim();
        String st_RecipientAddress = et_RecipientAddress.getText().toString();
        String st_ParcelPickupDate = tv_ParcelPickupDate.getText().toString();
        String st_ParcelDeliverDate = tv_ParcelDeliverDate.getText().toString();
        String st_delivery_Type = tv_delivery_Type.getText().toString();
        String st_Parcel_Type = tv_Parcel_Type.getText().toString();
        String st_NOofparcel = et_NOofparcel.getText().toString();
        String st_remarks = et_remarks.getText().toString();


        if (TextUtils.isEmpty(st_SenderName)) {
            et_SenderName.requestFocus();
            et_SenderName.setError("Enter sender name!");
            return;
        }
        if (TextUtils.isEmpty(st_SenderMobile) || st_SenderMobile.length() != 11) {
            et_SenderMobile.requestFocus();
            et_SenderMobile.setError("Enter correct sender mobile number!");
            return;
        }
        if (TextUtils.isEmpty(st_SenderCNIC) || st_SenderCNIC.length() != 15) {
            et_SenderCNIC.requestFocus();
            et_SenderCNIC.setError("Enter correct CNIC!");
            return;
        }
        if (TextUtils.isEmpty(st_SenderAddress)) {
            et_SenderAddress.requestFocus();
            et_SenderAddress.setError("Enter sender address!");
            return;
        }
        if (TextUtils.isEmpty(st_ParcelPickupDate)) {
            tv_ParcelPickupDate.requestFocus();
            tv_ParcelPickupDate.setError("Enter Parcel Pickup Date!");
            return;
        }
        if (TextUtils.isEmpty(st_Parcel_Type)) {
            tv_Parcel_Type.requestFocus();
            tv_Parcel_Type.setError("Select Parcel Type!");
            return;
        }
        if (TextUtils.isEmpty(st_delivery_Type)) {
            tv_delivery_Type.requestFocus();
            tv_delivery_Type.setError("Select Deliver Type!");
            return;
        }
        if (TextUtils.isEmpty(st_NOofparcel) || Integer.parseInt(st_NOofparcel) < 1) {
            et_NOofparcel.requestFocus();
            et_NOofparcel.setError("Set parcel qty!");
            return;
        }


        if (TextUtils.isEmpty(st_RecipientName)) {
            et_RecipientName.requestFocus();
            et_RecipientName.setError("Enter Recipient Name!");
            return;
        }
        if (TextUtils.isEmpty(st_RecipientMobile) || st_RecipientMobile.length() != 11) {
            et_RecipientMobile.requestFocus();
            et_RecipientMobile.setError("Enter correct recipient mobile number!");
            return;
        }
        if (TextUtils.isEmpty(st_RecipientAddress)) {
            et_RecipientAddress.requestFocus();
            et_RecipientAddress.setError("Enter recipient address!");
            return;
        }
      /* set visibility gone
      if (TextUtils.isEmpty(st_ParcelDeliverDate)) {
            tv_ParcelDeliverDate.requestFocus();
            tv_ParcelDeliverDate.setError("Enter Parcel Deliver Date!");
            return;
        }*/


        submitDeliveryRequestListModelArrayList.add(new SubmitDeliveryRequestListModel(
                currentDateTime, Vendor_Unique, Vendor_Title, st_Member_Unique, st_Member_LB_Card_Id,
                st_SenderName, st_SenderMobile, st_SenderCNIC, st_SenderAddress, st_ParcelPickupDate, st_RecipientName,
                st_RecipientMobile, st_RecipientAddress, int_getDelivery_Type, int_getParcel_Type, st_remarks, Integer.parseInt(st_NOofparcel), "",
                "", 0, 0
        ));


        callSubmitDeliveryRequestApi();

    }

    private void callSubmitDeliveryRequestApi() {


        progressDialog.show();
        SubmitDeliveryRequestList submitDeliveryRequestList = new SubmitDeliveryRequestList(submitDeliveryRequestListModelArrayList);
        Call<SubmitDeliveryRequestApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mSubmitDeliveryRequestApi(submitDeliveryRequestList);

        call.enqueue(new Callback<SubmitDeliveryRequestApi>() {
            @Override
            public void onResponse(@NonNull Call<SubmitDeliveryRequestApi> call, @NonNull Response<SubmitDeliveryRequestApi> response) {

                SubmitDeliveryRequestApi submitDeliveryRequestApi = response.body();

                if (response.isSuccessful()) {
                    if (submitDeliveryRequestApi != null) {
                        if (submitDeliveryRequestApi.getStatus() == 1) {
                            progressDialog.dismiss();


                            Toast.makeText(context, " " + submitDeliveryRequestApi.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, HomeActivity.class);
                            startActivity(intent);
                            finishAffinity();


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, " " + submitDeliveryRequestApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<SubmitDeliveryRequestApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    ////parcel type spinner Dialog function
    private void parcelTypeApi() {

        progressDialog.show();
        ArrayList<String> spinnerList = new ArrayList<>();

        Call<ParcelTypeListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mParcelTypeListApi();
        call.enqueue(new Callback<ParcelTypeListApi>() {
            @Override
            public void onResponse(@NonNull Call<ParcelTypeListApi> call, @NonNull Response<ParcelTypeListApi> response) {
                progressDialog.dismiss();
                ParcelTypeListApi parcelTypeListApi = response.body();
                if (response.isSuccessful()) {
                    if (parcelTypeListApi != null) {
                        if (parcelTypeListApi.getStatus() == 1) {

                            parcelTypelistModelArrayList = parcelTypeListApi.getParcel_Typelist();
                            for (int i = 0; i < parcelTypelistModelArrayList.size(); i++) {

                                String Parcel_Title = parcelTypelistModelArrayList.get(i).getParcel_Type_Title();
                                spinnerList.add(Parcel_Title);

                            }


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + parcelTypeListApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<ParcelTypeListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        ParcelType_SpinnerDialog = new SpinnerDialog(this, spinnerList,
                "Select parcel type", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        ParcelType_SpinnerDialog.setCancellable(true); // for cancellable
        ParcelType_SpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        ParcelType_SpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);

                int_getParcel_Type = parcelTypelistModelArrayList.get(position).getParcel_Type();
                tv_Parcel_Type.setText(item);

                tv_Parcel_Type.clearFocus();
                tv_Parcel_Type.setError(null);


            }
        });

        tv_Parcel_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParcelType_SpinnerDialog.showSpinerDialog();
            }
        });


    }

    ////delivery type spinner Dialog function
    private void deliveryTypeApi() {

        progressDialog.show();
        ArrayList<String> spinnerList = new ArrayList<>();

        Call<DeliveryTypeListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mDeliveryTypeListApi();
        call.enqueue(new Callback<DeliveryTypeListApi>() {
            @Override
            public void onResponse(@NonNull Call<DeliveryTypeListApi> call, @NonNull Response<DeliveryTypeListApi> response) {
                progressDialog.dismiss();
                DeliveryTypeListApi salutationListApi = response.body();
                if (response.isSuccessful()) {
                    if (salutationListApi != null) {
                        if (salutationListApi.getStatus() == 1) {

                            deliveryTypeListModelArrayList = salutationListApi.getDelivery_Typelist();
                            for (int i = 0; i < deliveryTypeListModelArrayList.size(); i++) {

                                String Title = deliveryTypeListModelArrayList.get(i).getDelivery_Type_Title();
                                spinnerList.add(Title);

                            }


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + salutationListApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<DeliveryTypeListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        deliveryType_SpinnerDialog = new SpinnerDialog(this, spinnerList,
                "Select delivery type", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        deliveryType_SpinnerDialog.setCancellable(true); // for cancellable
        deliveryType_SpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        deliveryType_SpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);

                int_getDelivery_Type = deliveryTypeListModelArrayList.get(position).getDelivery_Type();
                tv_delivery_Type.setText(item);

                tv_delivery_Type.clearFocus();
                tv_delivery_Type.setError(null);


            }
        });

        tv_delivery_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deliveryType_SpinnerDialog.showSpinerDialog();
            }
        });


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

    private void cnicMasking() {

        et_SenderCNIC.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        et_SenderCNIC.addTextChangedListener(new TextWatcher() {
            int len = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                String val = et_SenderCNIC.getText().toString();
                if ((val.length() == 5 && len < val.length()) || (val.length() == 13 && len < val.length())) {
                    et_SenderCNIC.append("-");
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String str = et_SenderCNIC.getText().toString();
                len = str.length();

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }

    public void btn_deliveryHistory(View view) {
        Intent intent = new Intent(context, DeliveryRequestHistoryActivity.class);
        intent.putExtra("Vendor_Unique", Vendor_Unique);
        intent.putExtra("Vendor_Title", Vendor_Title);
        intent.putExtra("Vendor_Full_Name", Vendor_Full_Name);
        intent.putExtra("Vendor_Mobile", Vendor_Mobile);
        intent.putExtra("st_Member_Unique", st_Member_Unique);
        startActivity(intent);
    }

    public void btn_back(View view) {
        finish();
    }


    private void bannerADAdMod() {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        // mAdView = getView().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

//    public void expandableView(View view) {
//
//        et_CNICDonation = findViewById(R.id.et_CNICDonation);
//        iv_expandIcon = findViewById(R.id.iv_expandView);
//        cardView = findViewById(R.id.cardView);
//        lay_expandableView = findViewById(R.id.lay_expandableView);
//
//        if (lay_expandableView.getVisibility() == View.GONE) {
//            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//            lay_expandableView.setVisibility(View.VISIBLE);
//            iv_expandIcon.setRotation(180);
//
//        } else {
//            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//            lay_expandableView.setVisibility(View.GONE);
//            iv_expandIcon.setRotation(360);
//
//        }
//    }
}