package com.example.onlineshoppingandroid.data;

import java.util.ArrayList;

public class UserSingleton {
    private static UserSingleton ourInstance;
    private ArrayList<UserData> userList = null;

    private UserSingleton() {
        userList = new ArrayList<>();
    }

    public static UserSingleton getInstance() {

        if(ourInstance == null) {
            ourInstance = new UserSingleton();
        }
        return ourInstance;
    }

    public ArrayList<UserData> getUserArray() {
        return this.userList;
    }

    public boolean addToUserArray(UserData value) {
        userList.add(value);
        return true;
    }

    public boolean emptyUserList(){
        userList.clear();
        return true;
    }
}
