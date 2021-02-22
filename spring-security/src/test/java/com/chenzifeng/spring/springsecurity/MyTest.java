package com.chenzifeng.spring.springsecurity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity
 * @ClassName: MyTest
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/22 16:04
 * @Version: 1.0
 */
public class MyTest {

    public static void main(String[] args) {
        String time = "Wed Feb 03 18:49:24 CST 2021";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        try {
            Date date = new Date(time);
            System.out.println(sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
