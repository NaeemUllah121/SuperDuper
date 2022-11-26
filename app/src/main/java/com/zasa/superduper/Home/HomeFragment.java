package com.zasa.superduper.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.zasa.superduper.CategoryListCompanyWise.ChildItemApi;
import com.zasa.superduper.CompanyListCategoryWiseS.BranchListCategoryWiseApiResponse;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Utils.PrefsManager;
import com.zasa.superduper.databinding.FragmentHomeBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ProgressDialog progressDialog;
    Context context;

    private FragmentHomeBinding binding;
    MaterialAlertDialogBuilder materialAlertDialogBuilder;


    ImageSlider imageSlider;
    List<SlideModel> list = new ArrayList<>();//default list
    ArrayList<SliderApiModel> sliderApiModelList = new ArrayList<>();  //custom list

    public ArrayList<com.zasa.superduper.CategoryListCompanyWise.ChildItemList> ChildItemList = new ArrayList<>();
    CompanyTypeListRcvAdapter companyTypeListRcvAdapter;
    ArrayList<CompanyTypeListModel> companyTypeListModelArrayList = new ArrayList<>();


    ArrayList<BranchListCategoryWiseApiResponse> branchListCategoryWiseApiModelArrayList = new ArrayList<>();
    TopDiscountsAdapter topDiscountsAdapter;

    ArrayList<SliderApiModel> sliderApiModelArrayList = new ArrayList<>();
    SliderAdapter slider_adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);


        //CompanyTypeList RCV
//        linearLayoutManager = new LinearLayoutManager(requireActivity());
////        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        TopDiscountsRcv.setLayoutManager(linearLayoutManager);

        ImageSliderAdapter();
        companyTypeListApi();
        topDiscounts();
        return binding.getRoot();
    }


    private void sliderApi() {

        progressDialog.show();
        Call<SliderApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mSliderApi("LBP");

        call.enqueue(new Callback<SliderApi>() {
            @Override
            public void onResponse(@NonNull Call<SliderApi> call, @NonNull Response<SliderApi> response) {
                SliderApi sliderApi = response.body();
                if (response.isSuccessful()) {

                    if (sliderApi != null) {

                        if (sliderApi.getStatus() == 1) {

                            // progressDialog.dismiss();
                            if (sliderApi.getSliderList().equals(sliderApiModelList)) {
                                Toast.makeText(context, "same data", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (sliderApi.getSliderList().size() > 0) {
                                    sliderApiModelList.clear();
                                }

                            }

                            sliderApiModelList = sliderApi.getSliderList();
                            for (int i = 0; i < sliderApiModelList.size(); i++) {
                                String Slider_Image = sliderApiModelList.get(i).getSlider_Image();
                                String Slider_Title = sliderApiModelList.get(i).getSlider_Title();
                                //list.add(new SlideModel(Slider_Image,Slider_Title, ScaleTypes.FIT));
                                list.add(new SlideModel(Slider_Image, ScaleTypes.CENTER_INSIDE));
                            }

                            imageSlider.setImageList(list, ScaleTypes.CENTER_INSIDE);
                            imageSlider.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemSelected(int i) {
                                    String Slider_Link = sliderApiModelList.get(i).getSlider_Link();

//                                    Intent intent = new Intent(context, WebViewActivity.class);
//                                    intent.putExtra("URL", Slider_Link);
//                                    startActivity(intent);

                                    /* Uri uri = Uri.parse(lin);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                                if (intent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(HomeActivity.this, "Sorry... Browser App is not available.", Toast.LENGTH_LONG).show();
                                }*/

                                }
                            });

//                            companyTypeListApi();

                        } else {
                            //swipe.setRefreshing(false);
                            progressDialog.dismiss();
                            Toast.makeText(context, " " + sliderApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //swipe.setRefreshing(false);
                        progressDialog.dismiss();
                        Toast.makeText(context, response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // swipe.setRefreshing(false);
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SliderApi> call, @NonNull Throwable t) {
                //swipe.setRefreshing(false);
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void ImageSliderAdapter() {
        progressDialog.show();
        Call<SliderApi> call = RetrofitInstance.getInstance().getApiInterface().mSliderApi("LBP");
        call.enqueue(new Callback<SliderApi>() {
            @Override
            public void onResponse(@NonNull Call<SliderApi> call, @NonNull Response<SliderApi> response) {
                SliderApi requestApi = response.body();
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    assert requestApi != null;
                    sliderApiModelArrayList = requestApi.getSliderList();
                    slider_adapter = new SliderAdapter(getContext(), sliderApiModelArrayList);
                    binding.imageSlider.setSliderAdapter(slider_adapter);
                    binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
                    binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                    binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
                    binding.imageSlider.startAutoCycle();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SliderApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


//    private void BranchListApi() {
//
//        progressDialog.show();
//        Call<BranchListCategoryWiseApiResponse> call = RetrofitInstance
//                .getInstance()
//                .getApiInterface()
//                .mBranchListCategoryWiseApiResponse("0101");
//
//        call.enqueue(new Callback<BranchListCategoryWiseApiResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<BranchListCategoryWiseApiResponse> call, @NonNull Response<BranchListCategoryWiseApiResponse> response) {
//                BranchListCategoryWiseApiResponse branchListCategoryWiseApiResponse = response.body();
//                if (response.isSuccessful()) {
//
//                    if (branchListCategoryWiseApiResponse != null) {
//
//                        if (branchListCategoryWiseApiResponse.getStatus() == 1) {
//                            progressDialog.dismiss();
//                            branchListCategoryWiseApiModelArrayList = branchListCategoryWiseApiResponse.getBranchList();
//                            companyListCategoryWiseAdapter = new CompanyListCategoryWiseAdapter(context,branchListCategoryWiseApiModelArrayList);
//                            CompanyTypeListRecyclerView.setAdapter(companyListCategoryWiseAdapter);
//                            //companyTypeListRcvAdapter.notifyDataSetChanged();
//                            companyListCategoryWiseAdapter.notifyItemInserted(branchListCategoryWiseApiModelArrayList.size());
////                            Lay_noItemInCompanyList.setVisibility(View.GONE);
////                            Lay_withData.setVisibility(View.VISIBLE);
//
//
//                        } else {
//                            progressDialog.dismiss();
//                            Toast.makeText(context, " " + branchListCategoryWiseApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            //if dataList is empty
//                            if (branchListCategoryWiseApiResponse.getStatus() == 2) {
////                                Lay_noItemInCompanyList.setVisibility(View.VISIBLE);
////                                Lay_withData.setVisibility(View.GONE);
//                            }
//                        }
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(context, response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(context, "" + response.code() + " " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<BranchListCategoryWiseApiResponse> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }


    private void companyTypeListApi() {

        progressDialog.show();
        Call<CompanyTypeListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCompanyTypeListApi();

        call.enqueue(new Callback<CompanyTypeListApi>() {
            @Override
            public void onResponse(@NonNull Call<CompanyTypeListApi> call, @NonNull Response<CompanyTypeListApi> response) {
                CompanyTypeListApi companyTypeListApi = response.body();
                if (response.isSuccessful()) {
                    if (companyTypeListApi != null) {
                        if (companyTypeListApi.getStatus() == 1) {

                            //progressDialog.dismiss();
//                            companyTypeListModelArrayList = companyTypeListApi.getCompanyTypeList();
//                            companyTypeListRcvAdapter = new CompanyTypeListRcvAdapter(companyTypeListModelArrayList, context);
//                            CompanyTypeListRecyclerView.setAdapter(companyTypeListRcvAdapter);
//                            //companyTypeListRcvAdapter.notifyDataSetChanged();
//                            companyTypeListRcvAdapter.notifyItemInserted(companyTypeListModelArrayList.size());
//


                            companyTypeListModelArrayList = companyTypeListApi.getCompanyTypeList();
                            binding.CategoryRcv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            companyTypeListRcvAdapter = new CompanyTypeListRcvAdapter(companyTypeListModelArrayList, context);
                            binding.CategoryRcv.setAdapter(companyTypeListRcvAdapter);


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + companyTypeListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //swipe.setRefreshing(false);
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //swipe.setRefreshing(false);
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CompanyTypeListApi> call, @NonNull Throwable t) {
                //swipe.setRefreshing(false);
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void topDiscounts() {

        progressDialog.show();
        Call<ChildItemApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mChildItemApi(PrefsManager.getCompanyCode(), "1");

        call.enqueue(new Callback<ChildItemApi>() {
            @Override
            public void onResponse(@NonNull Call<ChildItemApi> call, @NonNull Response<ChildItemApi> response) {
                ChildItemApi companyTypeListApi = response.body();
                if (response.isSuccessful()) {
                    if (companyTypeListApi != null) {
                        if (companyTypeListApi.getStatus() == 1) {

                            //progressDialog.dismiss();
                            ChildItemList = companyTypeListApi.getItemList();
                            binding.topDiscounts.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            topDiscountsAdapter = new TopDiscountsAdapter(ChildItemList, context);
                            binding.topDiscounts.setAdapter(topDiscountsAdapter);


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + companyTypeListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //swipe.setRefreshing(false);
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //swipe.setRefreshing(false);
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChildItemApi> call, @NonNull Throwable t) {
                //swipe.setRefreshing(false);
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}