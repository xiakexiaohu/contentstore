package com.tl.service;

import com.tl.pojo.Cart;
import com.tl.pojo.OrderIterm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: contentstore
 * @Description:订单详情
 * @Author: tuliang
 * @Date: 2019/2/28 9:38 PM
 **/
public interface OrderItermService {
    //获取用户最近对某商品一次购买记录
    OrderIterm getLastOrderItermByUidPid(String uid, String pid);

    //批量插入
    int batchSave(List<OrderIterm> orderItermList);

    //获取用户所有购物物品
    List<OrderIterm> getOrderItermByUid(String uid);
}
