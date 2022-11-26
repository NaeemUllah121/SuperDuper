package com.zasa.superduper.CourierService.Models;

public class DeliveryTypeListModel {

    private int Delivery_Type;
    private String Delivery_Type_Title;

    public DeliveryTypeListModel() {
    }

    public DeliveryTypeListModel(int delivery_Type, String delivery_Type_Title) {
        Delivery_Type = delivery_Type;
        Delivery_Type_Title = delivery_Type_Title;
    }

    public int getDelivery_Type() {
        return Delivery_Type;
    }

    public void setDelivery_Type(int delivery_Type) {
        Delivery_Type = delivery_Type;
    }

    public String getDelivery_Type_Title() {
        return Delivery_Type_Title;
    }

    public void setDelivery_Type_Title(String delivery_Type_Title) {
        Delivery_Type_Title = delivery_Type_Title;
    }
}
