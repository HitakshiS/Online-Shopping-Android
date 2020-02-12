package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.data.DataSingleton;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;

public class ItemDetail extends Activity {
    DataSingleton ourInstance = DataSingleton.getInstance();;
    private ArrayList<HomeData> list = null;
    Boolean updateHomeScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        list = ourInstance.getArray();

        final TextView quantityView = findViewById(R.id.quantity_item_text);
        ImageView imageView = findViewById(R.id.image_item_view);
        TextView nameView = findViewById(R.id.name_item_text);
        TextView priceView = findViewById(R.id.price_item_text);
        TextView stockView = findViewById(R.id.stock_item_text);
        TextView descriptionView = findViewById(R.id.description_item_text);
        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);
        TextView quantityChange = findViewById(R.id.quantity_change_item_text);
        final Button increaseButton = findViewById(R.id.increase_item_button);
        final Button decreaseButton = findViewById(R.id.decrease_item_button);

        customHeaderText.setText(R.string.item_detail_str);
        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(getApplicationContext(), CartScreen.class);
                startActivity(cartIntent);
            }
        });

        final Bundle extras = getIntent().getExtras();

        if(extras != null){
            final int position = extras.getInt("PositionProduct");

            if(position != -1){
                if(list.get(position).getmQuantity() == 0 ){
                    decreaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
                    stockView.setTextColor(Color.rgb(0, 128, 0));
                }
                else if(list.get(position).getmQuantity() < list.get(position).getmStock()){
                    decreaseButton.setBackgroundColor(Color.rgb(244, 164, 96));
                    stockView.setTextColor(Color.rgb(0, 128, 0));
                }
                else{
                    increaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
                    decreaseButton.setBackgroundColor(Color.rgb(244, 164, 96));
                    stockView.setTextColor(Color.rgb(255, 0, 0));
                }
                increaseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateHomeScreen = true;
                        list.get(position).addToQuantity();
                        int quantity = list.get(position).getmQuantity();
                        TextView quantityView = findViewById(R.id.quantity_item_text);
                        TextView quantityChange = findViewById(R.id.quantity_change_item_text);
                        TextView stockView = findViewById(R.id.stock_item_text);
                        quantityView.setText(String.format(getString(R.string.Quantity), quantity));
                        quantityChange.setText(String.valueOf(quantity));
                        int stock = list.get(position).getmStock();

                        decreaseButton.setBackgroundColor(Color.rgb(244, 164, 96));
                        if(quantity<stock){
                            list.get(position).setmStockStatus(getString(R.string.in_stock_str));
                            stockView.setText(list.get(position).getmStockStatus());
                            stockView.setTextColor(Color.rgb(0, 128, 0));
                            increaseButton.setBackgroundColor(Color.rgb(244, 164, 96));
                        }
                        else{
                            list.get(position).setmStockStatus(getString(R.string.out_of_stock_str));
                            stockView.setText(list.get(position).getmStockStatus());
                            stockView.setTextColor(Color.rgb(255, 0, 0));
                            increaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
                        }
                    }
                });
                decreaseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateHomeScreen = true;
                        list.get(position).removeFromQuantity();
                        int quantity = list.get(position).getmQuantity();
                        TextView quantityView = findViewById(R.id.quantity_item_text);
                        TextView quantityChange = findViewById(R.id.quantity_change_item_text);
                        TextView stockView = findViewById(R.id.stock_item_text);
                        quantityView.setText(String.format(getString(R.string.Quantity), quantity));
                        quantityChange.setText(String.valueOf(quantity));
                        int stock = list.get(position).getmStock();
                        if(quantity>stock){
                            increaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
                            list.get(position).setmStockStatus(getString(R.string.out_of_stock_str));
                            stockView.setTextColor(Color.rgb(255, 0, 0));
                            stockView.setText(list.get(position).getmStockStatus());
                        }
                        else{
                            increaseButton.setBackgroundColor(Color.rgb(244, 164, 96));
                            list.get(position).setmStockStatus(getString(R.string.in_stock_str));
                            stockView.setTextColor(Color.rgb(0, 128, 0));
                            stockView.setText(list.get(position).getmStockStatus());
                        }
                        if(quantity==0){
                            decreaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
                        }
                    }
                });
                quantityView.setText(String.format(getString(R.string.Quantity), list.get(position).getmQuantity()));
                quantityChange.setText(String.valueOf(list.get(position).getmQuantity()));
                nameView.setText(String.format(getString(R.string.Product), list.get(position).getmNameProduct()));
                priceView.setText(String.format(getString(R.string.Price), list.get(position).getmPrice()));
                stockView.setText(list.get(position).getmStockStatus());
                descriptionView.setText(String.format(getString(R.string.Description), list.get(position).getmDescription()));
                int resId = getApplicationContext().getResources().getIdentifier(list.get(position).getmNameOfImage(),"drawable",getApplicationContext().getPackageName());
                imageView.setImageResource(resId);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(updateHomeScreen) {
            final Bundle extras = getIntent().getExtras();
            final int position = extras.getInt("PositionProduct");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("KeyForUpdate", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
