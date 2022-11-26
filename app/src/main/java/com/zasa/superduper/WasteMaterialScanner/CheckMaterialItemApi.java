package com.zasa.superduper.WasteMaterialScanner;

public class CheckMaterialItemApi {
    private String Message;
    private int Status;
    SEMItemModel SEMItem;

    public CheckMaterialItemApi() {
    }

    public CheckMaterialItemApi(String message, int status, SEMItemModel SEMItem) {
        Message = message;
        Status = status;
        this.SEMItem = SEMItem;
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

    public SEMItemModel getSEMItem() {
        return SEMItem;
    }

    public void setSEMItem(SEMItemModel SEMItem) {
        this.SEMItem = SEMItem;
    }
}
