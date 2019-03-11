package com.tl.service;


import com.tl.dao.OrderItermDao;
import com.tl.dao.ProductDao;
import com.tl.pojo.OrderIterm;
import com.tl.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/2/27 3:41 PM
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderItermDao orderItermDao;

    @Override
    public int insert(Product product) {
        return productDao.insert(product);
    }

    //自动会填充默认值
    @Override
    public int insertSelective(Product product) {
        return productDao.insertSelective(product);
    }

    //获取所有商品
    @Override
    public List<Product> listAll() {
        return productDao.listAllProduct();
    }

    //获取用户下单商品
    @Override
    public List<OrderIterm> getProductByUserId(String uid) {
        //获取所有已经被用户下单的商品(已经在mapper去重了)
         return orderItermDao.getProductByUserId(uid);
    }

    //获取所有用户下单的商品
    @Override
    public List<OrderIterm> getProductHasBrought() {
        return orderItermDao.getProductHasBrought();
    }


    //获取对应商家发布的商品
    @Override
    public List<Product> getProductByOwnerId(String uid) {

        return productDao.getProductByOwnerId(uid);
    }

    @Override
    public void remove(String pid) {
        productDao.remove(pid);
    }

    @Override
    public Product getProductById(String pid) {
        return productDao.getProductById(pid);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public void updateProductCountByPid(String pid, Integer saleCount) {
        productDao.updateProductCountByPid(pid, saleCount);
    }
}
