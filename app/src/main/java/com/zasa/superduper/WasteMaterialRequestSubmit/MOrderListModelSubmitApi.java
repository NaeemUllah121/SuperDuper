package com.zasa.superduper.WasteMaterialRequestSubmit;

public class MOrderListModelSubmitApi {

    String Message;
    int Status;

    public MOrderListModelSubmitApi() {
    }

    public MOrderListModelSubmitApi(String message, int status) {
        Message = message;
        Status = status;
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
}
