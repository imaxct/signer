/**
 * signer
 * Created by maxct on 2016/8/16.
 */
$("li").on("click", function (event) {
    var e = event.currentTarget;
    $(e).parent().find(".active").removeClass("active");
    $(e).addClass("active");
    $("section").removeClass("active");
    $($(e).find("a").attr("href")).addClass("active");
});
$("#log").on("click", function (event) {
    $("#log").prop("disabled", true).val("正在登陆");
    var params = $("#login-form").serialize();
    $.ajax({url:"User/login", type:"post", data:params, dataType:"json", success:function (res) {
        if (!res){return;}
        if (res.errcode==0){
            window.location.href="Index";
        }else{
            alert(res.errmsg);
            $("#log").prop("disabled", false).val("submit");
        }

    }});
});
$("#reg").on("click", function (event) {
    $("#reg").prop("disabled", true).val("正在注册");
    var params = $("#register-form").serialize();
    $.ajax({url:"User/register", type:"post", data:params, dataType:"json", success:function (res) {
        if (!res){return;}
        if (res.errcode==0){
            window.location.href="Index";
        }if (res.errcode==-2){
            refreshCode();
            alert(res.errmsg);
            $("#reg").prop("disabled", false).val("submit");
        }else{
            $("#reg").prop("disabled", false).val("submit");
            alert(res.errmsg);
        }
    }});
});
function refreshCode() {
    $("#code_img").attr("src", "User/vcode?r=" + Math.random())
}