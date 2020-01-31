package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.HomeAdapter;

public class HomeScreen extends Activity {
    //initial data
    private static final int ITEM_VALUES = 1;
    HomeAdapter mHomeAdapter = null;
    ListView mListView = null;
    Home[] myItemArray = new Home[]{
            new Home("Broccoli", 50, 10, "In stock","broccoli", "With a shape resembling that of a cauliflower, Broccoli have clusters of small, tight flower heads. These heads turn bright green on cooking and tastes slightly bitter."),
            new Home("Carrot", 45, 7, "In stock","carrot", "A popular sweet-tasting root vegetable, Carrots are narrow and cone shaped. They have thick, fleshy, deeply colored root, which grows underground, and feathery green leaves that emerge above the ground."),
            new Home("Cucumber", 18, 15, "In stock","cucumber", "With high water content and crunchy flesh, Cucumbers have striped, light to dark green coloured skin that is edible."),
            new Home("Capsicum", 40, 20, "In stock","capsicum", "Leaving a moderately pungent taste on the tongue, Green capsicums, also known as green peppers are bell shaped, medium-sized fruit pods. They have thick and shiny skin with a fleshy texture inside."),
            new Home("Tomato", 10, 30, "In stock","tomato", "Tomato Hybrids are high-quality fruits compared to desi, local tomatoes. They contain numerous edible seeds and are red in colour due to lycopene, an anti-oxidant."),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        mListView = findViewById(R.id.myListView);
        mHomeAdapter = new HomeAdapter(this,R.layout.home_listview ,myItemArray);

        if(mListView != null){
            mListView.setAdapter(mHomeAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intentItem = new Intent(view.getContext(), ItemDetail.class);
                intentItem.putExtra("NameProduct", String.valueOf(myItemArray[i].mNameProduct));
                intentItem.putExtra("PriceProduct", String.valueOf(myItemArray[i].mPrice));
                intentItem.putExtra("QuantityProduct", String.valueOf(myItemArray[i].mQuantity));
                intentItem.putExtra("StockProduct", String.valueOf(myItemArray[i].mStock));
                intentItem.putExtra("DescriptionProduct",String.valueOf(myItemArray[i].mDescription));
                intentItem.putExtra("ImageProduct", String.valueOf(myItemArray[i].mNameOfImage));
                //intentItem.putExtra("PositionProduct",String.valueOf(myItemArray[i]));
                startActivityForResult(intentItem, ITEM_VALUES);
            }
        });

    }

    public class Home{
        public String mNameProduct;
        public int mQuantity;
        public int mPrice;
        public int mStock;
        public String mNameOfImage;
        public String mStockStatus;
        public String mDescription;
        public Home(String startNameOfProduct, int startPrice, int startStock, String startStockStatus, String startNameOfImage, String startDescription ){

            this.mNameProduct = startNameOfProduct;
            this.mQuantity = 0;
            this.mPrice = startPrice;
            this.mStock = startStock;
            this.mStockStatus = startStockStatus;
            this.mNameOfImage = startNameOfImage;
            this.mDescription = startDescription;
        }

        public void addToQuantity(){
            if(this.mQuantity<this.mStock) {
                this.mQuantity += 1;
            }
        }

        public void removeFromQuantity(){
            if(this.mQuantity > 0){
                this.mQuantity -= 1;
            }
        }
        public int getmAmount() {
            return mPrice;
        }

        public String setmStockStatus(String stockStatus) {
            this.mStockStatus = stockStatus;
            return mStockStatus;
        }

        public String getmStockStatus() {
            return mStockStatus;
        }
        public int getmStock() {
            return mStock;
        }

        public int getmQuantity(){
            return mQuantity;
        }

        public String getmNameOfImage() {
            return mNameOfImage;
        }

        public String getmDescription() {
            return mDescription;
        }
    }

}
