package com.zasa.superduper.CourierService.Models;

import java.util.ArrayList;

public class SubmitDeliveryRequestList {

    ArrayList<SubmitDeliveryRequestListModel> DeliveryModel = new ArrayList < SubmitDeliveryRequestListModel > ();

    public SubmitDeliveryRequestList() {
    }

    public SubmitDeliveryRequestList(ArrayList<SubmitDeliveryRequestListModel> deliveryModel) {
        DeliveryModel = deliveryModel;
    }

    public ArrayList<SubmitDeliveryRequestListModel> getDeliveryModel() {
        return DeliveryModel;
    }

    public void setDeliveryModel(ArrayList<SubmitDeliveryRequestListModel> deliveryModel) {
        DeliveryModel = deliveryModel;
    }
}
