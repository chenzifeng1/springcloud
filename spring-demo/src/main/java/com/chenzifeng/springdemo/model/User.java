package com.chenzifeng.springdemo.model;

import com.chenzifeng.springdemo.tool.Gender;


public abstract class User {

    private String Name;
    private String kind;
    private Gender gender;

    public User(String name, String kind, Gender gender) {
        Name = name;
        this.kind = kind;
        this.gender = gender;
    }

    public User(String name) {
        Name = name;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


}
