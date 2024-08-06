<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      
<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <title>${orderVo.orderId}</title>
    <link rel="stylesheet" href="<c:url value='/css/order_style.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
</head>

<body>
    <c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>

    <main>
        <section id="order" class="my-5">
            <h2 class="mb-4">주문 상세</h2>
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>주문 번호</th>
                        <th>제품</th>
                        <th>주문 일자</th>
                        <th>가격</th>
                        <th>수량</th>
                        <th>주문 상태</th>
                    </tr>
                </thead>
                <tbody>
                	<tr>
                		<td>${orderVo.orderId}</td>
                		<td>${orderVo.albumId}</td>
                		<td>${orderDate}</td>
                		<td></td>
                		<td>${orderVo.oQuantity}</td>
                		<td>${status}</td>
                	</tr>
                </tbody>
            </table>
            <div>
            	<h2>포토북 이미지</h2>
            	<div id="gallery">
            	<c:forEach var="index" begin="1" end="${imagesCount}">
            		<img src="<c:url value="${imgSrc}/${index}.jpg" />" />
        		</c:forEach>	
        		</div> 
            </div><br>
             <p>
				<a href="<c:url value="/users/order"/>">뒤로 가기</a>
			</p>
        </section>
    </main>
    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 	<c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
</body>
</html>