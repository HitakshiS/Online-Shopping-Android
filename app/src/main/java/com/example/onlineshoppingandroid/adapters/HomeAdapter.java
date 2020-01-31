package com.example.onlineshoppingandroid.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.activities.HomeScreen;

public class HomeAdapter  extends ArrayAdapter<HomeScreen.Home> {
    Context mContext;
    HomeScreen.Home[] mData = null;
    int mLayoutResourceId;


    public HomeAdapter(Context context, int resource, HomeScreen.Home[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.mData = data;
        this.mLayoutResourceId = resource;
    }

    @Override
    public HomeScreen.Home getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HomeHolder holder = null;

        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);

            holder = new HomeHolder();

            holder.nameView =  row.findViewById(R.id.nameTextView);
            holder.price =  row.findViewById(R.id.priceTextView);
            holder.stock =  row.findViewById(R.id.stockTextView);
            holder.quantityView =  row.findViewById(R.id.quantityTextView);
            holder.imageView =  row.findViewById(R.id.imageView);


            row.setTag(holder);
        }
        else{
            holder = (HomeHolder) row.getTag();
        }

        final HomeScreen.Home home = mData[position];

        final Button increase = row.findViewById(R.id.increase_button);
        final Button decrease = row.findViewById(R.id.decrease_button);
        final TextView quantityTextView = row.findViewById(R.id.quantity_text);
        final TextView stockTextView = row.findViewById(R.id.stockTextView);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.addToQuantity();
                int quantity = home.getmQuantity();
                quantityTextView.setText(String.valueOf(quantity));
                int stock = home.getmStock();
                decrease.setBackgroundColor(Color.rgb(244, 164, 96));
                if(quantity<stock){
                    home.setmStockStatus("In Stock");
                    stockTextView.setText(home.getmStockStatus());
                    stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    increase.setBackgroundColor(Color.rgb(244, 164, 96));
                }
                else{
                    home.setmStockStatus("Out of Stock");
                    stockTextView.setText(home.getmStockStatus());
                    stockTextView.setTextColor(Color.rgb(255, 0, 0));
                    increase.setBackgroundColor(Color.rgb(169, 169, 169));
                }
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.removeFromQuantity();
                int quantity = home.getmQuantity();
                quantityTextView.setText(String.valueOf(quantity));
                int stock = home.getmStock();
                if(quantity>stock){
                    increase.setBackgroundColor(Color.rgb(169, 169, 169));
                    home.setmStockStatus("Out of Stock");
                    stockTextView.setText(home.getmStockStatus());
                }
                else{
                    increase.setBackgroundColor(Color.rgb(244, 164, 96));
                    home.setmStockStatus("In Stock");
                    stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    stockTextView.setText(home.getmStockStatus());
                }
                if(quantity==0){
                    decrease.setBackgroundColor(Color.rgb(169, 169, 169));
                }
            }
        });

        String nameValue = "Product: "+ home.mNameProduct;
        String quantityValue = "Quantity: " + home.getmQuantity();
        String priceValue = "Price: " + home.mPrice;
        holder.nameView.setText(nameValue);
        holder.price.setText(priceValue);
        holder.quantityView.setText(quantityValue);
        int resId = mContext.getResources().getIdentifier(home.mNameOfImage,"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);

        return row;
    }

    private static class HomeHolder {
        TextView nameView;
        TextView price;
        TextView stock;
        TextView quantityView;
        ImageView imageView;
    }
}
