package com.zasa.superduper.RegisterUser;

import java.util.ArrayList;

public class CityListApi {

    private String Message;
    private int Status;
    ArrayList< CityListApiModel > Citylist = new ArrayList < CityListApiModel > ();

    public CityListApi() {
    }

    public CityListApi(String message, int status, ArrayList<CityListApiModel> citylist) {
        Message = message;
        Status = status;
        Citylist = citylist;
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

    public ArrayList<CityListApiModel> getCitylist() {
        return Citylist;
    }

    public void setCitylist(ArrayList<CityListApiModel> citylist) {
        Citylist = citylist;
    }
}
