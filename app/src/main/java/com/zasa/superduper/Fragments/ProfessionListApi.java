package com.zasa.superduper.Fragments;

import java.util.ArrayList;

public class ProfessionListApi {

    private String Message;
    private int Status;
    ArrayList< ProfessionListModel > Professionlist = new ArrayList < ProfessionListModel > ();

    public ProfessionListApi() {
    }

    public ProfessionListApi(String message, int status, ArrayList<ProfessionListModel> professionlist) {
        Message = message;
        Status = status;
        Professionlist = professionlist;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public ArrayList<ProfessionListModel> getProfessionlist() {
        return Professionlist;
    }
}
