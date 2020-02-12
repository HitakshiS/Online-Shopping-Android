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
import com.example.onlineshoppingandroid.data.DataSingleton;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;

public class HomeAdapter extends ArrayAdapter<HomeData> {
    Context mContext;
    ArrayList<HomeData> mData = null;
    int mLayoutResourceId;
    DataSingleton ourInstance = DataSingleton.getInstance();


    public HomeAdapter(Context context, int resource, ArrayList<HomeData> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HomeHolder holder = null;

        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);

            holder = new HomeHolder();

            holder.nameView =  row.findViewById(R.id.nameTextView);
            holder.price =  row.findViewById(R.id.priceTextView);
            holder.stock =  row.findViewById(R.id.stockTextView);
            holder.quantityTextView =  row.findViewById(R.id.quantityTextView);
            holder.imageView =  row.findViewById(R.id.imageView);

            row.setTag(holder);
        }
        else{
            holder = (HomeHolder) row.getTag();
        }

        final HomeData home = mData.get(position);

        final Button increase = row.findViewById(R.id.increase_button);
        final Button decrease = row.findViewById(R.id.decrease_button);
        final TextView quantityTextView = row.findViewById(R.id.quantityTextView);
        final TextView quantityView = row.findViewById(R.id.quantity_text);
        final TextView stockTextView = row.findViewById(R.id.stockTextView);


        if(home.getmQuantity() == 0 ){
            decrease.setBackgroundColor(Color.rgb(169, 169, 169));
            stockTextView.setTextColor(Color.rgb(0, 128, 0));
        }
        else if(home.getmQuantity() < home.getmStock()){
            decrease.setBackgroundColor(Color.rgb(244, 164, 96));
            stockTextView.setTextColor(Color.rgb(0, 128, 0));
        }
        else if (home.getmQuantity() >= home.getmStock()){
            stockTextView.setText(mContext.getString(R.string.out_of_stock_str));
            increase.setBackgroundColor(Color.rgb(169, 169, 169));
            decrease.setBackgroundColor(Color.rgb(244, 164, 96));
            stockTextView.setTextColor(Color.rgb(255, 0, 0));
        }

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.addToQuantity();
                int quantity = home.getmQuantity();
                quantityTextView.setText(String.format(mContext.getString(R.string.Quantity), quantity));
                quantityView.setText(String.valueOf(quantity));
                int stock = home.getmStock();
                decrease.setBackgroundColor(Color.rgb(244, 164, 96));
                if(quantity<stock){
                    home.setmStockStatus(mContext.getString(R.string.in_stock_str));
                    stockTextView.setText(home.getmStockStatus());
                    stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    increase.setBackgroundColor(Color.rgb(244, 164, 96));
                }
                else{
                    home.setmStockStatus(mContext.getString(R.string.out_of_stock_str));
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
                quantityTextView.setText(String.format(mContext.getString(R.string.Quantity), quantity));
                quantityView.setText(String.valueOf(quantity));
                int stock = home.getmStock();
                if(quantity>stock){
                    increase.setBackgroundColor(Color.rgb(169, 169, 169));
                    home.setmStockStatus(mContext.getString(R.string.out_of_stock_str));
                    stockTextView.setText(home.getmStockStatus());
                }
                else{
                    increase.setBackgroundColor(Color.rgb(244, 164, 96));
                    home.setmStockStatus(mContext.getString(R.string.in_stock_str));
                    stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    stockTextView.setText(home.getmStockStatus());
                }
                if(quantity==0){
                    decrease.setBackgroundColor(Color.rgb(169, 169, 169));
                }
            }
        });

        String nameValue = String.format(mContext.getString(R.string.Product), home.getmNameProduct());
        String quantityValue = String.format(mContext.getString(R.string.Quantity), home.getmQuantity());
        String priceValue = String.format(mContext.getString(R.string.Price), home.getmPrice());
        holder.nameView.setText(nameValue);
        holder.price.setText(priceValue);
        quantityView.setText(String.valueOf(home.getmQuantity()));
        holder.quantityTextView.setText(quantityValue);
        int resId = mContext.getResources().getIdentifier(home.getmNameOfImage(),"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);
        return row;
    }

    private static class HomeHolder {
        TextView nameView;
        TextView price;
        TextView stock;
        TextView quantityTextView;
        ImageView imageView;
    }
}
