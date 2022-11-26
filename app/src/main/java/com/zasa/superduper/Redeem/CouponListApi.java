package com.zasa.superduper.Redeem;

import java.util.ArrayList;

public class CouponListApi {
    private String Message;
    private int Status;
    ArrayList< CouponListModel > CouponList = new ArrayList < CouponListModel > ();
    private String CouponSingle = null;


    public CouponListApi() {
    }

    public CouponListApi(String message, int status, ArrayList<CouponListModel> couponList, String couponSingle) {
        Message = message;
        Status = status;
        CouponList = couponList;
        CouponSingle = couponSingle;
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

    public ArrayList<CouponListModel> getCouponList() {
        return CouponList;
    }

    public void setCouponList(ArrayList<CouponListModel> couponList) {
        CouponList = couponList;
    }

    public String getCouponSingle() {
        return CouponSingle;
    }

    public void setCouponSingle(String couponSingle) {
        CouponSingle = couponSingle;
    }
}
