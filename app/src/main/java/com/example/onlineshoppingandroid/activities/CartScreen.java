package com.example.onlineshoppingandroid.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshoppingandroid.R;
import com.example.onlineshoppingandroid.adapters.CartAdapter;
import com.example.onlineshoppingandroid.data.DataSingleton;
import com.example.onlineshoppingandroid.data.HomeData;

import java.util.ArrayList;

public class CartScreen extends Activity {

    CartAdapter mCartAdapter = null;
    RecyclerView mListView = null;
    DataSingleton ourInstance = DataSingleton.getInstance();;
    private ArrayList<HomeData> homeList = new ArrayList<>();
    private ArrayList<HomeData> cartList = new ArrayList<>();
    int totalAmountItems = 0;
    TextView totalAmount;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_screen);

        TextView customHeaderText = findViewById(R.id.header_text);
        final ImageView cartImage = findViewById(R.id.cart_imageView);
        Button payment = findViewById(R.id.payment_button);

        customHeaderText.setText(R.string.str_cart);
        cartImage.setVisibility(View.INVISIBLE);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    LayoutInflater li = LayoutInflater.from(context);
                    final View popUpView = li.inflate(R.layout.popup_dialog, null);

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(popUpView);

                    TextView headerText = popUpView.findViewById(R.id.alert_text);
                    TextView messageText = popUpView.findViewById(R.id.message_text);
                    headerText.setText(R.string.payment_info_alert_str);
                    messageText.setText(R.string.payment_alert_msg_str);
                    alertDialogBuilder
                            .setCancelable(true)
                            .setPositiveButton(R.string.payment_success_str,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            for(int i=0; i<cartList.size(); i++){
                                                HomeData successData = new HomeData(cartList.get(i).getmNameProduct(), cartList.get(i).getmPrice(), cartList.get(i).getmStock(), cartList.get(i).getmStockStatus(), cartList.get(i).getmNameOfImage(), cartList.get(i).getmDescription());
                                                ourInstance.addToCart(successData);
                                            }
                                            Intent SuccessIntent = new Intent(context, SuccessScreen.class);
                                            startActivity(SuccessIntent);
                                        }
                                    })
                            .setNegativeButton(R.string.payment_failure_str,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            Intent failureIntent = new Intent(context, FailureScreen.class);
                                            startActivity(failureIntent);
                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        homeList = ourInstance.getArray();
        int Amount  = totalCostofItems();
        totalAmount = findViewById(R.id.cart_total_amount);
        totalAmount.setText(String.format(getString(R.string.str_total_amount), Amount));

        mListView = findViewById(R.id.myCartListView);
        mCartAdapter = new CartAdapter(this, cartList);

        mListView.setLayoutManager(new LinearLayoutManager(CartScreen.this));

        if(mListView != null && homeList != null){
            mListView.setAdapter(mCartAdapter);
        }

        mCartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position, HomeData homeData) {
                showConfimationToDelete(position);
            }

            @Override
            public void onIncreaseDecreaseQuantity(int position, Boolean increase) {

                if(increase){
                    int Amount = totalAmountItems + cartList.get(position).getmPrice();
                    totalAmountItems = Amount;
                    totalAmount = findViewById(R.id.cart_total_amount);
                    totalAmount.setText(String.format(getString(R.string.cart_total_amt_str), totalAmountItems));
                }
                else{
                    int Amount = totalAmountItems - cartList.get(position).getmPrice();
                    totalAmountItems = Amount;
                    totalAmount = findViewById(R.id.cart_total_amount);
                    totalAmount.setText(String.format(getString(R.string.cart_total_amt_str), totalAmountItems));
                }
            }
        });

    }

    private void showConfimationToDelete(int i) {
        final int position = i;
        LayoutInflater li = LayoutInflater.from(context);
        final View popUpView = li.inflate(R.layout.popup_dialog, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(popUpView);

        TextView messageText = popUpView.findViewById(R.id.message_text);
        messageText.setText(String.format(getString(R.string.str_confirm_delete), cartList.get(i).getmNameProduct()));

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton(R.string.str_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                int Amount = totalAmountItems - cartList.get(position).getmAmount();
                                totalAmountItems = Amount;
                                cartList.get(position).setmQuantity(0);
                                cartList.get(position).setmAmount(0);
                                cartList.remove(position);
                                mCartAdapter.notifyDataSetChanged();
                                mCartAdapter.notifyItemRangeChanged(position, cartList.size());
                                mCartAdapter.notifyDataSetChanged();
                                totalAmount = findViewById(R.id.cart_total_amount);
                                totalAmount.setText(String.format(getString(R.string.cart_total_amt_str), totalAmountItems));
                            }
                        })
                .setNegativeButton(R.string.str_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public int totalCostofItems(){
        homeList = ourInstance.getArray();

        for(int i=0; i<homeList.size(); i++){
            if(homeList.get(i).getmQuantity()> 0){
                cartList.add(homeList.get(i));
                totalAmountItems = totalAmountItems + homeList.get(i).getmAmount();
            }
        }
        return totalAmountItems;
    }

    @Override
    public void onBackPressed() {
        navigateTohome();
    }
    public void navigateTohome() {
        if(cartList.isEmpty()){
            ourInstance.emptyLists();
        }
        Intent homeIntent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(homeIntent);
    }

}
