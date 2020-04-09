package com.chenzifeng.springdemo.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userId;
    private int bookId;
    private long createAt;
    private boolean isDeal;
    private boolean hasRest;

    public Record(int userId,int bookId){
        this.userId = userId;
        this.bookId = bookId;
        this.createAt = System.currentTimeMillis();
    }

    public Record() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public long getCreateAt() {
        return createAt;
    }

    public boolean isDeal() {
        return isDeal;
    }

    public void setDeal(boolean deal) {
        isDeal = deal;
    }

    public boolean isHasRest() {
        return hasRest;
    }

    public void setHasRest(boolean hasRest) {
        this.hasRest = hasRest;
    }
}
