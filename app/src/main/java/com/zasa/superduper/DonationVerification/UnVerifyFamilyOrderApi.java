package com.zasa.superduper.DonationVerification;

import java.util.ArrayList;

public class UnVerifyFamilyOrderApi {

    private String Message;
    private int Status;
    ArrayList < UnVerifyFamilyOrderModel > FOHList = new ArrayList< UnVerifyFamilyOrderModel >();
    private String FOHeader = null;
    private String FODetail = null;


    public UnVerifyFamilyOrderApi() {
    }

    public UnVerifyFamilyOrderApi(String message, int status, ArrayList<UnVerifyFamilyOrderModel> FOHList, String FOHeader, String FODetail) {
        Message = message;
        Status = status;
        this.FOHList = FOHList;
        this.FOHeader = FOHeader;
        this.FODetail = FODetail;
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

    public ArrayList<UnVerifyFamilyOrderModel> getFOHList() {
        return FOHList;
    }

    public void setFOHList(ArrayList<UnVerifyFamilyOrderModel> FOHList) {
        this.FOHList = FOHList;
    }

    public String getFOHeader() {
        return FOHeader;
    }

    public void setFOHeader(String FOHeader) {
        this.FOHeader = FOHeader;
    }

    public String getFODetail() {
        return FODetail;
    }

    public void setFODetail(String FODetail) {
        this.FODetail = FODetail;
    }
}
