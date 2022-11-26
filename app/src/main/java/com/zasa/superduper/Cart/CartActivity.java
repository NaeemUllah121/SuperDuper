package com.zasa.superduper.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.SubmitGeneralOrder.CheckoutActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.SQLite.CartSQLiteDB;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;
    View view_itemInCart, view_noItemInCart;

    public static TextView tv_Total;
    public static float ft_CartTotal=0;

    RecyclerView cartListRecyclerView;
    CartListRCVAdapter cartListRCVAdapter;
    ArrayList<CartListModel> cartListModelArrayList;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        context = CartActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        view_itemInCart = findViewById(R.id.itemInCart);
        view_noItemInCart = findViewById(R.id.noItemInCart);



        cartListRecyclerView = findViewById(R.id.recycler_cart);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartListRecyclerView.setLayoutManager(linearLayoutManager);

        cartListModelArrayList = new ArrayList<>();

        ////get all products data which added in cart
        getAllItemDataFromSQLite();

        cartListRCVAdapter = new CartListRCVAdapter(cartListModelArrayList, this);
        cartListRecyclerView.setAdapter(cartListRCVAdapter);
        cartListRCVAdapter.notifyDataSetChanged();

        ////get All Item Price From SQLite and show in Rcv
        getAllItemPriceFromSQLite();

        //show layout according to cart is empty or not
        layoutVisibility();

    }

    private void getAllItemDataFromSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();
        if (cursor.getCount() == 0) {    //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            //Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()) {

            cartListModelArrayList.add(new CartListModel(
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
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12))
            );

        }
    }

    public void getAllItemPriceFromSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();
        if (cursor.getCount() == 0) {             //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            //Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        float sql_Price = 0, newSql_Price = 0;
        while (cursor.moveToNext()) {
            sql_Price = sql_Price + Float.parseFloat(cursor.getString(12));

            ////round OF number
            DecimalFormat twoDForm = new DecimalFormat("#.#");
            newSql_Price = Float.parseFloat(twoDForm.format(sql_Price));

        }
        //Toast.makeText(this, "sql_Price " + newSql_Price, Toast.LENGTH_SHORT).show();

        tv_Total = findViewById(R.id.tv_total);
        if (newSql_Price > 0) {

            tv_Total.setText("" + newSql_Price);
        } else {
            tv_Total.setText("0.0");
        }
    }

    public int getAllItemQTYFromSQLite() {
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
        } else {
        }
        return sql_ItemQty;
    }

    public void btn_Checkout(View view) {
        if (cartListModelArrayList.size() > 0) {
            startActivity(new Intent(context, CheckoutActivity.class));
        } else {

            view_noItemInCart.setVisibility(View.VISIBLE);
            view_itemInCart.setVisibility(View.GONE);
        }

    }

    public void layoutVisibility() {
        if (cartListModelArrayList.size() > 0) {
            view_itemInCart.setVisibility(View.VISIBLE);
            view_noItemInCart.setVisibility(View.GONE);
        } else {
            view_noItemInCart.setVisibility(View.VISIBLE);
            view_itemInCart.setVisibility(View.GONE);
        }
    }

    public void btn_ContinueShopping(View view) {
        startActivity(new Intent(context, HomeActivity.class));
       // this.finish();
        finishAffinity();
    }

    public void btn_back_cart(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllItemPriceFromSQLite();
        layoutVisibility();

    }


}