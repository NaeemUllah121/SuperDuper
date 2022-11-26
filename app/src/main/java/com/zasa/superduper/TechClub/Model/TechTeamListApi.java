package com.zasa.superduper.TechClub.Model;

import java.util.ArrayList;

public class TechTeamListApi {

    private String Message;
    private int Status;
    private String MemberDetails = null;
    ArrayList< TechTeamListModel > MemberList = new ArrayList < TechTeamListModel > ();

    public TechTeamListApi() {
    }

    public TechTeamListApi(String message, int status, String memberDetails, ArrayList<TechTeamListModel> memberList) {
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

    public ArrayList<TechTeamListModel> getMemberList() {
        return MemberList;
    }
}
