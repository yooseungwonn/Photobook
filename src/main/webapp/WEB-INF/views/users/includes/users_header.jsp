<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link type="text/css" 
		rel="stylesheet" 
		href='<c:url value="/css/header_footer.css"/>'>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<script src="<c:url value='/javascript/users/user.js'/>"></script>

	
</head>

<header>
    <h1><a href="<c:url value="/users"/>"></a>PHOTOB</h1>
    <nav>
        <ul>
	    	<li><a href="<c:url value="/users/home"/>"><i class="bi bi-house-heart" style="font-size: 24px;"></i></a></li>
    	<c:choose>
    		<c:when test="${not empty authUser}">
	            <li><a href="<c:url value="/users/profile"/>">Profile</a></li>
	            <li><a href="<c:url value="/users/photobook"/>">Creation</a></li>
	            <li><a href="<c:url value="/users/order"/>">Order</a></li>
	            <li><a href="<c:url value="/users/boardList"/>">Board</a></li>
        		<li><a href="<c:url value="/users/logout"/>" onclick="logOut(event)">Logout</a></li>
        		<c:if test="${sessionScope.authUser.role == 'U'}">
        		<a><b>${userName}</b>님</a>
        		</c:if>
		        <c:if test="${sessionScope.authUser.role == 'A'}">
		            <li><a href="<c:url value='/admin/home' />" class="admin-menu" onclick="goAdmin(event)">Admin Page</a></li>
		        </c:if>
		        
    		</c:when>
		    <c:otherwise>
		        <li><a href="<c:url value='/users/login' />"><i class="bi bi-person" style="font-size: 24px;"></i></a></li>
		        
		        
		        
		    </c:otherwise>
		</c:choose>
		</ul>
	</nav>
</header>