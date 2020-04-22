package com.chenzifeng.springdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne(optional = false)
    private Student student;

    public Record(Student student,int bookId){
        this.student = student;
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

    public Student getStudent() {
        return student;
    }


    /**
     * 为了防止Student转换为JSon对象时，出现无限包含的关系，设置@JsonBackReference
     * @param student
     */
    @JsonBackReference
    public void setStudent(Student student) {
        this.student = student;
    }
}
