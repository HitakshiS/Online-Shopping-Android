package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.SuccessAdapter;
import com.example.onlineshoppingandroid.data.DataSingleton;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;

import static java.lang.String.*;

public class SuccessScreen extends Activity {
    SuccessAdapter mSuccessAdapter = null;
    RecyclerView mListView = null;
    DataSingleton ourInstance = DataSingleton.getInstance();
    ArrayList<HomeData> successList = new ArrayList<>();
    ArrayList<HomeData> homeList = new ArrayList<>();
    int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);
        TextView totalBill = findViewById(R.id.success_total_amount);

        customHeaderText.setText(valueOf(R.string.payment_success_text));
        cartImage.setVisibility(View.INVISIBLE);

        Button homeNavigation = findViewById(R.id.success_shopping_button);
        homeNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successList.clear();
                ourInstance.emptyLists();
                Intent homeIntent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(homeIntent);
            }
        });

        homeList = ourInstance.getArray();
        successList = ourInstance.getCartList();
        for(int i=0; i<successList.size(); i++){
            for(int j=0; j<homeList.size(); j++){
                if(successList.get(i).getmNameProduct().equals(homeList.get(j).getmNameProduct())){
                    successList.get(i).setmQuantity(homeList.get(i).getmQuantity());
                    successList.get(i).setmAmount(homeList.get(i).getmAmount());
                    totalAmount = totalAmount + homeList.get(i).getmAmount();
                }
            }
            totalBill.setText(format(getString(R.string.total_bill), totalAmount));
        }

        mListView = findViewById(R.id.mySuccessListView);
        mSuccessAdapter = new SuccessAdapter(this, successList);

        mListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if(mListView != null){
            mListView.setAdapter(mSuccessAdapter);
        }

    }

    @Override
    public void onBackPressed() {
        navigateToCart();
    }
    public void navigateToCart() {
        successList.clear();
        ourInstance.emptyLists();
//        for(int i=0; i<homeList.size(); i++){
//            for(int j=0; j<successList.size(); j++){
//                if(successList.get(j).getmNameProduct().equals(homeList.get(i).getmNameProduct())){
//                    homeList.get(i).setmQuantity(0);
//                }
//            }
//        }
        Intent cartIntent = new Intent(getApplicationContext(), CartScreen.class);
        startActivity(cartIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
