package com.tl.pojo;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/1 3:53 PM
 **/
public class ProductCustom {
    //定制化的商品pojo
    private Product product;
    //商品是否被出售：1表示出售 0表示未出售
    private int isBrought;

    //商品售出数量
    private int countBrought;

    //上一次购买的价格
    private double lastPrice;

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public int getCountBrought() {
        return countBrought;
    }

    public void setCountBrought(int countBrought) {
        this.countBrought = countBrought;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getIsBrought() {
        return isBrought;
    }

    public void setIsBrought(int isBrought) {
        this.isBrought = isBrought;
    }


}
