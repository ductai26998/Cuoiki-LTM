<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
<c:if test="${ errors.size() > 0 }">
<div class="error-container">
<c:forEach items="${errors}" var="error">
<p style="color: red;">${ error }</p>
</c:forEach>
</div>
</c:if>
<form action="register" method="post">
<label>Username</label>
<input type="text" name="username">
<label>Password</label>
<input type="password" name="password">
<label>Repeat Password</label>
<input type="password" name="repassword">
<input type="submit" value="Register">
</form>
<a href="login" >Login</a>
</body>
</html>