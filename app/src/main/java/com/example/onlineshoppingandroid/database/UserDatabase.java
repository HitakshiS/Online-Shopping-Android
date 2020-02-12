package com.example.onlineshoppingandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserData.db";
    public static final String USERS_TABLE_NAME = "UserTable";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_PASSWORD = "password";
    public static final String USERS_COLUMN_PHONE = "phone";
    public static final String USERS_COLUMN_ADDRESS = "address";

    public UserDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table UserTable " +
                        "(id integer, name text NOT NULL , " +
                        "email text primary key NOT NULL, " +
                        "password text NOT NULL, phone text NOT NULL, address text NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS UserTable");
        onCreate(db);
    }

    public boolean insertUsers (String name, String email, String password, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        long result = db.insert("UserTable", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getUsersData(String startEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
//        String query= "SELECT  * FROM " + USERS_TABLE_NAME +"WHERE email = ?", new String[] {startEmail};
        Cursor res =  db.rawQuery( "select * from UserTable where email = '" + startEmail + "'", null );
        return res;
    }

    public int numberOfUserRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser (Integer id, String name, String email, String password, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        db.update("UserTable", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteUser (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("UserTable",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteUserTable () {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("UserTable",
                null,
                null);
    }

    public Cursor getAllUsers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from UserTable", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
            res.moveToNext();
        }
        return res;
    }
}
