package com.zasa.superduper.CategoryListCompanyWise;

import java.util.ArrayList;

public class ItemListCompanyWiseApi {

    private String Message;
    private float Status;
    ArrayList< ChildItemList > ItemList = new ArrayList < ChildItemList > ();

    public ItemListCompanyWiseApi() {
    }

    public ItemListCompanyWiseApi(String message, float status, ArrayList<ChildItemList> itemList) {
        Message = message;
        Status = status;
        ItemList = itemList;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public float getStatus() {
        return Status;
    }

    public void setStatus(float status) {
        Status = status;
    }

    public ArrayList<ChildItemList> getItemList() {
        return ItemList;
    }

    public void setItemList(ArrayList<ChildItemList> itemList) {
        ItemList = itemList;
    }
}
