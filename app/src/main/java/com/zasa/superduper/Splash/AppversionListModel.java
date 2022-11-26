package com.zasa.superduper.Splash;

import androidx.annotation.Keep;

@Keep
public class AppversionListModel {

    private String App_ID;
    private float App_Version;
    private String Web_App_Static;
    private String Msg_Link;
    private String Msg_Flag;
    private String Msg_Title;
    private String Msg_Desc;
    private String Msg_Imag_Link;
    private int Hit_Counts;
    private String Cache_Clear_Flag;
    private String Contact_Number;
    private String Support_Email;
    private String Website_Url;
    private String Privacy_Policy_Url;
    private String Terms_Conditions_Url;
    private String Tech_Promo;
    private double Membership_Charges;

    public AppversionListModel() {
    }

    public AppversionListModel(String app_ID, float app_Version, String web_App_Static, String msg_Link, String msg_Flag, String msg_Title, String msg_Desc, String msg_Imag_Link, int hit_Counts, String cache_Clear_Flag, String contact_Number, String support_Email, String website_Url, String privacy_Policy_Url, String terms_Conditions_Url, String tech_Promo, double membership_Charges) {
        App_ID = app_ID;
        App_Version = app_Version;
        Web_App_Static = web_App_Static;
        Msg_Link = msg_Link;
        Msg_Flag = msg_Flag;
        Msg_Title = msg_Title;
        Msg_Desc = msg_Desc;
        Msg_Imag_Link = msg_Imag_Link;
        Hit_Counts = hit_Counts;
        Cache_Clear_Flag = cache_Clear_Flag;
        Contact_Number = contact_Number;
        Support_Email = support_Email;
        Website_Url = website_Url;
        Privacy_Policy_Url = privacy_Policy_Url;
        Terms_Conditions_Url = terms_Conditions_Url;
        Tech_Promo = tech_Promo;
        Membership_Charges = membership_Charges;
    }

    public String getApp_ID() {
        return App_ID;
    }

    public float getApp_Version() {
        return App_Version;
    }

    public String getWeb_App_Static() {
        return Web_App_Static;
    }

    public String getMsg_Link() {
        return Msg_Link;
    }

    public String getMsg_Flag() {
        return Msg_Flag;
    }

    public String getMsg_Title() {
        return Msg_Title;
    }

    public String getMsg_Desc() {
        return Msg_Desc;
    }

    public String getMsg_Imag_Link() {
        return Msg_Imag_Link;
    }

    public int getHit_Counts() {
        return Hit_Counts;
    }

    public String getCache_Clear_Flag() {
        return Cache_Clear_Flag;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public String getSupport_Email() {
        return Support_Email;
    }

    public String getWebsite_Url() {
        return Website_Url;
    }

    public String getPrivacy_Policy_Url() {
        return Privacy_Policy_Url;
    }

    public String getTerms_Conditions_Url() {
        return Terms_Conditions_Url;
    }

    public String getTech_Promo() {
        return Tech_Promo;
    }

    public double getMembership_Charges() {
        return Membership_Charges;
    }
}
