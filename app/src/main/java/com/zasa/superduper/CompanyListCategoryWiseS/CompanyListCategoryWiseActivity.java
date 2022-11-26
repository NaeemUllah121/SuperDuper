package com.zasa.superduper.CompanyListCategoryWiseS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.zasa.superduper.Home.SliderApi;
import com.zasa.superduper.Home.SliderApiModel;
import com.zasa.superduper.Home.WebViewActivity;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyListCategoryWiseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;

    ImageSlider imageSlider;
    List<SlideModel> list;//default list
    List<SliderApiModel> sliderApiModelList;  //custom list


    String st_appId;
    TextView tv_SeeAllBtn, header_detail, tv_cateName;

    RecyclerView CompanyListRecyclerView;
    GridLayoutManager gridLayoutManager;
    CompanyListCategoryWiseAdapter companyListCategoryWiseAdapter;
    ArrayList<Model> models = new ArrayList<>();
    ArrayList<BranchListCategoryWiseApiModel> branchListCategoryWiseApiModelArrayList = new ArrayList<>();


    String st_getCityId, st_getAreaId, st_getCompany_Category_Code, st_Category_Name;

    EditText et_SearchCompany;
    View SearchLay, HeaderLay, Lay_noItemInCompanyList, Lay_withData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list_category_wise);


        context = CompanyListCategoryWiseActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);


        st_getCityId = getIntent().getStringExtra("st_getCityId");
        st_getAreaId = getIntent().getStringExtra("st_getAreaId");
        st_getCompany_Category_Code = getIntent().getStringExtra("st_getCompany_Category_Code");
        st_Category_Name = getIntent().getStringExtra("st_Category_Name");



        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel != null) {
            st_appId = appversionListModel.getApp_ID();
        }

        //image slider
        imageSlider = findViewById(R.id.image_slider);
        list = new ArrayList<>();

        Lay_withData = findViewById(R.id.Lay_withData);
        Lay_noItemInCompanyList = findViewById(R.id.noItemInCompanyList);

        header_detail = findViewById(R.id.header_detail);
        tv_cateName = findViewById(R.id.tv_cateName);
        header_detail.setText(st_Category_Name);
        tv_cateName.setText(st_Category_Name);

        CompanyListRecyclerView = findViewById(R.id.CompanyListRecyclerView);
        CompanyListRecyclerView.setNestedScrollingEnabled(true);
        gridLayoutManager = new GridLayoutManager(context, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        CompanyListRecyclerView.setLayoutManager(gridLayoutManager);


        sliderApi();
        //BranchListApi();

        //////search able header//////////
        searchBar();
    }

    private void sliderApi() {

        progressDialog.show();
        Call<SliderApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mSliderApi(st_appId);

        call.enqueue(new Callback<SliderApi>() {
            @Override
            public void onResponse(@NonNull Call<SliderApi> call, @NonNull Response<SliderApi> response) {
                SliderApi sliderApi = response.body();
                if (response.isSuccessful()) {

                    if (sliderApi != null) {

                        if (sliderApi.getStatus() == 1) {

                            // progressDialog.dismiss();

                            sliderApiModelList = sliderApi.getSliderList();
                            for (int i = 0; i < sliderApiModelList.size(); i++) {
                                String Slider_Image = sliderApiModelList.get(i).getSlider_Image();
                                String Slider_Title = sliderApiModelList.get(i).getSlider_Title();
                                //list.add(new SlideModel(Slider_Image,Slider_Title, ScaleTypes.FIT));
                                list.add(new SlideModel(Slider_Image, ScaleTypes.CENTER_INSIDE));
                            }

                            BranchListApi();

                            imageSlider.setImageList(list, ScaleTypes.CENTER_INSIDE);
                            imageSlider.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemSelected(int i) {
                                    String Slider_Link = sliderApiModelList.get(i).getSlider_Link();

                                    Intent intent = new Intent(context, WebViewActivity.class);
                                    intent.putExtra("URL", Slider_Link);
                                    startActivity(intent);

                                    /* Uri uri = Uri.parse(lin);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                                if (intent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(HomeActivity.this, "Sorry... Browser App is not available.", Toast.LENGTH_LONG).show();
                                }*/

                                }
                            });

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, " " + sliderApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SliderApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void BranchListApi() {

        progressDialog.show();
        Call<BranchListCategoryWiseApiResponse> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mBranchListCategoryWiseApiResponse(st_getCompany_Category_Code);

        call.enqueue(new Callback<BranchListCategoryWiseApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<BranchListCategoryWiseApiResponse> call, @NonNull Response<BranchListCategoryWiseApiResponse> response) {
                BranchListCategoryWiseApiResponse branchListCategoryWiseApiResponse = response.body();
                if (response.isSuccessful()) {

                    if (branchListCategoryWiseApiResponse != null) {

                        if (branchListCategoryWiseApiResponse.getStatus() == 1) {

                            progressDialog.dismiss();

                            branchListCategoryWiseApiModelArrayList = branchListCategoryWiseApiResponse.getBranchList();

                            companyListCategoryWiseAdapter = new CompanyListCategoryWiseAdapter(context, branchListCategoryWiseApiModelArrayList);
                            CompanyListRecyclerView.setAdapter(companyListCategoryWiseAdapter);
                            companyListCategoryWiseAdapter.notifyItemInserted(branchListCategoryWiseApiModelArrayList.size());

                            Lay_noItemInCompanyList.setVisibility(View.GONE);
                            Lay_withData.setVisibility(View.VISIBLE);


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, " " + branchListCategoryWiseApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            //if dataList is empty
                            if (branchListCategoryWiseApiResponse.getStatus() == 2) {
                                Lay_noItemInCompanyList.setVisibility(View.VISIBLE);
                                Lay_withData.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BranchListCategoryWiseApiResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void searchBar() {

        HeaderLay = findViewById(R.id.layHeader);
        SearchLay = findViewById(R.id.laySearch);
        et_SearchCompany = findViewById(R.id.SearchCompanyList);


        et_SearchCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//s is the text that enter in edittext

                try {
                    filter(s.toString());
                } catch (Exception e) {
                    Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });
    }

    private void filter(String text) {

        ArrayList<BranchListCategoryWiseApiModel> filteredList = new ArrayList<>();
        for (BranchListCategoryWiseApiModel item : branchListCategoryWiseApiModelArrayList) {
            if (item.getBranch_Name().toLowerCase().contains(text.toLowerCase()) /*|| item.getDis().toLowerCase().contains(text.toLowerCase())*/) {
                filteredList.add(item);
            }
        }
        companyListCategoryWiseAdapter.filteredListMethod(filteredList);
    }

    public void btn_close(View view) {
        et_SearchCompany.getText().clear();
        et_SearchCompany.setText("");
    }

    public void btn_search(View view) {

        //set focus on edit text and open keyboard
        et_SearchCompany.requestFocus();
       /* InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et_SearchCompany, InputMethodManager.SHOW_IMPLICIT);

        imageSlider.setVisibility(View.GONE);
        HeaderLay.setVisibility(View.GONE);
        SearchLay.setVisibility(View.VISIBLE);

    }

    public void btn_SearchBack(View view) {

        et_SearchCompany.getText().clear();
        SearchLay.setVisibility(View.GONE);

        HeaderLay.setVisibility(View.VISIBLE);
        imageSlider.setVisibility(View.VISIBLE);
    }


    public void btn_back(View view) {
        finish();
    }

    public void btn_GoToBack(View view) {
        startActivity(new Intent(context, HomeActivity.class));
        finishAffinity();
    }

}