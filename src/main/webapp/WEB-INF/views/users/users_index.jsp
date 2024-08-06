<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHOTOB</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>

<body class="d-flex flex-column min-vh-150">
    <c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>

    <main>
        <section class="hero position-relative">
            <div class="container-fluid p-0">
            <div class="row">
            <div class="col d-flex justify-content-end">
            <c:choose>
            	<c:when test="${not empty sessionScope.authUser}">
                    <a href="photobook">
                    <img src="<c:url value='/images/photobook1.jpg'/>" alt="photo" class="img-fluid w-100" style="height: auto; max-height: 80vh;">
                    </a>
                </c:when>
                <c:otherwise>
                	<a href="javascript:void(0)" onclick="showLoginMessage()">
                	<img src="<c:url value='/images/photobook1.jpg'/>" alt="photo" class="img-fluid w-100" style="height: auto; max-height: 80vh;">
                	</a>
                </c:otherwise>
                </c:choose>
                </div>
                </div>
        	</div>
        	
        	
        </section>
    </main>
    <footer class="bg-light text-center text-lg-start fixed-bottom py-2">
    <c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
    </footer>
    <script type="text/javascript">
        function showLoginMessage() {
            alert("로그인 후 이용 가능합니다");
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>