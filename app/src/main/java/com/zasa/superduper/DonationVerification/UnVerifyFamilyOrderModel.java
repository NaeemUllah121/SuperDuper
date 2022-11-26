package com.zasa.superduper.DonationVerification;

public class UnVerifyFamilyOrderModel {

    private String Order_Id;
    private String Vendor_Unique;
    private String Order_Date;
    private String Member_Unique;
    private float Order_Amount;
    private String Vendor_Payment_Type = null;
    private int Vendor_Payment_Status;
    private String Vendor_Payment_Datetime = null;
    private String Vendor_Payment_SlipNo = null;
    private String Admin_LoginID = null;
    private String Vendor_Payment_Remarks;
    private String Order_OTP;
    private String Order_OTP_Verify;
    private String Order_Status;
    private String Vendor_Login_Id;
    private String Created_Date_Time;
    private String Assigned_To = null;
    private String Assigned_On = null;
    private String Member_Verification;
    private String Member_Verification_On = null;


    public UnVerifyFamilyOrderModel() {
    }

    public UnVerifyFamilyOrderModel(String order_Id, String vendor_Unique, String order_Date, String member_Unique, float order_Amount, String vendor_Payment_Type, int vendor_Payment_Status, String vendor_Payment_Datetime, String vendor_Payment_SlipNo, String admin_LoginID, String vendor_Payment_Remarks, String order_OTP, String order_OTP_Verify, String order_Status, String vendor_Login_Id, String created_Date_Time, String assigned_To, String assigned_On, String member_Verification, String member_Verification_On) {
        Order_Id = order_Id;
        Vendor_Unique = vendor_Unique;
        Order_Date = order_Date;
        Member_Unique = member_Unique;
        Order_Amount = order_Amount;
        Vendor_Payment_Type = vendor_Payment_Type;
        Vendor_Payment_Status = vendor_Payment_Status;
        Vendor_Payment_Datetime = vendor_Payment_Datetime;
        Vendor_Payment_SlipNo = vendor_Payment_SlipNo;
        Admin_LoginID = admin_LoginID;
        Vendor_Payment_Remarks = vendor_Payment_Remarks;
        Order_OTP = order_OTP;
        Order_OTP_Verify = order_OTP_Verify;
        Order_Status = order_Status;
        Vendor_Login_Id = vendor_Login_Id;
        Created_Date_Time = created_Date_Time;
        Assigned_To = assigned_To;
        Assigned_On = assigned_On;
        Member_Verification = member_Verification;
        Member_Verification_On = member_Verification_On;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
    }

    public String getVendor_Unique() {
        return Vendor_Unique;
    }

    public void setVendor_Unique(String vendor_Unique) {
        Vendor_Unique = vendor_Unique;
    }

    public String getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(String order_Date) {
        Order_Date = order_Date;
    }

    public String getMember_Unique() {
        return Member_Unique;
    }

    public void setMember_Unique(String member_Unique) {
        Member_Unique = member_Unique;
    }

    public float getOrder_Amount() {
        return Order_Amount;
    }

    public void setOrder_Amount(float order_Amount) {
        Order_Amount = order_Amount;
    }

    public String getVendor_Payment_Type() {
        return Vendor_Payment_Type;
    }

    public void setVendor_Payment_Type(String vendor_Payment_Type) {
        Vendor_Payment_Type = vendor_Payment_Type;
    }

    public int getVendor_Payment_Status() {
        return Vendor_Payment_Status;
    }

    public void setVendor_Payment_Status(int vendor_Payment_Status) {
        Vendor_Payment_Status = vendor_Payment_Status;
    }

    public String getVendor_Payment_Datetime() {
        return Vendor_Payment_Datetime;
    }

    public void setVendor_Payment_Datetime(String vendor_Payment_Datetime) {
        Vendor_Payment_Datetime = vendor_Payment_Datetime;
    }

    public String getVendor_Payment_SlipNo() {
        return Vendor_Payment_SlipNo;
    }

    public void setVendor_Payment_SlipNo(String vendor_Payment_SlipNo) {
        Vendor_Payment_SlipNo = vendor_Payment_SlipNo;
    }

    public String getAdmin_LoginID() {
        return Admin_LoginID;
    }

    public void setAdmin_LoginID(String admin_LoginID) {
        Admin_LoginID = admin_LoginID;
    }

    public String getVendor_Payment_Remarks() {
        return Vendor_Payment_Remarks;
    }

    public void setVendor_Payment_Remarks(String vendor_Payment_Remarks) {
        Vendor_Payment_Remarks = vendor_Payment_Remarks;
    }

    public String getOrder_OTP() {
        return Order_OTP;
    }

    public void setOrder_OTP(String order_OTP) {
        Order_OTP = order_OTP;
    }

    public String getOrder_OTP_Verify() {
        return Order_OTP_Verify;
    }

    public void setOrder_OTP_Verify(String order_OTP_Verify) {
        Order_OTP_Verify = order_OTP_Verify;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public void setOrder_Status(String order_Status) {
        Order_Status = order_Status;
    }

    public String getVendor_Login_Id() {
        return Vendor_Login_Id;
    }

    public void setVendor_Login_Id(String vendor_Login_Id) {
        Vendor_Login_Id = vendor_Login_Id;
    }

    public String getCreated_Date_Time() {
        return Created_Date_Time;
    }

    public void setCreated_Date_Time(String created_Date_Time) {
        Created_Date_Time = created_Date_Time;
    }

    public String getAssigned_To() {
        return Assigned_To;
    }

    public void setAssigned_To(String assigned_To) {
        Assigned_To = assigned_To;
    }

    public String getAssigned_On() {
        return Assigned_On;
    }

    public void setAssigned_On(String assigned_On) {
        Assigned_On = assigned_On;
    }

    public String getMember_Verification() {
        return Member_Verification;
    }

    public void setMember_Verification(String member_Verification) {
        Member_Verification = member_Verification;
    }

    public String getMember_Verification_On() {
        return Member_Verification_On;
    }

    public void setMember_Verification_On(String member_Verification_On) {
        Member_Verification_On = member_Verification_On;
    }
}
