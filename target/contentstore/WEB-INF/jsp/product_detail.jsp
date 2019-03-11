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
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>


<head>
    <meta charset="utf-8"/>
    <title>商品详情</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@include file="head.jsp" %>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="pid">
            <span style="display: none" id="cur_pid">${productCustom.product.pid}</span>
        </div>
        <div class="img"><img
                src="${productCustom.product.pimage}"
                alt=""></div>
        <div class="cnt">
            <h2>${productCustom.product.ptitle}</h2>
            <p class="summary">${productCustom.product.psummary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value" id="cur_price">${productCustom.product.price}</span>
            </div>
            <div class="count">
                <span class="v-unit">库存数量:</span><span class="v-value" id="count">${productCustom.product.pcount}</span>
            </div>
            <!--卖家-->
            <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 1}">
                <div class="oprt f-cb">
                    <a href="${ctx}/product/edit?pid=${productCustom.product.pid}" class="u-btn u-btn-primary">编 辑</a>
                </div>
            </c:if>

            <!--买家-->
            <div class="oprt f-cb">
                <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 0 && productCustom.isBrought == 1}">
                    <!--买家已经购买了-->
                    <span class="u-btn u-btn-primary z-dis">已购买</span>
                    <span class="buyprice">当时购买价格：¥${productCustom.lastPrice}</span>
                </c:if>
                <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 0 && productCustom.isBrought == 0}">
                    <!--买家未购买-->
                    <div class="num">
                        购买数量：
                        <span id="plusNum" class="lessNum"><a>-</a></span>
                        <span class="totalNum" id="allNum">1</span>
                        <span id="addNum" class="moreNum"><a>+</a></span>
                    </div>
                    <button class="u-btn u-btn-primary" id="add">
                        加入购物车
                    </button>
                </c:if>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${productCustom.product.pdesc}
    </div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageShow.js"></script>
</body>
</html>
