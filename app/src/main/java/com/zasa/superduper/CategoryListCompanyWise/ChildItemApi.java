package com.zasa.superduper.CategoryListCompanyWise;

import java.util.ArrayList;

public class ChildItemApi {
    private String Message;
    private int Status;
    ArrayList< ChildItemList > ItemList = new ArrayList < ChildItemList > ();

    public ChildItemApi(String message, int status, ArrayList<ChildItemList> itemList) {
        Message = message;
        Status = status;
        ItemList = itemList;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public ArrayList<ChildItemList> getItemList() {
        return ItemList;
    }
}
