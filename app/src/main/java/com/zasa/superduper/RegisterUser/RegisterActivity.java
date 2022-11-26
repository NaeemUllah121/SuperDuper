package com.zasa.superduper.RegisterUser;

import static com.zasa.superduper.Utils.Constant.generateRandomString;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.Calendar;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Context context;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    String st_SenderEmail = "z4us864@gmail.com", st_SenderPass = "9292qwe9292";


    TextInputEditText et_FirstName, et_LastName, et_Email, et_CnfPass, et_Phone, et_pass, et_CNIC, et_Address;
    //TextView tv_DOB,et_Area, et_City, et_Country;
    String st_Phone, st_pass, st_CnfPass, st_FirstName, st_LastName, st_Email, st_City, st_Area, st_Country, st_DOB, st_cnic, st_Address;

    RadioGroup rg_Gender;
    RadioButton rb_SelectedGender;
    String st_Gender;

    private SpinnerDialog countrySpinnerDialog, citySpinnerDialog, areaSpinnerDialog;

    ArrayList<CountryListApiModel> countryListApiArrayListModel;
    ArrayList<CityListApiModel> cityListApiModelArrayList;
    ArrayList<AreaListApiModel> areaListApiModelArrayList;

    String st_getCityId, st_getCountryId, st_getAreaId;
    DatePickerDialog.OnDateSetListener setListener;

    AutoCompleteTextView tv_DOB, et_Area, et_City, et_Country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = RegisterActivity.this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        et_FirstName = findViewById(R.id.regFirstName);
        et_LastName = findViewById(R.id.regLastName);
        et_Email = findViewById(R.id.et_Email);
        et_Country = findViewById(R.id.regCountry);
        et_City = findViewById(R.id.regCity);
        et_Area = findViewById(R.id.regArea);
        et_Phone = findViewById(R.id.regPhone);
        et_pass = findViewById(R.id.regPass);
        et_CnfPass = findViewById(R.id.regCnfPass);
        et_CNIC = findViewById(R.id.et_CNIC);
        tv_DOB = findViewById(R.id.tv_DOB);
        et_Address = findViewById(R.id.et_Address);
        rg_Gender = findViewById(R.id.RG_Gender);

        radioBtn();
        getDOB();
        cnicMasking();
        searchCountry();
        et_Country.setText("Pakistan");
        st_getCountryId = "092";
        searchCity();

    }
    public void btn_Signup(View view) {

        st_FirstName = et_FirstName.getText().toString().trim();
        st_LastName = et_LastName.getText().toString().trim();
        st_Phone = et_Phone.getText().toString().trim();
        st_pass = et_pass.getText().toString().trim();
        st_CnfPass = et_CnfPass.getText().toString().trim();
        st_Country = et_Country.getText().toString().trim();
        st_City = et_City.getText().toString().trim();
        st_Area = et_Area.getText().toString().trim();
        st_Email = et_Email.getText().toString().trim();
        st_DOB = tv_DOB.getText().toString().trim();
        st_cnic = et_CNIC.getText().toString().trim();
        st_Address = et_Address.getText().toString().trim();

        radioBtn();

        if (st_FirstName.isEmpty()) {
            et_FirstName.requestFocus();
            et_FirstName.setError("Enter first name!");
            return;
        }

        if (st_LastName.isEmpty()) {
            et_LastName.requestFocus();
            et_LastName.setError("Enter last name!");
            return;
        }
        if (st_Phone.length() != 11) {
            et_Phone.requestFocus();
            et_Phone.setError("Enter correct mobile number!");
            return;
        }
  /*      if (TextUtils.isEmpty(st_cnic)) {
            et_CNIC.requestFocus();
            et_CNIC.setError("Please enter correct CNIC!");
            return;
        }
        if (TextUtils.isEmpty(st_DOB)) {
            tv_DOB.requestFocus();
            tv_DOB.setError("Please enter your date of birth!");
            return;
        }*/

        if (!Patterns.EMAIL_ADDRESS.matcher(st_Email).matches()) {
            et_Email.requestFocus();
            et_Email.setError("Enter correct email!");
            return;
        }

        if (TextUtils.isEmpty(st_Country)) {
            et_Country.requestFocus();
            et_Country.setError("Please select your country!");
            return;
        }
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
        if (TextUtils.isEmpty(st_Address)) {
            et_Address.requestFocus();
            et_Address.setError("Enter your address!");
            return;
        }

        if (st_pass.length() < 6 || st_pass.isEmpty()) {
            et_pass.requestFocus();
            et_pass.setError("Password must contains 6-digits!");
            return;
        }
        if (!st_CnfPass.equals(st_pass) || st_CnfPass.isEmpty()) {
            et_CnfPass.requestFocus();
            et_CnfPass.setError("Password did not match!");
            return;
        }

        cnfEmailPhoneDialog();


    }

    public void cnfEmailPhoneDialog() {


//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_company_info, null);
//            builder.setView(dialogView).setCancelable(true);


        Dialog dialogView = new Dialog(context);
        dialogView.setCanceledOnTouchOutside(false);
        dialogView.setContentView(R.layout.alertdialog_confirm_msg);

        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.style_custom_dialog_background));
        } else {
            dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog

        TextView Phone_dialog, Email_dialog;
        ImageView closebtn;
        Button btn_Yes, btn_no;


        Phone_dialog = dialogView.findViewById(R.id.Phone_dialog);
        Email_dialog = dialogView.findViewById(R.id.Email_dialog);
        closebtn = dialogView.findViewById(R.id.closebtn);
        btn_Yes = dialogView.findViewById(R.id.btn_Yes);
        btn_no = dialogView.findViewById(R.id.btn_no);


        Phone_dialog.setText(st_Phone);
        Email_dialog.setText(st_Email);


        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
        btn_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogView.dismiss();
                signUpApi();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
        dialogView.show();


    }


    private void signUpApi() {

        progressDialog.show();
        String st_generateRandomString = generateRandomString(6, context);
        Call<AddMemberApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mAddMemberApi(st_FirstName, st_LastName, st_Phone, st_Email, st_DOB + " 00:00:00", st_getCountryId,
                        st_getCityId, st_getAreaId, st_pass, st_cnic, st_Gender, st_Address);
        call.enqueue(new Callback<AddMemberApi>() {
            @Override
            public void onResponse(@NonNull Call<AddMemberApi> call, @NonNull Response<AddMemberApi> response) {
                AddMemberApi addMemberApi = response.body();
                if (response.isSuccessful()) {
                    if (addMemberApi != null) {
                        if (addMemberApi.getStatus() == 1) {

                            Constant.sendVerificationEmail(context, st_SenderEmail, st_SenderPass, st_Email, "Email Verification",
                                    "Hello " + st_FirstName + "\n\nVerification Code: " + st_generateRandomString + "\nThis mail sent by Smile Services for email verification.");

                            sharedPrefManager.saveRandomString(st_generateRandomString);


                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "" + addMemberApi.getMessage(), Toast.LENGTH_SHORT).show();
                            // startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "" + addMemberApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<AddMemberApi> call, @NonNull Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



    //get value from radio btn
    private void radioBtn() {
        int GenderId = rg_Gender.getCheckedRadioButtonId();
        rb_SelectedGender = rg_Gender.findViewById(GenderId);
        st_Gender = rb_SelectedGender.getText().toString();

        if (rb_SelectedGender.getText().toString().equals("Male")) {
            st_Gender = "M";
        } else if (rb_SelectedGender.getText().toString().equals("Female")) {
            st_Gender = "F";
        } else {
            st_Gender = "N";
        }


    }


    ////search Country spinner Dialog function
    private void searchCountry() {

        progressDialog.show();
        ArrayList<String> countryNameList = new ArrayList<>();

        Call<CountryListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCountryApi();
        call.enqueue(new Callback<CountryListApi>() {
            @Override
            public void onResponse(@NonNull Call<CountryListApi> call, @NonNull Response<CountryListApi> response) {
                progressDialog.dismiss();
                CountryListApi countryListApi = response.body();
                if (response.isSuccessful()) {
                    if (countryListApi != null) {
                        if (countryListApi.getStatus() == 1) {

                            countryListApiArrayListModel = countryListApi.getCountrylist();
                            for (int i = 0; i < countryListApiArrayListModel.size(); i++) {

                                String CountryName = countryListApiArrayListModel.get(i).getCountry_Title();
                                countryNameList.add(CountryName);

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
                et_Country.setText(item);

                et_Country.clearFocus();
                et_Country.setError(null);

                searchCity();

            }
        });

        et_Country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countrySpinnerDialog.showSpinerDialog();
            }
        });


    }

    ////Search city spinner Dialog function
    private void searchCity() {

        progressDialog.show();
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
                            progressDialog.dismiss();

                            cityListApiModelArrayList = cityListApi.getCitylist();
                            for (int i = 0; i < cityListApiModelArrayList.size(); i++) {
                                String city = cityListApiModelArrayList.get(i).getCity_Title();
                                cityNameList.add(city);
                            }


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "" + cityListApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                    }


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<CityListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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

                et_City.setText(item);

                et_City.clearFocus();
                et_City.setError(null);

                searchArea();

            }
        });

        et_City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                citySpinnerDialog.showSpinerDialog();
            }
        });


    }

    ////Search area spinner Dialog function
    private void searchArea() {

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
                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "" + areaListApi.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<AreaListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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

                et_Area.setText(item);

                et_Area.clearFocus();
                et_Area.setError(null);
            }
        });

        et_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                areaSpinnerDialog.showSpinerDialog();
            }
        });
    }



    private void cnicMasking() {

        et_CNIC.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        et_CNIC.addTextChangedListener(new TextWatcher() {
            int len = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                String val = et_CNIC.getText().toString();
                if ((val.length() == 5 && len < val.length()) || (val.length() == 13 && len < val.length())) {
                    et_CNIC.append("-");
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String str = et_CNIC.getText().toString();
                len = str.length();

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }


    ////////////////date picker Dialog/////////////////

    public void getDOB() {

        tv_DOB.setText(getTodayDate());

        Calendar cal = Calendar.getInstance();
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);

        tv_DOB.setOnClickListener(new View.OnClickListener() {
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
                String date = day + " - " + month + " - " + year;
                tv_DOB.setText(date);

            }
        };
    }

    ///set current date on datepicker button
    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int year = cal.get(Calendar.YEAR);
        int pastYear = year - 20;
        return pastYear + "-" + month + "-" + day;
    }
    ////////////////date picker Dialog/////////////////

}