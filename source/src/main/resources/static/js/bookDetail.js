$(function () {
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
    $.ajax({
        type: "post",
        url: "/usr/getOneBookDetail",
        data: {
            "bookid" : theRequest.bookid
        },
        dataType: "json",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.length == 1){
                swal({
                    title : "温馨提示",
                    text : data.error
                });
                return;
            }
            var bookDetail = data[0].book;
            var addr = data[1].addr;

            //左半部分图片部分
            $(".divBookDetail-left").append("<div class=\"divBookDetail-left-imgDiv\">\n" +
                "                    <img src=/"+ bookDetail.bookimage + " class=\"divBookDetail-left-img img-rounded center-block\">\n" +
                "                </div>");

            //右半部分信息内容
            $(".divBookDetail-right").append("<div class=\"divBookDetail-right-title\">\n" +
                "                    "+ bookDetail.bookname +"\n" +
                "                </div>\n" +
                "                <div class=\"divBookDetail-right-authorAndPress\">\n" +
                "                    <div>\n" +
                "                        ISBN：" + bookDetail.bookisbn + "\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        简介：" + bookDetail.bookinfor +"\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"divBookDetail-right-price\">\n" +
                "                    <div>\n" +
                "                        书店价：\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        ￥" + bookDetail.bookprice +"\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"divBookDetail-right-address\">\n" +
                "                    <div>配送至：</div>\n" +
                "                    <div>" + addr +"</div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"divBookDetail-right-purchase\">\n" +
                "                    <div onclick='addCart("+ bookDetail.bookid + ")'>加入购物车</div>\n" +
                "                    <div onclick='count("+ bookDetail.bookid + ")'>立即购买</div>\n" +
                "                </div>");


        }
    });

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
});

function addCart(bookId) {
    $.ajax({
        type: "post",
        url: "/usr/addCart",
        data: {
            "bookid" : bookId
        },
        dataType: "json",
        async: false,
        success: function (data) {
           if(data.added == "false"){
               swal({
                   title : "温馨提示",
                   text : data.reason,
               });
           }else{
               swal({
                   title : "温馨提示",
                   text : data.reason
               });
           }
        }
    });
}

function count(bookId) {
    $.ajax({
        type: "post",
        url: "/usr/addCount",
        data: {
            "bookid" : bookId
        },
        dataType: "json",
        async: false,
        success: function (data) {
            if(data.added == "false"){
                swal({
                    title : "温馨提示",
                    text : data.reason,
                }, function (isConfirmed) {
                    window.location.href = "/usr/index";
                });
            }else{
                window.location.href = "/usr/count?Item=" + data.orderNumber;
            }
        }
    });
}