package com.zasa.superduper.WasteMaterialRequestHistory;

public class MaterialRequestHistoryModel {

    String oName,oQty,oDate,oStatus;

    public MaterialRequestHistoryModel(String oName, String oQty, String oDate, String oStatus) {
        this.oName = oName;
        this.oQty = oQty;
        this.oDate = oDate;
        this.oStatus = oStatus;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoQty() {
        return oQty;
    }

    public void setoQty(String oQty) {
        this.oQty = oQty;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }
}
