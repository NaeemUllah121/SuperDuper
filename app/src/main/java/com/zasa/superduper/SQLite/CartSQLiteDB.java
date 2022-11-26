package com.zasa.superduper.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartSQLiteDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Cart.DB";
    public static final String CART_TABLE = "CART";
    public static final int VERSION = 1;


    public CartSQLiteDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String qry = " CREATE TABLE " + CART_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,P_NAME TEXT NOT NULL, P_UNIT TEXT , P_PRICE TEXT NOT NULL, P_Qty TEXT NOT NULL, P_Description TEXT NOT NULL, P_IMG TEXT, P_CODE TEXT NOT NULL, COMPANY_CODE TEXT NOT NULL, Branch_Code TEXT, CLIENT_CODE TEXT NOT NULL, P_OLD_PRICE Text,P_AMOUNT Text,P_NET_AMOUNT Text) ";
        sqLiteDatabase.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String qry = "DROP TABLE IF EXISTS " + CART_TABLE;
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }

    //Create
    public boolean insertValue(String p_name, String p_unit, String p_price, String p_Qty, String p_description, String p_img, String p_code, String CompanyCode,String Branch_Code , String ClientCode, String p_old_price, String p_amount, String p_net_amount) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();    //use database ojb to write value in DB table
        ContentValues contentValues = new ContentValues();
        contentValues.put("P_NAME", p_name);
        contentValues.put("P_UNIT", p_unit);
        contentValues.put("P_PRICE", p_price);
        contentValues.put("P_Qty", p_Qty);
        contentValues.put("P_Description", p_description);
        contentValues.put("P_IMG", p_img);
        contentValues.put("P_CODE", p_code);
        contentValues.put("COMPANY_CODE", CompanyCode);
        contentValues.put("Branch_Code", Branch_Code);
        contentValues.put("CLIENT_CODE", ClientCode);
        contentValues.put("P_OLD_PRICE", p_old_price);
        contentValues.put("P_AMOUNT", p_amount);
        contentValues.put("P_NET_AMOUNT", p_net_amount);
        long check = sqLiteDatabase.insert(CART_TABLE, null, contentValues);// insert method returns 1(Long DataType) if data is inserted else -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Read or view data
    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String qry = " SELECT * FROM " + CART_TABLE + " ORDER BY ID DESC ";   //show data in descending order
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + CUSTOMER_TABLE, null);
        Cursor cursor = sqLiteDatabase.rawQuery(qry, null);
        return cursor;
    }


    public Cursor getItemIdData(String itemCode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + CART_TABLE + " WHERE P_CODE=? ", new String[]{itemCode},null);
        return cursor;
    }


    public Cursor getBranchCode(String BranchCode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + CART_TABLE + " WHERE Branch_Code=? ", new String[]{BranchCode},null);
        return cursor;
    }


    //Update
    public boolean updateData(String id, String p_unit, String p_name, String p_price, String p_Qty, String p_description, String p_img, String p_old_price) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("P_NAME", p_name);
        contentValues.put("P_UNIT", p_unit);
        contentValues.put("P_PRICE", p_price);
        contentValues.put("P_Qty", p_Qty);
        contentValues.put("P_Description", p_description);
        contentValues.put("P_IMG", p_img);
        contentValues.put("P_OLD_PRICE", p_old_price);
        sqLiteDatabase.update(CART_TABLE, contentValues, "ID=?", new String[]{id});
        return true;
    }


    //Update product qty
    public boolean updateQty(String itemCode, String p_Qty) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("P_CODE", itemCode);
        contentValues.put("P_Qty", p_Qty);
        sqLiteDatabase.update(CART_TABLE, contentValues, "P_CODE=?", new String[]{itemCode});
        return true;
    }


    // update ProductNetAmount And ProductAmount
    public boolean updateProductNetAmount(String itemCode, String ProductAmount, String ProductNetAmount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("P_CODE", itemCode);
        contentValues.put("P_AMOUNT", ProductAmount);
        contentValues.put("P_NET_AMOUNT", ProductNetAmount);
        sqLiteDatabase.update(CART_TABLE, contentValues, "P_CODE=?", new String[]{itemCode});
        return true;
    }


    //Delete
//    public Integer deleteData(String id) {
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  "+CUSTOMER_TABLE, "WHERE ID=?" , new String[]{id});
//
//
//        return sqLiteDatabase.delete(CUSTOMER_TABLE, "ID=?", new String[]{id});// delete method returns 1(Integer DataType) if data is inserted else -1
//
//    }


    //Delete
    public boolean deleteData(String itemCode) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + CART_TABLE + " WHERE P_CODE=? ", new String[]{itemCode});
        if (cursor.getCount() > 0) {
            int check = sqLiteDatabase.delete(CART_TABLE, "P_CODE=?", new String[]{itemCode});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    //Delete
    public void deleteAllData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + CART_TABLE);
        //sqLiteDatabase.execSQL("VACUUM");

    /*    String sql = "DELETE FROM MAIN_TABLE";
        stmt.executeUpdate(sql);
        sql = "VACUUM";
        stmt.executeUpdate(sql);*/

    }

}
