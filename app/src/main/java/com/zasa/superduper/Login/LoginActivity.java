package com.zasa.superduper.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.RegisterUser.RegisterActivity;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.PrefsManager;
import com.zasa.superduper.Utils.SharedPrefManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText et_Phone, et_pass;
    CheckBox cb_RememberPass;
    String st_Phone, st_pass;

    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    Context context;
    SharedPreferences sharedPreferences;
    String sp_memberID;

    String st_Member_Unique, st_fName, st_lastName, st_mobile, st_email,
            Email_Verify, Mobile_Verify, Member_CNIC, Member_CNIC_Verified, st_Member_DOB, st_Member_Address, st_Api_Member_Image_String, st_userImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        sharedPrefManager = new SharedPrefManager(context);// call custom sharedPrefManager Class constructure

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        cb_RememberPass = findViewById(R.id.login_check_box);
        et_Phone = findViewById(R.id.loginPhone);
        et_pass = findViewById(R.id.loginPass);


        sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        sp_memberID = sharedPreferences.getString("Member_Unique", "");
//        sp_Member_Types = sharedPreferences.getInt("Member_Type", 1);

        SharedPreferences sp_RememberMe = getSharedPreferences("RememberMe", MODE_PRIVATE);
        String phone = sp_RememberMe.getString("uPhone", "");
        String pass = sp_RememberMe.getString("uPass", "");
        if (!phone.isEmpty() && !pass.isEmpty()) {
            et_Phone.setText(phone);
            et_pass.setText(pass);

        }
    }

    public void Login(View view) {

        st_Phone = et_Phone.getText().toString().trim();
        st_pass = et_pass.getText().toString().trim();

        if (st_Phone.length() != 11) {
            et_Phone.requestFocus();
            et_Phone.setError("Enter correct mobile number");
            return;
        }

        if (st_pass.length() < 4) {
            et_pass.requestFocus();
            et_pass.setError("Password must contains 4-digits");
            return;
        }


        progressDialog.show();
        Call<LoginApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mLoginApi(st_Phone, st_pass);

        call.enqueue(new Callback<LoginApi>() {
            @Override
            public void onResponse(@NonNull Call<LoginApi> call, @NonNull Response<LoginApi> response) {
                LoginApi loginApi = response.body();
                if (response.isSuccessful()) {
                    if (loginApi != null) {
                        if (loginApi.getStatus() == 1) {

                            sharedPrefManager.saveLoginUser(loginApi.getZasa_Members());///store value in sharedPref

                            if (cb_RememberPass.isChecked()) {
                                sharedPrefManager.rememberMe(st_Phone, st_pass);//put values in sharedPreference
                            }

                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "" + loginApi.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            PrefsManager.setMemberID(loginApi.getZasa_Members().getMember_Unique());
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "" + loginApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void forgetPassword(View view) {
//        startActivity(new Intent(context, ForgetPassActivity.class));
    }


    public void JoinNowBtn(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

    }







/*    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPrefManager.isLoggedIn()) {
         //   Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }*/

 /*   @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);

        if(sharedPreferences.getAll().containsKey("Member_Unique")){
            Toast.makeText(context, "shared pref Not null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "shared pref  null", Toast.LENGTH_SHORT).show();

        }
    }*/
}