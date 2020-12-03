package com.chenzifeng.springdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String typeName;

    public BookType() {
    }

    public BookType(String typeName) {
        this.typeName = typeName;
    }

}
