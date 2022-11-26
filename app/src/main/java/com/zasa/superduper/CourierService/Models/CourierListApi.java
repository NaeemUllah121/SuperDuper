package com.zasa.superduper.CourierService.Models;

import java.util.ArrayList;

public class CourierListApi {

    private String Message;
    private float Status;
    private String CourierSingle = null;
    ArrayList< CourierListModel > Courierlist = new ArrayList < CourierListModel > ();

    public CourierListApi() {
    }

    public CourierListApi(String message, float status, String courierSingle, ArrayList<CourierListModel> courierlist) {
        Message = message;
        Status = status;
        CourierSingle = courierSingle;
        Courierlist = courierlist;
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

    public String getCourierSingle() {
        return CourierSingle;
    }

    public void setCourierSingle(String courierSingle) {
        CourierSingle = courierSingle;
    }

    public ArrayList<CourierListModel> getCourierlist() {
        return Courierlist;
    }

    public void setCourierlist(ArrayList<CourierListModel> courierlist) {
        Courierlist = courierlist;
    }
}
