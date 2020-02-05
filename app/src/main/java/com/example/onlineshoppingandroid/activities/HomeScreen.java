package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.HomeAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeScreen extends Activity {
    private static final int ITEM_VALUES = 1;
    HomeAdapter mHomeAdapter = null;
    ListView mListView = null;
    DataSingleton ourInstance = DataSingleton.getInstance();;
    private ArrayList<HomeData> list = null;
    private ArrayList<CartData> cartList = new ArrayList<>();
    final HomeData data1 = new HomeData("Broccoli", 50, 10, "In Stock","broccoli", "With a shape resembling that of a cauliflower, Broccoli have clusters of small, tight flower heads. These heads turn bright green on cooking and tastes slightly bitter.");
    final HomeData data2 = new HomeData("Carrot", 45, 7, "In Stock","carrot", "A popular sweet-tasting root vegetable, Carrots are narrow and cone shaped. They have thick, fleshy, deeply colored root, which grows underground, and feathery green leaves that emerge above the ground.");
    final HomeData data3 = new HomeData("Cucumber", 18, 15, "In Stock","cucumber", "With high water content and crunchy flesh, Cucumbers have striped, light to dark green coloured skin that is edible.");
    final HomeData data4 = new  HomeData("Capsicum", 40, 20, "In Stock","capsicum", "Leaving a moderately pungent taste on the tongue, Green capsicums, also known as green peppers are bell shaped, medium-sized fruit pods. They have thick and shiny skin with a fleshy texture inside.");
    final HomeData data5 = new  HomeData("Tomato", 10, 30, "In Stock","tomato", "Tomato Hybrids are high-quality fruits compared to desi, local tomatoes. They contain numerous edible seeds and are red in colour due to lycopene, an anti-oxidant.");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        ImageView cartImage = findViewById(R.id.cart_imageView);

        ourInstance.addToArray(data1);
        ourInstance.addToArray(data2);
        ourInstance.addToArray(data3);
        ourInstance.addToArray(data4);
        ourInstance.addToArray(data5);
        list = ourInstance.getArray();
        int i;
        for(i=0; i<list.size(); i++){
            if(list.get(i).getmQuantity() != 0){
                CartData data = new CartData(list.get(i).getmNameProduct(), list.get(i).getmQuantity(), list.get(i).getmPrice(), list.get(i).getmStock(), list.get(i).getmStockStatus(), list.get(i).getmNameOfImage());
                cartList.add(data);
            }
        }

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(getApplicationContext(), CartScreen.class);
                Bundle args = new Bundle();
                args.putSerializable("cart_list",(Serializable)cartList);
                cartIntent.putExtra("BUNDLE",args);
                startActivity(cartIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mListView = findViewById(R.id.myListView);
        mHomeAdapter = new HomeAdapter(this,R.layout.home_listview ,list);

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
