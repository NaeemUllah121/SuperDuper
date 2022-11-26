package com.zasa.superduper.TechClub.Model;

import java.util.ArrayList;

public class TechMembersListApi {

    private String Message;
    private int Status;
    private String MemberDetails = null;
    ArrayList< TechMembersListModel > MemberList = new ArrayList < TechMembersListModel > ();

    public TechMembersListApi() {
    }


    public TechMembersListApi(String message, int status, String memberDetails, ArrayList<TechMembersListModel> memberList) {
        Message = message;
        Status = status;
        MemberDetails = memberDetails;
        MemberList = memberList;
    }

    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public String getMemberDetails() {
        return MemberDetails;
    }

    public ArrayList<TechMembersListModel> getMemberList() {
        return MemberList;
    }
}
