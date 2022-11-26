package com.zasa.superduper.TechClub.Model;

import java.util.ArrayList;

public class TechTopMembersListApi {

    private String Message;
    private int Status;
    private String MemberDetails = null;
    ArrayList< TechTopMembersListModel > MemberList = new ArrayList < TechTopMembersListModel > ();

    public TechTopMembersListApi() {
    }

    public TechTopMembersListApi(String message, int status, String memberDetails, ArrayList<TechTopMembersListModel> memberList) {
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

    public ArrayList<TechTopMembersListModel> getMemberList() {
        return MemberList;
    }
}
