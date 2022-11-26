package com.zasa.superduper.WasteMaterialRequestHistory;

import java.util.ArrayList;

public class MRHistoryApi {
    private String Message;
    private int Status;
    ArrayList< MOHListModel > MOHList = new ArrayList < MOHListModel > ();
    private String MOHeader = null;
    private String MODetail = null;

    public MRHistoryApi() {
    }

    public MRHistoryApi(String message, int status, ArrayList<MOHListModel> MOHList, String MOHeader, String MODetail) {
        Message = message;
        Status = status;
        this.MOHList = MOHList;
        this.MOHeader = MOHeader;
        this.MODetail = MODetail;
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

    public ArrayList<MOHListModel> getMOHList() {
        return MOHList;
    }

    public void setMOHList(ArrayList<MOHListModel> MOHList) {
        this.MOHList = MOHList;
    }

    public String getMOHeader() {
        return MOHeader;
    }

    public void setMOHeader(String MOHeader) {
        this.MOHeader = MOHeader;
    }

    public String getMODetail() {
        return MODetail;
    }

    public void setMODetail(String MODetail) {
        this.MODetail = MODetail;
    }
}
