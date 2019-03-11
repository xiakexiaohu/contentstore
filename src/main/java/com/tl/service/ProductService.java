package com.tl.service;

import com.tl.pojo.OrderIterm;
import com.tl.pojo.Product;

import javax.persistence.criteria.Order;
import java.util.List;

public interface ProductService {
    //默认插入，默认值null
    int insert(Product product);

    //插入值过滤默认值自动填充
    int insertSelective(Product product);

    //获取所有产品
    List<Product> listAll();

    //获取用户下单过的商品
    List<OrderIterm> getProductByUserId(String uid);

    //获取所有用户下单的所有商品
    List<OrderIterm> getProductHasBrought();

    //获取对应商家发布的商品
    List<Product> getProductByOwnerId(String uid);

    //删除对应商品
    void remove(String pid);

    //获取对应商品信息
    Product getProductById(String pid);

    //更新商品信息
    void updateProduct(Product product);

    //更新商品库存
    void updateProductCountByPid(String uid, Integer saleCount);
}
