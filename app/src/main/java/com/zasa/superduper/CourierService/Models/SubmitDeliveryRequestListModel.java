package com.zasa.superduper.CourierService.Models;

public class SubmitDeliveryRequestListModel {

    private String System_Date;
    private String Vendor_Unique;
    private String Vendor_Title;
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
    private String Customer_Remarks;
    private int No_Of_Parcels;
    private String Collected_By;
    private String Collected_On;
    private float Material_LB_Points;
    private float Material_LB_Points_Total;

    public SubmitDeliveryRequestListModel() {
    }

    public SubmitDeliveryRequestListModel(String system_Date, String vendor_Unique, String vendor_Title, String member_Unique, String member_LB_Card_Id, String customer_Full_Name, String customer_Mobile, String customer_CNIC, String collection_Address, String collection_DateTime, String receiver_Full_Name, String receiver_Mobile, String delivery_Address, int delivery_Type, int parcel_Type, String customer_Remarks, int no_Of_Parcels, String collected_By, String collected_On, float material_LB_Points, float material_LB_Points_Total) {
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
        Customer_Remarks = customer_Remarks;
        No_Of_Parcels = no_Of_Parcels;
        Collected_By = collected_By;
        Collected_On = collected_On;
        Material_LB_Points = material_LB_Points;
        Material_LB_Points_Total = material_LB_Points_Total;
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

    public String getCustomer_Remarks() {
        return Customer_Remarks;
    }

    public int getNo_Of_Parcels() {
        return No_Of_Parcels;
    }

    public String getCollected_By() {
        return Collected_By;
    }

    public String getCollected_On() {
        return Collected_On;
    }

    public float getMaterial_LB_Points() {
        return Material_LB_Points;
    }

    public float getMaterial_LB_Points_Total() {
        return Material_LB_Points_Total;
    }
}
