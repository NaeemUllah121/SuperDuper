package com.zasa.superduper.CourierService.Models;

import java.util.ArrayList;

public class DeliveryTypeListApi {

    private String Message;
    private float Status;
    ArrayList< DeliveryTypeListModel > Delivery_Typelist = new ArrayList < DeliveryTypeListModel > ();

    public DeliveryTypeListApi() {
    }

    public DeliveryTypeListApi(String message, float status, ArrayList<DeliveryTypeListModel> delivery_Typelist) {
        Message = message;
        Status = status;
        Delivery_Typelist = delivery_Typelist;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public float getStatus() {
        return Status;
    }

    public void setStatus(float status) {
        Status = status;
    }

    public ArrayList<DeliveryTypeListModel> getDelivery_Typelist() {
        return Delivery_Typelist;
    }

    public void setDelivery_Typelist(ArrayList<DeliveryTypeListModel> delivery_Typelist) {
        Delivery_Typelist = delivery_Typelist;
    }
}
