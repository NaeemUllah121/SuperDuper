package com.zasa.superduper.CategoryListCompanyWise;

import java.util.ArrayList;

public class CategoryItemCombineModel {

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

    ArrayList< ChildItemList > ItemList = new ArrayList < ChildItemList > ();

    public CategoryItemCombineModel() {
    }

    public CategoryItemCombineModel(String client_Code, String company_Code, int item_Category_Code, String item_Category_Name, String item_Category_Short_Name, int item_Category_IsActive, String DEPT_CODE, String GROUP_CODE, int item_Type_Code, String created_By, String created_Date_Time, String edited_By, String edited_Date_Time, ArrayList<ChildItemList> itemList) {
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
        ItemList = itemList;
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

    public int getItem_Category_Code() {
        return Item_Category_Code;
    }

    public void setItem_Category_Code(int item_Category_Code) {
        Item_Category_Code = item_Category_Code;
    }

    public String getItem_Category_Name() {
        return Item_Category_Name;
    }

    public void setItem_Category_Name(String item_Category_Name) {
        Item_Category_Name = item_Category_Name;
    }

    public String getItem_Category_Short_Name() {
        return Item_Category_Short_Name;
    }

    public void setItem_Category_Short_Name(String item_Category_Short_Name) {
        Item_Category_Short_Name = item_Category_Short_Name;
    }

    public int getItem_Category_IsActive() {
        return Item_Category_IsActive;
    }

    public void setItem_Category_IsActive(int item_Category_IsActive) {
        Item_Category_IsActive = item_Category_IsActive;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public String getGROUP_CODE() {
        return GROUP_CODE;
    }

    public void setGROUP_CODE(String GROUP_CODE) {
        this.GROUP_CODE = GROUP_CODE;
    }

    public int getItem_Type_Code() {
        return Item_Type_Code;
    }

    public void setItem_Type_Code(int item_Type_Code) {
        Item_Type_Code = item_Type_Code;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public String getCreated_Date_Time() {
        return Created_Date_Time;
    }

    public void setCreated_Date_Time(String created_Date_Time) {
        Created_Date_Time = created_Date_Time;
    }

    public String getEdited_By() {
        return Edited_By;
    }

    public void setEdited_By(String edited_By) {
        Edited_By = edited_By;
    }

    public String getEdited_Date_Time() {
        return Edited_Date_Time;
    }

    public void setEdited_Date_Time(String edited_Date_Time) {
        Edited_Date_Time = edited_Date_Time;
    }

    public ArrayList<ChildItemList> getItemList() {
        return ItemList;
    }

    public void setItemList(ArrayList<ChildItemList> itemList) {
        ItemList = itemList;
    }
}
