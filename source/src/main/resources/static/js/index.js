$(function () {
    //加载图书的信息
    $.ajax({
        type: "post",
        url: "/usr/getAllBookSimples",
        data: {},
        dataType: "json",
        async : false,
        success: function(data){
            var list = data.bookSimpleList;
            var computer = [];
            var others = [];
            for (var i = 0; i < list.length;i++){
                if(list[i].bookshopid=="1001"){
                    computer.push(list[i]);
                }else{
                    others.push(list[i]);
                }
            }

            for (var i = 0; i < computer.length;i++){
                $("#divBook-class-computer").after("<div class=\"divBook\" onclick='openNewBookDetail("+computer[i].bookid+")'>\n" +
                    "            <div class=\"divBook-imgDiv\">\n" +
                    "                <img src='/"+computer[i].bookimage+"' class=\"divBook-img img-rounded center-block\">\n" +
                    "            </div>\n" +
                    "            <div class=\"divBook-titleAndPrice\">\n" +
                    "                <div class=\"divBook-title\">"+ computer[i].bookname +" </div>\n" +
                    "                <div class=\"divBook-price\">￥"+computer[i].bookprice+"</div>\n" +
                    "            </div>\n" +
                    "        </div>");
            }

            for (var i = 0; i < others.length;i++){
                $("#divBook-class-others").after("<div class=\"divBook\" onclick='openNewBookDetail("+others[i].bookid+")'>\n" +
                    "            <div class=\"divBook-imgDiv\">\n" +
                    "                <img src='/"+others[i].bookimage+"' class=\"divBook-img img-rounded center-block\">\n" +
                    "            </div>\n" +
                    "            <div class=\"divBook-titleAndPrice\">\n" +
                    "                <div class=\"divBook-title\">"+ others[i].bookname +" </div>\n" +
                    "                <div class=\"divBook-price\">￥"+others[i].bookprice+"</div>\n" +
                    "            </div>\n" +
                    "        </div>");
            }
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

function openNewBookDetail(bookid) {
    window.location.href = "/usr/bookDetail?bookid=" + bookid;
}

