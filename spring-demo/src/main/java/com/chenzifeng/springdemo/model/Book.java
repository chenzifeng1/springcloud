package com.chenzifeng.springdemo.model;

import com.chenzifeng.springdemo.util.BookStatusEnum;

import javax.persistence.*;

@Entity
@Table(name  = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int typeId;
    private int num; //总数量
    private int restNum; //剩余数量
    private long recordId; //借出记录
    private BookStatusEnum bookStatus;
    private String bookName;
    private String author;


    public Book(String name,String author,int typeId,int num){
        this.bookName = name;
        this.author = author;
        this.typeId = typeId;
        this.num = num;
        this.bookStatus = BookStatusEnum.ON;
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

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public BookStatusEnum getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatusEnum bookStatus) {
        this.bookStatus = bookStatus;
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
