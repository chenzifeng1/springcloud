package com.chenzifeng.springdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookTpye {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String typeName;

    public BookTpye() {
    }


}
