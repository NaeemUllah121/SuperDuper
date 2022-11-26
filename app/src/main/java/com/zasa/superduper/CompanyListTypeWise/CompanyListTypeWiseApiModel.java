package com.zasa.superduper.CompanyListTypeWise;

public class CompanyListTypeWiseApiModel {
    private String Company_Code;
    private String Company_Name;
    private String Company_Logo = null;
    private String Company_Address = null;
    private String Company_Currency = null;
    private String Company_Currency_Sign = null;
    private String Company_Phone = null;
    private String Company_Fax = null;
    private String Company_Url = null;
    private String Company_Email = null;
    private String Company_Contact_Person = null;
    private int Business_Type_Code;
    private String Company_Ntn = null;
    private String Company_Stn = null;
    private String Country_Id = null;
    private String City_Id = null;
    private String Client_Code;
    private String Company_Active = null;
    private String Company_Type_Code;
    private float Max_Discount_Limit;
    private String LB_Client = null;

    public CompanyListTypeWiseApiModel() {
    }

    public CompanyListTypeWiseApiModel(String company_Code, String company_Name, String company_Logo, String company_Address, String company_Currency, String company_Currency_Sign, String company_Phone, String company_Fax, String company_Url, String company_Email, String company_Contact_Person, int business_Type_Code, String company_Ntn, String company_Stn, String country_Id, String city_Id, String client_Code, String company_Active, String company_Type_Code, float max_Discount_Limit, String LB_Client) {
        Company_Code = company_Code;
        Company_Name = company_Name;
        Company_Logo = company_Logo;
        Company_Address = company_Address;
        Company_Currency = company_Currency;
        Company_Currency_Sign = company_Currency_Sign;
        Company_Phone = company_Phone;
        Company_Fax = company_Fax;
        Company_Url = company_Url;
        Company_Email = company_Email;
        Company_Contact_Person = company_Contact_Person;
        Business_Type_Code = business_Type_Code;
        Company_Ntn = company_Ntn;
        Company_Stn = company_Stn;
        Country_Id = country_Id;
        City_Id = city_Id;
        Client_Code = client_Code;
        Company_Active = company_Active;
        Company_Type_Code = company_Type_Code;
        Max_Discount_Limit = max_Discount_Limit;
        this.LB_Client = LB_Client;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public void setCompany_Code(String company_Code) {
        Company_Code = company_Code;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getCompany_Logo() {
        return Company_Logo;
    }

    public void setCompany_Logo(String company_Logo) {
        Company_Logo = company_Logo;
    }

    public String getCompany_Address() {
        return Company_Address;
    }

    public void setCompany_Address(String company_Address) {
        Company_Address = company_Address;
    }

    public String getCompany_Currency() {
        return Company_Currency;
    }

    public void setCompany_Currency(String company_Currency) {
        Company_Currency = company_Currency;
    }

    public String getCompany_Currency_Sign() {
        return Company_Currency_Sign;
    }

    public void setCompany_Currency_Sign(String company_Currency_Sign) {
        Company_Currency_Sign = company_Currency_Sign;
    }

    public String getCompany_Phone() {
        return Company_Phone;
    }

    public void setCompany_Phone(String company_Phone) {
        Company_Phone = company_Phone;
    }

    public String getCompany_Fax() {
        return Company_Fax;
    }

    public void setCompany_Fax(String company_Fax) {
        Company_Fax = company_Fax;
    }

    public String getCompany_Url() {
        return Company_Url;
    }

    public void setCompany_Url(String company_Url) {
        Company_Url = company_Url;
    }

    public String getCompany_Email() {
        return Company_Email;
    }

    public void setCompany_Email(String company_Email) {
        Company_Email = company_Email;
    }

    public String getCompany_Contact_Person() {
        return Company_Contact_Person;
    }

    public void setCompany_Contact_Person(String company_Contact_Person) {
        Company_Contact_Person = company_Contact_Person;
    }

    public int getBusiness_Type_Code() {
        return Business_Type_Code;
    }

    public void setBusiness_Type_Code(int business_Type_Code) {
        Business_Type_Code = business_Type_Code;
    }

    public String getCompany_Ntn() {
        return Company_Ntn;
    }

    public void setCompany_Ntn(String company_Ntn) {
        Company_Ntn = company_Ntn;
    }

    public String getCompany_Stn() {
        return Company_Stn;
    }

    public void setCompany_Stn(String company_Stn) {
        Company_Stn = company_Stn;
    }

    public String getCountry_Id() {
        return Country_Id;
    }

    public void setCountry_Id(String country_Id) {
        Country_Id = country_Id;
    }

    public String getCity_Id() {
        return City_Id;
    }

    public void setCity_Id(String city_Id) {
        City_Id = city_Id;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public void setClient_Code(String client_Code) {
        Client_Code = client_Code;
    }

    public String getCompany_Active() {
        return Company_Active;
    }

    public void setCompany_Active(String company_Active) {
        Company_Active = company_Active;
    }

    public String getCompany_Type_Code() {
        return Company_Type_Code;
    }

    public void setCompany_Type_Code(String company_Type_Code) {
        Company_Type_Code = company_Type_Code;
    }

    public float getMax_Discount_Limit() {
        return Max_Discount_Limit;
    }

    public void setMax_Discount_Limit(float max_Discount_Limit) {
        Max_Discount_Limit = max_Discount_Limit;
    }

    public String getLB_Client() {
        return LB_Client;
    }

    public void setLB_Client(String LB_Client) {
        this.LB_Client = LB_Client;
    }
}
