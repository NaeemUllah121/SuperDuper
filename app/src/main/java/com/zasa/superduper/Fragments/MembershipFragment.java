package com.zasa.superduper.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
import com.zasa.superduper.RegisterUser.AreaListApi;
import com.zasa.superduper.RegisterUser.AreaListApiModel;
import com.zasa.superduper.RegisterUser.CityListApi;
import com.zasa.superduper.RegisterUser.CityListApiModel;
import com.zasa.superduper.RegisterUser.CountryListApiModel;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.Calendar;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MembershipFragment extends Fragment {


    public MembershipFragment() {
        // Required empty public constructor
    }


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;


    CardView cardview_payment;
    View lay_paymentOptions,iv_expandView;
    TextInputEditText et_mFirstName, et_mLastName, et_mPhone, et_mEmail, et_mAddress;
    String st_Phone, st_FirstName, st_LastName, st_Email, st_getCityName, st_getAreaName, st_Country, st_Address, st_DOB,st_Member_Gender;
    int getMember_Profession, getMember_Designation;

    RadioGroup RG_mGender;
    RadioButton rb_SelectedGender;
    String st_Gender;

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
    Button btn_UpgradeMembership;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_membership, container, false);

        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);


        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
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


        lay_paymentOptions = view.findViewById(R.id.lay_paymentOptions);
        iv_expandView = view.findViewById(R.id.iv_expandView);
        cardview_payment = view.findViewById(R.id.cardview_payment);
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


        et_mFirstName = view.findViewById(R.id.et_mFirstName);
        et_mLastName = view.findViewById(R.id.et_mLastName);
        et_mPhone = view.findViewById(R.id.et_mPhone);
        tv_mDOB = view.findViewById(R.id.tv_mDOB);
        RG_mGender = view.findViewById(R.id.RG_mGender);
        et_mEmail = view.findViewById(R.id.et_mEmail);
        tv_mCity = view.findViewById(R.id.tv_mCity);
        tv_mArea = view.findViewById(R.id.tv_mArea);
        et_mAddress = view.findViewById(R.id.et_mAddress);
        tv_mProfession = view.findViewById(R.id.tv_mProfession);
        tv_mDesignation = view.findViewById(R.id.tv_mDesignation);

        et_mFirstName.setText(st_FirstName);
        et_mLastName.setText(st_LastName);
        et_mPhone.setText(st_Phone);
        tv_mDOB.setText(subtractDate(st_DOB));
        et_mEmail.setText(st_Email);


        btn_back = view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                requireActivity().finishAffinity();
            }
        });
        btn_UpgradeMembership = view.findViewById(R.id.btn_UpgradeMembership);
        btn_UpgradeMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUpgradeMembership();
            }
        });


        radioBtn();
        datePicker();
        ProfessionSpinnerDialog();
        setRadioBtnValue();


        return view;
    }

    private void mUpgradeMembership() {

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

        startActivity(new Intent(context,HomeActivity.class));
        requireActivity().finishAffinity();

    }


    //get value from radio btn
    private void radioBtn() {
        int GenderId = RG_mGender.getCheckedRadioButtonId();
        rb_SelectedGender = RG_mGender.findViewById(GenderId);
        st_Gender = rb_SelectedGender.getText().toString();

        if (rb_SelectedGender.getText().toString().equals("Male")) {
            st_Gender = "M";
        } else if (rb_SelectedGender.getText().toString().equals("Female"))  {
            st_Gender = "F";
        }else {
            st_Gender = "N";
        }


    }


    //set value from radio btn
    private void setRadioBtnValue() {

        if (st_Member_Gender.equals("M")) {
           // st_Gender = "Male";
            RG_mGender.check(R.id.rb_Male);
            RG_mGender.check(R.id.rb_Male);
            RG_mGender.check(R.id.rb_Male);
        } else if (st_Member_Gender.equals("F"))  {
            //st_Gender = "Female";
            RG_mGender.check(R.id.rb_Female);

        }else {
            //st_Gender = "N";
            RG_mGender.check(R.id.rb_other);

        }


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
                ProfessionListApi cityListApi = response.body();
                if (response.isSuccessful()) {

                    if (cityListApi != null) {

                        if (cityListApi.getStatus() == 1) {
                            //progressDialog.dismiss();


                            professionListModelArrayList = cityListApi.getProfessionlist();
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
            public void onFailure(@NonNull Call<ProfessionListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        ProfessionSpinnerDialog = new SpinnerDialog(requireActivity(), NameList,
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
                DesignationListApi cityListApi = response.body();
                if (response.isSuccessful()) {

                    if (cityListApi != null) {

                        if (cityListApi.getStatus() == 1) {
                            //progressDialog.dismiss();

                            designationListModelArrayList = cityListApi.getDesignationlist();
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
            public void onFailure(@NonNull Call<DesignationListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        DesignationSpinnerDialog = new SpinnerDialog(requireActivity(), NameList,
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

        citySpinnerDialog = new SpinnerDialog(requireActivity(), cityNameList,
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


        areaSpinnerDialog = new SpinnerDialog(requireActivity(), areaNameList,
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
}