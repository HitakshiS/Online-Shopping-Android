package com.example.onlineshoppingandroid.data;

import java.util.ArrayList;

public class DataSingleton {
    private static DataSingleton ourInstance;
    private ArrayList<HomeData> list = null;
    private ArrayList<HomeData> cartList = null;

    private DataSingleton() {
        list = new ArrayList<>();
        cartList = new ArrayList<>();
    }

    public static DataSingleton getInstance() {

        if(ourInstance == null) {
            ourInstance = new DataSingleton();
        }
        return ourInstance;
    }
    
    public ArrayList<HomeData> getArray() {
        return this.list;
    }

    public boolean addToArray(HomeData value) {
        list.add(value);
        return true;
    }

    public ArrayList<HomeData> getCartList() {
        return this.cartList;
    }

    public boolean addToCart(HomeData value) {
        cartList.add(value);
        return true;
    }

    public boolean emptyLists(){
        cartList.clear();
        list.clear();
        return true;
    }
}
