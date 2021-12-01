<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins',sans-serif;
}
html, body{
  display: grid;
  height: 100vh;
  width: 100%;
  place-items: center;
  background: linear-gradient(to right, #3273ec 0%, #52b7e6 100%);
}
::selection{
  background: #5daff1;

}
.container{
  background: #fff;
  max-width: 500px;
  width: 100%;
  padding: 25px 30px;
  border-radius: 5px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.15);
}
.container form .title{
  font-size: 30px;
  font-weight: 600;
  margin: 20px 0 10px 0;
  position: relative;
}
.container form .title:before{
  content: '';
  position: absolute;
  height: 4px;
  width: 33px;
  left: 0px;
  bottom: 3px;
  border-radius: 5px;
  background: linear-gradient(to right, #3273ec 0%, #52b7e6 100%);
}
.container form .input-box{
  width: 100%;
  height: 45px;
  margin-top: 25px;
  position: relative;
}
.container form .input-box input{
  width: 100%;
  height: 100%;
  outline: none;
  font-size: 16px;
  border: none;
}
.container form .underline::before{
  content: '';
  position: absolute;
  height: 2px;
  width: 100%;
  background: #ccc;
  left: 0;
  bottom: 0;
}
.container form .underline::after{
  content: '';
  position: absolute;
  height: 2px;
  width: 100%;
  background: linear-gradient(to right, #3273ec 0%, #52b7e6 100%);
  left: 0;
  bottom: 0;
  transform: scaleX(0);
  transform-origin: left;
  transition: all 0.3s ease;
}
.container form .input-box input:focus ~ .underline::after,
.container form .input-box input:valid ~ .underline::after{
  transform: scaleX(1);
  transform-origin: left;
}
.container form .button{
  margin: 40px 0 20px 0;
}
.container .input-box input[type="submit"]{
  background: linear-gradient(to right, #013f79 0%, #559beb 100%);
  font-size: 17px;
  color: #fff;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}
.container .input-box input[type="submit"]:hover{
  letter-spacing: 1px;
  background: linear-gradient(to left, #013f79 0%, #559beb 100%);
}
.container .option{
  font-size: 14px;
  text-align: center;
}
.sign-up{
    background: linear-gradient(to right, #2d6353 0%, #63d489 100%);
    font-size: 17px;
    color: #fff;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease;
    width: 100%;
    height: 45px;
    outline: none;
    font-size: 16px;
    border: none;
}
</style>
</head>
<body>
<div class="container">
<c:if test="${ errors.size() > 0 }">
<div class="error-container">
<c:forEach items="${errors}" var="error">
<p style="color: red;">${ error }</p>
</c:forEach>
</div>
</c:if>
      <form action="register" method="post">
        <div class="title">Register</div>
        <div class="input-box underline">
          <input type="text" placeholder="Enter Your Username" name="username" required>
          <div class="underline"></div>
        </div>
        <div class="input-box">
          <input type="password" placeholder="Enter Your Password" name="password" required>
          <div class="underline"></div>
        </div>
        <div class="input-box">
            <input type="password" placeholder="Repeat Password" name="repassword" required>
            <div class="underline"></div>
          </div>
        <div class="input-box button">
          <input type="submit" name="" value="Register">
        </div>
        <div class="option">Have an account?</div>
        <div style="text-align: center; padding-top: 10px;">
            <a href="login" >Sign in</a>
        </div>
      </form>
    </div>
</body>
</html>