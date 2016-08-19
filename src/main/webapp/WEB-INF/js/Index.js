var errMsg = new Proxy({
    0:"已签到",
    1:"cookie过期",
    3:"已签到",
    233:"尚未签到",
    110001:"已签到",
    160002:"已签到",
    160003:"零点,稍后再试",
    160008:"签太快了,稍后再试",
    340006:"贴吧被封",
    340010:"已签到"
}, {
    get:function (target, key) {
        return target.hasOwnProperty(key)?target[key]:"未知错误";
    }
});
$(document).on("click", ".update-liked-tieba", function (event) {
    var e = event.currentTarget;
    var id = $(e).prop("id");
    $.getJSON("Account/update?id="+id, function (res) {
        if (!res)return;
        if (res.errcode == 0){
            alert(String.raw `更新成功,新增${res.add}个贴吧,减少${res.minus}个贴吧`);
        }else{
            alert(res.errmsg);
        }
    });
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
        if (res.errcode == 0){
            window.location.reload();
        }else
            $("#errmsg").html(res.errmsg).show();
    }, "json");
});
$(".show-tieba-detail").on("click", function (event) {
    var e = event.currentTarget;
    $.getJSON("Account/list?id="+$(e).prop("id"), function (res) {
        if (!res){return;}
        if (res.errcode==0){
            $("#tieba-list").html("");
            $(res.list).each(function (i) {
                var succ = errMsg[this.errcode]=="已签到";
                $("#tieba-list").append(String.raw `<tr>
<td>${this.id}</td>
<td>${this.name}</td>
<td>${this.lastSign?this.lastSign:'尚未签到'}</td>
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
$(document).on("click", ".skip" ,function (event) {
    console.log(event);
    var e = event.currentTarget;
    var id = $(e).prop("id");
    var skip = false;
    if ($(e).prop("checked")){
        skip = true;
    }
    $.getJSON("Tieba/skip?id="+id+"&skip="+skip, function (res) {
        if (!res){return;}
        if (res.errcode == 0)
            alert("ok");
        else alert(res.errmsg);
    });
});
function logout() {
    $.get("User/logout", function () {
        window.location.href="index.jsp";
    });
}