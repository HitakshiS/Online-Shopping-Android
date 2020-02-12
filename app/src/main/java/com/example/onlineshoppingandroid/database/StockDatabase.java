package com.example.onlineshoppingandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StockDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StockData.db";
    public static final String STOCK_TABLE_NAME = "StockTable";
    public static final String STOCK_COLUMN_ID = "id";
    public static final String STOCK_COLUMN_NAME = "productName";
    public static final String STOCK_COLUMN_PRICE = "productPrice";
    public static final String STOCK_COLUMN_STOCK = "productStock";
    public static final String STOCK_COLUMN_IMAGE = "productImageName";
    public static final String STOCK_COLUMN_DESCRIPTION = "productDescription";

    public StockDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table StockTable " +
                        "(id integer primary key NOT NULL, productName text NOT NULL , " +
                        "productPrice text NOT NULL, " +
                        "productStock text NOT NULL, productImageName text NOT NULL, productDescription text NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS UserTable");
        onCreate(db);
    }

    public boolean insertUsers (String name, String price, String stock, String image, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productName", name);
        contentValues.put("productPrice", price);
        contentValues.put("productStock", stock);
        contentValues.put("productImageName", image);
        contentValues.put("productDescription", description);
        long result = db.insert("StockTable", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getUsersData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from StockTable where id = '" + id + "'", null );
        return res;
    }

    public int numberOfStockRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, STOCK_TABLE_NAME);
        return numRows;
    }

    public boolean updateStockTable (Integer id, String name, String email, String password, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        db.update("StockTable", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteStock (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("StockTable",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteStockTable () {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("StockTable",
                null,
                null);
    }

    public Cursor getAllStock() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from StockTable", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(STOCK_COLUMN_NAME)));
            res.moveToNext();
        }
        return res;
    }
}