package com.chenzifeng.springdemo.model;

import com.chenzifeng.springdemo.util.GenderEnum;


public abstract class User {

    private String Name;
    private String kind;
    private GenderEnum genderEnum;

    public User(String name, String kind, GenderEnum genderEnum) {
        Name = name;
        this.kind = kind;
        this.genderEnum = genderEnum;
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

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }


}
