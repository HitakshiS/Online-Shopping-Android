package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshoppingandroid.R;

public class FailureScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.failure);

        TextView customHeaderText = findViewById(R.id.header_text);
        ImageView cartImage = findViewById(R.id.cart_imageView);

        customHeaderText.setText(R.string.payment_failed_str);
        cartImage.setVisibility(View.INVISIBLE);

        Button cartNavigation = findViewById(R.id.failure_button);
        cartNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCart();
            }
        });
    }

    @Override
    public void onBackPressed() {
        navigateToCart();
    }
    public void navigateToCart() {
        Intent cartIntent = new Intent(getApplicationContext(), CartScreen.class);
        startActivity(cartIntent);
    }
}
