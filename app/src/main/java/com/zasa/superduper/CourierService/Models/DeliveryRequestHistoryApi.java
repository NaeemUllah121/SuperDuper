package com.zasa.superduper.CourierService.Models;

import java.util.ArrayList;

public class DeliveryRequestHistoryApi {

    private String Message;
    private int Status;
    ArrayList< DeliveryRequestHistoryModel > DHList = new ArrayList < DeliveryRequestHistoryModel > ();
    private String DHeader = null;

    public DeliveryRequestHistoryApi() {
    }

    public DeliveryRequestHistoryApi(String message, int status, ArrayList<DeliveryRequestHistoryModel> DHList, String DHeader) {
        Message = message;
        Status = status;
        this.DHList = DHList;
        this.DHeader = DHeader;
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

    public ArrayList<DeliveryRequestHistoryModel> getDHList() {
        return DHList;
    }

    public void setDHList(ArrayList<DeliveryRequestHistoryModel> DHList) {
        this.DHList = DHList;
    }

    public String getDHeader() {
        return DHeader;
    }

    public void setDHeader(String DHeader) {
        this.DHeader = DHeader;
    }
}
