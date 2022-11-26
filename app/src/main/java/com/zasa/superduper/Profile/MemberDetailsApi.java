package com.zasa.superduper.Profile;

import java.util.ArrayList;

public class MemberDetailsApi {
    private String Message;
    private int Status;
    MemberDetailsApiModel MemberDetails;
    ArrayList< MemberListApiModel > MemberList = new ArrayList < MemberListApiModel > ();

    public MemberDetailsApi() {
    }

    public MemberDetailsApi(String message, int status, MemberDetailsApiModel memberDetails, ArrayList<MemberListApiModel> memberList) {
        Message = message;
        Status = status;
        MemberDetails = memberDetails;
        MemberList = memberList;
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

    public MemberDetailsApiModel getMemberDetails() {
        return MemberDetails;
    }

    public void setMemberDetails(MemberDetailsApiModel memberDetails) {
        MemberDetails = memberDetails;
    }

    public ArrayList<MemberListApiModel> getMemberList() {
        return MemberList;
    }

    public void setMemberList(ArrayList<MemberListApiModel> memberList) {
        MemberList = memberList;
    }
}
