<%--
  Created by IntelliJ IDEA.
  User: tuliang
  Date: 2019/2/22
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<% String path = request.getContextPath(); %>--%>
<html>
<head>
    <title>用户列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    基于SSM框架的用户管理系统：简单实现增、删、改、查。
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>用户列表 —— 显示所有用户</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>用户id</th>
                    <th>用户账号</th>
                    <th>用户密码</th>
                    <th>用户昵称</th>
                    <th>用户类型</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr>
                        <td>${user.uid}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.nickname}</td>
                        <td>${user.type}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

