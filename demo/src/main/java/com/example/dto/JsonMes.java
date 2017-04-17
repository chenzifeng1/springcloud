package com.example.dto;

/**
 * Created by 陈子枫 on 2017/2/23.
 */
public class JsonMes {
    private int code;
    private String massage;

    public JsonMes(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public JsonMes() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
