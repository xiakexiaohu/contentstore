package com.tl.interceptor;

import com.tl.pojo.User;
import com.tl.utils.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Program: contentstore
 * @Description: 判断用户权限的Spring MVC的拦截器
 * @Author: tuliang
 * @Date: 2019/2/23 4:57 PM
 **/
public class AuthorizedInterceptor implements HandlerInterceptor {

    //定义不需要拦截的路径
    private static final String[] INGORE_URE={"/loginForm","/login","/404.html"};


    /**
     * @param response
     * @param request
     * @param o
     * @return boolean
     * @description preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用
     * 当返回值为false是，整个请求结束。否则，则会继续执行postHandler和afterCompletion
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //默认用户没有登录
        boolean isLoginFlag=false;
        //获取请求是否需要拦截
        String servletPath = request.getServletPath();
        for (String str : INGORE_URE) {
            if (servletPath.contains(str)) {
                //不需要拦截的url
                isLoginFlag = true;
                break;
            }
        }

        //拦截请求
        if (!isLoginFlag) {
            //1.获取session中的用户
            User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
            //2.判断用户是否已经登录
            if (user == null) {
                //如果用户没有登录，跳转到登录页面
                request.setAttribute("message", "请先登录才能操作!");
                request.getRequestDispatcher(Constant.LOGIN).forward(request, response);
                //返回标识
                return isLoginFlag;
            }else{
                //用户正常登录
                isLoginFlag=true;
            }
        }
        return false;
    }

    /**
     * @param request
     * @param response
     * @param o
     * @param modelAndView
     * @return void
     * @description postHandle方法之后在preHandle方法返回true时，才会继续执行，
     * 执行时间是在处理器进行处理之后，也就是在Controller方法调用之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * @param request
     * @param response
     * @param o
     * @param e
     * @return void
     * @description afterCompletion方法在preHandle方法返回true是才会执行
     * 该方法将在整个请求完成之后执行，主要作用用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
