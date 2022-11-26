package com.zasa.superduper.Profile;

import com.zasa.superduper.Login.Zasa_MembersModel;

public class UpdateMemberApi {
    Zasa_MembersModel zasa_Members;
    private String Message;
    private int Status;

    public UpdateMemberApi() {
    }

    public UpdateMemberApi(Zasa_MembersModel zasa_Members, String message, int status) {
        this.zasa_Members = zasa_Members;
        Message = message;
        Status = status;
    }

    public Zasa_MembersModel getZasa_Members() {
        return zasa_Members;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }
}
