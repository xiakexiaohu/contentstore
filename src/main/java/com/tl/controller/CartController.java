package com.tl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tl.pojo.Cart;
import com.tl.pojo.CartCustom;
import com.tl.pojo.Product;
import com.tl.pojo.User;
import com.tl.service.CartService;
import com.tl.service.ProductService;
import com.tl.utils.Constant;
import com.tl.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/3/8 5:01 PM
 **/
@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/del")
    @ResponseBody
    public String batchDelCart(@RequestParam("cids") String cids, HttpSession session) {

        User user = (User) session.getAttribute(Constant.USER_SESSION);
        if (user == null) {
            return "login_form";
        }

        //传递过来的数据需要进行烦解析
        JSONArray cidsArr = (JSONArray) JSONArray.parseArray(cids);
        List<String> cidsList = new ArrayList<>();

        for (Object cid : cidsArr) {
            cidsList.add((String) cid);
        }

        cartService.delCartByCids(cidsList);

        return "success";

    }

    @RequestMapping(value = "/list")
    public ModelAndView listAllCart(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        //查询当前用户购物车
        if (user == null) {
            mv.setViewName("login_form");
            mv.addObject("message", "您还未登录！");
            return mv;
        }
        List<Cart> cartLists= cartService.getCartByUid(user.getUid());
        HashMap<String, String> productMaps = new HashMap<>();
        //获取所有商品信息
        List<Product> productLists=productService.listAll();
        List<CartCustom> cartCustomLists = new ArrayList<>();

        new CartCustom();
        for (Product p : productLists) {
            productMaps.put(p.getPid(), p.getPtitle());
        }
        for (Cart c : cartLists) {
            CartCustom cartCustom = new CartCustom();
            cartCustom.setCart(c);
            if (productMaps.get(c.getPid()) != null) {
                cartCustom.setProductTitle(productMaps.get(c.getPid()));
            }
            cartCustomLists.add(cartCustom);
        }
        mv.setViewName("cart_detail");
        mv.addObject("cartCustomLists", cartCustomLists);
        return mv;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public Map<String, Object> addToCart(@RequestParam("pid") String pid, @RequestParam("price") String price,
                                   @RequestParam("num") String num, HttpSession session
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        Cart cart = new Cart();
        String cid = UUIDUtils.getUUID();
        cart.setCid(cid);
        cart.setCount(Integer.valueOf(num));
        cart.setPrice(Double.valueOf(price));
        cart.setPid(pid);
        cart.setUid(user.getUid());
        //加入购物车
        int res = cartService.insertSelective(cart);
        if (res == 1) {
            resultMap.put("result", "添加成功！");
            resultMap.put("code", Constant.SUCCESS_CODE);
        } else {
            resultMap.put("result", "添加出错了！");
            resultMap.put("code", Constant.FAILED_CODE);
        }
        return resultMap;
    }


}
