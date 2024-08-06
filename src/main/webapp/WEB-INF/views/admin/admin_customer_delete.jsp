<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 삭제</title>
    
</head>
<body>
    <h1>회원정보 삭제</h1>
    <p>정말로 위 회원정보를 삭제하시겠습니까? </p>
    <p><strong>이름: </strong><span id="userName">${user.name}</span></p>
    <p><strong>이메일: </strong><span id="userEmail">${user.email}</span></p>
    <p>이 작업은 되돌릴 수 없습니다.</p>
    
    <form action="admin_customer_delete.jsp" method="post">
        <input type="hidden" name="userId" value="${param.userId}">
        <button type="submit">예, 삭제합니다</button>
        <!-- 삭제된 고객관리 페이지로 리다이렉트되어야함 -->
    </form>
    
    <a href="javascript:history.back()"><button type="submit">아니오, 취소합니다</button></a>
 
    <script>
        document.querySelector('form').addEventListener('submit', function(e) {
            if (!confirm('정말로 삭제하시겠습니까?')) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html>