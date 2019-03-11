<%--
  Created by IntelliJ IDEA.
  User: tuliang
  Date: 2019/2/23
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>内容商店</title>
    <link rel="stylesheet" href="${ctx}/css/style.css"/>
</head>
<body>
    <%@ include file="head.jsp"%>
    <form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
        <div class="fmitem">
            <label class="fmlab">用户名：</label>
            <div class="fmipt">
                <input class="u-ipt" name="userName" autofocus/>
            </div>
        </div>
        <div class="fmitem">
            <label class="fmlab">密码：</label>
            <div class="fmipt">
                <input class="u-ipt" type="password" name="password"/>
            </div>
        </div>
        <div class="fmitem fmitem-nolab fmitem-btn">
            <div class="fmipt">
                <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
            </div>
        </div>
    </form>
    <%@include file="footer.jsp"%>
<script type="text/javascript" src="${ctx}/js/md5.js"></script>
<script type="text/javascript" src="${ctx}/js/global.js"></script>
<script type="text/javascript" src="${ctx}/js/pageLogin.js"></script>
</body>
</html>
