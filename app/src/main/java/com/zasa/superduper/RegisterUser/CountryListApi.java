package com.zasa.superduper.RegisterUser;

import java.util.ArrayList;

public class CountryListApi {

    private String Message;
    private int Status;
    ArrayList<CountryListApiModel> Countrylist = new ArrayList <CountryListApiModel> ();

    public CountryListApi() {
    }

    public CountryListApi(String message, int status, ArrayList<CountryListApiModel> countrylist) {
        Message = message;
        Status = status;
        Countrylist = countrylist;
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

    public ArrayList<CountryListApiModel> getCountrylist() {
        return Countrylist;
    }

    public void setCountrylist(ArrayList<CountryListApiModel> countrylist) {
        Countrylist = countrylist;
    }
}
