package com.zasa.superduper.CompanyCategoryListTypeWiseS;

public class CompanyCategoryListModel {

    private String Company_Category_Code;
    private String Company_Category_Name;
    private String Company_Type_Code;

    public CompanyCategoryListModel() {
    }

    public CompanyCategoryListModel(String company_Category_Code, String company_Category_Name, String company_Type_Code) {
        Company_Category_Code = company_Category_Code;
        Company_Category_Name = company_Category_Name;
        Company_Type_Code = company_Type_Code;
    }

    public String getCompany_Category_Code() {
        return Company_Category_Code;
    }

    public String getCompany_Category_Name() {
        return Company_Category_Name;
    }

    public String getCompany_Type_Code() {
        return Company_Type_Code;
    }
}
