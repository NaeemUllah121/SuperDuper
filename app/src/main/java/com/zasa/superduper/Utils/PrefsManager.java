package com.zasa.superduper.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    public PrefsManager() {
        preferences = MyApplication.context.getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public static String getCompanyCode() {
        return preferences.getString("CompanyCode", "");
    }

    public static void setCompanyCode(String value) {
        editor.putString("CompanyCode", value).commit();
    }

    public static String getCompanyName() {

        return preferences.getString("CompanyName", "");
    }

    public static void setCompanyName(String value) {
        editor.putString("CompanyName", value).commit();
    }

    public static String getCompanyLogo() {
        return preferences.getString("CompanyLogo", "");
    }

    public static void setCompanyLogo(String value) {
        editor.putString("CompanyLogo", value).commit();
    }

    public static void setCompanyAddress(String value) {
        editor.putString("CompanyAddress", value).commit();
    }

    public static String getCompanyAddress() {
        return preferences.getString("CompanyAddress", "");
    }


    public static void setCompanyCurrency(String value) {
        editor.putString("CompanyCurrency", value).commit();
    }

    public static String getCompanyCurrency() {
        return preferences.getString("CompanyCurrency", "");
    }



    public static String getCompanyCurrencySign() {
        return preferences.getString("CompanyCurrencySign", "");
    }

    public static void setCompanyCurrencySign(String value) {
        editor.putString("CompanyCurrencySign", value).commit();
    }

    public static String getCompanyPhone() {

        return preferences.getString("CompanyPhone", "");
    }

    public static void setCompanyPhone(String value) {

        editor.putString("CompanyPhone", value).commit();
    }

    public static String getCompanyFax() {

        return preferences.getString("CompanyFax", "");
    }

    public static void setCompanyFax(String value) {

        editor.putString("CompanyFax", value).commit();
    }

    public static String getCompanyUrl() {
        return preferences.getString("CompanyUrl", "");
    }

    public static void setCompanyUrl(String value) {
        editor.putString("CompanyUrl", value).commit();
    }


    public static String getMemberID() {
        return preferences.getString("MemberID", "");
    }

    public static void setMemberID(String value) {
        editor.putString("MemberID", value).commit();
    }
}
