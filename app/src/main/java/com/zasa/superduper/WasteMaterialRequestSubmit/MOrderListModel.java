package com.zasa.superduper.WasteMaterialRequestSubmit;

public class MOrderListModel {

    private String Member_Unique;
    private float LB_Points;
    private String Collection_Address;
    private String Collection_DateTime;
    private String Material_Code;
    private String Material_Name;
    private float Material_Qty_Request;
    private float Material_LB_Points;
    private float Material_LB_Points_Total;
    private String Customer_Full_Name;
    private String Customer_Mobile_Number;

    public MOrderListModel() {
    }

    public MOrderListModel(String member_Unique, float LB_Points, String collection_Address, String collection_DateTime, String material_Code, String material_Name, float material_Qty_Request, float material_LB_Points, float material_LB_Points_Total, String customer_Full_Name, String customer_Mobile_Number) {
        Member_Unique = member_Unique;
        this.LB_Points = LB_Points;
        Collection_Address = collection_Address;
        Collection_DateTime = collection_DateTime;
        Material_Code = material_Code;
        Material_Name = material_Name;
        Material_Qty_Request = material_Qty_Request;
        Material_LB_Points = material_LB_Points;
        Material_LB_Points_Total = material_LB_Points_Total;
        Customer_Full_Name = customer_Full_Name;
        Customer_Mobile_Number = customer_Mobile_Number;
    }

    public String getMember_Unique() {
        return Member_Unique;
    }

    public void setMember_Unique(String member_Unique) {
        Member_Unique = member_Unique;
    }

    public float getLB_Points() {
        return LB_Points;
    }

    public void setLB_Points(float LB_Points) {
        this.LB_Points = LB_Points;
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

    public String getMaterial_Code() {
        return Material_Code;
    }

    public void setMaterial_Code(String material_Code) {
        Material_Code = material_Code;
    }

    public String getMaterial_Name() {
        return Material_Name;
    }

    public void setMaterial_Name(String material_Name) {
        Material_Name = material_Name;
    }

    public float getMaterial_Qty_Request() {
        return Material_Qty_Request;
    }

    public void setMaterial_Qty_Request(float material_Qty_Request) {
        Material_Qty_Request = material_Qty_Request;
    }

    public float getMaterial_LB_Points() {
        return Material_LB_Points;
    }

    public void setMaterial_LB_Points(float material_LB_Points) {
        Material_LB_Points = material_LB_Points;
    }

    public float getMaterial_LB_Points_Total() {
        return Material_LB_Points_Total;
    }

    public void setMaterial_LB_Points_Total(float material_LB_Points_Total) {
        Material_LB_Points_Total = material_LB_Points_Total;
    }

    public String getCustomer_Full_Name() {
        return Customer_Full_Name;
    }

    public void setCustomer_Full_Name(String customer_Full_Name) {
        Customer_Full_Name = customer_Full_Name;
    }

    public String getCustomer_Mobile_Number() {
        return Customer_Mobile_Number;
    }

    public void setCustomer_Mobile_Number(String customer_Mobile_Number) {
        Customer_Mobile_Number = customer_Mobile_Number;
    }
}
