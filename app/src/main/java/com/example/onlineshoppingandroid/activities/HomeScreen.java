package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.HomeAdapter;
import com.example.onlineshoppingandroid.data.DataSingleton;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;


public class HomeScreen extends Activity {
    private static final int ITEM_VALUES = 1;
    HomeAdapter mHomeAdapter = null;
    ListView mListView = null;
    DataSingleton ourInstance = DataSingleton.getInstance();
    private ArrayList<HomeData> list = new ArrayList<>();

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        ImageView cartImage = findViewById(R.id.cart_imageView);

        final HomeData data1 = new HomeData(getString(R.string.Broccoli), 50, 10, getString(R.string.in_stock_str),"broccoli", getString(R.string.broccoli_des_str));
        final HomeData data2 = new HomeData(getString(R.string.Carrot), 45, 7, getString(R.string.in_stock_str),"carrot", getString(R.string.carrot_des_str));
        final HomeData data3 = new HomeData(getString(R.string.Cucumber), 18, 15, getString(R.string.in_stock_str),"cucumber", getString(R.string.cucumber_des_str));
        final HomeData data4 = new  HomeData(getString(R.string.Capsicum), 40, 20, getString(R.string.in_stock_str),"capsicum", getString(R.string.capsicum_des_str));
        final HomeData data5 = new  HomeData(getString(R.string.Tomato), 10, 30, getString(R.string.in_stock_str),"tomato", getString(R.string.tomato_des_str));


        ourInstance.addToArray(data1);
        ourInstance.addToArray(data2);
        ourInstance.addToArray(data3);
        ourInstance.addToArray(data4);
        ourInstance.addToArray(data5);
        list = ourInstance.getArray();
        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(HomeScreen.this, CartScreen.class);
                startActivity(cartIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        mListView = findViewById(R.id.myListView);
        mHomeAdapter = new HomeAdapter(HomeScreen.this,R.layout.home_listview ,list);

        if(mListView != null){
            mListView.setAdapter(mHomeAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intentItem = new Intent(view.getContext(), ItemDetail.class);
                intentItem.putExtra("PositionProduct",Integer.valueOf(i));
                startActivityForResult(intentItem, ITEM_VALUES);
            }
        });
    }
}
