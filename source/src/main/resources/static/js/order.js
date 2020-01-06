$(function () {
    //加载用户的信息
    $.ajax({
        type: "post",
        url: "/getLoginCustomer",
        data: {},
        dataType: "json",
        async: false,
        success: function (data) {

            var customer = data.loginCustomer;

            if (customer != null) {
                $(".nav-login-title").remove();
                $(".nav-login-registor").remove();

                $(".information").prepend('<li><a href=\"#\">' + customer.name + '</a></li>');
            }
        }
    });
});