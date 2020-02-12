package com.example.onlineshoppingandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.data.DataSingleton;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;

public class SuccessAdapter extends RecyclerView.Adapter<SuccessAdapter.SuccessHolder> {
    Context mContext;
    ArrayList<HomeData> mData = null;

    public SuccessAdapter(Context context, ArrayList<HomeData> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public SuccessAdapter.SuccessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.success_list, parent, false);

        return new SuccessAdapter.SuccessHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SuccessAdapter.SuccessHolder holder, final int position) {


        final HomeData cart = mData.get(position);
        holder.name.setText(String.format(mContext.getString(R.string.Product), cart.getmNameProduct()));
        holder.quantity.setText(String.format(mContext.getString(R.string.Quantity), cart.getmQuantity()));
        holder.amount.setText(String.format(mContext.getString(R.string.Amount), cart.getmAmount()));

        holder.price.setText(String.format(mContext.getString(R.string.Price), cart.getmPrice()));
        int resId = mContext.getResources().getIdentifier(cart.getmNameOfImage(),"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SuccessHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView amount;
        ImageView imageView;
        TextView quantity;
        TextView price;

        public SuccessHolder(@NonNull View itemView) {
            super(itemView);

            name =  itemView.findViewById(R.id.success_name);
            amount =  itemView.findViewById(R.id.success_amount);
            imageView =  itemView.findViewById(R.id.success_image);
            quantity = itemView.findViewById(R.id.success_quantity);
            price = itemView.findViewById(R.id.success_price);
        }
    }
}
