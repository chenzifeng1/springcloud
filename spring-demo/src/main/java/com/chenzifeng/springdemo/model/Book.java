package com.chenzifeng.springdemo.model;

import com.chenzifeng.springdemo.util.BookStatusEnum;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int typeId;
    /**
     * 总数量
     */
    private int num;
    //剩余数量
    private int restNum;
    private String bookName;
    private String author;


    public Book(String name, String author, int typeId, int num) {
        this.bookName = name;
        this.author = author;
        this.typeId = typeId;
        this.num = num;
        //添加图书时，剩余数量与总数量一致
        this.restNum = num;
    }


    public int getId() {
        return id;
    }


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int tpyeId) {
        this.typeId = tpyeId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRestNum() {
        return restNum;
    }

    public void setRestNum(int restNum) {
        this.restNum = restNum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book() {
    }


}
