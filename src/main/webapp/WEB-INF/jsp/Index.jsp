<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta lang="zh_CN">
    <link rel="stylesheet" href="css/base.css">
    <title>${user.username}|signer@imaxct</title>
</head>
<body>
<h2>${user.username}</h2>
<a href="#" style="float: right" onclick="logout()">退出</a>
<div class="word">绑定的Tieba账号</div>
<div class="line"></div>
<c:choose>
    <c:when test="${empty accounts}">
        <h4>还没有绑定账号</h4>
    </c:when>
    <c:otherwise>
        <table class="table">
            <thead>
            <tr>
                <th>账号ID</th>
                <th>Uid</th>
                <th>贴吧数</th>
                <th>更新</th>
                <th>取消绑定</th>
            </tr>
            </thead>
            <tbody id="account-list">
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td><a class="show-tieba-detail" href="#" id="${account.id}">${account.name}</a></td>
                    <td>${account.uid}</td>
                    <td>${account.tiebaTotal}</td>
                    <td><a id="${account.id}" class="update-liked-tieba" href="javascript:;">更新喜欢的吧</a></td>
                    <td><a id="${account.id}" class="unbind-account" href="javascript:;">解绑</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<a id="bind-btn" href="javascript:;">点此绑定账号</a>
<div class="bind" style="display: none">
    <a id="close" href="javascript:;" style="float: right">收起</a>
    <p>
        开启chrome的隐身窗口,登录百度后在左侧按照图片点击,
        在<code>passport.baidu.com</code>域名下的<code>Cookie</code>文件夹中包含BDUSS<br>
        你可以通过修改密码使BDUSS失效
    </p>
    <img src="img/tutorial.png">
    <input id="bduss" type="text" name="bduss" id="cookie" placeholder="BDUSS">
    <input id="bind-submit" type="button" value="submit">
    <p id="errmsg" style="color:red; display: none;"></p>
</div>
<div class="tieba-detail">
    <div class="word">签到详细信息</div>
    <div class="line"></div>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>贴吧</th>
                <th>上次签到</th>
                <th>状态</th>
                <th>跳过</th>
            </tr>
        </thead>
        <tbody id="tieba-list">
        </tbody>
    </table>
</div>
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/Index.js"></script>
</body>
</html>
