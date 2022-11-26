package com.zasa.superduper.RegisterUser;

public class AddMemberApi {

    String Message;
    int Status;

    public AddMemberApi() {
    }

    public AddMemberApi(String message, int status) {
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
