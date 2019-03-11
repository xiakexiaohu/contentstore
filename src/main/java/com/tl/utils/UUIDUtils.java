package com.tl.utils;

import java.util.UUID;

/**
 * @Program: contentstore
 * @Description: 随机生成uuid
 * @Author: tuliang
 * @Date: 2019/2/28 10:39 AM
 **/
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
