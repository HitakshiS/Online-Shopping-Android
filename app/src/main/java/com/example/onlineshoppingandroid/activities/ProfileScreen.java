package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshoppingandroid.R;

public class ProfileScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        final ImageView cartImage = findViewById(R.id.cart_imageView);
        Button payment = findViewById(R.id.payment_button);

        customHeaderText.setText(R.string.profile);
        cartImage.setVisibility(View.INVISIBLE);
    }
}
