package com.zasa.superduper.Home;

import androidx.annotation.Keep;

@Keep
public class SliderApiModel {
    private String App_ID;
    private int Slider_ID;
    private String Slider_Title;
    private String Slider_Link;
    private String Slider_Image;

    public SliderApiModel() {
    }

    public SliderApiModel(String app_ID, int slider_ID, String slider_Title, String slider_Link, String slider_Image) {
        App_ID = app_ID;
        Slider_ID = slider_ID;
        Slider_Title = slider_Title;
        Slider_Link = slider_Link;
        Slider_Image = slider_Image;
    }

    public String getApp_ID() {
        return App_ID;
    }

    public void setApp_ID(String app_ID) {
        App_ID = app_ID;
    }

    public int getSlider_ID() {
        return Slider_ID;
    }

    public void setSlider_ID(int slider_ID) {
        Slider_ID = slider_ID;
    }

    public String getSlider_Title() {
        return Slider_Title;
    }

    public void setSlider_Title(String slider_Title) {
        Slider_Title = slider_Title;
    }

    public String getSlider_Link() {
        return Slider_Link;
    }

    public void setSlider_Link(String slider_Link) {
        Slider_Link = slider_Link;
    }

    public String getSlider_Image() {
        return Slider_Image;
    }

    public void setSlider_Image(String slider_Image) {
        Slider_Image = slider_Image;
    }
}
