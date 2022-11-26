package com.zasa.superduper.TechClub.Model;

public class TechMemTypeListModel {

    private int Tech_Member_Type1;
    private String Tech_Member_Type_Title;

    public TechMemTypeListModel() {
    }

    public TechMemTypeListModel(int tech_Member_Type1, String tech_Member_Type_Title) {
        Tech_Member_Type1 = tech_Member_Type1;
        Tech_Member_Type_Title = tech_Member_Type_Title;
    }

    public int getTech_Member_Type1() {
        return Tech_Member_Type1;
    }

    public String getTech_Member_Type_Title() {
        return Tech_Member_Type_Title;
    }
}
