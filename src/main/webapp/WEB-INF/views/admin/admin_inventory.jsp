<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객 센터</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/common_style.css"/>' />
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/pagination_style.css"/>' />
<script src="<c:url value='/javascript/admin/admin.js'/>"></script>
</head>

<body>
	<c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>

	<div class="main-content">
		<div class="header">
			<h2>재고 관리</h2>
		</div>
		<div class="card">
			<div class="card-header">재고 검색</div>
			<div class="card-body">
				<form action="<c:url value="/admin/inventory/search"/>" method="GET">
					<div class="form-group">
						<input type="text" name="keyword" placeholder="상품 번호 검색">
					</div>
					<button type="submit" class="btn btn-primary">검색</button>
				</form>
				<br> <a href="<c:url value="/admin/inventory"/>"><button
						type="submit" class="btn btn-primary">초기화</button></a>

			</div>
		</div>
		<div class="card">
			<div class="card-header">상품 목록</div>
			<div class="card-body">
				<table class="table">
					<thead>
						<tr>
							<th>앨범ID</th>
							<th>가격</th>
							<th>수량</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<!-- 검색 결과가 있을 경우 검색된 항목을 표시 -->
						<c:if test="${not empty invenDetail}">
							<tr>
								<td>${invenDetail.albumId}</td>
								<td>${invenDetail.albumPrice}</td>
								<td>${invenDetail.aQuantity}</td>
								<td>
									<form action="<c:url value='/admin/inventory/store'/>"
										method="GET" id="store">
										<input type="number" name="aQuantity" value="0" min="0"
											step="10" id="aQuantity"> <input type="hidden"
											name="albumId" value="${invenDetail.albumId}">
										<button class="btn btn-primary" onclick="store(event)">입고</button>
									</form>
								</td>
							</tr>
						</c:if>
						<!-- 전체 재고 목록을 표시 -->
						<c:forEach items="${invenList}" var="invenVo">
							<tr>
								<td>${invenVo.albumId}</td>
								<td>${invenVo.albumPrice}</td>
								<td>${invenVo.aQuantity}</td>
								<td>
									<form action="<c:url value='/admin/inventory/store'/>"
										method="GET">
										<input type="number" name="aQuantity" value="0" min="0"
											step="10" id="aQuantity"> <input type="hidden"
											name="albumId" value="${invenVo.albumId}">
										<button type="submit" class="btn btn-primary"
											onclick="store(event)">입고</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<footer>
			<div class="pagination">
				<c:if test="${currentPage > 1}">
					<a
						href="<c:url value='/admin/inventory?page=${currentPage - 1}&size=5'/>">
						< </a>
				</c:if>

				<c:forEach begin="1" end="${totalPages}" var="pageNum">
					<c:choose>
						<c:when test="${pageNum == currentPage}">
							<span>${pageNum}</span>
						</c:when>
						<c:otherwise>
							<a
								href="<c:url value='/admin/inventory?page=${pageNum}&size=5'/>">${pageNum}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage < totalPages}">
					<a
						href="<c:url value='/admin/inventory?page=${currentPage + 1}&size=5'/>">></a>
				</c:if>
			</div>
		</footer>
	</div>
	<script>
		// 입고
		function store(event) {
			event.preventDefault(); // 기본 폼 제출 방지

			const form = event.target.parentNode;
			const adminConfirmed = confirm(`해당 상품을 입고하시겠습니까? 수량을 잘 확인해주세요.`);
			if (adminConfirmed) {

				form.submit(); // 폼 제출
			}
		}
	</script>
</body>
</html>