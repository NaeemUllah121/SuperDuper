package com.zasa.superduper.WasteMaterialScanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.SQLite.ScannedItemSqliteDB;
import com.zasa.superduper.WasteMaterialRequestSubmit.SaveEarthActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerViewActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Context context;
    ProgressDialog progressDialog;

    ZXingScannerView zXingScannerView;
    ImageView flashOn, flashOff;
    TextView TextLay;

    String Material_Code, Client_Code, Company_Code, Material_Name;
    int Material_Type, Material_IsActive;
    float One_Material_LB_Points, Material_LB_Points, Material_LB_Points_Total;
    int Material_Qty = 1;
    boolean mAutoFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_view);

        if(savedInstanceState != null) {
         // mAutoFocus = savedInstanceState.getBoolean(AUTO_FOCUS_STATE, true);

        }

        context = ScannerViewActivity.this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        flashOn = findViewById(R.id.flashOn);
        flashOff = findViewById(R.id.flashOff);
        zXingScannerView = findViewById(R.id.zxing);

        TextLay = findViewById(R.id.Text);
       /// TextLay.setSelected(true);  // Set focus to the textview (moving text)
       // TextLay.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        requestForCameraPermission();
    }



    @Override
    public void handleResult(Result rawResult) {

        //scanned item code
        String Code = rawResult.getText();
        Material_Code = Code.toUpperCase();


//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                zXingScannerView.resumeCameraPreview(ScannerViewActivity.this);
//            }
//        }, 2000);

        AddDataInSqlite();


    }




    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
         }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.setAspectTolerance(0.2f);

        //zXingScannerView.setAutoFocus(true);
        //zXingScannerView.setSoundEffectsEnabled(true);
        requestForCameraPermission();


    }

    private void requestForCameraPermission() {
        Dexter.withContext(getApplicationContext())  //Dexter is used for runtime permission
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        zXingScannerView.setResultHandler(ScannerViewActivity.this);
                        zXingScannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        requestForCameraPermission();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();  //ask continuously for permission utill he grant permission
                    }
                }).check();
    }

    public void flashOn(View view) {

        zXingScannerView.setFlash(true);
        flashOn.setVisibility(View.GONE);
        flashOff.setVisibility(View.VISIBLE);
    }

    public void flashOff(View view) {
        zXingScannerView.setFlash(false);
        flashOn.setVisibility(View.VISIBLE);
        flashOff.setVisibility(View.GONE);
    }


    private void CheckMaterialItemApi(String scanResult) {

        progressDialog.show();
        Call<CheckMaterialItemApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mCheckMaterialApi(scanResult);//scanned member card id
        call.enqueue(new Callback<CheckMaterialItemApi>() {
            @Override
            public void onResponse(@NonNull Call<CheckMaterialItemApi> call, @NonNull Response<CheckMaterialItemApi> response) {

                progressDialog.dismiss();

                CheckMaterialItemApi checkMaterialItemApi = response.body();

                if (response.isSuccessful()) {
                    if (checkMaterialItemApi != null) {
                        if (checkMaterialItemApi.getStatus() == 1) {

                            String Material_Code = checkMaterialItemApi.SEMItem.getMaterial_Code();
                            int Material_Type = checkMaterialItemApi.SEMItem.getMaterial_Type();
                            String Material_Name = checkMaterialItemApi.SEMItem.getMaterial_Name();
                            int Material_IsActive = checkMaterialItemApi.SEMItem.getMaterial_IsActive();
                            float One_Material_LB_Points = checkMaterialItemApi.SEMItem.getLB_Points();
                            String Client_Code = checkMaterialItemApi.SEMItem.getClient_Code();
                            String Company_Code = checkMaterialItemApi.SEMItem.getCompany_Code();

                            Toast.makeText(context, "" + checkMaterialItemApi.getMessage(), Toast.LENGTH_LONG).show();

                            float MaterialLB_Points = (One_Material_LB_Points * Material_Qty);

                            /////Add data in sqlite//////
                            ScannedItemSqliteDB cartSQLiteDB = new ScannedItemSqliteDB(context);
                            boolean check = cartSQLiteDB.insertValue(Material_Code, Material_Name, One_Material_LB_Points + "", Material_Qty + "", MaterialLB_Points + "", Material_LB_Points_Total + "", Material_Type + "", Material_IsActive + "", Company_Code, Client_Code);

                            if (check == true) {
                              //  Toast.makeText(context, "Data is inserted", Toast.LENGTH_SHORT).show();

                            } else {
                               // Toast.makeText(context, "Data is not inserted", Toast.LENGTH_SHORT).show();
                            }
                            /////Add data in sqlite/////


                            Intent intent = new Intent(ScannerViewActivity.this, SaveEarthActivity.class);
                             /*intent.putExtra("Material_Code", Material_Code);
                            intent.putExtra("Material_Name", Material_Name);
                            intent.putExtra("Material_IsActive", Material_IsActive);
                            intent.putExtra("LB_Points", One_Material_LB_Points);
                            intent.putExtra("Client_Code", Client_Code);
                            intent.putExtra("Company_Code", Company_Code);
                            intent.putExtra("Material_Type", Material_Type);*/
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + checkMaterialItemApi.getMessage(), Toast.LENGTH_SHORT).show();
                            restartScanner();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                        restartScanner();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    restartScanner();
                }

            }

            @Override
            public void onFailure(@NonNull Call<CheckMaterialItemApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                restartScanner();

            }
        });

    }

    private void restartScanner() {
        zXingScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        zXingScannerView.resumeCameraPreview(ScannerViewActivity.this);
                    }
                }, 1000);


//                zXingScannerView.setResultHandler(ScannerViewActivity.this);
//                zXingScannerView.startCamera();
            }
        });
    }


    //////////Add data into SQLite
    public void AddDataInSqlite() {

        boolean checkkk;
        checkkk = checkItemExistenceInSQLite(Material_Code);//check that item is already exist in Local Db or not

        if (checkkk == false) {

            //call Api when  item is not exist in local DB
            CheckMaterialItemApi(Material_Code);


        } else {

            startActivity(new Intent(ScannerViewActivity.this, SaveEarthActivity.class));
            finish();
       /*     Toast.makeText(context, "Touch to continue", Toast.LENGTH_SHORT).show();
            zXingScannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zXingScannerView.setResultHandler(ScannerViewActivity.this);
                    zXingScannerView.startCamera();
                }
            });*/
        }


    }

    private boolean checkItemExistenceInSQLite(String Material_Code) {


        ScannedItemSqliteDB cartSQLiteDB = new ScannedItemSqliteDB(context);
        Cursor cursor = cartSQLiteDB.getItemIdData(Material_Code);
        if (cursor.getCount() == 0) {   //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
          //  Toast.makeText(this, "Nothing Found", Toast.LENGTH_LONG).show();
            //Log.d(TAG, " checkItemExistenceInSQLite() 1st if body-->return false");
            return false;
        }
        int sql_Qty;
        float ft_Points;
        while (cursor.moveToNext()) {


           // Toast.makeText(context, Material_Code + " Item already exist!", Toast.LENGTH_SHORT).show();

            ft_Points = Float.parseFloat(cursor.getString(3));//get One_Material_LB_Points
            sql_Qty = 1 + Integer.parseInt(cursor.getString(4));//get Qty

            //update Qty And LB_points if item if not exist in local DB
            updateQtyAndLB_points(Material_Code, sql_Qty, ft_Points);

        }

        return true;

    }

    private void updateQtyAndLB_points(String material_code, int newQty, float ft_Points) {

        float Material_LB_Points = (ft_Points * newQty);

        ScannedItemSqliteDB sqLiteDBHelper = new ScannedItemSqliteDB(context);
        boolean isUpdate = sqLiteDBHelper.updateQtyAndLB_points(material_code, newQty + "", Material_LB_Points + "");

        if (isUpdate == true) {


          //  Toast.makeText(context, "Qty And LB_points updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(context, "Data not Found", Toast.LENGTH_SHORT).show();
        }

    }
}