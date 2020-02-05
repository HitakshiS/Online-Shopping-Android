package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.CartAdapter;

import java.util.ArrayList;

public class CartScreen extends Activity {

    CartAdapter mCartAdapter = null;
    ListView mListView = null;
    DataSingleton ourInstance = DataSingleton.getInstance();;
//    private ArrayList<HomeData> list = null;
    private ArrayList<CartData> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);
        Button payment = findViewById(R.id.payment_button);

        customHeaderText.setText("Cart");
        cartImage.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<CartData> cartList = (ArrayList<CartData>) args.getSerializable("cart_list");

        mListView = findViewById(R.id.myCartListView);
        mCartAdapter = new CartAdapter(this,R.layout.cart_listview ,cartList);

        if(mListView != null){
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
