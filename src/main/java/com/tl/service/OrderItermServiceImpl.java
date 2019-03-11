package com.tl.service;

import com.tl.dao.OrderItermDao;
import com.tl.pojo.OrderIterm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/8 12:20 AM
 **/
@Service
public class OrderItermServiceImpl implements OrderItermService{
    @Autowired
    private OrderItermDao orderItermDao;

    @Override
    public OrderIterm getLastOrderItermByUidPid(String uid, String pid) {
        return orderItermDao.getLastProductByUidPid(uid,pid);
    }

    //批量插入
    @Override
    public int batchSave(List<OrderIterm> orderItermList) {
        return orderItermDao.batchSave(orderItermList);
    }

    //获取用户已经购物的物品
    @Override
    public List<OrderIterm> getOrderItermByUid(String uid) {
        return orderItermDao.getProductByUserId(uid);
    }


}
