package com.zasa.superduper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.Fragments.DesignationListApi;
import com.zasa.superduper.Fragments.DesignationListModel;
import com.zasa.superduper.Fragments.ProfessionListApi;
import com.zasa.superduper.Fragments.ProfessionListModel;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.Profile.UpdateMemberApi;
import com.zasa.superduper.RegisterUser.AreaListApi;
import com.zasa.superduper.RegisterUser.AreaListApiModel;
import com.zasa.superduper.RegisterUser.CityListApi;
import com.zasa.superduper.RegisterUser.CityListApiModel;
import com.zasa.superduper.RegisterUser.CountryListApiModel;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MembershipUpgradeActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;


    CardView cardview_payment;
    View lay_paymentOptions, iv_expandView, lay_pending, lay_main;
    TextView tv_PaymentTitle;
    TextInputEditText et_mFirstName, et_mTransactionID, et_mLastName, et_mPhone, et_mEmail, et_mAddress;
    String st_Phone, st_Member_Unique, st_FirstName, st_LastName, st_Email, st_getCityName, st_getAreaName, st_Country, st_Address, st_DOB, st_Member_Gender;
    int getMember_Profession, getMember_Designation;

    RadioGroup RG_mGender, RG_MembershipPaymentMethods;
    RadioButton rb_SelectedGender;
    String st_Gender;
    int int_selectedPaymentMethod = 0;
    private SpinnerDialog ProfessionSpinnerDialog, DesignationSpinnerDialog, citySpinnerDialog, areaSpinnerDialog;

    ArrayList<CountryListApiModel> countryListApiArrayListModel;
    ArrayList<CityListApiModel> cityListApiModelArrayList;
    ArrayList<AreaListApiModel> areaListApiModelArrayList;
    ArrayList<ProfessionListModel> professionListModelArrayList;
    ArrayList<DesignationListModel> designationListModelArrayList;

    String st_getCityId, st_getCountryId, st_getAreaId;
    DatePickerDialog.OnDateSetListener setListener;

    AutoCompleteTextView tv_mDOB, tv_mArea, tv_mCity, et_Country, tv_mProfession, tv_mDesignation;
    ImageView btn_back;
    double Membership_Charges;
    TextView tv_MembershipFees;
    ImageView iv_screenshot;
    Uri Uri_screenshot;

    String st_mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_upgrade);

        context = MembershipUpgradeActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);


        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel == null) {
            Toast.makeText(context, "App Info Not Found!", Toast.LENGTH_SHORT).show();
        } else {
            Membership_Charges = appversionListModel.getMembership_Charges();
        }
        tv_MembershipFees = findViewById(R.id.tv_MembershipFees);

        String FormattedAmount = Constant.getFormattedAmount(Membership_Charges, context);
        tv_MembershipFees.setText(FormattedAmount);


        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            st_Member_Unique = memberDetailsApiModel.getMember_Unique();
            st_FirstName = memberDetailsApiModel.getMember_FName();
            st_LastName = memberDetailsApiModel.getMember_LName();
            st_Member_Gender = memberDetailsApiModel.getMember_Gender();
            st_Phone = memberDetailsApiModel.getMember_Mobile();
            st_DOB = memberDetailsApiModel.getMember_DOB();
            st_Email = memberDetailsApiModel.getMember_Email();
            st_Address = memberDetailsApiModel.getMember_Address();
            st_getAreaId = memberDetailsApiModel.getArea_Id();
            st_getCityId = memberDetailsApiModel.getCity_Id();
            st_getCountryId = memberDetailsApiModel.getCountry_Id();
            //Toast.makeText(context, "if", Toast.LENGTH_SHORT).show();
        } else {
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            st_Member_Unique = sharedPreferences.getString("Member_Unique", "");
            st_FirstName = sharedPreferences.getString("Member_FName", "");
            st_LastName = sharedPreferences.getString("Member_LName", "");
            st_Member_Gender = sharedPreferences.getString("Member_Gender", "");
            st_Phone = sharedPreferences.getString("Member_Mobile", "");
            st_DOB = sharedPreferences.getString("Member_DOB", "");
            st_Email = sharedPreferences.getString("Member_Email", "");
            st_Address = sharedPreferences.getString("Member_Address", "");
            st_getAreaId = sharedPreferences.getString("Area_Id", "");
            st_getCityId = sharedPreferences.getString("City_Id", "");
            st_getCountryId = sharedPreferences.getString("Country_Id", "");
            //Toast.makeText(context, "else  "+st_getAreaId, Toast.LENGTH_SHORT).show();
        }


        lay_main = findViewById(R.id.lay_main);

        et_mTransactionID = findViewById(R.id.et_mTransactionID);
        et_mFirstName = findViewById(R.id.et_mFirstName);
        iv_screenshot = findViewById(R.id.iv_screenshot);
        et_mLastName = findViewById(R.id.et_mLastName);
        et_mPhone = findViewById(R.id.et_mPhone);
        tv_mDOB = findViewById(R.id.tv_mDOB);
        et_mEmail = findViewById(R.id.et_mEmail);
        tv_mCity = findViewById(R.id.tv_mCity);
        tv_mArea = findViewById(R.id.tv_mArea);
        et_mAddress = findViewById(R.id.et_mAddress);
        tv_mProfession = findViewById(R.id.tv_mProfession);
        tv_mDesignation = findViewById(R.id.tv_mDesignation);

        et_mFirstName.setText(st_FirstName);
        et_mLastName.setText(st_LastName);
        et_mPhone.setText(st_Phone);
        tv_mDOB.setText(subtractDate(st_DOB));
        et_mEmail.setText(st_Email);


        expandablePaymentView();
        getGenderRadioBtn();
        datePicker();
        ProfessionSpinnerDialog();
        setRadioBtnValue();
        getMembershipRadioBtn();

        paymentDialog();

    }

    private void expandablePaymentView() {

        tv_PaymentTitle = findViewById(R.id.tv_PaymentTitle);
        lay_paymentOptions = findViewById(R.id.lay_paymentOptions);
        iv_expandView = findViewById(R.id.iv_expandView);
        cardview_payment = findViewById(R.id.cardview_payment);
        cardview_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /////////////Expandable cardView////////////////
                if (lay_paymentOptions.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardview_payment, new AutoTransition());
                    lay_paymentOptions.setVisibility(View.VISIBLE);
                    iv_expandView.setRotation(180);

                } else {
                    TransitionManager.beginDelayedTransition(cardview_payment, new AutoTransition());
                    lay_paymentOptions.setVisibility(View.GONE);
                    iv_expandView.setRotation(360);

                }
            }
        });
    }

    public void btn_Done(View view) {


        mUpgradeMembership();
    }



    private void mUpgradeMembership() {

        String st_mTransactionID = et_mTransactionID.getText().toString().trim();
        String st_FirstName = et_mFirstName.getText().toString().trim();
        String st_LastName = et_mLastName.getText().toString().trim();
        String st_Phone = et_mPhone.getText().toString().trim();
        String st_DOB = tv_mDOB.getText().toString().trim();
        String st_mEmail = et_mEmail.getText().toString().trim();
        String st_mProfession = tv_mProfession.getText().toString().trim();
        String st_mDesignation = tv_mDesignation.getText().toString().trim();
        String st_mCity = tv_mCity.getText().toString().trim();
        String st_mArea = tv_mArea.getText().toString().trim();
        String st_Address = et_mAddress.getText().toString().trim();


        if (Uri_screenshot == null) {
            Toast.makeText(context, "Please add payment screenshot!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (int_selectedPaymentMethod == 0) {
            Toast.makeText(context, "Please select payment method!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(st_mTransactionID)) {
            et_mTransactionID.requestFocus();
            et_mTransactionID.setError("Enter Transaction ID!");
            return;
        }
        if (st_FirstName.isEmpty()) {
            et_mFirstName.requestFocus();
            et_mFirstName.setError("Enter first name!");
            return;
        }

        if (st_LastName.isEmpty()) {
            et_mLastName.requestFocus();
            et_mLastName.setError("Enter last name!");
            return;
        }
        if (st_Phone.length() != 11) {
            et_mPhone.requestFocus();
            et_mPhone.setError("Enter correct mobile number!");
            return;
        }

        if (TextUtils.isEmpty(st_DOB)) {
            tv_mDOB.requestFocus();
            tv_mDOB.setError("Please enter your date of birth!");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(st_mEmail).matches()) {
            et_mEmail.requestFocus();
            et_mEmail.setError("Enter correct email!");
            return;
        }

        if (TextUtils.isEmpty(st_mProfession)) {
            tv_mProfession.requestFocus();
            tv_mProfession.setError("Please select your profession!");
            return;
        }


        if (TextUtils.isEmpty(st_mDesignation)) {
            tv_mDesignation.requestFocus();
            tv_mDesignation.setError("Please select your designation!");
            return;
        }

        if (TextUtils.isEmpty(st_mCity)) {
            tv_mCity.requestFocus();
            tv_mCity.setError("Please select your city!");
            return;
        }
        if (TextUtils.isEmpty(st_mArea)) {
            tv_mArea.requestFocus();
            tv_mArea.setError("Please select your area!");
            return;
        }
        if (TextUtils.isEmpty(st_Address)) {
            et_mAddress.requestFocus();
            et_mAddress.setError("Enter your address!");
            return;
        }

        Constant.sendVerificationEmail(this, "z4us864@gmail.com", "9292qwe9292",
                st_mEmail, "Payment Verification Pending",
                "Please wait, shortly you will receive a call for verification.");


        Bitmap bitmap = ((BitmapDrawable) iv_screenshot.getDrawable()).getBitmap();
        String img = Constant.encodeToBase64(bitmap, context);

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        Call<UpdateMemberApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mUpdateMembershipApi(st_Member_Unique, st_FirstName, st_LastName, st_Phone, st_DOB + " 00:00:00", st_mEmail, st_getCountryId,
                        st_getCityId, st_getAreaId, st_Address, getMember_Profession + "", getMember_Designation + "", st_mTransactionID, img, int_selectedPaymentMethod);
        call.enqueue(new Callback<UpdateMemberApi>() {
            @Override
            public void onResponse(@NonNull Call<UpdateMemberApi> call, @NonNull Response<UpdateMemberApi> response) {
                UpdateMemberApi updateMemberApi = response.body();
                if (response.isSuccessful()) {
                    if (updateMemberApi != null) {
                        if (updateMemberApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            sharedPrefManager.saveLoginUser(updateMemberApi.getZasa_Members());
                            Toast.makeText(context, "" + updateMemberApi.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, HomeActivity.class));
                            finishAffinity();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + updateMemberApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<UpdateMemberApi> call, @NonNull Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    //get value from radio btn
    private void getGenderRadioBtn() {

        RG_mGender = findViewById(R.id.RG_mGender);
        RG_mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                int GenderId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(GenderId);
                st_Gender = radioButton.getText().toString();

                if (radioButton.getText().toString().equals("Male")) {
                    st_Gender = "M";
                } else if (radioButton.getText().toString().equals("Female")) {
                    st_Gender = "F";
                } else {
                    st_Gender = "N";
                }
                //Toast.makeText(context, ""+st_Gender, Toast.LENGTH_SHORT).show();


            }
        });

       /* int GenderId = RG_mGender.getCheckedRadioButtonId();
        rb_SelectedGender = RG_mGender.findViewById(GenderId);
        st_Gender = rb_SelectedGender.getText().toString();

        if (rb_SelectedGender.getText().toString().equals("Male")) {
            st_Gender = "M";
        } else if (rb_SelectedGender.getText().toString().equals("Female")) {
            st_Gender = "F";
        } else {
            st_Gender = "N";
        }*/


    }


    //set value from radio btn
    private void setRadioBtnValue() {

        if (TextUtils.isEmpty(st_Member_Gender)) {

            RG_mGender.check(R.id.rb_other);

        } else if (st_Member_Gender.equals("M")) {
            // st_Gender = "Male";
            RG_mGender.check(R.id.rb_Male);

        } else if (st_Member_Gender.equals("F")) {
            //st_Gender = "Female";
            RG_mGender.check(R.id.rb_Female);

        } else {
            //st_Gender = "N";
            RG_mGender.check(R.id.rb_other);

        }


    }


    private void getMembershipRadioBtn() {

        RG_MembershipPaymentMethods = findViewById(R.id.RG_MembershipPaymentMethods);
        RG_MembershipPaymentMethods.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                int Id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(Id);
                String selectedPaymentMethod = radioButton.getText().toString();

                if (selectedPaymentMethod.equals("Meezan Bank")) {
                    int_selectedPaymentMethod = 1;
                } else if (selectedPaymentMethod.equals("Bank AlHabib")) {
                    int_selectedPaymentMethod = 2;
                } else if (selectedPaymentMethod.equals("jazzCash")) {
                    int_selectedPaymentMethod = 3;
                } else {
                    int_selectedPaymentMethod = 4;
                }

                // Toast.makeText(context, "" + int_selectedPaymentMethod, Toast.LENGTH_SHORT).show();

                ////expandable view reset////////
                tv_PaymentTitle.setText(selectedPaymentMethod);
                TransitionManager.beginDelayedTransition(cardview_payment, new AutoTransition());
                lay_paymentOptions.setVisibility(View.GONE);
                iv_expandView.setRotation(360);
                paymentDialog();

            }
        });


    }


    ////Search city spinner Dialog function
    private void ProfessionSpinnerDialog() {

        progressDialog.show();
        progressDialog.setMessage("Loading profession...");
        final ArrayList<String> NameList = new ArrayList<>();

        Call<ProfessionListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mProfessionListApi();

        call.enqueue(new Callback<ProfessionListApi>() {
            @Override
            public void onResponse(@NonNull Call<ProfessionListApi> call, @NonNull Response<ProfessionListApi> response) {
                ProfessionListApi professionListApi = response.body();
                if (response.isSuccessful()) {

                    if (professionListApi != null) {

                        if (professionListApi.getStatus() == 1) {
                            //progressDialog.dismiss();


                            professionListModelArrayList = professionListApi.getProfessionlist();
                            for (int i = 0; i < professionListModelArrayList.size(); i++) {
                                String name = professionListModelArrayList.get(i).getMember_Profession_Title();
                                NameList.add(name);

                              /*  if (cityListApiModelArrayList.get(i).getCity_Id().equals(st_getCityId)) {//get city name according to city code
                                    st_getCityName = cityListApiModelArrayList.get(i).getCity_Title();
                                    tv_mCity.setText(st_getCityName);
                                }*/

                            }

                            DesignationSpinnerDialog();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + professionListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<ProfessionListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        ProfessionSpinnerDialog = new SpinnerDialog(this, NameList,
                "Select Profession", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        ProfessionSpinnerDialog.setCancellable(true); // for cancellable
        ProfessionSpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        ProfessionSpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);
                getMember_Profession = professionListModelArrayList.get(position).getMember_Profession();
                // Toast.makeText(context, " "+st_getCityId, Toast.LENGTH_SHORT).show();

                tv_mProfession.setText(item);

                tv_mProfession.clearFocus();
                tv_mProfession.setError(null);

                //searchArea(st_getCityId);

            }
        });

        tv_mProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfessionSpinnerDialog.showSpinerDialog();
            }
        });


    }

    private void DesignationSpinnerDialog() {

        progressDialog.show();
        progressDialog.setMessage("Loading designations...");
        final ArrayList<String> NameList = new ArrayList<>();

        Call<DesignationListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mDesignationListApi();

        call.enqueue(new Callback<DesignationListApi>() {
            @Override
            public void onResponse(@NonNull Call<DesignationListApi> call, @NonNull Response<DesignationListApi> response) {
                DesignationListApi designationListApi = response.body();
                if (response.isSuccessful()) {

                    if (designationListApi != null) {

                        if (designationListApi.getStatus() == 1) {
                            //progressDialog.dismiss();

                            designationListModelArrayList = designationListApi.getDesignationlist();
                            for (int i = 0; i < designationListModelArrayList.size(); i++) {
                                String name = designationListModelArrayList.get(i).getMember_Designation_Title();
                                NameList.add(name);

                              /*  if (cityListApiModelArrayList.get(i).getCity_Id().equals(st_getCityId)) {//get city name according to city code
                                    st_getCityName = cityListApiModelArrayList.get(i).getCity_Title();
                                    tv_mCity.setText(st_getCityName);
                                }*/

                            }
                            searchCity(st_getCountryId);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + designationListApi.getMessage(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NonNull Call<DesignationListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        DesignationSpinnerDialog = new SpinnerDialog(this, NameList,
                "Select Designation", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        DesignationSpinnerDialog.setCancellable(true); // for cancellable
        DesignationSpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        DesignationSpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);
                getMember_Designation = designationListModelArrayList.get(position).getMember_Designation();
                // Toast.makeText(context, " "+st_getCityId, Toast.LENGTH_SHORT).show();

                tv_mDesignation.setText(item);

                tv_mDesignation.clearFocus();
                tv_mDesignation.setError(null);

                //searchArea(st_getCityId);

            }
        });

        tv_mDesignation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DesignationSpinnerDialog.showSpinerDialog();
            }
        });


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
                                    tv_mCity.setText(st_getCityName);
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

                tv_mCity.setText(item);

                tv_mCity.clearFocus();
                tv_mCity.setError(null);

                tv_mArea.getText().clear();


                searchArea(st_getCityId);

            }
        });

        tv_mCity.setOnClickListener(new View.OnClickListener() {
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

                            progressDialog.dismiss();

                            areaListApiModelArrayList = areaListApi.getArealist();
                            for (int i = 0; i < areaListApiModelArrayList.size(); i++) {
                                String area = areaListApiModelArrayList.get(i).getArea_Title();
                                areaNameList.add(area);

                                if (areaListApiModelArrayList.get(i).getArea_Id().equals(st_getAreaId)) {//get area name according to area code
                                    st_getAreaName = areaListApiModelArrayList.get(i).getArea_Title();
                                    tv_mArea.setText(st_getAreaName);
                                }
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
                tv_mArea.setText(item);

                tv_mArea.clearFocus();
                tv_mArea.setError(null);

            }
        });

        tv_mArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                areaSpinnerDialog.showSpinerDialog();
            }
        });
    }


    public void datePicker() {

        // tv_DateOfbirth.setText(getTodayDate());

        Calendar cal = Calendar.getInstance();
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);

        tv_mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        android.R.style.Theme_Holo_Light_Dialog, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());//future date disable
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = year + " - " + month + " - " + day;
                tv_mDOB.setText(date);

            }
        };
    }

    public String subtractDate(String dateTime) {

        if (TextUtils.isEmpty(dateTime)) {
            return null;
        }

        String[] OData = dateTime.split("T");
        String date = OData[0];
        String OTime = OData[1];

        String[] OTimeData = OTime.split("\\.");//store all string before dot
        String time = OTimeData[0];

        //return date + " " + time;
        return date;
    }

    public void btn_back(View view) {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {

                // Uri imageUri = data.getData();
                Uri_screenshot = data.getData();


                try {
                    InputStream inputStream = getContentResolver().openInputStream(Uri_screenshot);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                    iv_screenshot.setImageBitmap(bitmap);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "No image selected! ", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void btn_UploadScreenshot(View view) {
        Constant.imagePicker(3, 4, MembershipUpgradeActivity.this);
    }


    private void paymentDialog() {

        Dialog dialogView = new Dialog(context);
        dialogView.setContentView(R.layout.alertdialog_membership_payment);
        dialogView.setCanceledOnTouchOutside(false);

        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.style_custom_dialog_background));
        } else {
            dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }

        dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog

        ImageView qr_jazzcash = dialogView.findViewById(R.id.qr_jazzcash);
        ImageView qr_easypaisa = dialogView.findViewById(R.id.qr_easypaisa);
        ImageView dialogPayment_close = dialogView.findViewById(R.id.dialogPayment_close);
        TextView tv_jazzcashNO = dialogView.findViewById(R.id.tv_jazzcashNO);
        TextView tv_easypaisaNO = dialogView.findViewById(R.id.tv_easypaisaNO);
        View lay_jazzcash = dialogView.findViewById(R.id.lay_jazzcash);
        View lay_easypaisa = dialogView.findViewById(R.id.lay_easypaisa);
        View lay_mezaanBank = dialogView.findViewById(R.id.lay_mezaanBank);
        View lay_BankAlHabib = dialogView.findViewById(R.id.lay_BankAlHabib);
        View lay_guidline = dialogView.findViewById(R.id.lay_guidline);


        if (int_selectedPaymentMethod == 0) {
            lay_guidline.setVisibility(View.VISIBLE);
            lay_easypaisa.setVisibility(View.GONE);
            lay_jazzcash.setVisibility(View.GONE);
            lay_BankAlHabib.setVisibility(View.GONE);
            lay_mezaanBank.setVisibility(View.GONE);
        }
        if (int_selectedPaymentMethod == 1) {
            lay_mezaanBank.setVisibility(View.VISIBLE);
            lay_easypaisa.setVisibility(View.GONE);
            lay_jazzcash.setVisibility(View.GONE);
            lay_BankAlHabib.setVisibility(View.GONE);
            lay_guidline.setVisibility(View.GONE);
        }
        if (int_selectedPaymentMethod == 2) {
            lay_BankAlHabib.setVisibility(View.VISIBLE);
            lay_easypaisa.setVisibility(View.GONE);
            lay_mezaanBank.setVisibility(View.GONE);
            lay_jazzcash.setVisibility(View.GONE);
            lay_guidline.setVisibility(View.GONE);
        }

        if (int_selectedPaymentMethod == 3) {
            lay_jazzcash.setVisibility(View.VISIBLE);
            lay_easypaisa.setVisibility(View.GONE);
            lay_mezaanBank.setVisibility(View.GONE);
            lay_BankAlHabib.setVisibility(View.GONE);
            lay_guidline.setVisibility(View.GONE);
        }
        if (int_selectedPaymentMethod == 4) {
            lay_easypaisa.setVisibility(View.VISIBLE);
            lay_jazzcash.setVisibility(View.GONE);
            lay_mezaanBank.setVisibility(View.GONE);
            lay_BankAlHabib.setVisibility(View.GONE);
            lay_guidline.setVisibility(View.GONE);
        }

//        ///show image
//        if (TextUtils.isEmpty(st_Msg_Imag_Link)) {
//            dialog_img.setImageResource(R.drawable.no_image_banner);
//        } else {
//            /*   byte[] imageBytes = Base64.decode(ItemImage, Base64.DEFAULT);
//            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//            childViewHolder.iv_itemImg.setImageBitmap(decodedImage);*/
//
//            Glide.with(context).load(st_Msg_Imag_Link)
//                    .centerInside().fitCenter().placeholder(R.drawable.no_image_banner).into(dialog_img);
//
//        }


        // Close Button
        dialogPayment_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });


        //  dialogView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogView.show();
    }


}