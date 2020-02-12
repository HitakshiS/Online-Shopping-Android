package com.example.onlineshoppingandroid.data;

import java.util.ArrayList;

public class UserData {

    private String mUserName;
    private String mUserEmail;
    private String mUserPassword;
    private ArrayList<String> mUserAddress;
    private String mUserNumber;

    public UserData(String startUserName, String startUserEmail, String startUserPassword, ArrayList<String> startUserAddress, String startUserNumber) {
        this.mUserName = startUserName;
        this.mUserEmail = startUserEmail;
        this.mUserPassword = startUserPassword;
        this.mUserAddress = startUserAddress;
        this.mUserNumber = startUserNumber;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public void setmUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public String getmUserPassword() {
        return mUserPassword;
    }

    public void setmUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }

    public String getmUserNumber() {
        return mUserNumber;
    }

    public void setmUserNumber(String mUserNumber) {
        this.mUserNumber = mUserNumber;
    }

    public ArrayList<String> getmUserAddress() {
        return mUserAddress;
    }

    public void setmUserAddress(ArrayList<String> mUserAddress) {
        this.mUserAddress = mUserAddress;
    }
}
