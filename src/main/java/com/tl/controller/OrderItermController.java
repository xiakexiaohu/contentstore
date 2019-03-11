package com.tl.controller;

import com.alibaba.fastjson.JSONArray;
import com.tl.pojo.*;
import com.tl.service.CartService;
import com.tl.service.OrderItermService;
import com.tl.service.ProductService;
import com.tl.utils.Constant;
import com.tl.utils.TimeStampUtils;
import com.tl.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/9 10:18 PM
 **/
@Controller
@RequestMapping(value = "/orderIterm")
public class OrderItermController {
    @Autowired
    private OrderItermService orderItermService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/add")
    @ResponseBody
    public String addOrderItem(@RequestParam("cids") String cids, HttpSession session) {
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        if (user == null) {
            return "login_form";
        }

        //将数据写入OrderItem
        //传递过来的数据需要进行烦解析
        JSONArray cidsArr = (JSONArray) JSONArray.parseArray(cids);
        List<String> cidsList = new ArrayList<>();

        for (Object cid : cidsArr) {
            cidsList.add((String) cid);
        }
        //获取购物详情
        List<Cart> cartLists = cartService.getCartByCids(cidsList);

        List<OrderIterm> orderItermList = new ArrayList<>();
        //填充数据
        for (Cart c : cartLists) {
            OrderIterm orderIterm = new OrderIterm();
            String oid= UUIDUtils.getUUID();
            orderIterm.setCount(c.getCount());
            //更新库存
            productService.updateProductCountByPid(c.getPid(),c.getCount());
            orderIterm.setUid(user.getUid());
            orderIterm.setOid(oid);
            orderIterm.setPid(c.getPid());
            orderIterm.setPrice(c.getPrice());
            orderItermList.add(orderIterm);
        }

        //批量插入
        orderItermService.batchSave(orderItermList);
        //购物车清楚
        cartService.delCartByCids(cidsList);


        return "success";
    }


    @RequestMapping(value="/list")
    public ModelAndView listAllOrderIterm(HttpSession session){
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        ModelAndView mv = new ModelAndView();
        if (user == null) {
            mv.setViewName("login_form");
            return mv;
        }

        String uid = user.getUid();
        List<OrderIterm> orderItermList=orderItermService.getOrderItermByUid(uid);
        //获取所有商品信息
        List<Product> allProduct = productService.listAll();
        HashMap<String, Product> productHashMap = new HashMap<>();
        //建立映射表
        for (Product p : allProduct) {
            productHashMap.put(p.getPid(), p);
        }


        //填充数据
        List<OrderItermCustom> orderItermCustomList = new ArrayList<>();
        double totalPrice=0.0;
        for (OrderIterm oi : orderItermList) {
            OrderItermCustom orderItermCustom = new OrderItermCustom();
            orderItermCustom.setOrderIterm(oi);
            //更新时间戳显示格式yyyy-MM-dd HH:mm:ss
            orderItermCustom.setTimeStamp(TimeStampUtils.dataToStrLong(oi.getCtime()));
            //TODO (需要判断product不存在的情况)
            if(productHashMap.get(oi.getPid())!=null){
                orderItermCustom.setProduct(productHashMap.get(oi.getPid()));
            }

            totalPrice += oi.getPrice() * oi.getCount();
            orderItermCustomList.add(orderItermCustom);
        }

        mv.addObject("orderItermCustomLists", orderItermCustomList);
        //保留小数点后两位
        mv.addObject("totalPrice", String.format("%.2f", totalPrice));
        mv.setViewName("account_detail");
        return mv;
    }

}
