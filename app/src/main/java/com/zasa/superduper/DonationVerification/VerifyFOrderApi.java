package com.zasa.superduper.DonationVerification;

public class VerifyFOrderApi {

    private String Message;
    private int Status;

    public VerifyFOrderApi() {
    }

    public VerifyFOrderApi(String message, int status) {
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
