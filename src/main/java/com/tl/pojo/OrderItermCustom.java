package com.tl.pojo;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/9 10:47 PM
 **/
public class OrderItermCustom {

    private  OrderIterm orderIterm;

    private Product product;

    public OrderIterm getOrderIterm() {
        return orderIterm;
    }

    private String timeStamp;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setOrderIterm(OrderIterm orderIterm) {
        this.orderIterm = orderIterm;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
