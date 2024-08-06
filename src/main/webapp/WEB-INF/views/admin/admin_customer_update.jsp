<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객 정보 수정</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/common_style.css"/>' />
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/profile_style.css"/>' />
<script src="<c:url value='/javascript/admin/admin.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>

	<main>
		<section id="update">
			<h2>고객 정보 수정</h2>
			<form action="<c:url value='/admin/updateUsers'/>" method="post" id="updateForm">
				<input type="hidden" name="userId" value="${userId}" />
				<div>
					<label for="name">이름:</label> <input type="text" id="userName"
						name="userName" value="${user.userName}" required>
				</div>
				<div>
					<label for="password">비밀번호:</label> <input type="password"
						id="password" name="password" value="${user.password}" required>
				</div>
				<div>
					<label for="email">이메일:</label> <input type="email" id="email"
						name="email" value="${user.email}" required>
				</div>
				<div>
					<label for="phone">전화번호:</label> <input type="tel" id="phoneNumber"
						name="phoneNumber" value="${user.phoneNumber}" required>
				</div>
				<div>
					<label for="address">주소:</label> <input type="text" id="address"
						name="address" value="${user.address}" required>
				</div>
				<button type="submit" onclick="userModify(event)">수정</button>
			</form>
		</section>
	</main>
	<c:import url="/WEB-INF/views/admin/includes/admin_footer.jsp"></c:import>
</body>
</html>