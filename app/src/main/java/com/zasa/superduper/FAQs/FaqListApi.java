package com.zasa.superduper.FAQs;

import java.util.ArrayList;

public class FaqListApi {
    private String Message;
    private int Status;
    ArrayList< FaqListModel > FaqList = new ArrayList < FaqListModel > ();

    public FaqListApi() {
    }

    public FaqListApi(String message, int status, ArrayList<FaqListModel> faqList) {
        Message = message;
        Status = status;
        FaqList = faqList;
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

    public ArrayList<FaqListModel> getFaqList() {
        return FaqList;
    }

    public void setFaqList(ArrayList<FaqListModel> faqList) {
        FaqList = faqList;
    }
}
