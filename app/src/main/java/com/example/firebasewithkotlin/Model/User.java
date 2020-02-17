package com.example.firebasewithkotlin.Model;

public class User {
    String age;
    String name;
    String profession;
    String uid;

    public User() {
    }

    public User(String age, String name, String profession, String uid) {
        this.age = age;
        this.name = name;
        this.profession = profession;
        this.uid = uid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
