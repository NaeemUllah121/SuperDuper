package com.zasa.superduper.WasteMaterialScanner;

public class SEMItemModel {

    private String Material_Code;
    private int Material_Type;
    private String Material_Name;
    private int Material_IsActive;
    private float LB_Points;
    private String Client_Code;
    private String Company_Code;

    public SEMItemModel() {
    }

    public SEMItemModel(String material_Code, int material_Type, String material_Name, int material_IsActive, float LB_Points, String client_Code, String company_Code) {
        Material_Code = material_Code;
        Material_Type = material_Type;
        Material_Name = material_Name;
        Material_IsActive = material_IsActive;
        this.LB_Points = LB_Points;
        Client_Code = client_Code;
        Company_Code = company_Code;
    }

    public String getMaterial_Code() {
        return Material_Code;
    }

    public void setMaterial_Code(String material_Code) {
        Material_Code = material_Code;
    }

    public int getMaterial_Type() {
        return Material_Type;
    }

    public void setMaterial_Type(int material_Type) {
        Material_Type = material_Type;
    }

    public String getMaterial_Name() {
        return Material_Name;
    }

    public void setMaterial_Name(String material_Name) {
        Material_Name = material_Name;
    }

    public int getMaterial_IsActive() {
        return Material_IsActive;
    }

    public void setMaterial_IsActive(int material_IsActive) {
        Material_IsActive = material_IsActive;
    }

    public float getLB_Points() {
        return LB_Points;
    }

    public void setLB_Points(float LB_Points) {
        this.LB_Points = LB_Points;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public void setClient_Code(String client_Code) {
        Client_Code = client_Code;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public void setCompany_Code(String company_Code) {
        Company_Code = company_Code;
    }
}
