package com.zasa.superduper.Login;

import androidx.annotation.Keep;

@Keep
public class Zasa_MembersModel {

    private String Member_Unique;
    private String Member_LB_Card_Id = null;
    private String Member_LoginID;
    private String Member_LoginPass;
    private String Member_RDate;
    private String Member_FName;
    private String Member_LName;
    private String Member_Gender;
    private String Member_Mobile;
    private String Member_Email;
    private String Member_DOB = null;
    private String Member_Image_String = null;
    private int Member_Type;
    private String Member_Profession = null;
    private String Member_Designation = null;
    private String Email_Verify;
    private String Mobile_Verify;
    private String Mobile_PCode;
    private String Email_PCode;
    private String Member_TPin = null;
    private String LB_Points = null;
    private String Other_Points = null;
    private String Last_Lat = null;
    private String Last_Long = null;
    private String Last_Location = null;
    private String Country_Id;
    private String City_Id;
    private String Area_Id;
    private String Member_Address;
    private int Member_Status;
    private String Status_Reason;
    private String Last_Status_Change_Date;
    private String Added_Company_Id = null;
    private String Added_Branch_Id = null;
    private String Self_Register_Flag;
    private String Fule_Limit = null;
    private String Fule_Limit_Date = null;
    private String Support_Limit = null;
    private String Support_Limit_Date = null;
    private String Member_CNIC = null;
    private String Member_CNIC_Verified = null;
    private String CNIC_Verified_By = null;
    private String CNIC_Verified_Datetime = null;
    private String Family_Limit_Type = null;
    private float Family_Limit ;
    private float Family_Limit_Used ;

    private String Tech_Member ;
    private int Tech_Member_Type;
    private long Tech_Points;
    private String Tech_Tagline ;
    private int Payment_Type ;
    private String Payment_Slip_Number = null;
    private String Request_On = null;
    private String Payment_Image_String = null;
    private String Payment_Verify = null;
    private String Payment_Verify_On = null;
    private String LB_Area = null;
    private String LB_Country = null;

    public Zasa_MembersModel() {
    }

    public Zasa_MembersModel(String member_Unique, String member_LB_Card_Id, String member_LoginID, String member_LoginPass, String member_RDate, String member_FName, String member_LName, String member_Gender, String member_Mobile, String member_Email, String member_DOB, String member_Image_String, int member_Type, String member_Profession, String member_Designation, String email_Verify, String mobile_Verify, String mobile_PCode, String email_PCode, String member_TPin, String LB_Points, String other_Points, String last_Lat, String last_Long, String last_Location, String country_Id, String city_Id, String area_Id, String member_Address, int member_Status, String status_Reason, String last_Status_Change_Date, String added_Company_Id, String added_Branch_Id, String self_Register_Flag, String fule_Limit, String fule_Limit_Date, String support_Limit, String support_Limit_Date, String member_CNIC, String member_CNIC_Verified, String CNIC_Verified_By, String CNIC_Verified_Datetime, String family_Limit_Type, float family_Limit, float family_Limit_Used, String tech_Member, int tech_Member_Type, long tech_Points, String tech_Tagline, int payment_Type, String payment_Slip_Number, String request_On, String payment_Image_String, String payment_Verify, String payment_Verify_On, String LB_Area, String LB_Country) {
        Member_Unique = member_Unique;
        Member_LB_Card_Id = member_LB_Card_Id;
        Member_LoginID = member_LoginID;
        Member_LoginPass = member_LoginPass;
        Member_RDate = member_RDate;
        Member_FName = member_FName;
        Member_LName = member_LName;
        Member_Gender = member_Gender;
        Member_Mobile = member_Mobile;
        Member_Email = member_Email;
        Member_DOB = member_DOB;
        Member_Image_String = member_Image_String;
        Member_Type = member_Type;
        Member_Profession = member_Profession;
        Member_Designation = member_Designation;
        Email_Verify = email_Verify;
        Mobile_Verify = mobile_Verify;
        Mobile_PCode = mobile_PCode;
        Email_PCode = email_PCode;
        Member_TPin = member_TPin;
        this.LB_Points = LB_Points;
        Other_Points = other_Points;
        Last_Lat = last_Lat;
        Last_Long = last_Long;
        Last_Location = last_Location;
        Country_Id = country_Id;
        City_Id = city_Id;
        Area_Id = area_Id;
        Member_Address = member_Address;
        Member_Status = member_Status;
        Status_Reason = status_Reason;
        Last_Status_Change_Date = last_Status_Change_Date;
        Added_Company_Id = added_Company_Id;
        Added_Branch_Id = added_Branch_Id;
        Self_Register_Flag = self_Register_Flag;
        Fule_Limit = fule_Limit;
        Fule_Limit_Date = fule_Limit_Date;
        Support_Limit = support_Limit;
        Support_Limit_Date = support_Limit_Date;
        Member_CNIC = member_CNIC;
        Member_CNIC_Verified = member_CNIC_Verified;
        this.CNIC_Verified_By = CNIC_Verified_By;
        this.CNIC_Verified_Datetime = CNIC_Verified_Datetime;
        Family_Limit_Type = family_Limit_Type;
        Family_Limit = family_Limit;
        Family_Limit_Used = family_Limit_Used;
        Tech_Member = tech_Member;
        Tech_Member_Type = tech_Member_Type;
        Tech_Points = tech_Points;
        Tech_Tagline = tech_Tagline;
        Payment_Type = payment_Type;
        Payment_Slip_Number = payment_Slip_Number;
        Request_On = request_On;
        Payment_Image_String = payment_Image_String;
        Payment_Verify = payment_Verify;
        Payment_Verify_On = payment_Verify_On;
        this.LB_Area = LB_Area;
        this.LB_Country = LB_Country;
    }

    public String getMember_Unique() {
        return Member_Unique;
    }

    public String getMember_LB_Card_Id() {
        return Member_LB_Card_Id;
    }

    public String getMember_LoginID() {
        return Member_LoginID;
    }

    public String getMember_LoginPass() {
        return Member_LoginPass;
    }

    public String getMember_RDate() {
        return Member_RDate;
    }

    public String getMember_FName() {
        return Member_FName;
    }

    public String getMember_LName() {
        return Member_LName;
    }

    public String getMember_Gender() {
        return Member_Gender;
    }

    public String getMember_Mobile() {
        return Member_Mobile;
    }

    public String getMember_Email() {
        return Member_Email;
    }

    public String getMember_DOB() {
        return Member_DOB;
    }

    public String getMember_Image_String() {
        return Member_Image_String;
    }

    public int getMember_Type() {
        return Member_Type;
    }

    public String getMember_Profession() {
        return Member_Profession;
    }

    public String getMember_Designation() {
        return Member_Designation;
    }

    public String getEmail_Verify() {
        return Email_Verify;
    }

    public String getMobile_Verify() {
        return Mobile_Verify;
    }

    public String getMobile_PCode() {
        return Mobile_PCode;
    }

    public String getEmail_PCode() {
        return Email_PCode;
    }

    public String getMember_TPin() {
        return Member_TPin;
    }

    public String getLB_Points() {
        return LB_Points;
    }

    public String getOther_Points() {
        return Other_Points;
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

    public String getMember_Address() {
        return Member_Address;
    }

    public int getMember_Status() {
        return Member_Status;
    }

    public String getStatus_Reason() {
        return Status_Reason;
    }

    public String getLast_Status_Change_Date() {
        return Last_Status_Change_Date;
    }

    public String getAdded_Company_Id() {
        return Added_Company_Id;
    }

    public String getAdded_Branch_Id() {
        return Added_Branch_Id;
    }

    public String getSelf_Register_Flag() {
        return Self_Register_Flag;
    }

    public String getFule_Limit() {
        return Fule_Limit;
    }

    public String getFule_Limit_Date() {
        return Fule_Limit_Date;
    }

    public String getSupport_Limit() {
        return Support_Limit;
    }

    public String getSupport_Limit_Date() {
        return Support_Limit_Date;
    }

    public String getMember_CNIC() {
        return Member_CNIC;
    }

    public String getMember_CNIC_Verified() {
        return Member_CNIC_Verified;
    }

    public String getCNIC_Verified_By() {
        return CNIC_Verified_By;
    }

    public String getCNIC_Verified_Datetime() {
        return CNIC_Verified_Datetime;
    }

    public String getFamily_Limit_Type() {
        return Family_Limit_Type;
    }

    public float getFamily_Limit() {
        return Family_Limit;
    }

    public float getFamily_Limit_Used() {
        return Family_Limit_Used;
    }

    public String getTech_Member() {
        return Tech_Member;
    }

    public int getTech_Member_Type() {
        return Tech_Member_Type;
    }

    public long getTech_Points() {
        return Tech_Points;
    }

    public String getTech_Tagline() {
        return Tech_Tagline;
    }

    public int getPayment_Type() {
        return Payment_Type;
    }

    public String getPayment_Slip_Number() {
        return Payment_Slip_Number;
    }

    public String getRequest_On() {
        return Request_On;
    }

    public String getPayment_Image_String() {
        return Payment_Image_String;
    }

    public String getPayment_Verify() {
        return Payment_Verify;
    }

    public String getPayment_Verify_On() {
        return Payment_Verify_On;
    }

    public String getLB_Area() {
        return LB_Area;
    }

    public String getLB_Country() {
        return LB_Country;
    }
}
