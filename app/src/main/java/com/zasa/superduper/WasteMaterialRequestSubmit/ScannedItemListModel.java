package com.zasa.superduper.WasteMaterialRequestSubmit;

public class ScannedItemListModel {


    private String ID;
    private String Material_Code;
    private String Material_Name;
    private String One_Material_LB_Point;
    private String Material_Qty_Request;
    private String Material_LB_Points;
    private String Material_LB_Points_Total;
    private String Material_Type;
    private String Material_IsActive;
    private String Client_Code;
    private String Company_Code;

    public ScannedItemListModel(String ID, String material_Code, String material_Name, String one_Material_LB_Point, String material_Qty_Request, String material_LB_Points, String material_LB_Points_Total, String material_Type, String material_IsActive, String client_Code, String company_Code) {
        this.ID = ID;
        Material_Code = material_Code;
        Material_Name = material_Name;
        One_Material_LB_Point = one_Material_LB_Point;
        Material_Qty_Request = material_Qty_Request;
        Material_LB_Points = material_LB_Points;
        Material_LB_Points_Total = material_LB_Points_Total;
        Material_Type = material_Type;
        Material_IsActive = material_IsActive;
        Client_Code = client_Code;
        Company_Code = company_Code;
    }

    public String getID() {
        return ID;
    }

    public String getMaterial_Code() {
        return Material_Code;
    }

    public String getMaterial_Name() {
        return Material_Name;
    }

    public String getOne_Material_LB_Point() {
        return One_Material_LB_Point;
    }

    public String getMaterial_Qty_Request() {
        return Material_Qty_Request;
    }

    public String getMaterial_LB_Points() {
        return Material_LB_Points;
    }

    public String getMaterial_LB_Points_Total() {
        return Material_LB_Points_Total;
    }

    public String getMaterial_Type() {
        return Material_Type;
    }

    public String getMaterial_IsActive() {
        return Material_IsActive;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    /*    private String ID;
    private String Material_Code;
    private String Material_Name;
    //private float LB_Points;
    private float One_Material_LB_Point;
    private int Material_Qty_Request;
    private float Material_LB_Points;
    private float Material_LB_Points_Total;
    private int Material_Type;
    private int Material_IsActive;
    private String Client_Code;
    private String Company_Code;*/

}

