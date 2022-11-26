package com.zasa.superduper.Splash;

import androidx.annotation.Keep;

@Keep
public class AppVersionApi {

    private String Message;
    private int Status;
    AppversionListModel AppversionList;

    public AppVersionApi() {
    }

    public AppVersionApi(String message, int status, AppversionListModel appversionList) {
        Message = message;
        Status = status;
        AppversionList = appversionList;
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

    public AppversionListModel getAppversionList() {
        return AppversionList;
    }

    public void setAppversionList(AppversionListModel appversionList) {
        AppversionList = appversionList;
    }
}
