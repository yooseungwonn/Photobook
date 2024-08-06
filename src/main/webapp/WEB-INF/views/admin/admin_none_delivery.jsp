<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 배송 관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/common_style.css"/>' />
</head>
<body>
	<c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>

	<div class="main-content">
		<div class="header">
			<h2>미처리 배송 조회</h2>
		</div>
		<div class="card">
			<div class="card-header">대기중인 배송 목록</div>
			<div class="card-body">
				<table class="table">
					<thead>
						<tr>
							<th>주문 ID</th>
							<th>주문자명</th>
							<th>배송 ID</th>
							<th>배송 일자</th>
							<th>배송 상태</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${deliveryInfos}" var="infoMap">
							<c:if test="${infoMap['status'] == '배송 준비'}">
								<tr>
									<td>${infoMap['shipmentsVo'].orderId}</td>
									<td>${infoMap['usersVo'].userName}</td>
									<td>${infoMap['shipmentsVo'].shipmentId}</td>
									<td>${infoMap['shipmentDate']}</td>
									<td>${infoMap['status']}</td>

								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/admin/includes/admin_footer.jsp"></c:import>
</body>

</html>