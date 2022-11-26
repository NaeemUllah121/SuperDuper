package com.zasa.superduper.WasteMaterialRequestHistory;

public class MOHListModel {
    private String Order_Id;
    private String Order_Date;
    private String Member_Unique;
    private String Member_LB_Card_Id = null;
    private String Customer_Full_Name;
    private String Customer_Mobile = null;
    private String Collection_Address;
    private String Collection_DateTime;
    private float LB_Points;
    private String Order_Status;
    private String Collected_By = null;

    public MOHListModel() {
    }

    public MOHListModel(String order_Id, String order_Date, String member_Unique, String member_LB_Card_Id, String customer_Full_Name, String customer_Mobile, String collection_Address, String collection_DateTime, float LB_Points, String order_Status, String collected_By) {
        Order_Id = order_Id;
        Order_Date = order_Date;
        Member_Unique = member_Unique;
        Member_LB_Card_Id = member_LB_Card_Id;
        Customer_Full_Name = customer_Full_Name;
        Customer_Mobile = customer_Mobile;
        Collection_Address = collection_Address;
        Collection_DateTime = collection_DateTime;
        this.LB_Points = LB_Points;
        Order_Status = order_Status;
        Collected_By = collected_By;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
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

    public String getMember_LB_Card_Id() {
        return Member_LB_Card_Id;
    }

    public void setMember_LB_Card_Id(String member_LB_Card_Id) {
        Member_LB_Card_Id = member_LB_Card_Id;
    }

    public String getCustomer_Full_Name() {
        return Customer_Full_Name;
    }

    public void setCustomer_Full_Name(String customer_Full_Name) {
        Customer_Full_Name = customer_Full_Name;
    }

    public String getCustomer_Mobile() {
        return Customer_Mobile;
    }

    public void setCustomer_Mobile(String customer_Mobile) {
        Customer_Mobile = customer_Mobile;
    }

    public String getCollection_Address() {
        return Collection_Address;
    }

    public void setCollection_Address(String collection_Address) {
        Collection_Address = collection_Address;
    }

    public String getCollection_DateTime() {
        return Collection_DateTime;
    }

    public void setCollection_DateTime(String collection_DateTime) {
        Collection_DateTime = collection_DateTime;
    }

    public float getLB_Points() {
        return LB_Points;
    }

    public void setLB_Points(float LB_Points) {
        this.LB_Points = LB_Points;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public void setOrder_Status(String order_Status) {
        Order_Status = order_Status;
    }

    public String getCollected_By() {
        return Collected_By;
    }

    public void setCollected_By(String collected_By) {
        Collected_By = collected_By;
    }
}
