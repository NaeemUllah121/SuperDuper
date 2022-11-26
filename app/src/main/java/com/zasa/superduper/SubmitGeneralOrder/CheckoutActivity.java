package com.zasa.superduper.SubmitGeneralOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.SQLite.CartSQLiteDB;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {


    private static final String TAG = "checkkk";
    ///bottom sheet
    private LinearLayout mBottomSheetLayout;
    private BottomSheetBehavior sheetBehavior;
    private ImageView header_Arrow_Image;
    Button btn_submitOrder;
    public TextView tv_HeaderGrandTotal, tv_OrderPayableAmount, tv_Subtotal, tv_ShippingFee, tv_orderDiscount, tv_orderAmount;
    View Total_Lay, Text_Lay, bottomSheet_ContinueBtn;

    float ft_orderNetAmount = 0;//with discount
    float ft_orderAmount = 0;//without discount
    float ft_orderDiscount = 0;//total discount amount
    float ft_grandTotal = 0;//other charges + orderNetAmount
    float ft_ShippingFee = 100, Item_Stax_Amount = 0;

    int a = 1;
    float ft_netTotal = 0;

    Context context;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String firstName, lastName, phone, LB_Points, Member_Unique, DateTime;

    ArrayList<OderModelList> oderModelListArrayList;

    TextInputEditText et_RecipientName, et_RecipientPhone, et_RecipientAddress;
    private String st_RecipientName, st_RecipientPhone, st_RecipientAddress;

    View PaymentMethodLay;
    TextView tv_setPaymentMethod;

    RadioGroup RG_PaymentMethods;
    RadioButton rb_SelectedMethod;
    String st_SelectedMethod;
    int int_ModeOfPayment = 1;

    String productCode, BuyNowClick, item_Net_Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        context = CheckoutActivity.this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");




        /////these values are coming from BuyItemActivity if user click buy item btn
        productCode = getIntent().getStringExtra("productCode");
        BuyNowClick = getIntent().getStringExtra("BuyNowClick");
        item_Net_Amount = getIntent().getStringExtra("item_Net_Amount");

        oderModelListArrayList = new ArrayList<>();

        sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        firstName = sharedPreferences.getString("Member_FName", "");
        lastName = sharedPreferences.getString("Member_LName", "");
        phone = sharedPreferences.getString("Member_Mobile", "");
        LB_Points = sharedPreferences.getString("LB_Points", "0.0");
        Member_Unique = sharedPreferences.getString("Member_Unique", "");
        DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        et_RecipientName = findViewById(R.id.RecipientName);
        et_RecipientPhone = findViewById(R.id.RecipientPhone);
        et_RecipientAddress = findViewById(R.id.RecipientAddress);

        et_RecipientName.setText(firstName + " " + lastName);
        et_RecipientPhone.setText(phone);
        //et_RecipientAddress.setText("");
        //et_RecipientAddress.setText("Address");


        ////////////////bottom sheet//////////////
        bottomSheet_ContinueBtn = findViewById(R.id.Lay_ContinueBtn);
        mBottomSheetLayout = findViewById(R.id.newbottom_sheet_layout);
        sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
        btn_submitOrder = findViewById(R.id.btn_submitOder);
        header_Arrow_Image = findViewById(R.id.newbottom_sheet_arrow);

        tv_HeaderGrandTotal = findViewById(R.id.tv_totall);
        tv_OrderPayableAmount = findViewById(R.id.tv_PayableAmount);
        tv_Subtotal = findViewById(R.id.tv_Subtotal);
        tv_ShippingFee = findViewById(R.id.tv_ShippingFee);
        tv_orderDiscount = findViewById(R.id.tv_orderDiscount);
        tv_orderAmount = findViewById(R.id.tv_orderAmount);

        Total_Lay = findViewById(R.id.TotalLay);
        Text_Lay = findViewById(R.id.TextLay);


        // getOderAmountAndNetTotalFromSQLite();//get Order amount and net total from sqlite

        radioBtn();
        //this line is not run if user click buyNow in BuyItemActivity
        if (!TextUtils.isEmpty(BuyNowClick)) {
            getBuyNowItemDataFromSqlite();
            Log.d(TAG, " getBuyNowItemDataFromSqlite() -->run ");
        } else {
            getOderAmountAndNetTotalFromSQLite();//get Order amount and net total from sqlite
            Log.d(TAG, " getOderAmountAndNetTotalFromSQLite() -->run ");

        }


        //ft_netTotal = Float.parseFloat(tv_Total.getText().toString());
        ft_netTotal = Float.parseFloat(ft_orderNetAmount + "");


        ft_grandTotal = ft_orderNetAmount + ft_ShippingFee;
        ft_orderDiscount = ft_orderAmount - ft_orderNetAmount;

        //   Toast.makeText(context, "ft_netTotal "+ft_netTotal+"\nft_orderNetAmount "+ft_orderNetAmount, Toast.LENGTH_SHORT).show();

        tv_HeaderGrandTotal.setText(ft_grandTotal + "");
        tv_OrderPayableAmount.setText(ft_grandTotal + "");
        //tv_Subtotal.setText(tv_Total.getText().toString());//netTotal
        tv_Subtotal.setText(ft_orderNetAmount + "");//netTotal
        tv_ShippingFee.setText(ft_ShippingFee + "");
        tv_orderDiscount.setText(ft_orderDiscount + "");
        tv_orderAmount.setText(ft_orderAmount + "");


        header_Arrow_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                   /* Text_Lay.setVisibility(View.VISIBLE);
                    Total_Lay.setVisibility(View.GONE);*/
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    /*Total_Lay.setVisibility(View.VISIBLE);
                    Text_Lay.setVisibility(View.GONE);*/
                }

            }
        });

        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                //when bottom sheet STATE_EXPANDED
                if (newState == 3) {
                    Text_Lay.setVisibility(View.VISIBLE);
                    Total_Lay.setVisibility(View.GONE);

                }
                ///when bottom sheet STATE_COLLAPSED or closed
                if (newState == 4) {
                    Total_Lay.setVisibility(View.VISIBLE);
                    Text_Lay.setVisibility(View.GONE);
                }
                // Toast.makeText(context, "" + newState, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                header_Arrow_Image.setRotation(slideOffset * 180);
            }
        });

        ////////////////bottom sheet end//////////////




        btn_submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                st_RecipientName = et_RecipientName.getText().toString();
                st_RecipientPhone = et_RecipientPhone.getText().toString();
                st_RecipientAddress = et_RecipientAddress.getText().toString();


                if (TextUtils.isEmpty(st_RecipientName)) {
                    et_RecipientName.requestFocus();
                    et_RecipientName.setError("Please enter recipient name!");
                    return;
                }
                if (TextUtils.isEmpty(st_RecipientPhone) || !(st_RecipientPhone.length() == 11)) {
                    et_RecipientPhone.requestFocus();
                    et_RecipientPhone.setError("Enter correct recipient phone!");
                    return;
                }
                if (TextUtils.isEmpty(st_RecipientAddress)) {
                    et_RecipientAddress.requestFocus();
                    et_RecipientAddress.setError("Please enter recipient address!");
                    return;
                }

                ////get all cart data from Sqlite and add this data in oderModelList ArrayList
                //getAllCartDataFromSqlite();
                //submitOderApi();

                if (!TextUtils.isEmpty(BuyNowClick)) {

                    oderModelListArrayList.clear();
                    getBuyNowItemDataFromSqlite();

                    submitOderApi();
                    Log.d(TAG, "BuyNowClick -->"+BuyNowClick);

                } else {

                    getAllCartDataFromSqlite();
                    submitOderApi();
                    Log.d(TAG, "AddToCartClick -->"+BuyNowClick);
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        DeleteProduct(productCode);
    }

    public void DeleteProduct(String productCode) {

        if (!TextUtils.isEmpty(BuyNowClick)) {
            CartSQLiteDB sqLiteDBHelper = new CartSQLiteDB(context);
            //  String id = cartListModelArrayList.get(position).getId();
            boolean check = sqLiteDBHelper.deleteData(productCode);
            if (check == true) {
                // Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Product not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getBuyNowItemDataFromSqlite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getItemIdData(productCode);
        if (cursor.getCount() == 0) {             //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            // Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }

        float ft_Item_Disc_Amount, ft_Item_Disc_Per, ft_p_price, ft_P_OLD_PRICE, ft_P_AMOUNT, ft_P_NET_AMOUNT, ft_LB_Points = 0;
        int int_p_Qty = 0;
        String Order_Id, p_name, p_unit, p_price, p_Qty, p_description, p_code, CompanyCode, BranchCode,ClientCode, P_OLD_PRICE, P_AMOUNT, P_NET_AMOUNT;

        while (cursor.moveToNext()) {

            Order_Id = cursor.getString(0);
            p_name = cursor.getString(1);
            p_unit = cursor.getString(2);
            p_price = cursor.getString(3);
            p_Qty = cursor.getString(4);
            p_description = cursor.getString(5);
            p_code = cursor.getString(7);
            CompanyCode = cursor.getString(8);
            BranchCode = cursor.getString(9);
            ClientCode = cursor.getString(10);
            P_OLD_PRICE = cursor.getString(11);
            P_AMOUNT = cursor.getString(12);
            P_NET_AMOUNT = cursor.getString(13);


            //Data parsing
            ft_p_price = Float.parseFloat(p_price);
            ft_P_OLD_PRICE = Float.parseFloat(P_OLD_PRICE);
            ft_P_AMOUNT = Float.parseFloat(P_AMOUNT);
            ft_P_NET_AMOUNT = Float.parseFloat(P_NET_AMOUNT);
            ft_LB_Points = Float.parseFloat(LB_Points);
            int_p_Qty = Integer.parseInt(p_Qty);


            if (ft_P_OLD_PRICE > 0) {

                // calculate Item_Disc_Amount
                ft_Item_Disc_Amount = ft_P_OLD_PRICE - ft_p_price;

                // calculate Item_Disc_Per
                float percentage = (float) ((ft_Item_Disc_Amount / ft_P_OLD_PRICE) * 100.0f);
                //  float percentage_value = 100.0f - percentage;
                ft_Item_Disc_Per = roundOneDecimals(percentage);
            } else {
                ft_Item_Disc_Amount = 0;
                ft_Item_Disc_Per = 0;
            }


            ft_orderNetAmount = ft_P_NET_AMOUNT;
            ft_orderAmount = ft_P_AMOUNT;



            if (st_SelectedMethod.equals("Cash on Delivery")) {
                int_ModeOfPayment = 1;
                // Toast.makeText(context, ""+int_ModeOfPayment, Toast.LENGTH_SHORT).show();
            } else {
                int_ModeOfPayment = 2;
                //  Toast.makeText(context, ""+int_ModeOfPayment, Toast.LENGTH_SHORT).show();

            }


//            st_RecipientName = et_RecipientName.getText().toString();
//            st_RecipientPhone = et_RecipientPhone.getText().toString();
//            st_RecipientAddress = et_RecipientAddress.getText().toString();

            oderModelListArrayList.add(new OderModelList(
                    Member_Unique, CompanyCode, BranchCode, ClientCode, ft_LB_Points,
                    ft_orderAmount, ft_orderDiscount,
                    ft_orderNetAmount, int_ModeOfPayment, ft_ShippingFee, st_RecipientPhone, st_RecipientName, st_RecipientAddress, DateTime, p_code,
                    p_name, int_p_Qty, ft_p_price, ft_P_NET_AMOUNT,
                    Item_Stax_Amount, ft_Item_Disc_Amount, ft_Item_Disc_Per, p_description));

            Log.d(TAG, "getBuyNowItemDataFromSqlite: "+oderModelListArrayList.toString());

        }
    }


    private void getAllCartDataFromSqlite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();
        if (cursor.getCount() == 0) {             //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            // Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }

        float ft_Item_Disc_Amount, ft_Item_Disc_Per, ft_p_price, ft_P_OLD_PRICE, ft_P_AMOUNT, ft_P_NET_AMOUNT, ft_LB_Points = 0;
        int int_p_Qty = 0;
        String Order_Id, p_name, p_unit, p_price, p_Qty, p_description, p_code, CompanyCode, BranchCode,ClientCode, P_OLD_PRICE, P_AMOUNT, P_NET_AMOUNT;

        while (cursor.moveToNext()) {

            Order_Id = cursor.getString(0);
            p_name = cursor.getString(1);
            p_unit = cursor.getString(2);
            p_price = cursor.getString(3);
            p_Qty = cursor.getString(4);
            p_description = cursor.getString(5);
            p_code = cursor.getString(7);
            CompanyCode = cursor.getString(8);
            BranchCode = cursor.getString(9);
            ClientCode = cursor.getString(10);
            P_OLD_PRICE = cursor.getString(11);
            P_AMOUNT = cursor.getString(12);
            P_NET_AMOUNT = cursor.getString(13);


            //Data parsing
            ft_p_price = Float.parseFloat(p_price);
            ft_P_OLD_PRICE = Float.parseFloat(P_OLD_PRICE);
            ft_P_AMOUNT = Float.parseFloat(P_AMOUNT);
            ft_P_NET_AMOUNT = Float.parseFloat(P_NET_AMOUNT);
            ft_LB_Points = Float.parseFloat(LB_Points);
            int_p_Qty = Integer.parseInt(p_Qty);


            if (ft_P_OLD_PRICE > 0) {

                // calculate Item_Disc_Amount
                ft_Item_Disc_Amount = ft_P_OLD_PRICE - ft_p_price;

                // calculate Item_Disc_Per
                float percentage = (float) ((ft_Item_Disc_Amount / ft_P_OLD_PRICE) * 100.0f);
                //  float percentage_value = 100.0f - percentage;
                ft_Item_Disc_Per = roundOneDecimals(percentage);
            } else {
                ft_Item_Disc_Amount = 0;
                ft_Item_Disc_Per = 0;
            }



            if (st_SelectedMethod.equals("Cash on Delivery")) {
                int_ModeOfPayment = 1;
                // Toast.makeText(context, ""+int_ModeOfPayment, Toast.LENGTH_SHORT).show();
            } else {
                int_ModeOfPayment = 2;
                //  Toast.makeText(context, ""+int_ModeOfPayment, Toast.LENGTH_SHORT).show();

            }

//            st_RecipientName = et_RecipientName.getText().toString();
//            st_RecipientPhone = et_RecipientPhone.getText().toString();
//            st_RecipientAddress = et_RecipientAddress.getText().toString();


            oderModelListArrayList.add(new OderModelList(
                    Member_Unique, CompanyCode,BranchCode,
                    ClientCode, ft_LB_Points,
                    ft_orderAmount, ft_orderDiscount,
                    ft_orderNetAmount, int_ModeOfPayment, ft_ShippingFee, st_RecipientPhone, st_RecipientName, st_RecipientAddress, DateTime, p_code,
                    p_name, int_p_Qty, ft_p_price, ft_P_NET_AMOUNT,
                    Item_Stax_Amount, ft_Item_Disc_Amount, ft_Item_Disc_Per, p_description));

        }
    }

    public void submitOderApi() {

        progressDialog.show();
        OrderModel orderModel = new OrderModel(oderModelListArrayList);
        Call<SubmitOrderApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mSubmitOrder(orderModel);

        call.enqueue(new Callback<SubmitOrderApi>() {
            @Override
            public void onResponse(@NonNull Call<SubmitOrderApi> call, @NonNull Response<SubmitOrderApi> response) {

                SubmitOrderApi submitOrderApi = response.body();

                if (response.isSuccessful()) {
                    if (submitOrderApi != null) {
                        if (submitOrderApi.getStatus() == 1) {
                            progressDialog.dismiss();

                            ///Delete All Data from SQLite
                            CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
                            cartSQLiteDB.deleteAllData();

//                            CuToast.sound= false ;//or false
//                            CuToast.showSuccess(CheckoutActivity.this,"Success",submitOrderApi.getMessage(),Toast.LENGTH_LONG);

                            Toast.makeText(context, " " + submitOrderApi.getMessage(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, HomeActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, " " + submitOrderApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<SubmitOrderApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void getOderAmountAndNetTotalFromSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();

        if (cursor.getCount() == 0) {
            // Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }

        while (cursor.moveToNext()) {
            ft_orderAmount = ft_orderAmount + Float.parseFloat(cursor.getString(12));
            ft_orderNetAmount = ft_orderNetAmount + Float.parseFloat(cursor.getString(13));

        }
        //   Toast.makeText(this, "netAmount " + ft_orderNetAmount, Toast.LENGTH_SHORT).show();

   /*     tv_Total = findViewById(R.id.tv_total);
        if (netAmount > 0) {

            tv_Total.setText("" + netAmount);
        } else {
            tv_Total.setText("0.0");
        }*/
    }

    public float roundOneDecimals(float value) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Float.parseFloat(twoDForm.format(value));
    }

    private void radioBtn() {

        PaymentMethodLay = findViewById(R.id.PaymentMethodLay);
        RG_PaymentMethods = findViewById(R.id.RG_OnlinePaymentMethods);

        int onlinePaymentId = RG_PaymentMethods.getCheckedRadioButtonId();
        rb_SelectedMethod = RG_PaymentMethods.findViewById(onlinePaymentId);
        st_SelectedMethod = rb_SelectedMethod.getText().toString();

        tv_setPaymentMethod = findViewById(R.id.tv_setPaymentMethod);
        tv_setPaymentMethod.setText(st_SelectedMethod);

        RG_PaymentMethods.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int onlinePaymentId = RG_PaymentMethods.getCheckedRadioButtonId();
                rb_SelectedMethod = RG_PaymentMethods.findViewById(onlinePaymentId);
                st_SelectedMethod = rb_SelectedMethod.getText().toString();
                tv_setPaymentMethod.setText(st_SelectedMethod);

                PaymentMethodLay.setVisibility(View.GONE);

                // Toast.makeText(context, "" + st_OnlineSelectedMethod, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void btn_back(View view) {
        DeleteProduct(productCode);
        finish();
    }

    public void btn_OnlinePaymentVisibility(View view) {
        a++;
        if (a % 2 == 0) {
            PaymentMethodLay.setVisibility(View.VISIBLE);
        } else {
            PaymentMethodLay.setVisibility(View.GONE);

        }
    }

}