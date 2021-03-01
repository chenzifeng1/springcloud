package com.chenzifeng.spring.springsecurity.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.utils
 * @ClassName: JsonUtils
 * @Author: czf
 * @Description: json对象封装等
 * @Date: 2021/3/1 9:10
 * @Version: 1.0
 */
@Data
public class JsonUtils {

    public static final String SUCCESS_INFO = "success";
    public static final String STATUS = "status";
    public static final String CODE = "code";
    public static final String INFO = "info";
    public static final String FAIL_INFO = "fail";
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    /**
     * 状态
     */
    public String status;

    /**
     * 状态码
     */
    public int code;

    /**
     * 说明
     */
    public String info;


    /**
     * 成功响应
     * @param info
     * @return
     */
    public static JSONObject successReponse(String info){
        JSONObject json = defaultSuccessResponse();
        json.put(INFO,info);
        return json;
    }


    /**
     * 默认成功响应
     * @return
     */
    public static JSONObject defaultSuccessResponse(){
        JSONObject json = new JSONObject();
        json.put(STATUS,SUCCESS_INFO);
        json.put(CODE,SUCCESS_CODE);
        return json;
    }


    /**
     * 获取响应
     * @param info
     * @param status
     * @param code
     * @return
     */
    public static JSONObject getResponse(String info,String status,int code){
        JSONObject json = new JSONObject();
        json.put(INFO,info);
        json.put(STATUS,status);
        json.put(CODE,code);
        return json;
    }
}
