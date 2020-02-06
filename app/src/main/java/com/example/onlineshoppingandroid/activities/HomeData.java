package com.example.onlineshoppingandroid.activities;


public class HomeData {
    private String mNameProduct;
    private int mQuantity;
    private int mPrice;
    private int mStock;
    private String mNameOfImage;
    private String mStockStatus;
    private String mDescription;
    private int mAmount;


    public HomeData(String startNameOfProduct, int startPrice, int startStock, String startStockStatus, String startNameOfImage, String startDescription ) {
        this.mNameProduct = startNameOfProduct;
        this.mQuantity = 0;
        this.mPrice = startPrice;
        this.mStock = startStock;
        this.mStockStatus = startStockStatus;
        this.mNameOfImage = startNameOfImage;
        this.mDescription = startDescription;
    }

    public void addToQuantity(){
        if(this.mQuantity<this.mStock) {
            this.mQuantity += 1;
        }
    }

    public String getmNameProduct() {
        return mNameProduct;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 0){
            this.mQuantity -= 1;
        }
    }
    public int getmPrice() {
        return mPrice;
    }

    public int setmAmount(int quantity) {
        this.mAmount = quantity * mPrice;
        return mAmount;
    }

    public String getmStockStatus() {
        return mStockStatus;
    }
    public int getmStock() {
        return mStock;
    }

    public int getmQuantity(){
        return mQuantity;
    }

    public int getmAmount() {
        this.mAmount = mPrice * mQuantity;
        return mAmount;
    }

    public String setmStockStatus(String stockStatus) {
        this.mStockStatus = stockStatus;
        return mStockStatus;
    }

    public String getmNameOfImage() {
        return mNameOfImage;
    }

    public String getmDescription() {
        return mDescription;
    }
}
