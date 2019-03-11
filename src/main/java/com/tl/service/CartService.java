package com.tl.service;

import com.tl.pojo.Cart;

import java.util.List;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/8 5:23 PM
 **/
public interface CartService {
    //加入购物车
    int insertSelective(Cart cart);

    //获取用户购物车
    List<Cart> getCartByUid(String uid);

    //批量删除购物车清单
    int delCartByCids(List<String> cids);

    //批量获取购物车清单
    List<Cart> getCartByCids(List<String> cids);
}
