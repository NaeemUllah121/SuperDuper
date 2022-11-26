package com.zasa.superduper.SubmitGeneralOrder;

public class OderModelList {

    private String Member_Unique;
    private String Company_Code;
    private String Branch_Code;
    private String Client_Code;
    private float LB_Points;
    private float Order_Amount;
    private float Order_Discount;
    private float Order_Net_Amount;
    private int Mode_Of_Payment;
    private float Delivery_Charges;
    private String Customer_Mobile;
    private String Customer_Full_Name;
    private String Delivery_Address;
    private String Delivery_DateTime;
    private String Item_Code;
    private String Item_Name;
    private int Item_Qty;
    private float Item_Rate;
    private float Item_Amount;
    private float Item_Stax_Amount;
    private float Item_Disc_Amount;
    private float Item_Disc_Per;
    private String Item_Desc;


    public OderModelList() {
    }

    public OderModelList(String member_Unique, String company_Code, String branch_Code, String client_Code, float LB_Points, float order_Amount, float order_Discount, float order_Net_Amount, int mode_Of_Payment, float delivery_Charges, String customer_Mobile, String customer_Full_Name, String delivery_Address, String delivery_DateTime, String item_Code, String item_Name, int item_Qty, float item_Rate, float item_Amount, float item_Stax_Amount, float item_Disc_Amount, float item_Disc_Per, String item_Desc) {
        Member_Unique = member_Unique;
        Company_Code = company_Code;
        Branch_Code = branch_Code;
        Client_Code = client_Code;
        this.LB_Points = LB_Points;
        Order_Amount = order_Amount;
        Order_Discount = order_Discount;
        Order_Net_Amount = order_Net_Amount;
        Mode_Of_Payment = mode_Of_Payment;
        Delivery_Charges = delivery_Charges;
        Customer_Mobile = customer_Mobile;
        Customer_Full_Name = customer_Full_Name;
        Delivery_Address = delivery_Address;
        Delivery_DateTime = delivery_DateTime;
        Item_Code = item_Code;
        Item_Name = item_Name;
        Item_Qty = item_Qty;
        Item_Rate = item_Rate;
        Item_Amount = item_Amount;
        Item_Stax_Amount = item_Stax_Amount;
        Item_Disc_Amount = item_Disc_Amount;
        Item_Disc_Per = item_Disc_Per;
        Item_Desc = item_Desc;
    }

    public String getMember_Unique() {
        return Member_Unique;
    }

    public void setMember_Unique(String member_Unique) {
        Member_Unique = member_Unique;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public void setCompany_Code(String company_Code) {
        Company_Code = company_Code;
    }

    public String getBranch_Code() {
        return Branch_Code;
    }

    public void setBranch_Code(String branch_Code) {
        Branch_Code = branch_Code;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public void setClient_Code(String client_Code) {
        Client_Code = client_Code;
    }

    public float getLB_Points() {
        return LB_Points;
    }

    public void setLB_Points(float LB_Points) {
        this.LB_Points = LB_Points;
    }

    public float getOrder_Amount() {
        return Order_Amount;
    }

    public void setOrder_Amount(float order_Amount) {
        Order_Amount = order_Amount;
    }

    public float getOrder_Discount() {
        return Order_Discount;
    }

    public void setOrder_Discount(float order_Discount) {
        Order_Discount = order_Discount;
    }

    public float getOrder_Net_Amount() {
        return Order_Net_Amount;
    }

    public void setOrder_Net_Amount(float order_Net_Amount) {
        Order_Net_Amount = order_Net_Amount;
    }

    public int getMode_Of_Payment() {
        return Mode_Of_Payment;
    }

    public void setMode_Of_Payment(int mode_Of_Payment) {
        Mode_Of_Payment = mode_Of_Payment;
    }

    public float getDelivery_Charges() {
        return Delivery_Charges;
    }

    public void setDelivery_Charges(float delivery_Charges) {
        Delivery_Charges = delivery_Charges;
    }

    public String getCustomer_Mobile() {
        return Customer_Mobile;
    }

    public void setCustomer_Mobile(String customer_Mobile) {
        Customer_Mobile = customer_Mobile;
    }

    public String getCustomer_Full_Name() {
        return Customer_Full_Name;
    }

    public void setCustomer_Full_Name(String customer_Full_Name) {
        Customer_Full_Name = customer_Full_Name;
    }

    public String getDelivery_Address() {
        return Delivery_Address;
    }

    public void setDelivery_Address(String delivery_Address) {
        Delivery_Address = delivery_Address;
    }

    public String getDelivery_DateTime() {
        return Delivery_DateTime;
    }

    public void setDelivery_DateTime(String delivery_DateTime) {
        Delivery_DateTime = delivery_DateTime;
    }

    public String getItem_Code() {
        return Item_Code;
    }

    public void setItem_Code(String item_Code) {
        Item_Code = item_Code;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public int getItem_Qty() {
        return Item_Qty;
    }

    public void setItem_Qty(int item_Qty) {
        Item_Qty = item_Qty;
    }

    public float getItem_Rate() {
        return Item_Rate;
    }

    public void setItem_Rate(float item_Rate) {
        Item_Rate = item_Rate;
    }

    public float getItem_Amount() {
        return Item_Amount;
    }

    public void setItem_Amount(float item_Amount) {
        Item_Amount = item_Amount;
    }

    public float getItem_Stax_Amount() {
        return Item_Stax_Amount;
    }

    public void setItem_Stax_Amount(float item_Stax_Amount) {
        Item_Stax_Amount = item_Stax_Amount;
    }

    public float getItem_Disc_Amount() {
        return Item_Disc_Amount;
    }

    public void setItem_Disc_Amount(float item_Disc_Amount) {
        Item_Disc_Amount = item_Disc_Amount;
    }

    public float getItem_Disc_Per() {
        return Item_Disc_Per;
    }

    public void setItem_Disc_Per(float item_Disc_Per) {
        Item_Disc_Per = item_Disc_Per;
    }

    public String getItem_Desc() {
        return Item_Desc;
    }

    public void setItem_Desc(String item_Desc) {
        Item_Desc = item_Desc;
    }


    @Override
    public String toString() {
        return "OderModelList{" +
                "Member_Unique='" + Member_Unique + '\'' +
                ", Company_Code='" + Company_Code + '\'' +
                ", Branch_Code='" + Branch_Code + '\'' +
                ", Client_Code='" + Client_Code + '\'' +
                ", LB_Points=" + LB_Points +
                ", Order_Amount=" + Order_Amount +
                ", Order_Discount=" + Order_Discount +
                ", Order_Net_Amount=" + Order_Net_Amount +
                ", Mode_Of_Payment=" + Mode_Of_Payment +
                ", Delivery_Charges=" + Delivery_Charges +
                ", Customer_Mobile='" + Customer_Mobile + '\'' +
                ", Customer_Full_Name='" + Customer_Full_Name + '\'' +
                ", Delivery_Address='" + Delivery_Address + '\'' +
                ", Delivery_DateTime='" + Delivery_DateTime + '\'' +
                ", Item_Code='" + Item_Code + '\'' +
                ", Item_Name='" + Item_Name + '\'' +
                ", Item_Qty=" + Item_Qty +
                ", Item_Rate=" + Item_Rate +
                ", Item_Amount=" + Item_Amount +
                ", Item_Stax_Amount=" + Item_Stax_Amount +
                ", Item_Disc_Amount=" + Item_Disc_Amount +
                ", Item_Disc_Per=" + Item_Disc_Per +
                ", Item_Desc='" + Item_Desc + '\'' +
                '}';
    }
}
