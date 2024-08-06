<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value='/css/login_style.css' />">
</head>
<body>
    <div class="auth-container">
        <h2>REGISTER</h2>
        <c:if test="${not empty error}">
    <p style="color: red;">${error}</p> <!-- 이메일 중복 오류메세지 스타일 적용 -->
</c:if>
        <form action="<c:url value='/users/register' />" method="post">
            <input type="text" name="userName" placeholder="이름" required>
            <input type="email" name="email" placeholder="이메일" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <input type="address" name="address" placeholder="주소" required>
            <input type="phoneNumber" name="phoneNumber" placeholder="휴대전화" required>
            <button type="submit">CREATE</button>
        </form>
        <div class="login-link">
            <a href="<c:url value='/users/login' />">Login</a>
        </div>
    </div>
</body>
 <c:if test="${not empty error}">
            <script>
                alert('${error}');
            </script>
        </c:if>
</html>