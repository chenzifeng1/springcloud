package com.chenzifeng.springdemo.model;

import com.chenzifeng.springdemo.util.GenderEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //学号
    private String schoolId;
    //专业
    private String marge;
    //班级
    private String classNum;


    private long creatAt; //创建时间

    @OneToMany(mappedBy = "student")
    private Set<Book> book = new HashSet<>();


    public Student(String name, String kind, GenderEnum genderEnum) {
        super(name, kind, genderEnum);
        this.creatAt = System.currentTimeMillis();
    }

    public Student(String name, String kind, GenderEnum genderEnum, String schoolId, String marge, String classNum) {
        super(name, kind, genderEnum);
        this.schoolId = schoolId;
        this.marge = marge;
        this.classNum = classNum;
        this.creatAt = System.currentTimeMillis();
    }

    public Student(String name, String schoolId, String marge, String classNum) {
        super(name);
        this.schoolId = schoolId;
        this.marge = marge;
        this.classNum = classNum;
        this.creatAt = System.currentTimeMillis();
    }

    public Student(String schoolId, String marge, String classNum) {
        this.schoolId = schoolId;
        this.marge = marge;
        this.classNum = classNum;
        this.creatAt = System.currentTimeMillis();
    }

    public Student() {
        this.creatAt = System.currentTimeMillis();
    }

    public String getSchoolId() {
        return schoolId;

    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getMarge() {
        return marge;
    }

    public void setMarge(String marge) {
        this.marge = marge;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public int getId() {
        return id;
    }
}
