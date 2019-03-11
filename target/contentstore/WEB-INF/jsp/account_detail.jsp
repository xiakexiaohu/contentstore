<%--
  Created by IntelliJ IDEA.
  User: tuliang
  Date: 2019/3/9
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 引入bootstrap样式 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<!-- 引入bootstrap-table样式 -->
<link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
<!-- jquery -->
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- bootstrap-table.min.js -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<head>
    <meta charset="utf-8"/>
    <title>财务</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@include file="head.jsp" %>
<div class="container">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>财务详情</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup>
            <col class="img"/>
            <col/>
            <col class="time"/>
            <col/>
            <col class="num"/>
            <col/>
            <col class="price"/>
            <col/>
        </colgroup>
        <thead>
        <tr>
            <th style="text-align: center">内容图片</th>
            <th style="text-align: center">内容名称</th>
            <th style="text-align: center">购买时间</th>
            <th style="text-align: center">购买数量(件)</th>
            <th style="text-align: center">购买价格(元)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderItermCustom" items="${orderItermCustomLists}">
            <tr>
                <td><a href="/product/detail?pid=${orderItermCustom.orderIterm.pid}"><img src="${orderItermCustom.product.pimage}" alt=""></a></td>
                <td><h4><a href="/product/detail?pid=${orderItermCustom.orderIterm.pid}">${orderItermCustom.product.ptitle}</a></h4></td>
                <td><span class="v-time">${orderItermCustom.timeStamp}</span></td>
                <td><span class="v-num">${orderItermCustom.orderIterm.count}</span></td>
                <td><span class="v-unit">¥</span><span class="value">${orderItermCustom.orderIterm.price}</span></td>
            </tr>
        </c:forEach>

        </tbody>
        <tfoot>
        <tr>
            <td colspan="4"><div class="total">总计：</div></td>
            <td><span class="v-unit">¥</span><span class="value">${totalPrice}</span>元</td>
        </tr>
        </tfoot>
    </table>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
