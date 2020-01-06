function login() {
    var username = $("#username").val();
    var password = $("#password").val();

    $.ajax({
        type : "post",
        url : "/usr/login",
        data : {
            "username" : username,
            "password" : password
        },
        async: false,
        dataType: "json",
        success: function (data) {
            if(data.isLogin == "yes"){
                window.location.href = "/usr/index";
            }else{
                window.location.href = "/usr/login";
            }

            return false;
        }
    });
}