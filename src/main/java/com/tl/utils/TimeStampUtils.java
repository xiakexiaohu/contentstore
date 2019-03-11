package com.tl.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/10 10:10 AM
 **/
public class TimeStampUtils {
    //将Date类型数据转成yyyy-MM-dd MM:dd:ss
    public static String dataToStrLong(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
