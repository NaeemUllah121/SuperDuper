package com.zasa.superduper.Redeem;

public class CouponListModel {

    private String Coupon_Unique;
    private String Member_Unique;
    private String Promotion_Unique;
    private String Promotion_Description;
    private int Promotion_Type;
    private int Promotion_Claim_Type;
    private float Promotion_Discount_Per;
    private int Promotion_Discount_Amount;
    private int Promotion_Cashback_Amount;
    private int Promotion_Limit_Min;
    private int Promotion_Limit_Max;
    private String Company_Category_Code;
    private String Issued_Company_Code;
    private String Issued_Branch_Code;
    private String Coupon_IssueDate;
    private String Coupon_ExpiryDate;
    private String Coupon_ClaimDate;
    private String Coupon_RadeemDate = null;

    public CouponListModel() {
    }

    public CouponListModel(String coupon_Unique, String member_Unique, String promotion_Unique, String promotion_Description, int promotion_Type, int promotion_Claim_Type, float promotion_Discount_Per, int promotion_Discount_Amount, int promotion_Cashback_Amount, int promotion_Limit_Min, int promotion_Limit_Max, String company_Category_Code, String issued_Company_Code, String issued_Branch_Code, String coupon_IssueDate, String coupon_ExpiryDate, String coupon_ClaimDate, String coupon_RadeemDate) {
        Coupon_Unique = coupon_Unique;
        Member_Unique = member_Unique;
        Promotion_Unique = promotion_Unique;
        Promotion_Description = promotion_Description;
        Promotion_Type = promotion_Type;
        Promotion_Claim_Type = promotion_Claim_Type;
        Promotion_Discount_Per = promotion_Discount_Per;
        Promotion_Discount_Amount = promotion_Discount_Amount;
        Promotion_Cashback_Amount = promotion_Cashback_Amount;
        Promotion_Limit_Min = promotion_Limit_Min;
        Promotion_Limit_Max = promotion_Limit_Max;
        Company_Category_Code = company_Category_Code;
        Issued_Company_Code = issued_Company_Code;
        Issued_Branch_Code = issued_Branch_Code;
        Coupon_IssueDate = coupon_IssueDate;
        Coupon_ExpiryDate = coupon_ExpiryDate;
        Coupon_ClaimDate = coupon_ClaimDate;
        Coupon_RadeemDate = coupon_RadeemDate;
    }

    public String getCoupon_Unique() {
        return Coupon_Unique;
    }

    public void setCoupon_Unique(String coupon_Unique) {
        Coupon_Unique = coupon_Unique;
    }

    public String getMember_Unique() {
        return Member_Unique;
    }

    public void setMember_Unique(String member_Unique) {
        Member_Unique = member_Unique;
    }

    public String getPromotion_Unique() {
        return Promotion_Unique;
    }

    public void setPromotion_Unique(String promotion_Unique) {
        Promotion_Unique = promotion_Unique;
    }

    public String getPromotion_Description() {
        return Promotion_Description;
    }

    public void setPromotion_Description(String promotion_Description) {
        Promotion_Description = promotion_Description;
    }

    public int getPromotion_Type() {
        return Promotion_Type;
    }

    public void setPromotion_Type(int promotion_Type) {
        Promotion_Type = promotion_Type;
    }

    public int getPromotion_Claim_Type() {
        return Promotion_Claim_Type;
    }

    public void setPromotion_Claim_Type(int promotion_Claim_Type) {
        Promotion_Claim_Type = promotion_Claim_Type;
    }

    public float getPromotion_Discount_Per() {
        return Promotion_Discount_Per;
    }

    public void setPromotion_Discount_Per(float promotion_Discount_Per) {
        Promotion_Discount_Per = promotion_Discount_Per;
    }

    public int getPromotion_Discount_Amount() {
        return Promotion_Discount_Amount;
    }

    public void setPromotion_Discount_Amount(int promotion_Discount_Amount) {
        Promotion_Discount_Amount = promotion_Discount_Amount;
    }

    public int getPromotion_Cashback_Amount() {
        return Promotion_Cashback_Amount;
    }

    public void setPromotion_Cashback_Amount(int promotion_Cashback_Amount) {
        Promotion_Cashback_Amount = promotion_Cashback_Amount;
    }

    public int getPromotion_Limit_Min() {
        return Promotion_Limit_Min;
    }

    public void setPromotion_Limit_Min(int promotion_Limit_Min) {
        Promotion_Limit_Min = promotion_Limit_Min;
    }

    public int getPromotion_Limit_Max() {
        return Promotion_Limit_Max;
    }

    public void setPromotion_Limit_Max(int promotion_Limit_Max) {
        Promotion_Limit_Max = promotion_Limit_Max;
    }

    public String getCompany_Category_Code() {
        return Company_Category_Code;
    }

    public void setCompany_Category_Code(String company_Category_Code) {
        Company_Category_Code = company_Category_Code;
    }

    public String getIssued_Company_Code() {
        return Issued_Company_Code;
    }

    public void setIssued_Company_Code(String issued_Company_Code) {
        Issued_Company_Code = issued_Company_Code;
    }

    public String getIssued_Branch_Code() {
        return Issued_Branch_Code;
    }

    public void setIssued_Branch_Code(String issued_Branch_Code) {
        Issued_Branch_Code = issued_Branch_Code;
    }

    public String getCoupon_IssueDate() {
        return Coupon_IssueDate;
    }

    public void setCoupon_IssueDate(String coupon_IssueDate) {
        Coupon_IssueDate = coupon_IssueDate;
    }

    public String getCoupon_ExpiryDate() {
        return Coupon_ExpiryDate;
    }

    public void setCoupon_ExpiryDate(String coupon_ExpiryDate) {
        Coupon_ExpiryDate = coupon_ExpiryDate;
    }

    public String getCoupon_ClaimDate() {
        return Coupon_ClaimDate;
    }

    public void setCoupon_ClaimDate(String coupon_ClaimDate) {
        Coupon_ClaimDate = coupon_ClaimDate;
    }

    public String getCoupon_RadeemDate() {
        return Coupon_RadeemDate;
    }

    public void setCoupon_RadeemDate(String coupon_RadeemDate) {
        Coupon_RadeemDate = coupon_RadeemDate;
    }
}
