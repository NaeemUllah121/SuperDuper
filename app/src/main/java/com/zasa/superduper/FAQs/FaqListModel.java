package com.zasa.superduper.FAQs;

public class FaqListModel {
    private int FAQ_Id;
    private String FAQ_Title;
    private String FAQ_Details;

    public FaqListModel() {
    }

    public FaqListModel(int FAQ_Id, String FAQ_Title, String FAQ_Details) {
        this.FAQ_Id = FAQ_Id;
        this.FAQ_Title = FAQ_Title;
        this.FAQ_Details = FAQ_Details;
    }

    public int getFAQ_Id() {
        return FAQ_Id;
    }

    public String getFAQ_Title() {
        return FAQ_Title;
    }

    public String getFAQ_Details() {
        return FAQ_Details;
    }
}
