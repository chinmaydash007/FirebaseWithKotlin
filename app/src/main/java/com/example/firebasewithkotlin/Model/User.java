package com.example.firebasewithkotlin.Model;

import androidx.annotation.Nullable;

public class User {
    String name;
    String number;
    String uid;


    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof User){
            User user= (User) obj;
            return this.uid.equals(user.getUid());
        }
        else{
            return false;
        }
    }
}
