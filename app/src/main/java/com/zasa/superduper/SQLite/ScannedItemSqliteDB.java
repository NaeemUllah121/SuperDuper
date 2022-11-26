package com.zasa.superduper.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ScannedItemSqliteDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ScannedItem.DB";
    public static final String ITEM_TABLE = "ScannedItem";
    public static final int VERSION = 1;


    public ScannedItemSqliteDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String qry = " CREATE TABLE " + ITEM_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,Material_Code TEXT NOT NULL , Material_Name TEXT NOT NULL, One_Material_LB_Points TEXT,Material_Qty_Request TEXT , Material_LB_Points TEXT , Material_LB_Points_Total TEXT, Material_Type TEXT, Material_IsActive TEXT, CLIENT_CODE TEXT NOT NULL, Company_Code Text NOT NULL ) ";
        sqLiteDatabase.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String qry = "DROP TABLE IF EXISTS " + ITEM_TABLE;
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }

    //Create
    public boolean insertValue(String M_Code, String M_Name,String  One_M_LB_Points, String M_Qty_Request, String M_LB_Points, String M_LB_Points_Total,  String M_Type, String M_IsActive, String CompanyCode, String ClientCode) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();    //use database ojb to write value in DB table
        ContentValues contentValues = new ContentValues();
        contentValues.put("Material_Code", M_Code);
        contentValues.put("Material_Name", M_Name);
        contentValues.put("One_Material_LB_Points", One_M_LB_Points);
        contentValues.put("Material_Qty_Request", M_Qty_Request);
        contentValues.put("Material_LB_Points", M_LB_Points);
        contentValues.put("Material_LB_Points_Total", M_LB_Points_Total);
        contentValues.put("Material_Type", M_Type);
        contentValues.put("Material_IsActive", M_IsActive);
        contentValues.put("COMPANY_CODE", CompanyCode);
        contentValues.put("CLIENT_CODE", ClientCode);

        long check = sqLiteDatabase.insert(ITEM_TABLE, null, contentValues);// insert method returns 1(Long DataType) if data is inserted else -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Read or view data
    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String qry = " SELECT * FROM " + ITEM_TABLE + " ORDER BY ID DESC ";   //show data in descending order
        // Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + CUSTOMER_TABLE, null);
        Cursor cursor = sqLiteDatabase.rawQuery(qry, null);
        return cursor;
    }

    public Cursor getItemIdData(String itemCode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + ITEM_TABLE + " WHERE Material_Code=? ", new String[]{itemCode},null);
        return cursor;
    }


    //Update
/*    public boolean updateData(String id, String p_unit, String p_name, String p_price, String p_Qty, String p_description, String p_img, String p_old_price) {
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
        sqLiteDatabase.update(ITEM_TABLE, contentValues, "ID=?", new String[]{id});
        return true;
    }*/


    //Update product qty
    public boolean updateQtyAndLB_points(String itemCode, String M_Qty, String M_LB_Points) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Material_LB_Points", M_LB_Points);
        contentValues.put("Material_Qty_Request", M_Qty);
        sqLiteDatabase.update(ITEM_TABLE, contentValues, "Material_Code=?", new String[]{itemCode});
        return true;
    }



    //Delete
    public boolean deleteItemData(String itemCode) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + ITEM_TABLE + " WHERE Material_Code=? ", new String[]{itemCode});
        if (cursor.getCount() > 0) {
            int check = sqLiteDatabase.delete(ITEM_TABLE, "Material_Code=?", new String[]{itemCode});
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
        sqLiteDatabase.execSQL("DELETE FROM " + ITEM_TABLE);
        //sqLiteDatabase.execSQL("VACUUM");

    /*    String sql = "DELETE FROM MAIN_TABLE";
        stmt.executeUpdate(sql);
        sql = "VACUUM";
        stmt.executeUpdate(sql);*/

    }

}
