<%--
  Created by IntelliJ IDEA.
  User: tuliang
  Date: 2019/2/27
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
    <title>购物车</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@include file="head.jsp" %>
<div class="container">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>购物车详情</h2>
    </div>
    <table class="table table-hover ">
        <thead>
        <tr align="center">
            <th></th>
            <th style="text-align: center">商品标题</th>
            <th style="text-align: center">单价</th>
            <th style="text-align: center">数量</th>
            <th style="text-align: center">金额</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cartCustom" items="${cartCustomLists}">
            <tr align="center">
                <td class="check-box"><input type="checkbox" name="mychk"/></td>
                <td class="cid" style="display: none">${cartCustom.cart.cid}</td>
                <td class="ptitle">${cartCustom.productTitle}</td>
                <td class="price">${cartCustom.cart.price}</td>
                <td class="count">${cartCustom.cart.count}</td>
                <td class="account">${cartCustom.cart.price*cartCustom.cart.count}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="totalbar" class="bottom-bar container">
    <div class="ttbar">
        <div class="lf">
            <input type="checkbox" id="selectAll" name="selectAll"><label class="lab" for="selectAll">全选</label> <a
                href="javascript:void(0)" class="batchdelete">移除选中商品</a>
        </div>
        <div class="rt">
            <p class="allproducts">已选商品 <span class="num">0</span> 件 <span class="itm">总价：<span
                    class="num">0.00</span></span></p>
            <a class="checkin " href="javascript:void(0)">结算</a>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<script type="text/javascript">

    //触发全选
    $("#selectAll").click(function () {
        $(".table input[type=checkbox]").prop("checked", this.checked);
        var chbNums = $(".table input[type=checkbox]").length;
        //更新选定数量
        if ($("#selectAll").prop("checked") == true) {
            //更新选定数量
            $(".allproducts>.num").text(chbNums);
        } else {
            //选中0件
            $(".allproducts>.num").text(0);
        }

        //更新选定价格
        $(".check-box>input[type=checkbox]").each(function () {
            var curRowTotalPrice = $(this).parent().siblings(".account").text();
            var countPrice = $(".itm>.num").text();
            var checkedNum = $(".allproducts>.num").text();
            if ($(this).prop("checked") == true) {
                //注意坑，两者相加必须都parseFloat转换
                $(".itm>.num").text((parseFloat(countPrice) + parseFloat(curRowTotalPrice)).toFixed(2));
                $(".allproducts>.num").text(parseInt(checkedNum));
            } else {
                $(".itm>.num").text((parseFloat(countPrice) - parseFloat(curRowTotalPrice)).toFixed(2));
                $(".allproducts>.num").text(0);
            }
        });
    });

    //触发单选，同时更新下方勾选栏信息
    $(".check-box>input[type=checkbox]").click(function () {
        var curRowTotalPrice = $(this).parent().siblings(".account").text();
        var countPrice = $(".itm>.num").text();
        var checkedNum = $(".allproducts>.num").text();
        if ($(this).prop("checked") == true) {
            //如果选中了，则更新数量和总价
            $(".itm>.num").text((parseFloat(countPrice) + parseFloat(curRowTotalPrice)).toFixed(2));
            $(".allproducts>.num").text(parseInt(checkedNum) + 1);
        } else {
            $(".itm>.num").text((parseFloat(countPrice) - parseFloat(curRowTotalPrice)).toFixed(2));
            $(".allproducts>.num").text(parseInt(checkedNum) - 1);
        }
    });

    //删除操作
    $(".batchdelete").click(function () {
        //无论是单选一个还是全选删除，都需要遍历所有
        if (window.confirm("您确定要移除购物车？") == true) {
            //$ids为jQuery对象
            var $cids=$("table input[type='checkbox']:checked").parent().siblings(".cid");
            var cidsArr=[];
            $cids.each(function () {
                cidsArr.push($(this).text());
            });
            var cidsJson = JSON.stringify(cidsArr);
            $.ajax({
                url:'/cart/del',
                type:'POST',
                data:{"cids": cidsJson},
                //至于dataType和contentType可以忽略
                success:function (res) {
                    alert('操作成功');
                    $("table input[type='checkbox']:checked").parent().parent().remove();
                    window.onload(true);
                },
                error:function () {
                    alert('操作失败')
                }
            })
        }
    });

    //结算操作
    $(".checkin").click(function () {
        //将所选中的传递到orderItem中
        if (window.confirm("您确定对所勾选商品进行结算？") == true) {
            var $cids=$("table input[type='checkbox']:checked").parent().siblings(".cid");
            var cidsArr=[];
            $cids.each(function () {
                cidsArr.push($(this).text());
            });
            var cidsJson = JSON.stringify(cidsArr);
            $.ajax({
               url:'/orderIterm/add',
               type:'POST',
               data:{"cids": cidsJson},
                success:function (res) {
                    alert('结算成功！');
                    window.location.href="/orderIterm/list";
                },
                error:function () {
                    alert('结算失败！');
                }
            });
        }
    });


</script>

</body>
</html>
