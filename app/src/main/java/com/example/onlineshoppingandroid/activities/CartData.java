package com.example.onlineshoppingandroid.activities;

public class CartData {
    private String mNameProduct;
    private int mQuantity;
    private int mPrice;
    private int mStock;
    private String mNameOfImage;
    private String mStockStatus;


    public CartData(String startNameOfProduct,int startQuantity, int startPrice, int startStock, String startStockStatus, String startNameOfImage ) {
        this.mNameProduct = startNameOfProduct;
        this.mQuantity = startQuantity;
        this.mPrice = startPrice;
        this.mStock = startStock;
        this.mStockStatus = startStockStatus;
        this.mNameOfImage = startNameOfImage;
    }

    public void addToQuantity(){
        if(this.mQuantity<this.mStock) {
            this.mQuantity += 1;
        }
    }

    public String getmNameProduct() {
        return mNameProduct;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String setmStockStatus(String stockStatus) {
        this.mStockStatus = stockStatus;
        return mStockStatus;
    }

    public String getmStockStatus() {
        return mStockStatus;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 0){
            this.mQuantity -= 1;
        }
    }
    public int getmAmount() {
        return mPrice * mQuantity;
    }

    public int getmStock() {
        return mStock;
    }

    public int getmQuantity(){
        return mQuantity;
    }

    public String getmNameOfImage() {
        return mNameOfImage;
    }
}
