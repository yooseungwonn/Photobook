<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 배송 정보</title>
    <link type="text/css" 
		rel="stylesheet" 
		href='<c:url value="/css/delivery_form_style.css"/>'/>
	<script type="text/javascript" 
		src='<c:url value="/javascript/admin/delivery_detail.js"/>'>
		
	</script> 
	<style>
		.container {
			margin-top:10px;
		}
	</style>
</head>

<body>
	<c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>
	
    <div class="container">
		<h1>배송 상세 정보</h1>
		<form id="modifyForm" action="<c:url value="/admin/delivery/modify"/>" method="post">
			<table id="orderTable">
	      		<tr class="section-title">
	              <td colspan="3">배송 정보</td>
	        	</tr>
	        	<tr>
	               <td>배송 일자
	               <!-- 관련은 없지만 shipmentId의 필요성으로 일부러 넣음 -->
	               <input type="hidden" name="shipmentId"
	               		value="${deliveryDetailInfos['shipmentsVo'].shipmentId}"/>
	               </td>
	               <td>
	               <!-- Date는 서비스에서 "yyyy-MM-dd" 형식으로 변환 필요 -->
	               <input type="datetime-local"
	               		value="${deliveryDetailInfos['shipmentDate']}" 
	              			disabled/>
	               <input type="hidden" 
	               		name="stringShipmentDate" value="${deliveryDetailInfos['shipmentDate']}"/>
	               </td>
	               <td><button type=button class="edit-btn">수정</button></td>
	           </tr>
	           <tr>
	               <td>배송 상태</td>
	               <td>
	               <select id="delivery_status" 
	               	data-default="${deliveryDetailInfos['shipmentsVo'].shipmentStatus}" disabled>
		               <option value="A">배송 준비</option>
		               <option value="B">배송 중</option>
		               <option value="C">배송 완료</option>

	               </select>
	               <input type="hidden" 
	               		name="shipmentStatus" value="${deliveryDetailInfos['shipmentsVo'].shipmentStatus}"/>
	               </td>
	               <td><button type=button id="confirm-btn" class="edit-btn">수정</button></td>
	           </tr>
	           <tr class="section-end">
	               <td colspan="3"></td>
	           </tr>
	           <tr class="section-title">
	               <td colspan="3">주문 정보</td>
	           </tr>
	           <tr>
	               <td>주문 번호</td>
	               <td>
	               <input type="text" value="${deliveryDetailInfos['ordersVo'].orderId}" disabled/>
	               <input type="hidden" 
	               		name="orderId" value="${deliveryDetailInfos['ordersVo'].orderId}"/>
	               <input type="hidden" 
	               		name="targetOrderId" value="${deliveryDetailInfos['ordersVo'].orderId}"/>
	               </td>
	               <td><button type=button id="confirm-btn" class="edit-btn">수정</button></td>
	           </tr>
	           <tr>
	               <td>주문자 아이디</td>
	               <td>
	               <input type="text" value="${deliveryDetailInfos['usersVo'].userId}" disabled/>
	               <input type="hidden" name="userId" value="${deliveryDetailInfos['usersVo'].userId}"/>
	               </td>
	           </tr>
	           <tr>
	               <td>주문자 이름</td>
	               <td>
	               <input type="text" value="${deliveryDetailInfos['usersVo'].userName}" disabled/>
	               </td>
	           </tr>
	           <tr>
	               <td>주문 일자</td>
	               <td>
	               <!-- Date는 서비스에서 "yyyy-MM-dd" 형식으로 변환 필요 -->
	               <input type="datetime-local" value="${deliveryDetailInfos['orderDate']}" disabled/>
	               <input type="hidden" 
	               		name="stringOrderDate" value="${deliveryDetailInfos['orderDate']}"/>
	               </td>
	               <td><button type=button class="edit-btn">수정</button></td>
	           </tr>
	           <tr class="section-end">
	               <td colspan="3"></td>
	           </tr>
	           <tr class="section-title">
	               <td colspan="3">상품 정보</td>
	           </tr>
	           <tr>
	               <td>상품 id</td>
	               <td>
	               <select id="albumVo"
	               	data-default="${deliveryDetailInfos['ordersVo'].albumId}" disabled>
	               	<c:forEach items="${deliveryDetailInfos['albumList']}" var="album">
		               	<option value="${album.albumId}">
		               		${album.albumId}
		               	</option>
	               	</c:forEach>
	               </select>
	               <input type="hidden" name="albumId"
	               		 value="${deliveryDetailInfos['ordersVo'].albumId}"/>
	               </td>
	               <td><button type=button id="confirm-btn" class="edit-btn">수정</button></td>
	           </tr>
	           <tr class="section-end">
	               <td colspan="3"></td>
	           </tr>
	           <tr class="section-title">
	               <td colspan="3">결제 정보</td>
	           </tr>
	           <tr>
	               <td>총 금액</td>
	               <td>
	               <input type="number" step="1000"
	               		value="${deliveryDetailInfos['ordersVo'].total}" 
	               		disabled/>
	               <input type="hidden" name="total"
	               		 value="${deliveryDetailInfos['ordersVo'].total}"/>
	               </td>
	               <td><button type=button id="confirm-btn" class="edit-btn">수정</button></td>
	           </tr>
	           <tr>
	               <td>총 수량</td>
	               <td>
	               <input type="number" min="1"
	               		value="${deliveryDetailInfos['ordersVo'].oQuantity}" 
	               		disabled/>
	               <input type="hidden" name="oQuantity"
	               		 value="${deliveryDetailInfos['ordersVo'].oQuantity}"/>
	               </td>
	               <td><button type=button id="confirm-btn" class="edit-btn">수정</button></td>
	            </tr>
        	</table>
        </form>
        
        <form action="<c:url value="/admin/delivery"/>" method="get">
       		<button id="big-btn">배송 관리로 가기</button>
        </form>
        
       	<button id="big-btn" type="submit" form="modifyForm">수정 확인</button>
    </div>
</body>
</html>