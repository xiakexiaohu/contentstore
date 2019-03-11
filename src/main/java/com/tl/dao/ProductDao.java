package com.tl.dao;

import com.tl.pojo.Product;

import java.util.List;

public interface ProductDao {
    int deleteByPrimaryKey(String pid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> listAllProduct();

    //列出所有指定商家的发布的商品
    List<Product> getProductByOwnerId(String uid);

    //删除对应商品
    void remove(String pid);

    //获取对应商品
    Product getProductById(String pid);

    //更新商品库存
    void updateProductCountByPid(String pid, Integer saleCount);

}
