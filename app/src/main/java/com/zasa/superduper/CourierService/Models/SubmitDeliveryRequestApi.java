package com.zasa.superduper.CourierService.Models;

public class SubmitDeliveryRequestApi {

    private String Message;
    private int Status;

    public SubmitDeliveryRequestApi() {
    }

    public SubmitDeliveryRequestApi(String message, int status) {
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
