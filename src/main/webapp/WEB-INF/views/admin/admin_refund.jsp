<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 환불 관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/common_style.css"/>' />
<script src="<c:url value='/javascript/admin/admin.js'/>" defer></script>
</head>
<body>
	<c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>
	<div class="main-content">
		<div class="card">
			<div class="header">
				<h2>환불 조회</h2>
			</div>
			<div class="card-header">환불 목록</div>
			<div class="card-body">
				<table class="table">
					<thead>
						<tr>
							<th>주문 ID</th>
							<th>주문자명</th>
							<th>환불 ID</th>
							<th>주문일자</th>
							<th>주문금액</th>
							<th>환불 상태</th>
							<th>환불 상태 변경</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${refundInfos}" var="infoMap">
							<tr>
								<td>${infoMap['ordersVo'].orderId}</td>
								<td>${infoMap['userName']}</td>
								<td>${infoMap['refundVo'].refundId}</td>
								<td>${infoMap['ordersVo'].orderDate}</td>
								<td>${infoMap['ordersVo'].total}</td>
								<td>${infoMap['status']}</td>
								<td>
									<form action="<c:url value="/admin/refund/changeStatus"/>"
										method="post" id="refundComplete">
										<input type="hidden" name="orderId"
											value="${infoMap['ordersVo'].orderId}" />
										<button type=submit class="btn btn-primary"
											onclick="adminRefund(event)">환불 완료</button>
									</form>
									<form action="<c:url value="/admin/refund/cancle"/>"
										method="post" id="refundCancel">
										<input type="hidden" name="orderId"
											value="${infoMap['ordersVo'].orderId}" />
										<button type=submit class="btn btn-primary" onclick="refundCancel(event)">환불 취소</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<c:import url="/WEB-INF/views/admin/includes/admin_footer.jsp"></c:import>

</html>