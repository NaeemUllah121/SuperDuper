package com.zasa.superduper.SubmitGeneralOrder;

import java.util.ArrayList;

public class OrderModel {
    ArrayList<OderModelList> OrderModel = new ArrayList <OderModelList> ();

    public OrderModel() {
    }

    public OrderModel(ArrayList<OderModelList> orderModel) {
        OrderModel = orderModel;
    }


    public ArrayList<OderModelList> getOrderModel() {
        return OrderModel;
    }
}
