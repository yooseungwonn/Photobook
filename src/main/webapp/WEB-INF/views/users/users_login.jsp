<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="<c:url value='/css/login_style.css' />">

</head>
<body>
	<div class="auth-container">
		<h2>LOGIN</h2>
		<c:if test="${not empty error}">
			<p style="color: red;">${error}
		</c:if>

		<form action="<c:url value='/users/login' />" method="post">
			<input type="email" name="email" placeholder="Email" required>
			<input type="password" name="password" placeholder="Password"
				required>
			<button type="submit">OK</button>
		</form>
		<div class="signup-link">
			<a href="<c:url value='/users/register' />">Create Account</a></br></br>
			
			<a href="<c:url value='/users/home' />">Home</a>
		</div>
	</div>
	<c:choose>
	<c:when test="${not empty error}">
		<script>
			alert('${error}');
		</script>
	</c:when>
	<c:when test="${not empty authUser}">
		<script>
			alert('${success}');
		</script>
	</c:when>
</c:choose>
</body>
</html>
