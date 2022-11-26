package com.zasa.superduper.CategoryListCompanyWise;

public class ParentItemCatList {////Category List (Company Wise)

    private String Client_Code;
    private String Company_Code;
    private int Item_Category_Code;
    private String Item_Category_Name;
    private String Item_Category_Short_Name;
    private int Item_Category_IsActive;
    private String DEPT_CODE = null;
    private String GROUP_CODE = null;
    private int Item_Type_Code;
    private String Created_By;
    private String Created_Date_Time;
    private String Edited_By = null;
    private String Edited_Date_Time = null;

    public ParentItemCatList(String client_Code, String company_Code, int item_Category_Code, String item_Category_Name, String item_Category_Short_Name, int item_Category_IsActive, String DEPT_CODE, String GROUP_CODE, int item_Type_Code, String created_By, String created_Date_Time, String edited_By, String edited_Date_Time) {
        Client_Code = client_Code;
        Company_Code = company_Code;
        Item_Category_Code = item_Category_Code;
        Item_Category_Name = item_Category_Name;
        Item_Category_Short_Name = item_Category_Short_Name;
        Item_Category_IsActive = item_Category_IsActive;
        this.DEPT_CODE = DEPT_CODE;
        this.GROUP_CODE = GROUP_CODE;
        Item_Type_Code = item_Type_Code;
        Created_By = created_By;
        Created_Date_Time = created_Date_Time;
        Edited_By = edited_By;
        Edited_Date_Time = edited_Date_Time;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public int getItem_Category_Code() {
        return Item_Category_Code;
    }

    public String getItem_Category_Name() {
        return Item_Category_Name;
    }

    public String getItem_Category_Short_Name() {
        return Item_Category_Short_Name;
    }

    public int getItem_Category_IsActive() {
        return Item_Category_IsActive;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public String getGROUP_CODE() {
        return GROUP_CODE;
    }

    public int getItem_Type_Code() {
        return Item_Type_Code;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public String getCreated_Date_Time() {
        return Created_Date_Time;
    }

    public String getEdited_By() {
        return Edited_By;
    }

    public String getEdited_Date_Time() {
        return Edited_Date_Time;
    }
}
