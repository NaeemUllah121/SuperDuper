package com.zasa.superduper.Home;

import java.util.ArrayList;

public class TopBrandListApi {

    private String Message;
    private int Status;
    ArrayList<TopBrandListModel> TopBrandList = new ArrayList < TopBrandListModel > ();

    public TopBrandListApi() {
    }

    public TopBrandListApi(String message, int status, ArrayList<TopBrandListModel> topBrandList) {
        Message = message;
        Status = status;
        TopBrandList = topBrandList;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public ArrayList<TopBrandListModel> getTopBrandList() {
        return TopBrandList;
    }
}
