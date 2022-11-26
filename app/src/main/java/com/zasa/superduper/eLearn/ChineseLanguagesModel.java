package com.zasa.superduper.eLearn;

public class ChineseLanguagesModel {
    int Img;
    String title;


    public ChineseLanguagesModel() {
    }

    public ChineseLanguagesModel(int img, String title) {
        Img = img;
        this.title = title;
    }

    public int getImg() {
        return Img;
    }

    public String getTitle() {
        return title;
    }
}
