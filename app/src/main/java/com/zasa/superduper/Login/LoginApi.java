package com.zasa.superduper.Login;

import androidx.annotation.Keep;

@Keep
public class LoginApi {
    private String Message;
    private int Status;
    Zasa_MembersModel zasa_Members;

    public LoginApi() {
    }

    public LoginApi(String message, int status, Zasa_MembersModel zasa_Members) {
        Message = message;
        Status = status;
        this.zasa_Members = zasa_Members;
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

    public Zasa_MembersModel getZasa_Members() {
        return zasa_Members;
    }

    public void setZasa_Members(Zasa_MembersModel zasa_Members) {
        this.zasa_Members = zasa_Members;
    }
}
