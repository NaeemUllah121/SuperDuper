package com.zasa.superduper.Fragments;

public class ProfessionListModel {

    private int Member_Profession;
    private String Member_Profession_Title;

    public ProfessionListModel() {
    }

    public ProfessionListModel(int member_Profession, String member_Profession_Title) {
        Member_Profession = member_Profession;
        Member_Profession_Title = member_Profession_Title;
    }

    public int getMember_Profession() {
        return Member_Profession;
    }

    public String getMember_Profession_Title() {
        return Member_Profession_Title;
    }
}
