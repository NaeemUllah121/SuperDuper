package com.zasa.superduper.WasteMaterialRequestSubmit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.SQLite.ScannedItemSqliteDB;
import com.zasa.superduper.WasteMaterialRequestHistory.MaterialRequestHistoryActivity;
import com.zasa.superduper.WasteMaterialScanner.ScannerViewActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveEarthActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;

    RecyclerView scannedItemListRecyclerView;
    ScannedItemRCVAdapter scannedItemRCVAdapter;
    ArrayList<ScannedItemListModel> scannedItemListModelArrayList;
    LinearLayoutManager linearLayoutManager;

    public static TextView totalPoints;

    String Material_Code, Client_Code, Company_Code, Material_Name;
    int Material_Type, Material_IsActive;
    float One_Material_LB_Points, Material_LB_Points, Material_LB_Points_Total;
    int Material_Qty = 1;


    SharedPreferences sharedPreferences;
    String firstName, lastName, phone, LB_Points, Member_Unique, DateTime;

    TextView tv_totalPointsDialog;
    Button btn_ok;
    ImageView closebtn;
    TextInputEditText et_RecipientName, et_RecipientPhone, et_RecipientAddress;
    private String st_RecipientName, st_RecipientPhone, st_RecipientAddress;

    ArrayList<MOrderListModel> mOrderListArrayListModel;
    View parentLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_earth);

        parentLay = findViewById(R.id.parentLay);
        context = SaveEarthActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        mOrderListArrayListModel = new ArrayList<>();

        sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        firstName = sharedPreferences.getString("Member_FName", "");
        lastName = sharedPreferences.getString("Member_LName", "");
        phone = sharedPreferences.getString("Member_Mobile", "");
        LB_Points = sharedPreferences.getString("LB_Points", "0.0");
        Member_Unique = sharedPreferences.getString("Member_Unique", "");
        DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

       /* ////these values are coming from ScannerView Activity

        Material_Code = getIntent().getStringExtra("Material_Code");
        Material_Name = getIntent().getStringExtra("Material_Name");
        Client_Code = getIntent().getStringExtra("Client_Code");
        Company_Code = getIntent().getStringExtra("Company_Code");
        Material_Type = getIntent().getIntExtra("Material_Type", 0);
        Material_IsActive = getIntent().getIntExtra("Material_IsActive", 0);
        One_Material_LB_Points = getIntent().getFloatExtra("LB_Points", 0);*/

        //AddDataInSqlite();

        totalPoints = findViewById(R.id.totalPoints);

        scannedItemListModelArrayList = new ArrayList<>();

        scannedItemListRecyclerView = findViewById(R.id.recycler_scanGain);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        scannedItemListRecyclerView.setLayoutManager(linearLayoutManager);

        getAllItemDataFromSQLite(); //get all data from sqlite and set in Rcv

        scannedItemRCVAdapter = new ScannedItemRCVAdapter(scannedItemListModelArrayList, context);
        scannedItemListRecyclerView.setAdapter(scannedItemRCVAdapter);
        scannedItemRCVAdapter.notifyDataSetChanged();
        //scannedItemRCVAdapter.notifyItemChanged(scannedItemListModelArrayList.size());


        getAllItemPointsFromSQLite();//get total points

    }


    private void mUserDetailsDialog() {

        /*final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View dialogView = getLayoutInflater().inflate(R.layout.alertdialog_lay_scan_material_recipient_confrom, null);
        builder.setView(dialogView).setCancelable(false);*/


        Dialog dialogView = new Dialog(context);
        dialogView.setContentView(R.layout.alertdialog_lay_scan_material_recipient_confrom);
        dialogView.setCancelable(false);

        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.style_custom_dialog_background));
        } else {
            dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog


        tv_totalPointsDialog = dialogView.findViewById(R.id.totalPointsDialog);
        et_RecipientName = dialogView.findViewById(R.id.RecipientNamee);
        et_RecipientPhone = dialogView.findViewById(R.id.RecipientPhonee);
        et_RecipientAddress = dialogView.findViewById(R.id.RecipientAddresss);
         btn_ok = dialogView.findViewById(R.id.btn_ok);
         closebtn = dialogView.findViewById(R.id.closebtn);

        et_RecipientName.setText(firstName + " " + lastName);
        et_RecipientPhone.setText(phone);
        et_RecipientAddress.setText("");
        tv_totalPointsDialog.setText(totalPoints.getText().toString());


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                st_RecipientName = et_RecipientName.getText().toString();
                st_RecipientPhone = et_RecipientPhone.getText().toString();
                st_RecipientAddress = et_RecipientAddress.getText().toString();

                if (TextUtils.isEmpty(st_RecipientName)) {
                    et_RecipientName.requestFocus();
                    et_RecipientName.setError("Please enter recipient name!");
                   // showSnackBar("Please enter user name!");
                    return;
                }
                if (TextUtils.isEmpty(st_RecipientPhone) || !(st_RecipientPhone.length() == 11)) {
                    et_RecipientName.requestFocus();
                    et_RecipientName.setError("Enter correct user phone");
                    //showSnackBar("Enter correct user phone!");
                    return;
                }
                if (TextUtils.isEmpty(st_RecipientAddress)) {
                    et_RecipientName.requestFocus();
                    et_RecipientName.setError("Please enter user address!");
                    //showSnackBar("Please enter user address!");
                    return;
                }
                ///////////get All Material Data From SQLite For Api Call
                getAllMaterialDataFromSQLiteForApiCall();
                ///Api Call
                submitMaterialOderApi();

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

    private void getAllItemDataFromSQLite() {

        ScannedItemSqliteDB sqliteDB = new ScannedItemSqliteDB(context);
        Cursor cursor = sqliteDB.getAllData();
        if (cursor.getCount() == 0) {    //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            //Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()) {

            scannedItemListModelArrayList.add(new ScannedItemListModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10))
            );

        }
    }

    private void getAllMaterialDataFromSQLiteForApiCall() {

        ScannedItemSqliteDB sqliteDB = new ScannedItemSqliteDB(context);
        Cursor cursor = sqliteDB.getAllData();
        if (cursor.getCount() == 0) {
            //Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }

        String Material_Code, Material_Name, Material_Qty_Request, Material_LB_Points;
        float ft_LB_Points, ft_Material_Qty_Request, ft_Material_LB_Points;

        while (cursor.moveToNext()) {

            Material_Code = cursor.getString(1);
            Material_Name = cursor.getString(2);
            Material_Qty_Request = cursor.getString(4);
            Material_LB_Points = cursor.getString(5);

            //Data parsing
            ft_LB_Points = Float.parseFloat(LB_Points);
            ft_Material_Qty_Request = Float.parseFloat(Material_Qty_Request);
            ft_Material_LB_Points = Float.parseFloat(Material_LB_Points);


            /*st_RecipientName = et_RecipientName.getText().toString();
            st_RecipientPhone = et_RecipientPhone.getText().toString();
            st_RecipientAddress = et_RecipientAddress.getText().toString();*/

            mOrderListArrayListModel.add(new MOrderListModel(
                    Member_Unique, ft_LB_Points, st_RecipientAddress, DateTime, Material_Code,
                    Material_Name, ft_Material_Qty_Request, ft_Material_LB_Points, 0,
                    st_RecipientName, st_RecipientPhone
            ));

        }
    }

    public void submitMaterialOderApi() {

        progressDialog.show();
        MOrderList orderModel = new MOrderList(mOrderListArrayListModel);
        Call<MOrderListModelSubmitApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mSubmitMOrderApi(orderModel);

        call.enqueue(new Callback<MOrderListModelSubmitApi>() {
            @Override
            public void onResponse(@NonNull Call<MOrderListModelSubmitApi> call, @NonNull Response<MOrderListModelSubmitApi> response) {

                MOrderListModelSubmitApi MOrderListModelSubmitApi = response.body();
                if (response.isSuccessful()) {
                    if (MOrderListModelSubmitApi != null) {
                        if (MOrderListModelSubmitApi.getStatus() == 1) {
                            progressDialog.dismiss();

                            ///Delete All Data from SQLite
                            ScannedItemSqliteDB sQLiteDB = new ScannedItemSqliteDB(context);
                            sQLiteDB.deleteAllData();

                            Toast.makeText(context, " " + MOrderListModelSubmitApi.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, HomeActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, " " + MOrderListModelSubmitApi.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NonNull Call<MOrderListModelSubmitApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void btn_SubmitRequest(View view) {



        if (scannedItemListModelArrayList.size() == 0) {
            Snackbar.make(view, "Insufficient Points!", Snackbar.LENGTH_LONG).show();
        } else {
            mUserDetailsDialog();
        }

    }



    public void getAllItemPointsFromSQLite() {

        ScannedItemSqliteDB sqliteDB = new ScannedItemSqliteDB(context);
        Cursor cursor = sqliteDB.getAllData();
        if (cursor.getCount() == 0) {             //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            //  Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        float sql_TotalPoints = 0, newSql_TotalPoints = 0;
        while (cursor.moveToNext()) {
            sql_TotalPoints = sql_TotalPoints + Float.parseFloat(cursor.getString(5));

            ////round OF number
            DecimalFormat twoDForm = new DecimalFormat("#.#");
            newSql_TotalPoints = Float.parseFloat(twoDForm.format(sql_TotalPoints));

        }
        //Toast.makeText(this, "sql_Price " + newSql_Price, Toast.LENGTH_SHORT).show();

        if (newSql_TotalPoints > 0) {

            totalPoints.setText("" + newSql_TotalPoints);
        } else {
            totalPoints.setText("0.0");
        }
    }

    public void btn_back(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finishAffinity();
    }

    public void btn_ScanMore(View view) {
        startActivity(new Intent(this, ScannerViewActivity.class));
        finish();
    }

    public void showSnackBar(String Msg) {

        Snackbar snackbar = Snackbar.make(parentLay, Msg, Snackbar.LENGTH_INDEFINITE)//LENGTH_INDEFINITE
                //.setAnchorView(parentView)
                //.setDuration(7000)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mUserDetailsDialog();
                    }
                });
        snackbar.show();
    }

    public void btn_MRequestHistory(View view) {
        startActivity(new Intent(context, MaterialRequestHistoryActivity.class));
    }

        /*    //////////Add data into SQLite
    public void AddDataInSqlite() {

        boolean checkkk;
        checkkk = checkItemExistenceInSQLite(Material_Code);//check that item is already exist in Local Db or not

        if (checkkk == false) {

            ScannedItemSqliteDB cartSQLiteDB = new ScannedItemSqliteDB(context);
            boolean check = cartSQLiteDB.insertValue(Material_Code, Material_Name, One_Material_LB_Points + "", Material_Qty + "", Material_LB_Points + "", Material_LB_Points_Total + "", Material_Type + "", Material_IsActive + "", Company_Code, Client_Code);

            if (check == true) {
                Toast.makeText(context, "Data is inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Data is not inserted", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean checkItemExistenceInSQLite(String Material_Code) {


        ScannedItemSqliteDB cartSQLiteDB = new ScannedItemSqliteDB(context);
        Cursor cursor = cartSQLiteDB.getItemIdData(Material_Code);
        if (cursor.getCount() == 0) {   //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            Toast.makeText(this, "Nothing Found", Toast.LENGTH_LONG).show();
            //Log.d(TAG, " checkItemExistenceInSQLite() 1st if body-->return false");
            return false;
        }
        int sql_Qty, i = 0;
        while (cursor.moveToNext()) {


            //Log.d(TAG, " while (cursor.moveToNext()) --> " + ++i + " -- " + " st_itemCode-->" + "  productCode-->" + productCode);


            Toast.makeText(context, Material_Code + " Item already exist!", Toast.LENGTH_SHORT).show();

            sql_Qty = 1 + Integer.parseInt(cursor.getString(4));


            updateQtyAndLB_points(Material_Code, sql_Qty);

        }

        return true;

    }

    private void updateQtyAndLB_points(String material_code, int newQty) {
        Material_LB_Points = (One_Material_LB_Points * newQty);
        ScannedItemSqliteDB sqLiteDBHelper = new ScannedItemSqliteDB(context);
        boolean isUpdate = sqLiteDBHelper.updateQtyAndLB_points(material_code, newQty + "", Material_LB_Points + "");

        if (isUpdate == true) {


            Toast.makeText(context, "Qty And LB_points updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data not Found", Toast.LENGTH_SHORT).show();
        }

    }*/


/*    private void mUserDetailsDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View dialogView = getLayoutInflater().inflate(R.layout.alertdialog_lay_scan_material_recipient_confrom, null);
        builder.setView(dialogView).setCancelable(false);

        tv_totalPointsDialog = dialogView.findViewById(R.id.totalPointsDialog);
        et_RecipientName = dialogView.findViewById(R.id.RecipientNamee);
        et_RecipientPhone = dialogView.findViewById(R.id.RecipientPhonee);
        et_RecipientAddress = dialogView.findViewById(R.id.RecipientAddresss);

        et_RecipientName.setText(firstName + " " + lastName);
        et_RecipientPhone.setText(phone);
        et_RecipientAddress.setText("");
        tv_totalPointsDialog.setText(totalPoints.getText().toString());

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                st_RecipientName = et_RecipientName.getText().toString();
                st_RecipientPhone = et_RecipientPhone.getText().toString();
                st_RecipientAddress = et_RecipientAddress.getText().toString();

                if (TextUtils.isEmpty(st_RecipientName)) {
                 *//*   et_RecipientName.requestFocus();
                    et_RecipientName.setError("Please enter recipient name!");*//*
                    showSnackBar("Please enter user name!");
                    return;

                }
                if (TextUtils.isEmpty(st_RecipientPhone) || !(st_RecipientPhone.length() == 11)) {
               *//*     et_RecipientPhone.requestFocus();
                    et_RecipientPhone.setError("Enter correct recipient phone!");*//*
                    showSnackBar("Enter correct user phone!");
                    return;
                }
                if (TextUtils.isEmpty(st_RecipientAddress)) {
                *//*    et_RecipientAddress.requestFocus();
                    et_RecipientAddress.setError("Please enter recipient address!");*//*
                    showSnackBar("Please enter user address!");
                    return;
                }
                ///////////get All Material Data From SQLite For Api Call
                getAllMaterialDataFromSQLiteForApiCall();
                ///Api Call
                submitMaterialOderApi();


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create();
        builder.show();


    }*/






}