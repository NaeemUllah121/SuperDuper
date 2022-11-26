package com.zasa.superduper.Splash;


import java.util.ArrayList;

public class CompanyDetailsApi {

    private String Message;
    private int Status;
    CompanyDetailSingleModel CompanySingle;
    ArrayList<CompanyListModel> CompanyList = new ArrayList<CompanyListModel>();

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

    public CompanyDetailSingleModel getCompanySingle() {
        return CompanySingle;
    }

    public void setCompanySingle(CompanyDetailSingleModel companySingle) {
        CompanySingle = companySingle;
    }

    public ArrayList<CompanyListModel> getCompanyList() {
        return CompanyList;
    }

    public void setCompanyList(ArrayList<CompanyListModel> companyList) {
        CompanyList = companyList;
    }


    public class CompanyDetailSingleModel {
        private String Company_Code;
        private String Company_Name;
        private String Company_Logo;
        private String Company_Address;
        private String Company_Currency;
        private String Company_Currency_Sign;
        private String Company_Phone;
        private String Company_Fax;
        private String Company_Url;
        private String Company_Email;
        private String Company_Contact_Person;
        private String Business_Type_Code;
        private String Company_Ntn;
        private String Company_Stn;
        private String Country_Id;
        private String City_Id;
        private String Client_Code;
        private String Company_Active;
        private String Company_Type_Code;
        private String Max_Discount_Limit;
        private String Company_Category_Code;
        private String Top_Brand_Flag;
        private int Top_Brand_Position;
        private String LB_Client;

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

        public String getBusiness_Type_Code() {
            return Business_Type_Code;
        }

        public void setBusiness_Type_Code(String business_Type_Code) {
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

        public String getMax_Discount_Limit() {
            return Max_Discount_Limit;
        }

        public void setMax_Discount_Limit(String max_Discount_Limit) {
            Max_Discount_Limit = max_Discount_Limit;
        }

        public String getCompany_Category_Code() {
            return Company_Category_Code;
        }

        public void setCompany_Category_Code(String company_Category_Code) {
            Company_Category_Code = company_Category_Code;
        }

        public String getTop_Brand_Flag() {
            return Top_Brand_Flag;
        }

        public void setTop_Brand_Flag(String top_Brand_Flag) {
            Top_Brand_Flag = top_Brand_Flag;
        }

        public int getTop_Brand_Position() {
            return Top_Brand_Position;
        }

        public void setTop_Brand_Position(int top_Brand_Position) {
            Top_Brand_Position = top_Brand_Position;
        }

        public String getLB_Client() {
            return LB_Client;
        }

        public void setLB_Client(String LB_Client) {
            this.LB_Client = LB_Client;
        }
    }

    public class CompanyListModel {

    }
}
