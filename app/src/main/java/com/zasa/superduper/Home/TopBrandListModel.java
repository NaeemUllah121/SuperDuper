package com.zasa.superduper.Home;


public class TopBrandListModel {

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
    private float Business_Type_Code;
    private String Company_Ntn = null;
    private String Company_Stn = null;
    private String Country_Id = null;
    private String City_Id = null;
    private String Client_Code;
    private String Company_Active;
    private String Company_Type_Code;
    private float Max_Discount_Limit;
    private String Company_Category_Code;
    private String Top_Brand_Flag;
    private float Top_Brand_Position;
    private String LB_Client = null;

    public TopBrandListModel() {
    }

    public TopBrandListModel(String company_Code, String company_Name, String company_Logo, String company_Address, String company_Currency, String company_Currency_Sign, String company_Phone, String company_Fax, String company_Url, String company_Email, String company_Contact_Person, float business_Type_Code, String company_Ntn, String company_Stn, String country_Id, String city_Id, String client_Code, String company_Active, String company_Type_Code, float max_Discount_Limit, String company_Category_Code, String top_Brand_Flag, float top_Brand_Position, String LB_Client) {
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
        Company_Category_Code = company_Category_Code;
        Top_Brand_Flag = top_Brand_Flag;
        Top_Brand_Position = top_Brand_Position;
        this.LB_Client = LB_Client;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public String getCompany_Logo() {
        return Company_Logo;
    }

    public String getCompany_Address() {
        return Company_Address;
    }

    public String getCompany_Currency() {
        return Company_Currency;
    }

    public String getCompany_Currency_Sign() {
        return Company_Currency_Sign;
    }

    public String getCompany_Phone() {
        return Company_Phone;
    }

    public String getCompany_Fax() {
        return Company_Fax;
    }

    public String getCompany_Url() {
        return Company_Url;
    }

    public String getCompany_Email() {
        return Company_Email;
    }

    public String getCompany_Contact_Person() {
        return Company_Contact_Person;
    }

    public float getBusiness_Type_Code() {
        return Business_Type_Code;
    }

    public String getCompany_Ntn() {
        return Company_Ntn;
    }

    public String getCompany_Stn() {
        return Company_Stn;
    }

    public String getCountry_Id() {
        return Country_Id;
    }

    public String getCity_Id() {
        return City_Id;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public String getCompany_Active() {
        return Company_Active;
    }

    public String getCompany_Type_Code() {
        return Company_Type_Code;
    }

    public float getMax_Discount_Limit() {
        return Max_Discount_Limit;
    }

    public String getCompany_Category_Code() {
        return Company_Category_Code;
    }

    public String getTop_Brand_Flag() {
        return Top_Brand_Flag;
    }

    public float getTop_Brand_Position() {
        return Top_Brand_Position;
    }

    public String getLB_Client() {
        return LB_Client;
    }

    /* String name,image;
    public TopBrandListModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }*/
}

