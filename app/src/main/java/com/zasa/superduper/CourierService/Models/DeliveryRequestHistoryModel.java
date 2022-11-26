package com.zasa.superduper.CourierService.Models;

public class DeliveryRequestHistoryModel {

    private String System_Id;
    private String System_Date;
    private String Vendor_Unique;
    private String Vendor_Title = null;
    private String Member_Unique;
    private String Member_LB_Card_Id;
    private String Customer_Full_Name;
    private String Customer_Mobile;
    private String Customer_CNIC;
    private String Collection_Address;
    private String Collection_DateTime;
    private String Receiver_Full_Name;
    private String Receiver_Mobile;
    private String Delivery_Address;
    private int Delivery_Type;
    private int Parcel_Type;
    private int Delivery_Status;
    private String Delivered_By = null;
    private String Delivered_On = null;
    private String Delivered_Chargs = null;
    private String Return_Reason = null;
    private String Collected_By;
    private String Collected_On;
    private String LB_Points = null;
    private String Customer_Remarks;
    private int No_Of_Parcels;

    public DeliveryRequestHistoryModel() {
    }

    public DeliveryRequestHistoryModel(String system_Id, String system_Date, String vendor_Unique, String vendor_Title, String member_Unique, String member_LB_Card_Id, String customer_Full_Name, String customer_Mobile, String customer_CNIC, String collection_Address, String collection_DateTime, String receiver_Full_Name, String receiver_Mobile, String delivery_Address, int delivery_Type, int parcel_Type, int delivery_Status, String delivered_By, String delivered_On, String delivered_Chargs, String return_Reason, String collected_By, String collected_On, String LB_Points, String customer_Remarks, int no_Of_Parcels) {
        System_Id = system_Id;
        System_Date = system_Date;
        Vendor_Unique = vendor_Unique;
        Vendor_Title = vendor_Title;
        Member_Unique = member_Unique;
        Member_LB_Card_Id = member_LB_Card_Id;
        Customer_Full_Name = customer_Full_Name;
        Customer_Mobile = customer_Mobile;
        Customer_CNIC = customer_CNIC;
        Collection_Address = collection_Address;
        Collection_DateTime = collection_DateTime;
        Receiver_Full_Name = receiver_Full_Name;
        Receiver_Mobile = receiver_Mobile;
        Delivery_Address = delivery_Address;
        Delivery_Type = delivery_Type;
        Parcel_Type = parcel_Type;
        Delivery_Status = delivery_Status;
        Delivered_By = delivered_By;
        Delivered_On = delivered_On;
        Delivered_Chargs = delivered_Chargs;
        Return_Reason = return_Reason;
        Collected_By = collected_By;
        Collected_On = collected_On;
        this.LB_Points = LB_Points;
        Customer_Remarks = customer_Remarks;
        No_Of_Parcels = no_Of_Parcels;
    }

    public String getSystem_Id() {
        return System_Id;
    }

    public String getSystem_Date() {
        return System_Date;
    }

    public String getVendor_Unique() {
        return Vendor_Unique;
    }

    public String getVendor_Title() {
        return Vendor_Title;
    }

    public String getMember_Unique() {
        return Member_Unique;
    }

    public String getMember_LB_Card_Id() {
        return Member_LB_Card_Id;
    }

    public String getCustomer_Full_Name() {
        return Customer_Full_Name;
    }

    public String getCustomer_Mobile() {
        return Customer_Mobile;
    }

    public String getCustomer_CNIC() {
        return Customer_CNIC;
    }

    public String getCollection_Address() {
        return Collection_Address;
    }

    public String getCollection_DateTime() {
        return Collection_DateTime;
    }

    public String getReceiver_Full_Name() {
        return Receiver_Full_Name;
    }

    public String getReceiver_Mobile() {
        return Receiver_Mobile;
    }

    public String getDelivery_Address() {
        return Delivery_Address;
    }

    public int getDelivery_Type() {
        return Delivery_Type;
    }

    public int getParcel_Type() {
        return Parcel_Type;
    }

    public int getDelivery_Status() {
        return Delivery_Status;
    }

    public String getDelivered_By() {
        return Delivered_By;
    }

    public String getDelivered_On() {
        return Delivered_On;
    }

    public String getDelivered_Chargs() {
        return Delivered_Chargs;
    }

    public String getReturn_Reason() {
        return Return_Reason;
    }

    public String getCollected_By() {
        return Collected_By;
    }

    public String getCollected_On() {
        return Collected_On;
    }

    public String getLB_Points() {
        return LB_Points;
    }

    public String getCustomer_Remarks() {
        return Customer_Remarks;
    }

    public int getNo_Of_Parcels() {
        return No_Of_Parcels;
    }
}
