<%--
  Created by IntelliJ IDEA.
  User: maxct
  Date: 2016/8/16
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta lang="zh_CN">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <style>
        html,body{width:100%;height:100%;cursor:default}
        html,body,p,h2,div{margin:0;padding:0}
        body{background:#2980B9;text-align:center;user-select:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none}
        html{font:12px "Segoe UI","Microsoft YaHei",FreeSans,Arimo,"Droid Sans","Hiragino Sans GB","Hiragino Sans GB W3",Arial,sans-serif}
        h2{margin-bottom:25px;font-size:30px;font-weight:300;color:#e05d6f}
        p{line-height:1.5em;font-size:12px;color:#95a2a9;margin-bottom:5px}
        .title{position:relative;top:75px;margin-bottom:.7em;line-height:30px;font-size:26px;font-weight:300;color:#fff;text-shadow:0 0 4px #666666}
        .box{position:relative;top:80px;width:600px;max-width:85%;margin:0 auto;background:#fff;padding:15px;box-shadow:0 0 50px #2964B9}
        .main{font-size:18px;color:#000;font-weight:500;line-height:1.7em;margin:0 0 10px}
        .foot{position:relative;top:80px;margin:15px 15px 0;font-size:12px;color:#4eb0f8}
        pre{background:#3498DB;color:#ffffff;padding:15px 20px;margin:25px -15px -15px;line-height:1.4em;font-size:14px;text-align:left;word-break:break-all;white-space:pre-wrap}
    </style>
    <title>${msg.title}|signer@imaxct</title>
</head>
<body>
<p class="title">Error</p>
<div class="box">
    <h2>${msg.title}</h2>
    <p class="main">${msg.errmsg}</p>
    <p>Try again later.</p>
    <pre>If you have any question, contact the manager.</pre>
</div>
<p class="foot">Hosted By imaxct</p>
<c:if test="${msg.redirect}">
    <script>
        window.setTimeout(function () {
            location.href="${msg.url}";
        }, 2000);
    </script>
</c:if>
</body>
</html>
