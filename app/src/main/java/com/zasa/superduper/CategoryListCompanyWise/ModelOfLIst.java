package com.zasa.superduper.CategoryListCompanyWise;

import java.util.ArrayList;

public class ModelOfLIst {

    private String Client_Code;
    private String Company_Code;
    private int Item_Category_Code;
    private String Item_Category_Name;
  //  private String Item_Category_Short_Name;
    private int Item_Category_IsActive;
//    private String DEPT_CODE = null;
//    private String GROUP_CODE = null;
    private int Item_Type_Code;
//    private String Created_By;
//    private String Created_Date_Time;
//    private String Edited_By = null;
//    private String Edited_Date_Time = null;
    ArrayList< ChildItemList > ItemList = new ArrayList < ChildItemList > ();

    public ModelOfLIst() {
    }

    public ModelOfLIst(String client_Code, String company_Code, int item_Category_Code, String item_Category_Name, int item_Category_IsActive, int item_Type_Code, ArrayList<ChildItemList> itemList) {
        Client_Code = client_Code;
        Company_Code = company_Code;
        Item_Category_Code = item_Category_Code;
        Item_Category_Name = item_Category_Name;
        Item_Category_IsActive = item_Category_IsActive;
        Item_Type_Code = item_Type_Code;
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

    public int getItem_Category_IsActive() {
        return Item_Category_IsActive;
    }

    public void setItem_Category_IsActive(int item_Category_IsActive) {
        Item_Category_IsActive = item_Category_IsActive;
    }

    public int getItem_Type_Code() {
        return Item_Type_Code;
    }

    public void setItem_Type_Code(int item_Type_Code) {
        Item_Type_Code = item_Type_Code;
    }

    public ArrayList<ChildItemList> getItemList() {
        return ItemList;
    }

    public void setItemList(ArrayList<ChildItemList> itemList) {
        ItemList = itemList;
    }

    @Override
    public String toString() {
        return "\n\nModelOfLIst{" +
                "Client_Code='" + Client_Code + '\'' +
                ", Company_Code='" + Company_Code + '\'' +
                ", Item_Category_Code=" + Item_Category_Code +
                ", Item_Category_Name='" + Item_Category_Name + '\'' +
                ", Item_Category_IsActive=" + Item_Category_IsActive +
                ", Item_Type_Code=" + Item_Type_Code +
                ", ItemList=" + ItemList +
                '}';
    }
}
