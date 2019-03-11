<%--
  Created by IntelliJ IDEA.
  User: tuliang
  Date: 2019/2/28
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <!--判断是否登陆-->
        <div class="user">
            <c:if test="${empty sessionScope.user_session}">
                亲，请<a href="${ctx}/dynamic/login_form">[登录]</a>
            </c:if>
            <c:if test="${not empty sessionScope.user_session}">
                <c:if test="${sessionScope.user_session.type == 1}">卖家你好:</c:if>
                <c:if test="${sessionScope.user_session.type == 0}">买家您好:</c:if>
                <span class="name">${sessionScope.user_session.nickname}</span>！<a href="${ctx}/user/logout">[退出]</a>
            </c:if>
            <ul class="nav">
                <li><a href="${ctx}/dynamic/main">首页</a></li>
                <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 1}">
                    <li><a href="${ctx}/product/publish">发布</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 0}">
                    <li><a href="${ctx}/orderIterm/list">账务</a></li>
                    <li><a href="${ctx}/cart/list">购物车</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
