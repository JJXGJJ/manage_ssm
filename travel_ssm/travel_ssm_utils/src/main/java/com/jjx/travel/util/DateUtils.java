package com.jjx.travel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author JJ
 * @Date 2020/10/29 14:44
 * @Version 1.0
 * @Describe 数据转换帮助类
 */
public class DateUtils {

    /**
     * 日期转换为字符串格式
     * @param date 日期
     * @param patt 转换格式
     * @return
     */
    public static String date2String(Date date,String patt){
        SimpleDateFormat format = new SimpleDateFormat(patt);
        String format1 = format.format(date);
        return format1;
    }

    /**
     *  字符串格式转换为日期类型
     * @param date
     * @param patt
     * @return
     */
    public static Date string2Date(String date,String patt){
        SimpleDateFormat format = new SimpleDateFormat(patt);
        Date parse = null;
        try {
            parse = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

}
