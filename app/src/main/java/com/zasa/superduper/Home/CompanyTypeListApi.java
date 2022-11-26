package com.zasa.superduper.Home;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class CompanyTypeListApi {

    private String Message;
    private float Status;
    ArrayList< CompanyTypeListModel > CompanyTypeList = new ArrayList < CompanyTypeListModel > ();

    public CompanyTypeListApi() {
    }

    public CompanyTypeListApi(String message, float status, ArrayList<CompanyTypeListModel> companyTypeList) {
        Message = message;
        Status = status;
        CompanyTypeList = companyTypeList;
    }

    public String getMessage() {
        return Message;
    }

    public float getStatus() {
        return Status;
    }

    public ArrayList<CompanyTypeListModel> getCompanyTypeList() {
        return CompanyTypeList;
    }
}
