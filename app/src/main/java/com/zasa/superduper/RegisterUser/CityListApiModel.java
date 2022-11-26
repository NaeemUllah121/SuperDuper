package com.zasa.superduper.RegisterUser;

public class CityListApiModel {

    private String City_Id;
    private String City_Title;
    private String Country_Id;
    private String Province_Id = null;

    public CityListApiModel() {
    }

    public CityListApiModel(String city_Id, String city_Title, String country_Id, String province_Id) {
        City_Id = city_Id;
        City_Title = city_Title;
        Country_Id = country_Id;
        Province_Id = province_Id;
    }

    public String getCity_Id() {
        return City_Id;
    }

    public void setCity_Id(String city_Id) {
        City_Id = city_Id;
    }

    public String getCity_Title() {
        return City_Title;
    }

    public void setCity_Title(String city_Title) {
        City_Title = city_Title;
    }

    public String getCountry_Id() {
        return Country_Id;
    }

    public void setCountry_Id(String country_Id) {
        Country_Id = country_Id;
    }

    public String getProvince_Id() {
        return Province_Id;
    }

    public void setProvince_Id(String province_Id) {
        Province_Id = province_Id;
    }

    @Override
    public String toString() {
        return "CityListApiModel{" +
                "City_Id='" + City_Id + '\'' +
                ", City_Title='" + City_Title + '\'' +
                ", Country_Id='" + Country_Id + '\'' +
                ", Province_Id='" + Province_Id + '\'' +
                '}';
    }
}
