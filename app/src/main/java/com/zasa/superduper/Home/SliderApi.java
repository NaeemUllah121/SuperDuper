package com.zasa.superduper.Home;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class SliderApi {
    private String Message;
    private int Status;
    ArrayList< SliderApiModel > SliderList = new ArrayList < SliderApiModel > ();

    public SliderApi() {
    }

    public SliderApi(String message, int status, ArrayList<SliderApiModel> sliderList) {
        Message = message;
        Status = status;
        SliderList = sliderList;
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

    public ArrayList<SliderApiModel> getSliderList() {
        return SliderList;
    }

    public void setSliderList(ArrayList<SliderApiModel> sliderList) {
        SliderList = sliderList;
    }
}
