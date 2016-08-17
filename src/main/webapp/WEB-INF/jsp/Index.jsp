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
<div class="word">绑定的Tieba账号</div>
<div class="line"></div>
<c:choose>
    <c:when test="${empty accounts}">
        <h4>还没有绑定账号</h4>
    </c:when>
    <c:otherwise>
        <p>list</p>
    </c:otherwise>
</c:choose>
<a id="bind-btn" href="javascript:;">点此绑定账号</a>
<div class="bind" style="display: none">
    <a id="close" href="javascript:;" style="float: right">收起</a>
    <p>
        开启chrome的隐身窗口,登录百度后在左侧按照图片点击,
        在<code>baidu.com</code>域名下的<code>Cookie</code>文件夹中包含BDUSS<br>
        你可以通过修改密码使BDUSS失效
    </p>
    <img src="img/tutorial.png">
    <input type="text" name="bduss" id="cookie" placeholder="BDUSS">
    <input id="bind-submit" type="button" value="submit">
</div>
<div class="tieba-detail">
    <div class="word"><span id="account_show"></span>的详细信息</div>
    <div class="line"></div>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>LAST_SIGN</th>
                <th>STATUS</th>
                <th>SKIP</th>
            </tr>
        </thead>
        <tbody id="detail-list">
        </tbody>
    </table>
</div>
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/Index.js"></script>
</body>
</html>
