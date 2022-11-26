package com.zasa.superduper.CompanyListCategoryWiseS;

public class Model {
    String coinsIn,coinOut,Date,serial;

    public Model(String coinsIn, String coinOut, String date, String serial) {
        this.coinsIn = coinsIn;
        this.coinOut = coinOut;
        Date = date;
        this.serial = serial;
    }

    public String getCoinsIn() {
        return coinsIn;
    }

    public void setCoinsIn(String coinsIn) {
        this.coinsIn = coinsIn;
    }

    public String getCoinOut() {
        return coinOut;
    }

    public void setCoinOut(String coinOut) {
        this.coinOut = coinOut;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
