package com.chenzifeng.springdemo.model;

import com.chenzifeng.springdemo.util.GenderEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String schoolId; //学号
    private String marge; //专业
    private String classNum; //班级

    private long creatAt; //创建时间




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
