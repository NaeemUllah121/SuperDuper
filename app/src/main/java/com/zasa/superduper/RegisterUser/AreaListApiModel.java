package com.zasa.superduper.RegisterUser;

import java.util.ArrayList;

public class AreaListApiModel {
    private String Area_Id;
    private String Area_Title;
    private String City_Id;
    ArrayList< LB_Members > LB_Members = new ArrayList < LB_Members > ();

    public AreaListApiModel() {
    }

    public AreaListApiModel(String area_Id, String area_Title, String city_Id, ArrayList<LB_Members> LB_Members) {
        Area_Id = area_Id;
        Area_Title = area_Title;
        City_Id = city_Id;
        this.LB_Members = LB_Members;
    }

    public String getArea_Id() {
        return Area_Id;
    }

    public void setArea_Id(String area_Id) {
        Area_Id = area_Id;
    }

    public String getArea_Title() {
        return Area_Title;
    }

    public void setArea_Title(String area_Title) {
        Area_Title = area_Title;
    }

    public String getCity_Id() {
        return City_Id;
    }

    public void setCity_Id(String city_Id) {
        City_Id = city_Id;
    }

    public ArrayList<LB_Members> getLB_Members() {
        return LB_Members;
    }

    public void setLB_Members(ArrayList<LB_Members> LB_Members) {
        this.LB_Members = LB_Members;
    }
}
