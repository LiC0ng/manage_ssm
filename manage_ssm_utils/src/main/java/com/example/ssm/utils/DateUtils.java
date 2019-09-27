package com.example.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转换成字符串
     * @param date 要转换的日期
     * @param pattern 要转换的日期的格式
     * @return 返回日期的字符串
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 字符串转换成日期
     * @param dateStr 要转换的日期字符串
     * @param pattern 日期格式
     * @return 返回Date
     */
    public static Date string2Date(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }
}
