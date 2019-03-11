package com.tl.dao;

import com.tl.pojo.OrderIterm;
import com.tl.pojo.Product;

import javax.persistence.criteria.Order;
import java.util.List;

public interface OrderItermDao {
    int deleteByPrimaryKey(String oid);

    int insert(OrderIterm record);

    int insertSelective(OrderIterm record);

    OrderIterm selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(OrderIterm record);

    int updateByPrimaryKey(OrderIterm record);

    //列出指定用户所购买的商品
    List<OrderIterm> getProductByUserId(String uid);

    //列出所有已经出售的商品
    List<OrderIterm> getProductHasBrought();

    //获取用最近下单的某一个商品订单详情
    OrderIterm getLastProductByUidPid(String uid,String pid);

    //批量插入
    int batchSave(List<OrderIterm> orderItermList);
}
