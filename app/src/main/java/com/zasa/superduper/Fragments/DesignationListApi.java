package com.zasa.superduper.Fragments;

import java.util.ArrayList;

public class DesignationListApi {
    private String Message;
    private int Status;
    ArrayList< DesignationListModel > Designationlist = new ArrayList < DesignationListModel > ();

    public DesignationListApi() {
    }

    public DesignationListApi(String message, int status, ArrayList<DesignationListModel> designationlist) {
        Message = message;
        Status = status;
        Designationlist = designationlist;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public ArrayList<DesignationListModel> getDesignationlist() {
        return Designationlist;
    }
}
