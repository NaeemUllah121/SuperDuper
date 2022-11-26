package com.zasa.superduper.RegisterUser;

import java.util.ArrayList;

public class AreaListApi {

    private String Message;
    private int Status;
    ArrayList< AreaListApiModel > Arealist = new ArrayList < AreaListApiModel > ();

    public AreaListApi() {
    }

    public AreaListApi(String message, int status, ArrayList<AreaListApiModel> arealist) {
        Message = message;
        Status = status;
        Arealist = arealist;
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

    public ArrayList<AreaListApiModel> getArealist() {
        return Arealist;
    }

    public void setArealist(ArrayList<AreaListApiModel> arealist) {
        Arealist = arealist;
    }
}
