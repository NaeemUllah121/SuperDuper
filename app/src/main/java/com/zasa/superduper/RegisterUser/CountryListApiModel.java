package com.zasa.superduper.RegisterUser;

import java.util.ArrayList;

public class CountryListApiModel {
    private String Country_Id;
    private String Country_Title;
    ArrayList< LB_Members > LB_Members = new ArrayList < LB_Members > ();

    public CountryListApiModel() {
    }

    public CountryListApiModel(String country_Id, String country_Title, ArrayList<LB_Members> LB_Members) {
        Country_Id = country_Id;
        Country_Title = country_Title;
        this.LB_Members = LB_Members;
    }

    public String getCountry_Id() {
        return Country_Id;
    }


    public void setCountry_Id(String country_Id) {
        Country_Id = country_Id;
    }

    public String getCountry_Title() {
        return Country_Title;
    }

    public void setCountry_Title(String country_Title) {
        Country_Title = country_Title;
    }

    public ArrayList<LB_Members> getLB_Members() {
        return LB_Members;
    }

    public void setLB_Members(ArrayList<LB_Members> LB_Members) {
        this.LB_Members = LB_Members;
    }
}

