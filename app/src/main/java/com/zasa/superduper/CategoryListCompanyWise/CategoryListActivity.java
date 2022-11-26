package com.zasa.superduper.CategoryListCompanyWise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.SQLite.CartSQLiteDB;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity {

    Context context;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    int i = 0;
    ImageView btn_cart;
    public static int sql_Qty = 0;
    public TextView tv_CartTotalItemQty;

    ArrayList<ParentItemCatList> parentItemCatListArrayList = new ArrayList<>();////Category List (Company Wise)
    ArrayList<ChildItemList> childItemListArrayList = new ArrayList<>();/////Item List (Comany & Category Wise)


    ArrayList<CategoryItemCombineModel> categoryItemCombineModelArrayList = new ArrayList<>();
    ArrayList<ChildItemList> newChildList = new ArrayList<>();/////Item List (Company Wise)

    RecyclerView ParentRecyclerViewItem;
    LinearLayoutManager layoutManager;
    ParentItemAdapter parentItemAdapter;
    //ChildItemAdapter childItemAdapter = new ChildItemAdapter();

    int Item_Category_Code, Item_Category_IsActive;
    String Company_Code, intentCompanyCode;
    public static String Branch_Code;

    TextView tv_CompanyName;

    View SearchLay, HeaderLay, noItemInCategoryList, topLay;
    EditText et_SearchCompany;
    CircleImageView circleImageView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        context = CategoryListActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);


        ////these values are coming from CompanyListCategoryWise Adapter
        intentCompanyCode = getIntent().getStringExtra("company_code");
        String CompanyName = getIntent().getStringExtra("Title");
        String imgUrl = getIntent().getStringExtra("imgUrl");
        String Company_Category_Code = getIntent().getStringExtra("Company_Category_Code");
        Branch_Code = getIntent().getStringExtra("Branch_Code");



        noItemInCategoryList = findViewById(R.id.noItemInCategoryList);
        topLay = findViewById(R.id.topLay);


        tv_CompanyName = findViewById(R.id.header_detail);
        tv_CompanyName.setText(CompanyName);


        circleImageView = findViewById(R.id.logo);
        Glide.with(context)
                .load(imgUrl)
                .centerInside()
                //.fitCenter()
                .placeholder(R.drawable.noimgg)//onimage
                .into(circleImageView);


        imageView = findViewById(R.id.banner);
        // String imgUrll = "http://apis.loyaltybunch.com/CatBanner/"+Company_Category_Code+".png";
        String imgUrll = "http://apis.loyaltybunch.com/Banners_Company/" + intentCompanyCode + ".png";

        Glide.with(context)
                .load(imgUrll)
                .centerInside()
                //.fitCenter()
                .placeholder(R.drawable.no_image_banner)//onimage
                .into(imageView);


        //Set total item qty on cart badge
        //tv_CartTotalItemQty =findViewById(R.id.categoryTotalItemQty);
        // getItemQty();


        ParentRecyclerViewItem = findViewById(R.id.CategoryListCompanyWiseRcv);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        // parentApi();
        CategoryItemCombineApi();
        searchableHeader();


    }

    private void searchableHeader() {
        //////search able header//////////
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
                   // parentItemAdapter.filter(s.toString()); //this method is define in parent Adapter
                   // filter(s.toString()); //this method is define in parent Adapter
                } catch (Exception e) {
                    Toast.makeText(context, "No data found!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });
        //////search able header//////////
    }


    //        private void filter(String text) {
//        ArrayList <ChildItemList> filteredList = new ArrayList<>();
//        for (ChildItemList item : childItemListArrayList) {
//            if (item.getItem_Name().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(item);
//            }
//        }
//        childItemAdapter.filteredListMethod(filteredList);
//
//    }

    public void getItemQty() {
        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();
        if (cursor.getCount() == 0) {             //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            Toast.makeText(context, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        int sql_ItemQty = 0;
        while (cursor.moveToNext()) {
            sql_ItemQty = sql_ItemQty + Integer.parseInt(cursor.getString(4));
        }

        Toast.makeText(context, "sql_Qty " + sql_ItemQty, Toast.LENGTH_SHORT).show();

        if (sql_ItemQty > 0) {
            tv_CartTotalItemQty.setVisibility(View.VISIBLE);
            tv_CartTotalItemQty.setText(sql_ItemQty + "");
        } else {
            tv_CartTotalItemQty.setVisibility(View.GONE);
        }

    }

    public void CategoryItemCombineApi() {

        progressDialog.show();
        Call<CategoryItemCombineApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCategoryItemCombineApi(intentCompanyCode);
        call.enqueue(new Callback<CategoryItemCombineApi>() {
            @Override
            public void onResponse(@NonNull Call<CategoryItemCombineApi> call, @NonNull Response<CategoryItemCombineApi> response) {

                CategoryItemCombineApi parentItemApi = response.body();
                if (response.isSuccessful()) {

                    if (parentItemApi != null) {

                        if (parentItemApi.getStatus() == 1) {
                            topLay.setVisibility(View.VISIBLE);

                            progressDialog.dismiss();
                            categoryItemCombineModelArrayList = parentItemApi.getCatItemList();
                            parentItemAdapter = new ParentItemAdapter(categoryItemCombineModelArrayList, context);

                            // Set the layout manager
                            // and adapter for items
                            // of the parent recyclerview

                            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
                          ParentRecyclerViewItem.setLayoutManager(layoutManager);

                            parentItemAdapter.notifyItemInserted(categoryItemCombineModelArrayList.size());
                          //parentItemAdapter.notifyItemInserted(parentItemCatListArrayList.size());


                        } else {
                            progressDialog.dismiss();

                            if (parentItemApi.getStatus() == 2) {
                                //if dataList is empty
                                noItemInCategoryList.setVisibility(View.VISIBLE);
                                topLay.setVisibility(View.GONE);
                            }
                            Toast.makeText(context, "" + parentItemApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<CategoryItemCombineApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void parentApi() {

        progressDialog.show();
        Call<ParentItemApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mParentItemApi(intentCompanyCode);
        call.enqueue(new Callback<ParentItemApi>() {
            @Override
            public void onResponse(@NonNull Call<ParentItemApi> call, @NonNull Response<ParentItemApi> response) {

                ParentItemApi parentItemApi = response.body();
                if (response.isSuccessful()) {

                    if (parentItemApi != null) {

                        if (parentItemApi.getStatus() == 1) {

                            //  progressDialog.dismiss();
                            parentItemCatListArrayList = parentItemApi.getCatList();

                            String st_Company_Code = parentItemApi.getCatList().get(0).getCompany_Code();

//                            for (int i = 0; i < parentItemCatListArrayList.size(); i++) {
//                                Company_Code = parentItemApi.getCatList().get(i).getCompany_Code();
//                                Item_Category_Code = parentItemApi.getCatList().get(i).getItem_Category_Code();
//                                Item_Category_IsActive = parentItemApi.getCatList().get(i).getItem_Category_IsActive();
//
//                                //childApi(Company_Code,Item_Category_Code+"");
//                            }

                            childddApi(st_Company_Code); ////show all vendor items without category wise


                        } else {
                            progressDialog.dismiss();

                            if (parentItemApi.getStatus() == 2) {
                                //if dataList is empty
                                noItemInCategoryList.setVisibility(View.VISIBLE);
                                ParentRecyclerViewItem.setVisibility(View.GONE);
                                topLay.setVisibility(View.GONE);
                            }
                            Toast.makeText(context, "" + parentItemApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<ParentItemApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void childApi(String Company_Code, String Item_Category_Code) {

        // progressDialog.show();
        Call<ChildItemApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mChildItemApi(Company_Code, Item_Category_Code);
        call.enqueue(new Callback<ChildItemApi>() {
            @Override
            public void onResponse(@NonNull Call<ChildItemApi> call, @NonNull Response<ChildItemApi> response) {

                ChildItemApi childItemApi = response.body();

                if (response.isSuccessful()) {
                    if (childItemApi != null) {
                        if (childItemApi.getStatus() == 1) {

                            Toast.makeText(context, "" + ++i, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            childItemListArrayList = childItemApi.getItemList();


                            //  parentItemAdapter = new ParentItemAdapter(parentItemCatListArrayList, childItemListArrayList, context);

                            // Set the layout manager
                            // and adapter for items
                            // of the parent recyclerview
                            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
                            ParentRecyclerViewItem.setLayoutManager(layoutManager);

                        } else {
                            progressDialog.dismiss();

                            //if dataList is empty
                            if (childItemApi.getStatus() == 2) {
                                noItemInCategoryList.setVisibility(View.VISIBLE);
                                ParentRecyclerViewItem.setVisibility(View.GONE);
                                topLay.setVisibility(View.GONE);
                                Toast.makeText(context, "" + childItemApi.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.errorBody(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<ChildItemApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void childddApi(String Company_Code) {

        // progressDialog.show();
        Call<ItemListCompanyWiseApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mItemListCompanyWiseApi(Company_Code);
        call.enqueue(new Callback<ItemListCompanyWiseApi>() {
            @Override
            public void onResponse(@NonNull Call<ItemListCompanyWiseApi> call, @NonNull Response<ItemListCompanyWiseApi> response) {

                ItemListCompanyWiseApi itemListCompanyWiseApi = response.body();

                if (response.isSuccessful()) {
                    if (itemListCompanyWiseApi != null) {
                        if (itemListCompanyWiseApi.getStatus() == 1) {

                            Toast.makeText(context, "" + ++i, Toast.LENGTH_SHORT).show();
                            childItemListArrayList = itemListCompanyWiseApi.getItemList();
                            progressDialog.dismiss();




                           // parentItemAdapter = new ParentItemAdapter(parentItemCatListArrayList, childItemListArrayList, modelOfLIstArrayList, context);

                            // Set the layout manager
                            // and adapter for items
                            // of the parent recyclerview
                            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
                            ParentRecyclerViewItem.setLayoutManager(layoutManager);

//                            parentItemAdapter.notifyItemInserted(parentItemCatListArrayList.size());
//                            parentItemAdapter.notifyItemInserted(childItemListArrayList.size());

                        } else {
                            progressDialog.dismiss();

                            //if dataList is empty
                            if (itemListCompanyWiseApi.getStatus() == 2) {
                                noItemInCategoryList.setVisibility(View.VISIBLE);
                                ParentRecyclerViewItem.setVisibility(View.GONE);
                                topLay.setVisibility(View.GONE);
                                Toast.makeText(context, "" + itemListCompanyWiseApi.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.errorBody(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<ItemListCompanyWiseApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }




    public void btn_back(View view) {
        finish();
    }

    public void btn_search(View view) {

        //set focus on edit text and open keyboard
        et_SearchCompany.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et_SearchCompany, InputMethodManager.SHOW_IMPLICIT);

        HeaderLay.setVisibility(View.GONE);
        SearchLay.setVisibility(View.VISIBLE);
    }

    public void btn_SearchBack(View view) {
        et_SearchCompany.getText().clear();
        SearchLay.setVisibility(View.GONE);
        HeaderLay.setVisibility(View.VISIBLE);
    }

    public void btn_close(View view) {
        et_SearchCompany.getText().clear();
        et_SearchCompany.setText("");
    }

    public void btn_ContinueShopping(View view) {

        startActivity(new Intent(context, HomeActivity.class));
        finishAffinity();
    }
}