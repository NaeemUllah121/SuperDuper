package com.zasa.superduper.Cart;

public class CartListModel {
    String id,itemName, itemUnit, itemPrice, ItemQty, itemDes,itemImg, itemCode,companyCode,clientCode,itemOldPrice,itemAmount,itemNetAmount;

    public CartListModel() {
    }

    public CartListModel(String id, String itemName, String itemUnit, String itemPrice, String itemQty, String itemDes, String itemImg, String itemCode, String companyCode, String clientCode, String itemOldPrice, String itemAmount, String itemNetAmount) {
        this.id = id;
        this.itemName = itemName;
        this.itemUnit = itemUnit;
        this.itemPrice = itemPrice;
        ItemQty = itemQty;
        this.itemDes = itemDes;
        this.itemImg = itemImg;
        this.itemCode = itemCode;
        this.companyCode = companyCode;
        this.clientCode = clientCode;
        this.itemOldPrice = itemOldPrice;
        this.itemAmount = itemAmount;
        this.itemNetAmount = itemNetAmount;
    }

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemQty() {
        return ItemQty;
    }

    public String getItemDes() {
        return itemDes;
    }

    public String getItemImg() {
        return itemImg;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public String getItemOldPrice() {
        return itemOldPrice;
    }

    public String getItemAmount() {
        return itemAmount;
    }

    public String getItemNetAmount() {
        return itemNetAmount;
    }
}