var errMsg = new Proxy({
    0:"签到成功",
    1:"cookie过期",
    3:"签到成功",
    110001:"未知错误",
    160002:"签到成功",
    160003:"零点,稍后再试",
    160008:"签太快了,稍后再试",
    340006:"贴吧被封",
    340010:"签到成功"
}, {
    get:function (target, key) {
        return target.hasOwnProperty(key)?target[key]:"未知错误";
    }
});

$("#bind-btn").on("click", function () {
    $(".bind").show();
});
$("#close").on("click", function () {
    $(".bind").hide();
});
$("#bind-submit").on("click" ,function () {
    var bduss = $("#bduss").val();
    if (bduss==null || bduss == "")
        alert("bduss不能为空");
    else
        bduss = bduss.trim();
    $.post("Account/bind", {bduss:bduss}, function (res) {
        if (!res){alert("网络错误");return;}
        console.log(res);
        if (res.errcode == 0){
            window.location.reload();
        }else
            $("#errmsg").html(res.errmsg).show();
    }, "json");
});
$(".show-tieba-detail").on("click", function (event) {
    var e = event.currentTarget;
    console.log($(e).prop("id"));
    $.getJSON("Account/list?id="+$(e).prop("id"), function (res) {
        if (!res){return;}
        if (res.errcode==0){
            $("#tieba-list").html("");
            $(res.list).each(function (i) {
                var succ = errMsg[this.errcode]=="签到成功";
                $("#tieba-list").append(String.raw `<tr>
<td>${this.id}</td>
<td>${this.name}</td>
<td>${this.lastSign}</td>
<td class="${succ?'succ':'error'}">${errMsg[this.errcode]}</td>
<td><input id="${this.id}" class="skip" type="checkbox" ${this.skip?"checked":""}></td>
</tr>`);
            });
            $(".tieba-detail").show();
        }else{
            alert(res.errmsg);
        }
    });
});