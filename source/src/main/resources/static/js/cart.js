$(function () {
    //加载用户的信息
    $.ajax({
        type: "post",
        url: "/usr/getLoginCustomer",
        data: {},
        dataType: "json",
        async: false,
        success: function (data) {

            var customer = data.loginCustomer;
            if(customer != null){
                $(".nav-login-title").remove();
                $(".nav-login-registor").remove();

                $(".information").prepend('<li><a href=\"#\">'+ customer.csuname +'</a></li>');
            }
        }
    });

    //加载购物车信息
    $.ajax({
        type: "post",
        url: "/usr/purchase",
        data: {},
        dataType: "json",
        async: false,
        success: function (data) {
            var cartItem = data.orders;
            for (var i = 0; i < cartItem.length; i++){
                $(".divCartItemTitle").after("<div class=\"divCartItem\" name = " + cartItem[i].ordernumber + ">\n" +
                    "            <div class=\"divCartItem-checkone\">\n" +
                    "                <input type=\"checkbox\" name=\"checkOne\">\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-item\">\n" +
                    "                <div class=\"divCartItem-imgDiv\">\n" +
                    "                    <img class=\"divCartItem-img img-rounded center-block\" src=\\"+ cartItem[i]["book"].bookimage +">\n" +
                    "                </div>\n" +
                    "                <div class=\"divCartItem-title\">\n" +
                    "                    " + cartItem[i].bookname + "\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\">\n" +
                    "                ￥"+ cartItem[i]["book"].bookprice +"\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\">\n" +
                    "                "+ cartItem[i].num +"\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\" name = " + parseFloat(cartItem[i]["book"].bookprice) * parseFloat(cartItem[i].num) +">\n" +
                    "                ￥"+ parseFloat(cartItem[i]["book"].bookprice) * parseFloat(cartItem[i].num)+"\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\">\n" +
                    "                <button type=\"button\" class=\"btn btn-primary\" onclick='deleteOneItem("+ cartItem[i].ordernumber +")'>删除</button>\n" +
                    "            </div>\n" +
                    "        </div>");
            }
        }
    });

    //设置checkbox以及总价格
    $(".divCartItem input:checkbox").click(function () {
        calculatePrices();
    });

    $(".divCart-cleanCart-checkAll input:checkbox").click(function () {
        if ($(this).prop("checked")) {
            $('input:checkbox').attr("checked", true);
            calculatePrices();
        } else {
            $('input:checkbox').attr("checked", false);
            calculatePrices();
        }
    });
});

function calculatePrices() {
    var sumPrice = 0;
    $(".divCartItem input:checkbox:checked").parents(".divCartItem").each(function () {
        sumPrice = sumPrice + parseFloat($(this).children(".divCartItem-little").eq(2).attr("name"));
    });
    sumPrice = sumPrice.toFixed(2);

    $(".divCart-cleanCart-buttonDiv span").text("共￥"+sumPrice+"元");
}

function deleteOneItem(id) {
    $.ajax({
        type: "post",
        url: "/usr/deleteOneItem",
        data: {
            "id" : id
        },
        dataType: "json",
        async: false,
        success: function (data) {
            swal({
                title: "温馨提示",
                text : data.error
            }, function(isConfirmed){
                if (data.error == "删除成功！") {
                    $(".divCartItem[name=" + id + "]").remove();
                    calculatePrices();
                }
            })
        }
    });
}

function count() {
    var list = "";
    $(".divCartItem input:checkbox:checked").parents(".divCartItem").each(function () {
        list = list + $(this).attr("name") + ",";
    });

    list = list.substring(0,list.length-1);

    if(list!=""){
        window.location.href = "/usr/count?item="+list;
    }
}
