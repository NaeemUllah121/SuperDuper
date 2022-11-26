package com.zasa.superduper.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.MembershipInfoActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.RegisterUser.AreaListApi;
import com.zasa.superduper.RegisterUser.AreaListApiModel;
import com.zasa.superduper.RegisterUser.CityListApi;
import com.zasa.superduper.RegisterUser.CityListApiModel;
import com.zasa.superduper.RegisterUser.CountryListApi;
import com.zasa.superduper.RegisterUser.CountryListApiModel;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;
    String sp_getRandomString;
    String st_SenderEmail = "z4us864@gmail.com", st_SenderPass = "9292qwe9292", emailStatus = "Y";
    public static boolean isFirstDialog = true;

    private Uri uri_userImage;

    private SpinnerDialog countrySpinnerDialog, citySpinnerDialog, areaSpinnerDialog;

    ArrayList<CountryListApiModel> countryListApiArrayListModel;
    ArrayList<CityListApiModel> cityListApiModelArrayList;
    ArrayList<AreaListApiModel> areaListApiModelArrayList;


    ImageView verifyphone, verifyCnic, verifyemail;
    TextView tv_fullName, tv_memberType, tv_cnic, tv_phone, tv_DateOfbirth, tv_Country, tv_city, tv_Area, et_email;
    EditText et_fName, et_lastName, et_address;
    String st_Member_Unique, st_fName, st_lastName, st_mobile, st_email,
            Email_Verify, Mobile_Verify, Member_CNIC, Member_CNIC_Verified, st_Member_DOB, st_Member_Address, st_Api_Member_Image_String, st_userImage;

    ExtendedFloatingActionButton UpgradeMembership_fab;
    String st_getCityId, st_getCountryId, st_getAreaId;
    String st_getCountryName, st_getCityName, st_getAreaName;
    String sp_memberID;
    int int_Member_Type, sp_Member_Types;
    DatePickerDialog.OnDateSetListener setListener;

    String imgUrl;
    CircleImageView iv_userImg;
    ImageView shine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //setSupportActionBar(toolbar);

        context = ProfileActivity.this;
        sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        sp_memberID = sharedPreferences.getString("Member_Unique", "");
        sp_Member_Types = sharedPreferences.getInt("Member_Type", 1);

        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        sharedPrefManager = new SharedPrefManager(context);

        iv_userImg = findViewById(R.id.userImg);
        tv_fullName = findViewById(R.id.p_fullName);
        shine = findViewById(R.id.shine);
        tv_memberType = findViewById(R.id.tv_memberType);
        tv_city = findViewById(R.id.p_city);
        et_fName = findViewById(R.id.p_fname);
        et_lastName = findViewById(R.id.p_lname);
        et_email = findViewById(R.id.p_email);
        et_address = findViewById(R.id.p_address);
        tv_cnic = findViewById(R.id.p_cnic);
        tv_phone = findViewById(R.id.p_phone);
        tv_DateOfbirth = findViewById(R.id.p_DateOfbirth);
        tv_Country = findViewById(R.id.p_Country);
        tv_Area = findViewById(R.id.p_Area);
        UpgradeMembership_fab = findViewById(R.id.UpgradeMembership_fab);


        verifyphone = findViewById(R.id.verifyphone);
        verifyCnic = findViewById(R.id.verifyCnic);
        verifyemail = findViewById(R.id.verifyemail);

        fab_BtnVisibility(sp_Member_Types);

        getMemberDetailsApi();

        // showUserImg();


    }


    private void mVerifyEmailDialog() {

        sp_getRandomString = sharedPrefManager.getRandomString();

        Dialog dialogView = new Dialog(context);
        dialogView.setContentView(R.layout.alertdialog_lay_verify_mail);
        dialogView.setCancelable(false);

        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.style_custom_dialog_background));
        } else {
            dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog


        TextInputEditText et_verificationCode = dialogView.findViewById(R.id.et_verificationCode);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        ImageView closebtn = dialogView.findViewById(R.id.closebtn);


        if (TextUtils.isEmpty(sp_getRandomString)) {

            //String st_generateRandomString= Constant.generateRandomString(6,context);
            sp_getRandomString = Constant.generateRandomString(6, context);

            Constant.sendVerificationEmail(context, st_SenderEmail, st_SenderPass, st_email, "Email Verification",
                    "Hello \n\nVerification Code: " + sp_getRandomString + "\nThis mail sent by Smile Services for email verification.");

            sharedPrefManager.saveRandomString(sp_getRandomString);
        }


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String st_verificationCode = et_verificationCode.getText().toString();


                if (TextUtils.isEmpty(st_verificationCode)) {
                    et_verificationCode.requestFocus();
                    et_verificationCode.setError("Enter correct verification Code!");
                    //showSnackBar("Enter correct user phone!");
                    return;
                }

                if (!st_verificationCode.equals(sp_getRandomString)) {
                    et_verificationCode.requestFocus();
                    et_verificationCode.setError("Enter correct verification Code!");
                    //showSnackBar("Enter correct user phone!");
                    return;
                }

                mVerifyEmailApi();

            }
        });

        // Close Button
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });

        dialogView.show();


    }

    private void mVerifyEmailApi() {

        progressDialog.show();
        Call<UpdateMemberApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mEmailVerification(st_Member_Unique, st_mobile, emailStatus);
        call.enqueue(new Callback<UpdateMemberApi>() {
            @Override
            public void onResponse(@NonNull Call<UpdateMemberApi> call, @NonNull Response<UpdateMemberApi> response) {
                UpdateMemberApi updateMemberApi = response.body();
                if (response.isSuccessful()) {
                    if (updateMemberApi != null) {
                        if (updateMemberApi.getStatus() == 1) {

                            progressDialog.dismiss();
                            Toast.makeText(context, "" + updateMemberApi.getMessage(), Toast.LENGTH_SHORT).show();
//                            sharedPrefManager.saveLoginUser(updateMemberApi.getZasa_Members());
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

    private void shineFunction() {
        //Start the animations preoidically by calling 'shineStart' method with ScheduledExecutorService
        ScheduledExecutorService executorService =
                Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Animation animation = new TranslateAnimation(
                                0, tv_memberType.getWidth() + shine.getWidth(), 0, 0);
                        animation.setDuration(550);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine.startAnimation(animation);
                    }
                });
            }
        }, 3, 3, TimeUnit.SECONDS);
    }

    private void showUserImg() {


        if (TextUtils.isEmpty(sp_memberID) || TextUtils.isEmpty(st_Api_Member_Image_String) || st_Api_Member_Image_String.length() < 200) {

            iv_userImg.setImageResource(R.drawable.noimg);
            //iv_userImg.setBackgroundResource(R.drawable.noimg);


        } else {
            //Bitmap bitmap=  decodeToBase64(st_Api_Member_Image_String);
            Bitmap bitmap = Constant.decodeToBase64(st_Api_Member_Image_String, context);
            iv_userImg.setImageBitmap(bitmap);
        }
    }

    private void getMemberDetailsApi() {
        progressDialog.show();

        Call<MemberDetailsApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mMemberDetailsApi(sp_memberID);

        call.enqueue(new Callback<MemberDetailsApi>() {
            @Override
            public void onResponse(@NonNull Call<MemberDetailsApi> call, @NonNull Response<MemberDetailsApi> response) {

                //progressDialog.dismiss();

                MemberDetailsApi memberDetailsApi = response.body();

                if (response.isSuccessful()) {
                    if (memberDetailsApi != null) {
                        if (memberDetailsApi.getStatus() == 1) {

                            sharedPrefManager.saveMemberDetails(memberDetailsApi.getMemberDetails());///save MemberDetails obj in sharedPref

                            st_Member_Unique = memberDetailsApi.getMemberDetails().getMember_Unique();
                            st_fName = memberDetailsApi.getMemberDetails().getMember_FName();
                            st_lastName = memberDetailsApi.getMemberDetails().getMember_LName();
                            st_mobile = memberDetailsApi.getMemberDetails().getMember_LoginID();
                            st_getCountryId = memberDetailsApi.getMemberDetails().getCountry_Id();
                            st_getCityId = memberDetailsApi.getMemberDetails().getCity_Id();
                            st_getAreaId = memberDetailsApi.getMemberDetails().getArea_Id();
                            st_email = memberDetailsApi.getMemberDetails().getMember_Email();
                            int_Member_Type = memberDetailsApi.getMemberDetails().getMember_Type();
                            st_Member_DOB = memberDetailsApi.getMemberDetails().getMember_DOB();
                            st_Member_Address = memberDetailsApi.getMemberDetails().getMember_Address();
                            st_Api_Member_Image_String = memberDetailsApi.getMemberDetails().getMember_Image_String();


                            fab_BtnVisibility(int_Member_Type);
                            String DOB = subtractDate(st_Member_DOB);
                            tv_DateOfbirth.setText(DOB);

                            Email_Verify = memberDetailsApi.getMemberDetails().getEmail_Verify();
                            Mobile_Verify = memberDetailsApi.getMemberDetails().getMobile_Verify();
                            Member_CNIC = memberDetailsApi.getMemberDetails().getMember_CNIC();
                            Member_CNIC_Verified = memberDetailsApi.getMemberDetails().getMember_CNIC_Verified();

                            // Email_Verify="N";


                            if (isFirstDialog) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (!TextUtils.isEmpty(Email_Verify)) {
                                            if (Email_Verify.equals("N")) {
                                                mVerifyEmailDialog();
                                            }
                                        }
                                    }
                                }, 1000);
                                isFirstDialog = false;
                            }


                            showUserImg();
                            searchCountry();
                            searchCity();
                            searchArea();
                            datePicker();

                            tv_fullName.setText(st_fName + " " + st_lastName);

                            et_fName.setText(st_fName);
                            et_lastName.setText(st_lastName);

                            et_email.setText(st_email);
                            viewVisibility(verifyemail, Email_Verify);

                            tv_phone.setText(st_mobile);
                            viewVisibility(verifyphone, Mobile_Verify);

                            tv_cnic.setText(Member_CNIC);
                            viewVisibility(verifyCnic, Member_CNIC_Verified);

                            et_address.setText(st_Member_Address);

                            if (int_Member_Type == 1) {
                                tv_memberType.setText("FREE MEMBER");
                            } else if (int_Member_Type == 2) {
                                shineFunction();
                                tv_memberType.setText("PREMIUM MEMBER");
                            } else {
                                tv_memberType.setText("FAMILY MEMBER");
                                shineFunction();
                            }


                            //      et_address.setText(st_getAreaName+", "+st_getCityName+", "+"Pakistan");

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + memberDetailsApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(@NonNull Call<MemberDetailsApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    ////search Country spinner Dialog function
    private void searchCountry() {

        //progressDialog.show();
        ArrayList<String> countryNameList = new ArrayList<>();

        Call<CountryListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCountryApi();
        call.enqueue(new Callback<CountryListApi>() {
            @Override
            public void onResponse(@NonNull Call<CountryListApi> call, @NonNull Response<CountryListApi> response) {
                // progressDialog.dismiss();
                CountryListApi countryListApi = response.body();
                if (response.isSuccessful()) {
                    if (countryListApi != null) {
                        if (countryListApi.getStatus() == 1) {

                            countryListApiArrayListModel = countryListApi.getCountrylist();
                            for (int i = 0; i < countryListApiArrayListModel.size(); i++) {

                                String CountryName = countryListApiArrayListModel.get(i).getCountry_Title();
                                countryNameList.add(CountryName);

                                if (countryListApiArrayListModel.get(i).getCountry_Id().equals(st_getCountryId)) {//get city name according to city code
                                    st_getCountryName = countryListApiArrayListModel.get(i).getCountry_Title();

                                    tv_Country.setText(st_getCountryName);
                                }

                            }


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + countryListApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<CountryListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        countrySpinnerDialog = new SpinnerDialog(this, countryNameList,
                "Select or Search Country", R.style.DialogAnimations_SmileWindow,
                "Close");// With 	Animation

        countrySpinnerDialog.setCancellable(true); // for cancellable
        countrySpinnerDialog.setShowKeyboard(false);// for open keyboard by default

        countrySpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                // Toast.makeText(CreateAccountActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //    etSearchItem.setText(item + " Position: " + position);

                st_getCountryId = countryListApiArrayListModel.get(position).getCountry_Id();
                tv_Country.setText(item);
                et_address.setText(st_getAreaName + ", " + st_getCityName + ", " + /*st_getCountryName*/item);

                tv_Country.clearFocus();
                tv_Country.setError(null);

                // searchCity();

            }
        });

        tv_Country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countrySpinnerDialog.showSpinerDialog();
            }
        });


    }

    ////Search city spinner Dialog function
    private void searchCity() {

        //progressDialog.show();
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
                            // progressDialog.dismiss();

                            cityListApiModelArrayList = cityListApi.getCitylist();
                            for (int i = 0; i < cityListApiModelArrayList.size(); i++) {
                                String city = cityListApiModelArrayList.get(i).getCity_Title();
                                cityNameList.add(city);

                                if (cityListApiModelArrayList.get(i).getCity_Id().equals(st_getCityId)) {//get city name according to city code
                                    st_getCityName = cityListApiModelArrayList.get(i).getCity_Title();

                                    tv_city.setText(st_getCityName);
                                }

                            }


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
                tv_city.setText(item);
                et_address.setText(st_getAreaName + ", " + /*st_getCityName*/item + ", " + st_getCountryName);

                tv_city.clearFocus();
                tv_city.setError(null);

                //searchArea();

            }
        });

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                citySpinnerDialog.showSpinerDialog();
            }
        });


    }

    ////Search area spinner Dialog function
    private void searchArea() {

        //progressDialog.show();
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
                                    tv_Area.setText(st_getAreaName);

                                    if (TextUtils.isEmpty(st_Member_Address)) {
                                        et_address.setText(st_getAreaName + ", " + st_getCityName + ", " + st_getCountryName);
                                    }

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

                tv_Area.setText(item);
                et_address.setText(/*st_getAreaName*/item + ", " + st_getCityName + ", " + st_getCountryName);


                tv_Area.clearFocus();
                tv_Area.setError(null);
            }
        });

        tv_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                areaSpinnerDialog.showSpinerDialog();
            }
        });
    }


    public void UpdateProfile(View view) {

        String st_FirstName = et_fName.getText().toString().trim();
        String st_LastName = et_lastName.getText().toString().trim();
        String st_Phone = tv_phone.getText().toString().trim();
        String st_DOB = tv_DateOfbirth.getText().toString().trim();
        String st_cnic = tv_cnic.getText().toString().trim();
        String st_Address = et_address.getText().toString().trim();
        String st_email = et_email.getText().toString().trim();

        Bitmap bitmap = ((BitmapDrawable) iv_userImg.getDrawable()).getBitmap();
        //String img = encodeToBase64(bitmap);
        String img = Constant.encodeToBase64(bitmap, context);

        if (TextUtils.isEmpty(st_FirstName)) {
            et_fName.requestFocus();
            et_fName.setError("Enter first name!");
            return;
        }

        if (TextUtils.isEmpty(st_LastName)) {
            et_lastName.requestFocus();
            et_lastName.setError("Enter last name!");
            return;
        }
        if (TextUtils.isEmpty(st_DOB)) {
            tv_DateOfbirth.requestFocus();
            tv_DateOfbirth.setError("Enter Date of birth!");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(st_email).matches()) {
            et_email.requestFocus();
            et_email.setError("Enter correct email!");
            return;
        }

        if (TextUtils.isEmpty(st_Address)) {
            et_address.requestFocus();
            et_address.setError("Enter address!");
            return;
        }


        progressDialog.show();

        Call<UpdateMemberApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mUpdateMemberApi(st_Member_Unique, st_FirstName, st_LastName, st_Phone, st_DOB + " 00:00:00", st_email, st_getCountryId,
                        st_getCityId, st_getAreaId, st_cnic, st_Address, img, "", "");
        call.enqueue(new Callback<UpdateMemberApi>() {
            @Override
            public void onResponse(@NonNull Call<UpdateMemberApi> call, @NonNull Response<UpdateMemberApi> response) {
                UpdateMemberApi updateMemberApi = response.body();
                if (response.isSuccessful()) {
                    if (updateMemberApi != null) {
                        if (updateMemberApi.getStatus() == 1) {


//                            CuToast.showSuccess(ProfileActivity.this,"message text");
//                            CuToast.showError(ProfileActivity.this,"message text");
//                            CuToast.showWarning(ProfileActivity.this,"message text");
//                            CuToast.showCustom(ProfileActivity.this,"title text","message text",Toast.LENGTH_SHORT
//                                    ,getResources().getColor(R.color.red)
//                                    ,R.drawable.ic_check_24);


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


    public void fab_UpgradeMembership(View view) {
        startActivity(new Intent(context, MembershipInfoActivity.class));
    }

    public void fab_BtnVisibility(int Member_Type) {

        if (Member_Type == 1) {
            UpgradeMembership_fab.setVisibility(View.VISIBLE);
        } else {
            UpgradeMembership_fab.setVisibility(View.GONE);
        }

    }

    public void btn_verifyEmail(View view) {
        displayPopupWindow(view);
    }

    public void btn_verifyCnic(View view) {
        displayPopupWindow(view);
    }

    public void btn_verifyPhone(View view) {
        displayPopupWindow(view);
    }


    public void viewVisibility(View view, String flag) {

        if (TextUtils.isEmpty(flag)) {
            view.setVisibility(View.GONE);
        } else if (flag.equals("Y")) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);


        }

    }

    private void displayPopupWindow(View anchorView) {
        PopupWindow popup = new PopupWindow(context);
        View layout = getLayoutInflater().inflate(R.layout.popup_content, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);

        // Show anchored to button
        //popup.setBackgroundDrawable(new BitmapDrawable());
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.showAsDropDown(anchorView);
        // popup.showAtLocation(anchorView, Gravity.RIGHT,100,0);
    }


    public void datePicker() {

        // tv_DateOfbirth.setText(getTodayDate());

        Calendar cal = Calendar.getInstance();
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);

        tv_DateOfbirth.setOnClickListener(new View.OnClickListener() {
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
                tv_DateOfbirth.setText(date);

            }
        };
    }

    public void btn_back(View view) {
        finish();
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


    public void btn_updateProfileImg(View view) {

        Constant.imagePicker(3, 4, ProfileActivity.this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {

                Uri imageUri = data.getData();


                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                    uri_userImage = imageUri;
                    iv_userImg.setImageBitmap(bitmap);


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

    public void showSnackBar(String runFun, String msg) {

        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE)
                .setAction("Enable", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(context, "Please enable all permissions!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setActionTextColor(this.getColor(android.R.color.holo_red_light))
                .show();


    }

    @Override
    protected void onPause() {
        super.onPause();

        if ( progressDialog!=null && progressDialog.isShowing() ){
            progressDialog.dismiss();

        }
    }


}