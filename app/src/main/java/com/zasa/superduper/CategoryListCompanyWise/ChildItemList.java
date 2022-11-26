package com.zasa.superduper.CategoryListCompanyWise;

public class ChildItemList {/////Item List (Comany & Category Wise)
    private String Client_Code;
    private String Company_Code;
    private int Item_Type_Code;
    private String Item_Code;
    private String Item_Name;
    private int Item_Code_Level;
    private int Item_Category_Code;
    private int Item_Sub_Category_Code;
    private int Item_Unit_Code;
    private String Item_Unit_Name = null;
    private String Item_Packing_Code = null;
    private int Item_Unit_PerPack;
    private int Item_Min_Stock_Qty;
    private int Item_Max_Stock_Qty;
    private String Item_Manufacturer_Code = null;
    private String Item_Part_Number = null;
    private String Item_Bar_Code;
    private int Item_IsActive;
    private float Item_Sale_Price;
    private float Item_Purchase_Price;
    private int Item_IsNon_Inventory;
    private int Item_IsSale;
    private int Item_IsPurchase;
    private int Item_IsProduction;
    private int Item_IsBatch_Required;
    private int Item_IsExpiry_Required;
    private String Item_IsFIFO = null;
    private String Item_IsLIFO = null;
    private int Item_IsNegitive_Quantity;
    private String Coa_Code_Purchase = null;
    private String Coa_Code_PurchaseReturn = null;
    private String Coa_Code_Sale = null;
    private String Coa_Code_SaleReturn = null;
    private String Coa_Code_DiscountOnSale = null;
    private String Coa_Code_DiscountOnPurchase = null;
    private String Coa_Code_STax = null;
    private int Item_Barcode_IsPrintable;
    private int Item_Reorder_Level;
    private int IsRunning_Item;
    private String Created_By;
    private String Created_Date_Time;
    private String Edited_By;
    private String Edited_Date_Time;
    private String Coa_Code_COGS = null;
    private float Item_Total_Plates;
    private float Item_Ampere;
    private float Item_Weight;
    private float Item_Width;
    private float Item_Height;
    private float Item_Length;
    private String Rack_Code = null;
    private String Item_Warranty = null;
    private int Item_Costing;
    private String Item_Image;
    private int  Item_Pack_Calculation_IsEnabled;
    private String Item_SKU = null;
    private String Item_HSCode;
    private String Inches_PerKG = null;
    private String LB_Discount_Percentage = null;
    private String Item_Description = null;

    public ChildItemList() {
    }

    public ChildItemList(String client_Code, String company_Code, int item_Type_Code, String item_Code, String item_Name, int item_Code_Level, int item_Category_Code, int item_Sub_Category_Code, int item_Unit_Code, String item_Unit_Name, String item_Packing_Code, int item_Unit_PerPack, int item_Min_Stock_Qty, int item_Max_Stock_Qty, String item_Manufacturer_Code, String item_Part_Number, String item_Bar_Code, int item_IsActive, float item_Sale_Price, float item_Purchase_Price, int item_IsNon_Inventory, int item_IsSale, int item_IsPurchase, int item_IsProduction, int item_IsBatch_Required, int item_IsExpiry_Required, String item_IsFIFO, String item_IsLIFO, int item_IsNegitive_Quantity, String coa_Code_Purchase, String coa_Code_PurchaseReturn, String coa_Code_Sale, String coa_Code_SaleReturn, String coa_Code_DiscountOnSale, String coa_Code_DiscountOnPurchase, String coa_Code_STax, int item_Barcode_IsPrintable, int item_Reorder_Level, int isRunning_Item, String created_By, String created_Date_Time, String edited_By, String edited_Date_Time, String coa_Code_COGS, float item_Total_Plates, float item_Ampere, float item_Weight, float item_Width, float item_Height, float item_Length, String rack_Code, String item_Warranty, int item_Costing, String item_Image, int item_Pack_Calculation_IsEnabled, String item_SKU, String item_HSCode, String inches_PerKG, String LB_Discount_Percentage, String item_Description) {
        Client_Code = client_Code;
        Company_Code = company_Code;
        Item_Type_Code = item_Type_Code;
        Item_Code = item_Code;
        Item_Name = item_Name;
        Item_Code_Level = item_Code_Level;
        Item_Category_Code = item_Category_Code;
        Item_Sub_Category_Code = item_Sub_Category_Code;
        Item_Unit_Code = item_Unit_Code;
        Item_Unit_Name = item_Unit_Name;
        Item_Packing_Code = item_Packing_Code;
        Item_Unit_PerPack = item_Unit_PerPack;
        Item_Min_Stock_Qty = item_Min_Stock_Qty;
        Item_Max_Stock_Qty = item_Max_Stock_Qty;
        Item_Manufacturer_Code = item_Manufacturer_Code;
        Item_Part_Number = item_Part_Number;
        Item_Bar_Code = item_Bar_Code;
        Item_IsActive = item_IsActive;
        Item_Sale_Price = item_Sale_Price;
        Item_Purchase_Price = item_Purchase_Price;
        Item_IsNon_Inventory = item_IsNon_Inventory;
        Item_IsSale = item_IsSale;
        Item_IsPurchase = item_IsPurchase;
        Item_IsProduction = item_IsProduction;
        Item_IsBatch_Required = item_IsBatch_Required;
        Item_IsExpiry_Required = item_IsExpiry_Required;
        Item_IsFIFO = item_IsFIFO;
        Item_IsLIFO = item_IsLIFO;
        Item_IsNegitive_Quantity = item_IsNegitive_Quantity;
        Coa_Code_Purchase = coa_Code_Purchase;
        Coa_Code_PurchaseReturn = coa_Code_PurchaseReturn;
        Coa_Code_Sale = coa_Code_Sale;
        Coa_Code_SaleReturn = coa_Code_SaleReturn;
        Coa_Code_DiscountOnSale = coa_Code_DiscountOnSale;
        Coa_Code_DiscountOnPurchase = coa_Code_DiscountOnPurchase;
        Coa_Code_STax = coa_Code_STax;
        Item_Barcode_IsPrintable = item_Barcode_IsPrintable;
        Item_Reorder_Level = item_Reorder_Level;
        IsRunning_Item = isRunning_Item;
        Created_By = created_By;
        Created_Date_Time = created_Date_Time;
        Edited_By = edited_By;
        Edited_Date_Time = edited_Date_Time;
        Coa_Code_COGS = coa_Code_COGS;
        Item_Total_Plates = item_Total_Plates;
        Item_Ampere = item_Ampere;
        Item_Weight = item_Weight;
        Item_Width = item_Width;
        Item_Height = item_Height;
        Item_Length = item_Length;
        Rack_Code = rack_Code;
        Item_Warranty = item_Warranty;
        Item_Costing = item_Costing;
        Item_Image = item_Image;
        Item_Pack_Calculation_IsEnabled = item_Pack_Calculation_IsEnabled;
        Item_SKU = item_SKU;
        Item_HSCode = item_HSCode;
        Inches_PerKG = inches_PerKG;
        this.LB_Discount_Percentage = LB_Discount_Percentage;
        Item_Description = item_Description;
    }

    public String getClient_Code() {
        return Client_Code;
    }

    public void setClient_Code(String client_Code) {
        Client_Code = client_Code;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public void setCompany_Code(String company_Code) {
        Company_Code = company_Code;
    }

    public int getItem_Type_Code() {
        return Item_Type_Code;
    }

    public void setItem_Type_Code(int item_Type_Code) {
        Item_Type_Code = item_Type_Code;
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

    public int getItem_Code_Level() {
        return Item_Code_Level;
    }

    public void setItem_Code_Level(int item_Code_Level) {
        Item_Code_Level = item_Code_Level;
    }

    public int getItem_Category_Code() {
        return Item_Category_Code;
    }

    public void setItem_Category_Code(int item_Category_Code) {
        Item_Category_Code = item_Category_Code;
    }

    public int getItem_Sub_Category_Code() {
        return Item_Sub_Category_Code;
    }

    public void setItem_Sub_Category_Code(int item_Sub_Category_Code) {
        Item_Sub_Category_Code = item_Sub_Category_Code;
    }

    public int getItem_Unit_Code() {
        return Item_Unit_Code;
    }

    public void setItem_Unit_Code(int item_Unit_Code) {
        Item_Unit_Code = item_Unit_Code;
    }

    public String getItem_Unit_Name() {
        return Item_Unit_Name;
    }

    public void setItem_Unit_Name(String item_Unit_Name) {
        Item_Unit_Name = item_Unit_Name;
    }

    public String getItem_Packing_Code() {
        return Item_Packing_Code;
    }

    public void setItem_Packing_Code(String item_Packing_Code) {
        Item_Packing_Code = item_Packing_Code;
    }

    public int getItem_Unit_PerPack() {
        return Item_Unit_PerPack;
    }

    public void setItem_Unit_PerPack(int item_Unit_PerPack) {
        Item_Unit_PerPack = item_Unit_PerPack;
    }

    public int getItem_Min_Stock_Qty() {
        return Item_Min_Stock_Qty;
    }

    public void setItem_Min_Stock_Qty(int item_Min_Stock_Qty) {
        Item_Min_Stock_Qty = item_Min_Stock_Qty;
    }

    public int getItem_Max_Stock_Qty() {
        return Item_Max_Stock_Qty;
    }

    public void setItem_Max_Stock_Qty(int item_Max_Stock_Qty) {
        Item_Max_Stock_Qty = item_Max_Stock_Qty;
    }

    public String getItem_Manufacturer_Code() {
        return Item_Manufacturer_Code;
    }

    public void setItem_Manufacturer_Code(String item_Manufacturer_Code) {
        Item_Manufacturer_Code = item_Manufacturer_Code;
    }

    public String getItem_Part_Number() {
        return Item_Part_Number;
    }

    public void setItem_Part_Number(String item_Part_Number) {
        Item_Part_Number = item_Part_Number;
    }

    public String getItem_Bar_Code() {
        return Item_Bar_Code;
    }

    public void setItem_Bar_Code(String item_Bar_Code) {
        Item_Bar_Code = item_Bar_Code;
    }

    public int getItem_IsActive() {
        return Item_IsActive;
    }

    public void setItem_IsActive(int item_IsActive) {
        Item_IsActive = item_IsActive;
    }

    public float getItem_Sale_Price() {
        return Item_Sale_Price;
    }

    public void setItem_Sale_Price(float item_Sale_Price) {
        Item_Sale_Price = item_Sale_Price;
    }

    public float getItem_Purchase_Price() {
        return Item_Purchase_Price;
    }

    public void setItem_Purchase_Price(float item_Purchase_Price) {
        Item_Purchase_Price = item_Purchase_Price;
    }

    public int getItem_IsNon_Inventory() {
        return Item_IsNon_Inventory;
    }

    public void setItem_IsNon_Inventory(int item_IsNon_Inventory) {
        Item_IsNon_Inventory = item_IsNon_Inventory;
    }

    public int getItem_IsSale() {
        return Item_IsSale;
    }

    public void setItem_IsSale(int item_IsSale) {
        Item_IsSale = item_IsSale;
    }

    public int getItem_IsPurchase() {
        return Item_IsPurchase;
    }

    public void setItem_IsPurchase(int item_IsPurchase) {
        Item_IsPurchase = item_IsPurchase;
    }

    public int getItem_IsProduction() {
        return Item_IsProduction;
    }

    public void setItem_IsProduction(int item_IsProduction) {
        Item_IsProduction = item_IsProduction;
    }

    public int getItem_IsBatch_Required() {
        return Item_IsBatch_Required;
    }

    public void setItem_IsBatch_Required(int item_IsBatch_Required) {
        Item_IsBatch_Required = item_IsBatch_Required;
    }

    public int getItem_IsExpiry_Required() {
        return Item_IsExpiry_Required;
    }

    public void setItem_IsExpiry_Required(int item_IsExpiry_Required) {
        Item_IsExpiry_Required = item_IsExpiry_Required;
    }

    public String getItem_IsFIFO() {
        return Item_IsFIFO;
    }

    public void setItem_IsFIFO(String item_IsFIFO) {
        Item_IsFIFO = item_IsFIFO;
    }

    public String getItem_IsLIFO() {
        return Item_IsLIFO;
    }

    public void setItem_IsLIFO(String item_IsLIFO) {
        Item_IsLIFO = item_IsLIFO;
    }

    public int getItem_IsNegitive_Quantity() {
        return Item_IsNegitive_Quantity;
    }

    public void setItem_IsNegitive_Quantity(int item_IsNegitive_Quantity) {
        Item_IsNegitive_Quantity = item_IsNegitive_Quantity;
    }

    public String getCoa_Code_Purchase() {
        return Coa_Code_Purchase;
    }

    public void setCoa_Code_Purchase(String coa_Code_Purchase) {
        Coa_Code_Purchase = coa_Code_Purchase;
    }

    public String getCoa_Code_PurchaseReturn() {
        return Coa_Code_PurchaseReturn;
    }

    public void setCoa_Code_PurchaseReturn(String coa_Code_PurchaseReturn) {
        Coa_Code_PurchaseReturn = coa_Code_PurchaseReturn;
    }

    public String getCoa_Code_Sale() {
        return Coa_Code_Sale;
    }

    public void setCoa_Code_Sale(String coa_Code_Sale) {
        Coa_Code_Sale = coa_Code_Sale;
    }

    public String getCoa_Code_SaleReturn() {
        return Coa_Code_SaleReturn;
    }

    public void setCoa_Code_SaleReturn(String coa_Code_SaleReturn) {
        Coa_Code_SaleReturn = coa_Code_SaleReturn;
    }

    public String getCoa_Code_DiscountOnSale() {
        return Coa_Code_DiscountOnSale;
    }

    public void setCoa_Code_DiscountOnSale(String coa_Code_DiscountOnSale) {
        Coa_Code_DiscountOnSale = coa_Code_DiscountOnSale;
    }

    public String getCoa_Code_DiscountOnPurchase() {
        return Coa_Code_DiscountOnPurchase;
    }

    public void setCoa_Code_DiscountOnPurchase(String coa_Code_DiscountOnPurchase) {
        Coa_Code_DiscountOnPurchase = coa_Code_DiscountOnPurchase;
    }

    public String getCoa_Code_STax() {
        return Coa_Code_STax;
    }

    public void setCoa_Code_STax(String coa_Code_STax) {
        Coa_Code_STax = coa_Code_STax;
    }

    public int getItem_Barcode_IsPrintable() {
        return Item_Barcode_IsPrintable;
    }

    public void setItem_Barcode_IsPrintable(int item_Barcode_IsPrintable) {
        Item_Barcode_IsPrintable = item_Barcode_IsPrintable;
    }

    public int getItem_Reorder_Level() {
        return Item_Reorder_Level;
    }

    public void setItem_Reorder_Level(int item_Reorder_Level) {
        Item_Reorder_Level = item_Reorder_Level;
    }

    public int getIsRunning_Item() {
        return IsRunning_Item;
    }

    public void setIsRunning_Item(int isRunning_Item) {
        IsRunning_Item = isRunning_Item;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public String getCreated_Date_Time() {
        return Created_Date_Time;
    }

    public void setCreated_Date_Time(String created_Date_Time) {
        Created_Date_Time = created_Date_Time;
    }

    public String getEdited_By() {
        return Edited_By;
    }

    public void setEdited_By(String edited_By) {
        Edited_By = edited_By;
    }

    public String getEdited_Date_Time() {
        return Edited_Date_Time;
    }

    public void setEdited_Date_Time(String edited_Date_Time) {
        Edited_Date_Time = edited_Date_Time;
    }

    public String getCoa_Code_COGS() {
        return Coa_Code_COGS;
    }

    public void setCoa_Code_COGS(String coa_Code_COGS) {
        Coa_Code_COGS = coa_Code_COGS;
    }

    public float getItem_Total_Plates() {
        return Item_Total_Plates;
    }

    public void setItem_Total_Plates(float item_Total_Plates) {
        Item_Total_Plates = item_Total_Plates;
    }

    public float getItem_Ampere() {
        return Item_Ampere;
    }

    public void setItem_Ampere(float item_Ampere) {
        Item_Ampere = item_Ampere;
    }

    public float getItem_Weight() {
        return Item_Weight;
    }

    public void setItem_Weight(float item_Weight) {
        Item_Weight = item_Weight;
    }

    public float getItem_Width() {
        return Item_Width;
    }

    public void setItem_Width(float item_Width) {
        Item_Width = item_Width;
    }

    public float getItem_Height() {
        return Item_Height;
    }

    public void setItem_Height(float item_Height) {
        Item_Height = item_Height;
    }

    public float getItem_Length() {
        return Item_Length;
    }

    public void setItem_Length(float item_Length) {
        Item_Length = item_Length;
    }

    public String getRack_Code() {
        return Rack_Code;
    }

    public void setRack_Code(String rack_Code) {
        Rack_Code = rack_Code;
    }

    public String getItem_Warranty() {
        return Item_Warranty;
    }

    public void setItem_Warranty(String item_Warranty) {
        Item_Warranty = item_Warranty;
    }

    public int getItem_Costing() {
        return Item_Costing;
    }

    public void setItem_Costing(int item_Costing) {
        Item_Costing = item_Costing;
    }

    public String getItem_Image() {
        return Item_Image;
    }

    public void setItem_Image(String item_Image) {
        Item_Image = item_Image;
    }

    public int getItem_Pack_Calculation_IsEnabled() {
        return Item_Pack_Calculation_IsEnabled;
    }

    public void setItem_Pack_Calculation_IsEnabled(int item_Pack_Calculation_IsEnabled) {
        Item_Pack_Calculation_IsEnabled = item_Pack_Calculation_IsEnabled;
    }

    public String getItem_SKU() {
        return Item_SKU;
    }

    public void setItem_SKU(String item_SKU) {
        Item_SKU = item_SKU;
    }

    public String getItem_HSCode() {
        return Item_HSCode;
    }

    public void setItem_HSCode(String item_HSCode) {
        Item_HSCode = item_HSCode;
    }

    public String getInches_PerKG() {
        return Inches_PerKG;
    }

    public void setInches_PerKG(String inches_PerKG) {
        Inches_PerKG = inches_PerKG;
    }

    public String getLB_Discount_Percentage() {
        return LB_Discount_Percentage;
    }

    public void setLB_Discount_Percentage(String LB_Discount_Percentage) {
        this.LB_Discount_Percentage = LB_Discount_Percentage;
    }

    public String getItem_Description() {
        return Item_Description;
    }

    public void setItem_Description(String item_Description) {
        Item_Description = item_Description;
    }
}
