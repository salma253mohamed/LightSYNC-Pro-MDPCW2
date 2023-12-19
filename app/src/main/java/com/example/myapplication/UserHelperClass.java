package com.example.myapplication;

import java.util.List;

public class UserHelperClass {
    private String fullName;
    private String userName;
    private List<String> itemList;

    // Default constructor for Firebase
    public UserHelperClass() {
        // Default constructor required for calls to DataSnapshot.getValue(UserHelperClass.class)
    }

    public UserHelperClass(String fullName, String userName, List<String> itemList) {
        this.fullName = fullName;
        this.userName = userName;
        this.itemList = itemList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }
}
