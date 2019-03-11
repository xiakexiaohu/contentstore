package com.tl.dao;

import com.tl.pojo.Cart;

import java.util.List;

public interface CartDao {
    int deleteByPrimaryKey(String cid);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    //根据用户id查找其购物车
    List<Cart> getCartByUid(String uid);

    //批量删除
    int delCartByCids(List<String> cids);

    //批量获取
    List<Cart> getCartByCids(List<String> cids);
}
