<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <title>内容商店</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/js/global.js"></script>
    <script type="text/javascript" src="${ctx}/js/pageIndex.js"></script>
    <script>
        function delProduct(pid) {
            var parent = $(this).parent();
            if (window.confirm("您确认要删除该商品？") == true) {
                $.ajax({
                    url: "/product/del",
                    type: "POST",
                    data: {"pid": pid},
                    success: function (res) {
                        alert("删除成功");
                        //删除该标签
                        $("#p-${pid}").remove();
                    },
                    error: function () {
                        alert("删除失败");
                    },
                    dataType: "text"
                });
            }
        }

        //标签页切换
        function selectTag(name, cursel, n) {
            for (var i = 1; i <= n; i++) {
                var product = document.getElementById(name + i);
                var pro = document.getElementById(name + "_" + i);
                product.className = i == cursel ? "hover" : "";
                pro.style.display = i == cursel ? "block" : "none";
            }
        }


    </script>
</head>
<%@ include file="head.jsp" %>
<body>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li class="z-sel" id="product1"><a href="javascript:void(0)" onclick="selectTag('product',1,2)">所有内容</a>
                </li>
                <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 0}">
                    <li class="z-sel" id="product2"><a href="javascript:void(0)" onclick="selectTag('product',2,2)">未购买的内容</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

    <!--标签页-->
    <div class="n-plist" id="product_1">
        <ul class="f-cb" id="plist">
            <c:forEach var="productCustom" items="#{productCustoms}" varStatus="index">
                <li id="p-${pid}">
                    <a href="${ctx}/product/detail?pid=${productCustom.product.pid}" class="link">
                        <div class="img"><img src="${productCustom.product.pimage}"
                                              alt="${productCustom.product.ptitle}"></div>
                        <h3>${productCustom.product.ptitle}</h3>
                        <div class="price">
                            <span class="v-unit">¥</span><span class="v-value">${productCustom.product.price}</span>
                            <span class="v-unit">月销量:<span>${productCustom.countBrought}</span></span>
                        </div>
                    </a>
                    <!--买家-->
                    <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 0}">
                        <c:if test="${productCustom.isBrought == 1}"><span class="had"><b>已购买</b></span></c:if>
                    </c:if>
                    <!--卖家-->
                    <c:if test="${not empty sessionScope.user_session && sessionScope.user_session.type == 1}">
                        <c:if test="${productCustom.isBrought == 1}">
                            <span class="had"><b>已售出</b></span>
                        </c:if>

                        <c:if test="${productCustom.isBrought == 0}">
                            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block"
                                    onclick="delProduct('${productCustom.product.pid}')">删除
                            </button>
                        </c:if>
                    </c:if>
                        <%--<div class="price"><span class="v-unit">月销量：</span><span class="v-value">${productCustom.countBrought}</span></div>--%>

                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="n-plist" id="product_2">
        <ul class="f-cb" id="plist">
            <c:forEach var="productCustom" items="#{noBuyProductCustoms}" varStatus="index">
                <li id="p-${pid}">
                    <a href="${ctx}/product/detail?pid=${productCustom.product.pid}" class="link">
                        <div class="img"><img src="${productCustom.product.pimage}"
                                              alt="${productCustom.product.ptitle}"></div>
                        <h3>${productCustom.product.ptitle}</h3>
                        <div class="price">
                            <span class="v-unit">¥</span><span class="v-value">${productCustom.product.price}</span>
                            <span class="v-unit">月销量:<span>${productCustom.countBrought}</span></span>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>


</div>
<%@ include file="footer.jsp" %>
</body>
</html>
