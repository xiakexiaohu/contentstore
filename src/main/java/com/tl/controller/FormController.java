package com.tl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Program: contentstore
 * @Description: 动态页面跳转
 * @Author: tuliang
 * @Date: 2019/2/26 4:18 PM
 **/
@Controller
@RequestMapping(value = "/dynamic")
public class FormController {


    @RequestMapping(value="/main")
    public String jumpToMain(){
        return "forward:/product/list";
    }

    @RequestMapping(value="/login_form")
    public String jumpTologinForm(){
        return "login_form";
    }
}
