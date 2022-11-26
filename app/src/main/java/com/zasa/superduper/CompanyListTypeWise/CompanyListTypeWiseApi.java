package com.zasa.superduper.CompanyListTypeWise;

import java.util.ArrayList;

public class CompanyListTypeWiseApi {
    private String Message;
    private int Status;
    private String CompanySingle = null;
    ArrayList<CompanyListTypeWiseApiModel> CompanyList = new ArrayList <CompanyListTypeWiseApiModel> ();

    public CompanyListTypeWiseApi() {
    }

    public CompanyListTypeWiseApi(String message, int status, String companySingle, ArrayList<CompanyListTypeWiseApiModel> companyList) {
        Message = message;
        Status = status;
        CompanySingle = companySingle;
        CompanyList = companyList;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getCompanySingle() {
        return CompanySingle;
    }

    public void setCompanySingle(String companySingle) {
        CompanySingle = companySingle;
    }

    public ArrayList<CompanyListTypeWiseApiModel> getCompanyList() {
        return CompanyList;
    }

    public void setCompanyList(ArrayList<CompanyListTypeWiseApiModel> companyList) {
        CompanyList = companyList;
    }
}
