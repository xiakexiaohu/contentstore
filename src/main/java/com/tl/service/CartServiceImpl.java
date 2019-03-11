package com.tl.service;

import com.tl.dao.CartDao;
import com.tl.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/8 5:23 PM
 **/
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDao cartDao;

    @Override
    public int insertSelective(Cart cart) {
        return cartDao.insertSelective(cart);
    }

    @Override
    public List<Cart> getCartByUid(String uid) {
        return cartDao.getCartByUid(uid);
    }

    @Override
    public int delCartByCids(List<String> cids) {
        return cartDao.delCartByCids(cids);
    }

    @Override
    public List<Cart> getCartByCids(List<String> cids) {
        return cartDao.getCartByCids(cids);
    }
}
