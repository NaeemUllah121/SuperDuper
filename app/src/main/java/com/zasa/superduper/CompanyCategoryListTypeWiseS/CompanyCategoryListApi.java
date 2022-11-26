package com.zasa.superduper.CompanyCategoryListTypeWiseS;

import java.util.ArrayList;

public class CompanyCategoryListApi {

    private String Message;
    private float Status;
    ArrayList< CompanyCategoryListModel > CompanyCategoryList = new ArrayList < CompanyCategoryListModel > ();

    public CompanyCategoryListApi() {
    }

    public CompanyCategoryListApi(String message, float status, ArrayList<CompanyCategoryListModel> companyCategoryList) {
        Message = message;
        Status = status;
        CompanyCategoryList = companyCategoryList;
    }

    public String getMessage() {
        return Message;
    }

    public float getStatus() {
        return Status;
    }

    public ArrayList<CompanyCategoryListModel> getCompanyCategoryList() {
        return CompanyCategoryList;
    }
}
