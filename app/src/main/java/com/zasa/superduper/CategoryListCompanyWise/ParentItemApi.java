package com.zasa.superduper.CategoryListCompanyWise;

import java.util.ArrayList;

public class ParentItemApi {
    private String Message;
    private int Status;
    ArrayList< ParentItemCatList > CatList = new ArrayList < ParentItemCatList > ();


    public ParentItemApi(String message, int status, ArrayList<ParentItemCatList> catList) {
        Message = message;
        Status = status;
        CatList = catList;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public ArrayList<ParentItemCatList> getCatList() {
        return CatList;
    }
}
