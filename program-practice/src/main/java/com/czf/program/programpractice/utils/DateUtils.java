package com.czf.program.programpractice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.utils
 * @ClassName: DateUtils
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/27 11:42
 * @Version: 1.0
 */
public class DateUtils {

    /**
     * 将日期转为 yyyy,MM,dd,HH,mm,ss 输出字符串
     *
     * @param date
     * @return
     */
    public static String format2Protocol(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
        return formatter.format(date);
    }


    /**
     * 获取指定时间后几天的时间
     *
     * @param dayNum       后几天  天数
     * @param specifiedDay 指定时间 字符串
     * @return 前几天的时间 字符串
     * @throws ParseException
     */
    public static String getDayAfter(int dayNum, String specifiedDay) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss").parse(specifiedDay);
        c.setTime(date);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + dayNum);
        return format2Protocol(c.getTime());
    }

}
