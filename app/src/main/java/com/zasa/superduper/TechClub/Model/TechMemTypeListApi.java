package com.zasa.superduper.TechClub.Model;

import java.util.ArrayList;

public class TechMemTypeListApi {
    private String Message;
    private int Status;
    ArrayList< TechMemTypeListModel > Tech_MTypelist = new ArrayList < TechMemTypeListModel > ();

    public TechMemTypeListApi() {
    }

    public TechMemTypeListApi(String message, int status, ArrayList<TechMemTypeListModel> tech_MTypelist) {
        Message = message;
        Status = status;
        Tech_MTypelist = tech_MTypelist;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public ArrayList<TechMemTypeListModel> getTech_MTypelist() {
        return Tech_MTypelist;
    }
}
