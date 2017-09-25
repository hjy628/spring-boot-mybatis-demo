package com.hjy.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hjy on 17-9-25.
 * 时间工具类
 */
@Slf4j
public class DateUtil {

    //获取不可预约时间
    public static int getInvalidTime() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        //当前时间加一天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        //明天的12点前都不可预约
        String invalidTime = sf.format(calendar.getTime()) + " 12:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
           return (int) (sdf.parse(invalidTime).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //获取当天日期时间戳
    public static Integer getLocalTimeStamp() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return toTimeStamp(sf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    //时间戳转字符串
    public static String toStrDate(Integer timeStamp){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.valueOf(timeStamp) * 1000;
        return sf.format(new Date(lt));
    }

    //时间戳转字符串(年月日)
    public static String toStrDate2(Integer timeStamp){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        long lt = Long.valueOf(timeStamp) * 1000;
        return sf.format(new Date(lt));
    }

    //字符串转时间戳
    public static int toTimeStamp(String timeStamp) throws ParseException {
        return (int)(defaultParseDate(timeStamp).getTime() / 1000);
    }

    //按照给定的格式化字符串格式化日期
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    //按照给定的格式化字符串解析日期
    public static Date defaultParseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }

    //按照给定的格式化字符串解析日期
    public static Date parseDate(String dateStr, String formatStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(dateStr);
    }


}
