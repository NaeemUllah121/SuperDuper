package com.zasa.superduper.Fragments;

public class DesignationListModel {

    private int Member_Designation;
    private String Member_Designation_Title;

    public DesignationListModel() {
    }

    public DesignationListModel(int member_Designation, String member_Designation_Title) {
        Member_Designation = member_Designation;
        Member_Designation_Title = member_Designation_Title;
    }

    public int getMember_Designation() {
        return Member_Designation;
    }

    public String getMember_Designation_Title() {
        return Member_Designation_Title;
    }
}
