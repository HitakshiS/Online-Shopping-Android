package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshoppingandroid.R;

public class ItemDetail extends Activity {
    HomeScreen.Home homeData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        ImageView imageView = findViewById(R.id.image_item_view);
        TextView nameView = findViewById(R.id.name_item_text);

        TextView priceView = findViewById(R.id.price_item_text);
        TextView stockView = findViewById(R.id.stock_item_text);
        TextView descriptionView = findViewById(R.id.description_item_text);
        TextView customHeaderText = findViewById(R.id.header_text);
        final Button increaseButton = findViewById(R.id.increase_item_button);
        final Button decreaseButton = findViewById(R.id.decrease_item_button);

        customHeaderText.setText("Item Detail");

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String nameValue = extras.getString("NameProduct");
            String quantityValue = extras.getString("QuantityProduct");
            String priceValue = extras.getString("PriceProduct");
            String stockValue = extras.getString("StockProduct");
            String descriptionValue = extras.getString("DescriptionProduct");
            String imageValue = extras.getString("ImageProduct");
            int position = extras.getInt("PositionProduct");
            String stockStatus;
            //final HomeScreen.Home home = homeData[position];
//            increaseButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    homeData.addToQuantity();
//                    int quantity = homeData.getmQuantity();
//                    TextView quantityView = findViewById(R.id.quantity_item_text);
//                    quantityView.setText("Quantity: " + String.valueOf(quantity));
//                }
//            });
//            decreaseButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    homeData.removeFromQuantity();
//                    int quantity = homeData.getmQuantity();
//                    TextView quantityView = findViewById(R.id.quantity_item_text);
//                    quantityView.setText("Quantity: " + String.valueOf(quantity));
//                    if(quantity==0){
//                        decreaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
//                    }
//                }
//            });

            if(nameValue != null && priceValue != null && stockValue != null && descriptionValue != null && imageValue != null){
                //int quantity = homeData.getmQuantity();
                if(Integer.valueOf(quantityValue) < Integer.valueOf(stockValue)){
                    increaseButton.setBackgroundColor(Color.rgb(244, 164, 96));
                    stockView.setTextColor(Color.rgb(0, 128, 0));
                    stockStatus = "In Stock";
                }
                else{
                    increaseButton.setBackgroundColor(Color.rgb(169, 169, 169));
                    stockView.setTextColor(Color.rgb(255, 0, 0));
                    stockStatus = "Out of stock";
                }
                nameView.setText("Product: " + nameValue);
                priceView.setText("Price: " + priceValue);
                stockView.setText(stockStatus);
                descriptionView.setText("Description: " + descriptionValue);
                int resId = getApplicationContext().getResources().getIdentifier(imageValue,"drawable",getApplicationContext().getPackageName());
                imageView.setImageResource(resId);

            }
        }
    }
}
