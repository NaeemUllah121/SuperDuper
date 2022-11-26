package com.zasa.superduper.Home;

import androidx.annotation.Keep;

@Keep
public class CompanyTypeListModel {
    private String Company_Type_Code;
    private String Company_Type_Name;

    public CompanyTypeListModel() {
    }

    public CompanyTypeListModel(String company_Type_Code, String company_Type_Name) {
        Company_Type_Code = company_Type_Code;
        Company_Type_Name = company_Type_Name;
    }

    public String getCompany_Type_Code() {
        return Company_Type_Code;
    }

    public void setCompany_Type_Code(String company_Type_Code) {
        Company_Type_Code = company_Type_Code;
    }

    public String getCompany_Type_Name() {
        return Company_Type_Name;
    }

    public void setCompany_Type_Name(String company_Type_Name) {
        Company_Type_Name = company_Type_Name;
    }
}
