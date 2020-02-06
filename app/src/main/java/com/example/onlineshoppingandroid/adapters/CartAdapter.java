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
import com.example.onlineshoppingandroid.activities.HomeData;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<HomeData> {
    Context mContext;
    ArrayList<HomeData> mData = null;
    int mLayoutResourceId;


    public CartAdapter(Context context, int resource, ArrayList<HomeData> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mData = data;
        this.mLayoutResourceId = resource;
    }

    @Override
    public HomeData getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CartAdapter.CartHolder holder = null;

        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);

            holder = new CartAdapter.CartHolder();

            holder.nameView =  row.findViewById(R.id.cart_name);
            holder.amount =  row.findViewById(R.id.cart_amount);
            holder.stock =  row.findViewById(R.id.cart_stock);
            holder.imageView =  row.findViewById(R.id.cart_image);

            row.setTag(holder);
        }
        else{
            holder = (CartAdapter.CartHolder) row.getTag();
        }

        final HomeData cart = mData.get(position);

        final Button increase = row.findViewById(R.id.increase_button);
        final Button decrease = row.findViewById(R.id.decrease_button);
        final TextView quantityView = row.findViewById(R.id.quantity_text);
        final TextView stockTextView = row.findViewById(R.id.cart_stock);
        //final Button deleteItem = row.findViewById(R.id.delete_item);


        if(cart.getmQuantity() == 0 ){
            decrease.setBackgroundColor(Color.rgb(169, 169, 169));
            stockTextView.setTextColor(Color.rgb(0, 128, 0));
        }
        else if(cart.getmQuantity() < cart.getmStock()){
            decrease.setBackgroundColor(Color.rgb(244, 164, 96));
            stockTextView.setTextColor(Color.rgb(0, 128, 0));
        }
        else if (cart.getmQuantity() >= cart.getmStock()){
            stockTextView.setText("Out of Stock");
            increase.setBackgroundColor(Color.rgb(169, 169, 169));
            decrease.setBackgroundColor(Color.rgb(244, 164, 96));
            stockTextView.setTextColor(Color.rgb(255, 0, 0));
        }

//        deleteItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteItem.setVisibility(View.GONE);
//            }
//        });

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addToQuantity();
                int quantity = cart.getmQuantity();
                cart.setmAmount(quantity);
                quantityView.setText(String.valueOf(quantity));
                int stock = cart.getmStock();
                decrease.setBackgroundColor(Color.rgb(244, 164, 96));
                if(quantity<stock){
                    cart.setmStockStatus("In Stock");
                    stockTextView.setText(cart.getmStockStatus());
                    stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    increase.setBackgroundColor(Color.rgb(244, 164, 96));
                }
                else{
                    cart.setmStockStatus("Out of Stock");
                    stockTextView.setText(cart.getmStockStatus());
                    stockTextView.setTextColor(Color.rgb(255, 0, 0));
                    increase.setBackgroundColor(Color.rgb(169, 169, 169));
                }
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.removeFromQuantity();
                int quantity = cart.getmQuantity();
                cart.setmAmount(quantity);
                quantityView.setText(String.valueOf(quantity));
                int stock = cart.getmStock();
                if(quantity>stock){
                    increase.setBackgroundColor(Color.rgb(169, 169, 169));
                    cart.setmStockStatus("Out of Stock");
                    stockTextView.setText(cart.getmStockStatus());
                }
                else{
                    increase.setBackgroundColor(Color.rgb(244, 164, 96));
                    cart.setmStockStatus("In Stock");
                    stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    stockTextView.setText(cart.getmStockStatus());
                }
                if(quantity==0){
                    decrease.setBackgroundColor(Color.rgb(169, 169, 169));
                }
            }
        });

        String nameValue = "Product: "+ cart.getmNameProduct();
        String priceValue = "Amount: " + cart.getmAmount();
        holder.nameView.setText(nameValue);
        holder.amount.setText(priceValue);
        quantityView.setText(String.valueOf(cart.getmQuantity()));
        int resId = mContext.getResources().getIdentifier(cart.getmNameOfImage(),"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);
        return row;
    }

    private static class CartHolder {
        TextView nameView;
        TextView amount;
        TextView stock;
        ImageView imageView;
    }
}
