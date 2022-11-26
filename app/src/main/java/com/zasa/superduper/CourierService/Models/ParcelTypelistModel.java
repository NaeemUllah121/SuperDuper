package com.zasa.superduper.CourierService.Models;

public class ParcelTypelistModel {

    private int Parcel_Type;
    private String Parcel_Type_Title;

    public ParcelTypelistModel() {
    }

    public ParcelTypelistModel(int parcel_Type, String parcel_Type_Title) {
        Parcel_Type = parcel_Type;
        Parcel_Type_Title = parcel_Type_Title;
    }

    public int getParcel_Type() {
        return Parcel_Type;
    }

    public void setParcel_Type(int parcel_Type) {
        Parcel_Type = parcel_Type;
    }

    public String getParcel_Type_Title() {
        return Parcel_Type_Title;
    }

    public void setParcel_Type_Title(String parcel_Type_Title) {
        Parcel_Type_Title = parcel_Type_Title;
    }
}
