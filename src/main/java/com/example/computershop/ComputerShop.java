package com.example.computershop;

abstract public class ComputerShop {
    private String ShopName = "Компьютерный магазин";

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }
}
