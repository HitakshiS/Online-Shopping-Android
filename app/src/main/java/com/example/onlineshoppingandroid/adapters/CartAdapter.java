package com.example.onlineshoppingandroid.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    Context mContext;
    ArrayList<HomeData> mData = null;

    private OnItemClickListener onItemClickListener;

    public CartAdapter(Context context, ArrayList<HomeData> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_listview, parent, false);

        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartHolder holder, final int position) {

        final HomeData cart = mData.get(position);

        if(cart.getmQuantity() == 0 ){
            holder.decrease.setBackgroundColor(Color.rgb(169, 169, 169));
            holder.stockTextView.setTextColor(Color.rgb(0, 128, 0));
        }
        else if(cart.getmQuantity() < cart.getmStock()){
            holder.decrease.setBackgroundColor(Color.rgb(244, 164, 96));
            holder.stockTextView.setTextColor(Color.rgb(0, 128, 0));
        }
        else if (cart.getmQuantity() >= cart.getmStock()){
            holder.stockTextView.setText(mContext.getString(R.string.out_of_stock_str));
            holder.increase.setBackgroundColor(Color.rgb(169, 169, 169));
            holder.decrease.setBackgroundColor(Color.rgb(244, 164, 96));
            holder.stockTextView.setTextColor(Color.rgb(255, 0, 0));
        }

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addToQuantity();
                int quantity = cart.getmQuantity();
                cart.setmAmount(quantity);
                holder.quantityView.setText(String.valueOf(quantity));
                holder.amount.setText(String.format(mContext.getString(R.string.Amount), cart.getmAmount()));
                int stock = cart.getmStock();
                holder.decrease.setBackgroundColor(Color.rgb(244, 164, 96));
                if(quantity<stock){
                    cart.setmStockStatus(mContext.getString(R.string.in_stock_str));
                    holder.stockTextView.setText(cart.getmStockStatus());
                    holder.stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    holder.increase.setBackgroundColor(Color.rgb(244, 164, 96));
                }
                else{
                    cart.setmStockStatus(mContext.getString(R.string.out_of_stock_str));
                    holder.stockTextView.setText(cart.getmStockStatus());
                    holder.stockTextView.setTextColor(Color.rgb(255, 0, 0));
                    holder.increase.setBackgroundColor(Color.rgb(169, 169, 169));
                }
                onItemClickListener.onIncreaseDecreaseQuantity(position, true);
            }
        });
        
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.removeFromQuantity();
                int quantity = cart.getmQuantity();
                cart.setmAmount(quantity);
                holder.quantityView.setText(String.valueOf(quantity));
                holder.amount.setText(String.format(mContext.getString(R.string.Amount), cart.getmAmount()));
                int stock = cart.getmStock();
                if(quantity>stock){
                    holder.increase.setBackgroundColor(Color.rgb(169, 169, 169));
                    cart.setmStockStatus(mContext.getString(R.string.out_of_stock_str));
                    holder.stockTextView.setText(cart.getmStockStatus());
                }
                else{
                    holder.increase.setBackgroundColor(Color.rgb(244, 164, 96));
                    cart.setmStockStatus(mContext.getString(R.string.in_stock_str));
                    holder.stockTextView.setTextColor(Color.rgb(0, 128, 0));
                    holder.stockTextView.setText(cart.getmStockStatus());
                }
                if(quantity==0){
                    holder.decrease.setBackgroundColor(Color.rgb(169, 169, 169));
                }
                onItemClickListener.onIncreaseDecreaseQuantity(position, false);
            }
        });

        String nameValue = String.format(mContext.getString(R.string.Product), cart.getmNameProduct());
        String priceValue = String.format(mContext.getString(R.string.Amount), cart.getmAmount());
        holder.nameView.setText(nameValue);
        holder.amount.setText(priceValue);
        holder.quantityView.setText(String.valueOf(cart.getmQuantity()));
        int resId = mContext.getResources().getIdentifier(cart.getmNameOfImage(),"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        ImageView deleteItem;
        TextView nameView;
        TextView amount;
        ImageView imageView;

        Button increase;
        Button decrease;
        TextView quantityView;
        TextView stockTextView;

        public CartHolder(@NonNull View itemView) {
            super(itemView);

            nameView =  itemView.findViewById(R.id.cart_name);
            amount =  itemView.findViewById(R.id.cart_amount);
            imageView =  itemView.findViewById(R.id.cart_image);

            increase = itemView.findViewById(R.id.increase_button);
            decrease = itemView.findViewById(R.id.decrease_button);
            quantityView = itemView.findViewById(R.id.quantity_text);
            stockTextView = itemView.findViewById(R.id.cart_stock);
            deleteItem = itemView.findViewById(R.id.delete_item);


            deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        int adapterPosition = getAdapterPosition();

                        if(adapterPosition > RecyclerView.NO_POSITION) {
                            onItemClickListener.onDeleteClick(adapterPosition, mData.get(adapterPosition));
                        }
                    }
                }
            });

        }
    }
    
    public interface OnItemClickListener {
        void onDeleteClick(int position, HomeData homeData);
        void onIncreaseDecreaseQuantity(int position, Boolean increase);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
