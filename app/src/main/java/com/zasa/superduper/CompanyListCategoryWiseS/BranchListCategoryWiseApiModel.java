package com.zasa.superduper.CompanyListCategoryWiseS;

public class BranchListCategoryWiseApiModel {

    private String Branch_Code;
    private String Branch_Name;
    private String Branch_Address;
    private String Branch_City;
    private String Branch_Country;
    private String Branch_Area;
    private float Branch_IsDefault;
    private String Company_Code;
    private String Client_Code;
    private String Branch_Login_Id = null;
    private String Branch_Login_Pass = null;
    private String Branch_Active;
    private float Business_Type_Code;
    private float Max_Discount_Limit;
    private String Company_Category_Code;


    public BranchListCategoryWiseApiModel() {
    }

    public BranchListCategoryWiseApiModel(String branch_Code, String branch_Name, String branch_Address, String branch_City, String branch_Country, String branch_Area, float branch_IsDefault, String company_Code, String client_Code, String branch_Login_Id, String branch_Login_Pass, String branch_Active, float business_Type_Code, float max_Discount_Limit, String company_Category_Code) {
        Branch_Code = branch_Code;
        Branch_Name = branch_Name;
        Branch_Address = branch_Address;
        Branch_City = branch_City;
        Branch_Country = branch_Country;
        Branch_Area = branch_Area;
        Branch_IsDefault = branch_IsDefault;
        Company_Code = company_Code;
        Client_Code = client_Code;
        Branch_Login_Id = branch_Login_Id;
        Branch_Login_Pass = branch_Login_Pass;
        Branch_Active = branch_Active;
        Business_Type_Code = business_Type_Code;
        Max_Discount_Limit = max_Discount_Limit;
        Company_Category_Code = company_Category_Code;
    }

    public String getBranch_Code() {
        return Branch_Code;
    }

    public String getBranch_Name() {
        return Branch_Name;
    }

    public String getBranch_Address() {
        return Branch_Address;
    }

    public String getBranch_City() {
        return Branch_City;
    }

    public String getBranch_Country() {
        return Branch_Country;
    }

    public String getBranch_Area() {
        return Branch_Area;
    }

    public float getBranch_IsDefault() {
        return Branch_IsDefault;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public String getBranch_Login_Id() {
        return Branch_Login_Id;
    }

    public String getBranch_Login_Pass() {
        return Branch_Login_Pass;
    }

    public String getBranch_Active() {
        return Branch_Active;
    }

    public float getBusiness_Type_Code() {
        return Business_Type_Code;
    }

    public float getMax_Discount_Limit() {
        return Max_Discount_Limit;
    }

    public String getCompany_Category_Code() {
        return Company_Category_Code;
    }
}
