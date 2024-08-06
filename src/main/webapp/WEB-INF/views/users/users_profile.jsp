<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필</title>
    <link type="text/css" 
		rel="stylesheet" 
		href='<c:url value="/css/profile_style.css"/>'>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="<c:url value='/javascript/users/user.js'/>"></script>
</head>
<body>
    <c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>

    <main>
        <section class="profile-container">
            <h2>프로필 관리</h2>
             <form action="<c:url value='/users/profile/update'/>" method="post" id="profileUpdate">
                <div>
                    <label for="userName">이름:</label>
                    <input type="text" id="userName" name="userName" value="${authuser.userName}" required>
                </div>
                <div>
                    <label for="password">비밀번호:</label>
                    <input type="password" id="password" name="password"  required>
                </div>
                <div>
                    <label for="phoneNumber">전화번호:</label>
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${authuser.phoneNumber}" required>
                </div>
                <div>
                    <label for="address">주소:</label>
                    <input type="text" id="address" name="address" value="${authuser.address}" required>
                </div>
                <button type="submit" onclick="profileUpdate(event)">업데이트</button>
            </form>
        </section>
    </main>

 	<c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>