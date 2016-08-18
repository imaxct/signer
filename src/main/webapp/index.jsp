<%--
  Created by IntelliJ IDEA.
  User: maxct
  Date: 2016/8/16
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>signer@imaxct</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
</head>
<body>
<h2>signer@imaxct</h2>
<nav>
    <ul>
        <li class="login active"><a href="#login">Login</a></li>
        <li class="register"><a href="#register">Register</a></li>
    </ul>
</nav>
<section id="login" class="active">
    <form id="login-form">
        <input type="text" name="username" placeholder="username" required><br>
        <input type="password" name="password" placeholder="password" required><br>
        <input type="button" id="log" value="submit">
    </form>
</section>
<section id="register">
    <form id="register-form">
        <input type="text" name="username" placeholder="username" required><br>
        <input type="password" name="password" placeholder="password" required><br>
        <input type="password" name="pass" placeholder="confirm password" required><br>
        <img id="code_img" src="User/vcode">
        <a id="refresh-btn" href="javascript:;" onclick="refreshCode()">refresh</a><br>
        <input id="vcode_text" type="text" name="vcode" placeholder="v-code" required><br>
        <input type="submit" id="reg" value="submit">
    </form>
</section>
<c:if test="${!empty user}">
    <script>
        window.location.href="Index";
    </script>
</c:if>
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/signer.js"></script>
</body>
</html>
