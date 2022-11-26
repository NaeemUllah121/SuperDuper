package com.zasa.superduper.CourierService.Models;

public class CourierListModel {

    private String Vendor_Unique;
    private String Vendor_LoginID;
    private String Vendor_LoginPass;
    private String Vendor_RDate = null;
    private String Vendor_Title;
    private String Vendor_Full_Name;
    private String Vendor_Mobile = null;
    private String Vendor_CNIC = null;
    private String Vendor_DOB = null;
    private String Mobile_Verify;
    private String Mobile_PCode;
    private String Vendor_TPin = null;
    private String Last_Lat = null;
    private String Last_Long = null;
    private String Last_Location = null;
    private String Country_Id = null;
    private String City_Id = null;
    private String Area_Id = null;
    private String Client_Code = null;
    private String Company_Code = null;
    private String Branch_Code = null;
    private int Vendor_Status;
    private String Status_Reason;
    private String Last_Status_Change_Date = null;
    private String Vendor_JazzCash = null;
    private String Vendor_BankAccount = null;
    private String Vendor_BankName = null;
    private String Vendor_Dakiya;

    public CourierListModel() {
    }

    public CourierListModel(String vendor_Unique, String vendor_LoginID, String vendor_LoginPass, String vendor_RDate, String vendor_Title, String vendor_Full_Name, String vendor_Mobile, String vendor_CNIC, String vendor_DOB, String mobile_Verify, String mobile_PCode, String vendor_TPin, String last_Lat, String last_Long, String last_Location, String country_Id, String city_Id, String area_Id, String client_Code, String company_Code, String branch_Code, int vendor_Status, String status_Reason, String last_Status_Change_Date, String vendor_JazzCash, String vendor_BankAccount, String vendor_BankName, String vendor_Dakiya) {
        Vendor_Unique = vendor_Unique;
        Vendor_LoginID = vendor_LoginID;
        Vendor_LoginPass = vendor_LoginPass;
        Vendor_RDate = vendor_RDate;
        Vendor_Title = vendor_Title;
        Vendor_Full_Name = vendor_Full_Name;
        Vendor_Mobile = vendor_Mobile;
        Vendor_CNIC = vendor_CNIC;
        Vendor_DOB = vendor_DOB;
        Mobile_Verify = mobile_Verify;
        Mobile_PCode = mobile_PCode;
        Vendor_TPin = vendor_TPin;
        Last_Lat = last_Lat;
        Last_Long = last_Long;
        Last_Location = last_Location;
        Country_Id = country_Id;
        City_Id = city_Id;
        Area_Id = area_Id;
        Client_Code = client_Code;
        Company_Code = company_Code;
        Branch_Code = branch_Code;
        Vendor_Status = vendor_Status;
        Status_Reason = status_Reason;
        Last_Status_Change_Date = last_Status_Change_Date;
        Vendor_JazzCash = vendor_JazzCash;
        Vendor_BankAccount = vendor_BankAccount;
        Vendor_BankName = vendor_BankName;
        Vendor_Dakiya = vendor_Dakiya;
    }

    public String getVendor_Unique() {
        return Vendor_Unique;
    }

    public String getVendor_LoginID() {
        return Vendor_LoginID;
    }

    public String getVendor_LoginPass() {
        return Vendor_LoginPass;
    }

    public String getVendor_RDate() {
        return Vendor_RDate;
    }

    public String getVendor_Title() {
        return Vendor_Title;
    }

    public String getVendor_Full_Name() {
        return Vendor_Full_Name;
    }

    public String getVendor_Mobile() {
        return Vendor_Mobile;
    }

    public String getVendor_CNIC() {
        return Vendor_CNIC;
    }

    public String getVendor_DOB() {
        return Vendor_DOB;
    }

    public String getMobile_Verify() {
        return Mobile_Verify;
    }

    public String getMobile_PCode() {
        return Mobile_PCode;
    }

    public String getVendor_TPin() {
        return Vendor_TPin;
    }

    public String getLast_Lat() {
        return Last_Lat;
    }

    public String getLast_Long() {
        return Last_Long;
    }

    public String getLast_Location() {
        return Last_Location;
    }

    public String getCountry_Id() {
        return Country_Id;
    }

    public String getCity_Id() {
        return City_Id;
    }

    public String getArea_Id() {
        return Area_Id;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public String getBranch_Code() {
        return Branch_Code;
    }

    public int getVendor_Status() {
        return Vendor_Status;
    }

    public String getStatus_Reason() {
        return Status_Reason;
    }

    public String getLast_Status_Change_Date() {
        return Last_Status_Change_Date;
    }

    public String getVendor_JazzCash() {
        return Vendor_JazzCash;
    }

    public String getVendor_BankAccount() {
        return Vendor_BankAccount;
    }

    public String getVendor_BankName() {
        return Vendor_BankName;
    }

    public String getVendor_Dakiya() {
        return Vendor_Dakiya;
    }
}
