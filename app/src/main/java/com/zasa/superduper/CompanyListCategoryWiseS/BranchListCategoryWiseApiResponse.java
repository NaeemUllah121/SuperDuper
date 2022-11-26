package com.zasa.superduper.CompanyListCategoryWiseS;

import java.util.ArrayList;

public class BranchListCategoryWiseApiResponse {


    private String Message;
    private int Status;
    private String BranchSingle = null;
    ArrayList< BranchListCategoryWiseApiModel > BranchList = new ArrayList < BranchListCategoryWiseApiModel > ();

    public BranchListCategoryWiseApiResponse() {
    }

    public BranchListCategoryWiseApiResponse(String message, int status, String branchSingle, ArrayList<BranchListCategoryWiseApiModel> branchList) {
        Message = message;
        Status = status;
        BranchSingle = branchSingle;
        BranchList = branchList;
    }


    public String getMessage() {
        return Message;
    }

    public int getStatus() {
        return Status;
    }

    public String getBranchSingle() {
        return BranchSingle;
    }

    public ArrayList<BranchListCategoryWiseApiModel> getBranchList() {
        return BranchList;
    }
}
