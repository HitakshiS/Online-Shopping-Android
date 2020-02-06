package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.HashSet;

public class CartScreen extends Activity {

    CartAdapter mCartAdapter = null;
    ListView mListView = null;
    DataSingleton ourInstance = DataSingleton.getInstance();;
    private ArrayList<HomeData> homeList = new ArrayList<>();
    private ArrayList<HomeData> cartList = new ArrayList<>();
    int totalAmountItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);
        Button payment = findViewById(R.id.payment_button);
        TextView totalAmount = findViewById(R.id.cart_total_amount);

        customHeaderText.setText("Cart");
        cartImage.setVisibility(View.INVISIBLE);

        homeList = ourInstance.getArray();

        for(int i=0; i<homeList.size(); i++){
            if(homeList.get(i).getmQuantity()> 0){
                cartList.add(homeList.get(i));
                totalAmountItems = totalAmountItems + homeList.get(i).getmAmount();
            }
        }
        totalAmount.setText("Total Amount: " + totalAmountItems);

        mListView = findViewById(R.id.myCartListView);
        mCartAdapter = new CartAdapter(this,R.layout.cart_listview , cartList);

        if(mListView != null && homeList != null){
            mListView.setAdapter(mCartAdapter);
        }

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), cartList, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
