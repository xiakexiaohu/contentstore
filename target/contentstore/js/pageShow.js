var $ = function (id) {
    return document.getElementById(id);
}

$('plusNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    if (num > 0) {
        num--;
        $('allNum').innerHTML = num;
    } else {
        alert("您没有购买任何商品");
    }
};

$('addNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    var count = $('count').textContent;
    if (num > count - 1) {
        alert('你购买数量超过库存量了！')
    } else {
        num++;
        $('allNum').innerHTML = num;
    }
};

var loading = new Loading();
var layer = new Layer();


$('add').onclick = function (e) {
    var pid = $('cur_pid').innerHTML;
    var price = $('cur_price').innerHTML;
    var num = $('allNum').innerHTML;


    // console.log('pid:' + pid);
    // console.log('price:' + price);
    // console.log('num:'+num);


    //var productDetail = {'id': id, 'price': price, 'title': title, 'num': num};
//     var name = 'products';
//     var productList1 = new Array;
//     var productList = util.getCookie(name);
//     if (productList == "" || productList == null) {
//         productList1.push(productDetail);
//         util.setCookie(name, productList1);
//     } else if (util.findOne(productList, id)) {
//         util.modifyTwo(productList, id, num);
//         util.setCookie(name, productList);
//     } else {
//         productList.push(productDetail);
//         util.setCookie(name, productList);
//     }
//     console.log(document.cookie);
// //		util.deleteCookie(name);
//     e == window.event || e;

    // ajax({
    //     url: "/cart/add",
    //     type: "POST",
    //     data: {pid: pid, title: title, price: price, num:num},
    //     success: function (res) {
    //         layer.hide();
    //         loading.show();
    //         loading.result('添加购物车成功');
    //     },
    //     error: function () {
    //         alert("添加购物车失败");
    //     }
    // });

    layer.reset({
        content: '确认加入购物车吗？',
        onconfirm: function () {
            //写入购物车
            layer.hide();
            loading.show();
            ajax({
                data:{"pid":pid,"price":price, "num": num},
                type:'POST',
                url:'/cart/add',
                success:function(result){
                    loading.result(result);
                },
                error:function () {
                    loading.result('操作失败！');
                }
            });
        }.bind(this)
    }).show();
    return;
};




