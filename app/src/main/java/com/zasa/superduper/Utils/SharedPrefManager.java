package com.zasa.superduper.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zasa.superduper.Login.Zasa_MembersModel;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.TechClub.Model.TechMemTypeListModel;
import com.zasa.superduper.TechClub.Model.TechMembersListModel;
import com.zasa.superduper.TechClub.Model.TechTeamListModel;
import com.zasa.superduper.TechClub.Model.TechTopMembersListModel;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "sharedPref";
    private SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }


    public void saveLoginUser(Zasa_MembersModel user) {

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Member_Unique", user.getMember_Unique());
        editor.putString("Member_LB_Card_Id", user.getMember_LB_Card_Id());
        editor.putString("Member_LoginID", user.getMember_LoginID());
        editor.putString("Member_LoginPass", user.getMember_LoginPass());
        editor.putString("Member_RDate", user.getMember_RDate());
        editor.putString("Member_FName", user.getMember_FName());
        editor.putString("Member_LName", user.getMember_LName());
        editor.putString("Member_Gender", user.getMember_Gender());
        editor.putString("Member_Mobile", user.getMember_Mobile());
        editor.putString("Member_Email", user.getMember_Email());
        editor.putString("Member_DOB", user.getMember_DOB());
        editor.putInt("Member_Type", user.getMember_Type());
        editor.putString("Email_Verify", user.getEmail_Verify());
        editor.putString("Mobile_Verify", user.getMobile_Verify());
        editor.putString("Mobile_PCode", user.getMobile_PCode());
        editor.putString("Email_PCode", user.getEmail_PCode());
        editor.putString("Member_TPin", user.getMember_TPin());
        editor.putString("LB_Points", user.getLB_Points());
        editor.putString("Other_Points", user.getOther_Points());
        editor.putString("Last_Lat", user.getLast_Lat());
        editor.putString("Last_Long", user.getLast_Long());
        editor.putString("Last_Location", user.getLast_Location());
        editor.putString("Country_Id", user.getCountry_Id());
        editor.putString("City_Id", user.getCity_Id());
        editor.putString("Area_Id", user.getArea_Id());
        editor.putString("Member_Address", user.getMember_Address());
        editor.putInt("Member_Status", user.getMember_Status());
        editor.putString("Status_Reason", user.getStatus_Reason());
        editor.putString("Last_Status_Change_Date", user.getLast_Status_Change_Date());
        editor.putString("Added_Company_Id", user.getAdded_Company_Id());
        editor.putString("Added_Branch_Id", user.getAdded_Branch_Id());
        editor.putString("Self_Register_Flag", user.getSelf_Register_Flag());
        editor.putString("Fule_Limit", user.getFule_Limit());
        editor.putString("Fule_Limit_Date", user.getFule_Limit_Date());
        editor.putString("Support_Limit", user.getSupport_Limit());
        editor.putString("Support_Limit_Date", user.getSupport_Limit_Date());
        editor.putString("Member_CNIC", user.getMember_CNIC());
        editor.putString("Member_CNIC_Verified", user.getMember_CNIC_Verified());
        editor.putString("CNIC_Verified_By", user.getCNIC_Verified_By());
        editor.putString("CNIC_Verified_Datetime", user.getCNIC_Verified_Datetime());
        editor.putString("Family_Limit_Type", user.getFamily_Limit_Type());
        editor.putFloat("Family_Limit", user.getFamily_Limit());
        editor.putFloat("Family_Limit_Used", user.getFamily_Limit_Used());

        editor.putInt("Payment_Type", user.getPayment_Type());
        editor.putString("Payment_Slip_Number", user.getPayment_Slip_Number());
        editor.putString("Request_On", user.getRequest_On());
        editor.putString("Payment_Image_String", user.getPayment_Image_String());
        editor.putString("Payment_Verify", user.getPayment_Verify());
        editor.putString("Payment_Verify_On", user.getPayment_Verify_On());

        editor.putString("LB_Area", user.getLB_Area());
        editor.putString("LB_Country", user.getLB_Country());

        editor.putBoolean("logged", true);
        editor.apply();

    }

    /////////save list////////////
    public void saveTechMemberTypeList(ArrayList<TechMemTypeListModel> arrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("list", json);
        editor.apply();
    }

    /////////load list////////////
    public ArrayList<TechMemTypeListModel> loadTechMemTypeListModel() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<TechMemTypeListModel>>() {
        }.getType();
        ArrayList<TechMemTypeListModel> arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            return arrayList = new ArrayList<>();
        }
        return arrayList;
    }


    /////////save list//////////////tech team
    public void saveTechTeamList(ArrayList<TechTeamListModel> arrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("TechTeam", json);
        editor.apply();
    }

    /////////load list////////////
    public ArrayList<TechTeamListModel> loadTechTeamListModel() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("TechTeam", null);
        Type type = new TypeToken<ArrayList<TechTeamListModel>>() {
        }.getType();
        ArrayList<TechTeamListModel> arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            return arrayList = new ArrayList<>();
        }
        return arrayList;
    }


    //tech top member
    public void saveTechTopMemberList(ArrayList<TechTopMembersListModel> arrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("TechTopMember", json);
        editor.apply();
    }

    public ArrayList<TechTopMembersListModel> loadTechTopMembersListModel() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("TechTopMember", null);
        Type type = new TypeToken<ArrayList<TechTopMembersListModel>>() {
        }.getType();
        ArrayList<TechTopMembersListModel> arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            return arrayList = new ArrayList<>();
        }
        return arrayList;
    }


    //tech member
    public void saveTechMemberList(ArrayList<TechMembersListModel> arrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("TechMember", json);
        editor.apply();
    }

    public ArrayList<TechMembersListModel> loadTechMembersListModel() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("TechMember", null);
        Type type = new TypeToken<ArrayList<TechMembersListModel>>() {
        }.getType();
        ArrayList<TechMembersListModel> arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            return arrayList = new ArrayList<>();
        }
        return arrayList;
    }


    ///////Save AppversionListModel////////////
    public void saveAppVersionModel(AppversionListModel appversionListModel) {

        Gson gson = new Gson();
        String json = gson.toJson(appversionListModel);

        SharedPreferences sharedPreferences = context.getSharedPreferences("AppVersionInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AppVersionInfo", json);
        editor.apply();

    }

    ///////Retrieve AppversionListModel////////////
    public AppversionListModel getAppversionListModel() {

        Gson gsonRetrieve = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppVersionInfo", Context.MODE_PRIVATE);
        String jsonRetrieve = sharedPreferences.getString("AppVersionInfo", null);

        AppversionListModel appversionListModel;
        if (jsonRetrieve == null) {
            return null;
        } else
            appversionListModel = gsonRetrieve.fromJson(jsonRetrieve, AppversionListModel.class);
        return appversionListModel;

    }


    ///////Save MemberDetailsApiModel////////////
    public void saveMemberDetails(MemberDetailsApiModel member) {

        Gson gson = new Gson();
        String json = gson.toJson(member);

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("member", json);
        editor.apply();

    }

    ///////Retrieve MemberDetailsApiModel////////////
    public MemberDetailsApiModel getMemberDetails() {

        Gson gsonRetrieve = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String jsonRetrieve = sharedPreferences.getString("member", null);

        MemberDetailsApiModel memberDetails;
        if (jsonRetrieve == null) {
            return null;
        } else
            memberDetails = gsonRetrieve.fromJson(jsonRetrieve, MemberDetailsApiModel.class);
        return memberDetails;

    }

    public void rememberMe(String st_Phone, String st_password) {
        sharedPreferences = context.getSharedPreferences("RememberMe", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("uPhone", st_Phone);
        editor.putString("uPass", st_password);
        editor.apply();
    }


    /////////generate random string for verification////////
    public void saveRandomString(String saveRandomString) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("saveRandomString", saveRandomString);
        editor.apply();
    }

    public String getRandomString() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("saveRandomString", "");
    }
    /////////generate random string for verification////////


    /////////save BranchCode////////
    public void saveBranchCode(String saveBranchCode) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("saveBranchCode", saveBranchCode);
        editor.apply();
    }

    public String getBranchCode() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("saveBranchCode", "");
    }
    /////////save BranchCode////////

    public boolean isLoggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);
    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
