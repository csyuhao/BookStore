//获取url后的参数
var url = location.search; //获取url中"?"符后的字串
var theRequest = new Object();
if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");
    for(var i = 0; i < strs.length; i ++) {
        theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
    }
}



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

            if (customer != null) {
                $(".nav-login-title").remove();
                $(".nav-login-registor").remove();

                $(".information").prepend('<li><a href=\"#\">' + customer.csuname + '</a></li>');
            }
        }
    });


    $.ajax({
        type: "post",
        url: "/usr/historyOrder",
        data: {},
        dataType: "json",
        async: false,
        success: function (data) {
            data = data.info;
            for (var i = 0; i < data.length; i++) {
                $(".divHistoryCartItemTitle").after("<div class=\"divCartItem\" name = " + data[i].ordernumber + ">\n" +
                    "            <div class=\"divCartItem-item\">\n" +
                    "                <div class=\"divCartItem-imgDiv\">\n" +
                    "                    <img class=\"divCartItem-img img-rounded center-block\" src=/" + data[i].book.bookimage + ">\n" +
                    "                </div>\n" +
                    "                <div class=\"divCartItem-title\">\n" +
                    "                    " + data[i].bookname + "\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\">\n" +
                    "                ￥" + data[i].book.bookprice + "\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\">\n" +
                    "                " + data[i].num + "\n" +
                    "            </div>\n" +
                    "            <div class=\"divCartItem-little\" name = " + parseFloat(data[i].book.bookprice) * parseFloat(data[i].num) + ">\n" +
                    "                ￥" + parseFloat(data[i].book.bookprice) * parseFloat(data[i].num) + "\n" +
                    "            </div>\n" +
                    "        </div>");
            }
        }
    });

    if(url!=""){

        var cartId = theRequest.item.split(',');

        for (var i = 0; i < cartId.length; i++){
            var isSuccess = false;
            $.ajax({
                type: "post",
                url: "/usr/oneOrderInfo",
                data: {
                    "id" : cartId[i]
                },
                dataType: "json",
                async: false,
                success: function (data) {
                    data = data.info;
                    console.log(data);
                    $(".divCartItemTitle").after("<div class=\"divCartItem\" name = " + data.ordernumber + ">\n" +
                        "            <div class=\"divCartItem-item\">\n" +
                        "                <div class=\"divCartItem-imgDiv\">\n" +
                        "                    <img class=\"divCartItem-img img-rounded center-block\" src=/" + data.book.bookimage + ">\n" +
                        "                </div>\n" +
                        "                <div class=\"divCartItem-title\">\n" +
                        "                    " + data.bookname + "\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "            <div class=\"divCartItem-little\">\n" +
                        "                ￥"+ data.book.bookprice +"\n" +
                        "            </div>\n" +
                        "            <div class=\"divCartItem-little\">\n" +
                        "                "+ data.num +"\n" +
                        "            </div>\n" +
                        "            <div class=\"divCartItem-little\" name = " + parseFloat(data.book.bookprice) * parseFloat(data.num) +">\n" +
                        "                ￥"+ parseFloat(data.book.bookprice) * parseFloat(data.num) + "\n" +
                        "            </div>\n" +
                        "        </div>");
                }
            });
        }
    }
});

function confirm() {

    //显示订单页面
    var cartId = theRequest.item.split(',');
    for (var i = 0; i < cartId.length; i++) {
        var isSuccess = false;
        $.ajax({
            type: "post",
            url: "/usr/buyBook",
            data: {
                "id": cartId[i]
            },
            dataType: "json",
            async: false,
            success: function (data) {
                if(data.info == "false"){
                    swal({
                        title: "温馨提示",
                        text : "购买失败！"
                    }, function(isConfirmed){
                        window.location.href = "/usr/index";
                        return ;
                    })
                }
            }
        });
    }
    swal({
        title: "温馨提示",
        text : "购买成功！"
    }, function(isConfirmed){
        window.location.href = "/usr/index";
        return ;
    })
}