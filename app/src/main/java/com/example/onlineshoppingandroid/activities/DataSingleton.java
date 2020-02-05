package com.example.onlineshoppingandroid.activities;

import java.util.ArrayList;

public class DataSingleton {
    private static DataSingleton ourInstance;
    private ArrayList<HomeData> list = null;


    private DataSingleton() {
        list = new ArrayList<>();
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
}
