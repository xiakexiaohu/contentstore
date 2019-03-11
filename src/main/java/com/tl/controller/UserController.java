package com.tl.controller;

import com.tl.pojo.User;
import com.tl.service.UserService;
import com.tl.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/2/22 4:38 PM
 **/
@Controller
@RequestMapping(value="/user")
public class UserController {
    //自动注入
    @Autowired
    private UserService userService;

    /**
     * @param username
     * @param password
     * @param session
     * @param mv
     * @return org.springframework.web.servlet.ModelAndView
     * @description 用户登录
     */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestParam("username") String username, @RequestParam("password") String password,
                                     HttpSession session){
        //判断用户是否登录成功
        User user = userService.login(username, password);
        Map<String, Object> resultMap = new HashMap<>();
        if (user != null) {
            //存在用户，将用户信息保存到session中
            session.setAttribute(Constant.USER_SESSION, user);
            //跳转到index页面(若是跳转jsp页面直接写路径，否则"redirect(重定向，地址变化)/forward(变化地址):路径")
            //因为前端采用Ajax传值，所以需要Controller通过JSON返回数据，JSON类型可以为map或者pojo类型
            //js中定义了result、message、code三个字段，所以该json需要分别定义。
            resultMap.put("result","success");
            resultMap.put("message","操作成功！");
            resultMap.put("code", Constant.SUCCESS_CODE);
        }else{
            resultMap.put("result", "failed");
            resultMap.put("message", "账号或者密码错误！请重试。");
            resultMap.put("code", Constant.FAILED_CODE);
        }
        return resultMap;
    }

    /**
     * @param mv
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @description 用户退出登录
     */

    @RequestMapping(value="/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session) {
        //注销session
        session.invalidate();
        //跳转到index页面
        mv.setViewName("redirect:/dynamic/main");
        return mv;
    }
}
