package com.zasa.superduper.SubmitGeneralOrder;

public class SubmitOrderApi {
    String Message;
    int Status;

    public SubmitOrderApi() {
    }

    public SubmitOrderApi(String message, int status) {
        Message = message;
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }
}
