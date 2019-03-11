<%--
  Created by IntelliJ IDEA.
  User: tuliang
  Date: 2019/2/27
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>内容商店</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
    <%@include file="head.jsp"%>
    <div class="g-doc">
        <div class="m-tab m-tab-fw m-tab-simple f-cb">
            <h2>内容编辑</h2>
        </div>
        <div class="n-public">
            <form class="m-form m-form-ht" id="form" method="post" action="/product/update?pid=${product.pid}" onsubmit="return false;" autocomplete="off">
                <div class="fmitem">
                    <label class="fmlab">标题：</label>

                    <div class="fmipt">
                        <input class="u-ipt ipt" name="title" autofocus placeholder="2-80字符" value="${product.ptitle}" />

                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab">摘要：</label>
                    <div class="fmipt">
                        <input class="u-ipt ipt" name="summary" placeholder="2-140字符" value="${product.psummary}"/>
                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab">图片：</label>
                    <div class="fmipt" id="uploadType">
                        <input name="pic" type="radio" value="url" checked /> 图片地址
                        <input name="pic" type="radio" value="file" /> 本地上传
                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab"></label>
                    <div class="fmipt" id="urlUpload">
                        <input class="u-ipt ipt"  name="image" value="${product.pimage}" placeholder="图片地址"/>
                    </div>
                    <div class="fmipt" id="fileUpload"  style="display:none">
                        <input class="u-ipt ipt" name="file" type="file" id="fileUp"/>
                        <button class="u-btn u-btn-primary" id="upload">上传</button>
                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab">正文：</label>
                    <div class="fmipt">
                        <textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符">${product.pdesc}</textarea>
                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab">库存量：</label>
                    <div class="fmipt">
                        <input type="number" style="width: 80px" step="1" min="1"  name="count"
                               placeholder="输入整数" data-error="请输入整数" value="${product.pcount}" required>
                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab">价格：</label>
                    <div class="fmipt">
                        <input style="width: 80px" value="${product.price}" name="price"/>元
                    </div>
                </div>
                <div class="fmitem fmitem-nolab fmitem-btn">
                    <div class="fmipt">
                        <button type="submit" class="u-btn u-btn-primary u-btn-lg">发布</button>
                    </div>
                </div>
            </form>
            <span class="imgpre"><img src="${product.pimage}" alt="" id="imgpre"></span>
        </div>
    </div>
    <%@include file="footer.jsp"%>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/public.js"></script>
</body>
</html>
