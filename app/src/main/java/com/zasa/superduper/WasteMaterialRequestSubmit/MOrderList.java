package com.zasa.superduper.WasteMaterialRequestSubmit;

import java.util.ArrayList;

public class MOrderList {

    ArrayList<MOrderListModel> MOrderModel = new ArrayList <MOrderListModel> ();

    public MOrderList() {
    }

    public MOrderList(ArrayList<MOrderListModel> MOrderModel) {
        this.MOrderModel = MOrderModel;
    }

    public ArrayList<MOrderListModel> getMOrderModel() {
        return MOrderModel;
    }

    public void setMOrderModel(ArrayList<MOrderListModel> MOrderModel) {
        this.MOrderModel = MOrderModel;
    }
}
