package com.zasa.superduper.CategoryListCompanyWise;

import java.util.ArrayList;

public class CategoryItemCombineApi {

    private String Message;
    private int Status;
    ArrayList < CategoryItemCombineModel > CatItemList = new ArrayList< CategoryItemCombineModel >();

    public CategoryItemCombineApi() {
    }

    public CategoryItemCombineApi(String message, int status, ArrayList<CategoryItemCombineModel> catItemList) {
        Message = message;
        Status = status;
        CatItemList = catItemList;
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

    public ArrayList<CategoryItemCombineModel> getCatItemList() {
        return CatItemList;
    }

    public void setCatItemList(ArrayList<CategoryItemCombineModel> catItemList) {
        CatItemList = catItemList;
    }
}
