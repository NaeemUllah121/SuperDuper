package com.zasa.superduper.CourierService.Models;

import java.util.ArrayList;

public class ParcelTypeListApi {
    private String Message;
    private int Status;
    ArrayList<ParcelTypelistModel> Parcel_Typelist = new ArrayList <ParcelTypelistModel> ();

    public ParcelTypeListApi() {
    }

    public ParcelTypeListApi(String message, int status, ArrayList<ParcelTypelistModel> parcel_Typelist) {
        Message = message;
        Status = status;
        Parcel_Typelist = parcel_Typelist;
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

    public ArrayList<ParcelTypelistModel> getParcel_Typelist() {
        return Parcel_Typelist;
    }

    public void setParcel_Typelist(ArrayList<ParcelTypelistModel> parcel_Typelist) {
        Parcel_Typelist = parcel_Typelist;
    }
}
