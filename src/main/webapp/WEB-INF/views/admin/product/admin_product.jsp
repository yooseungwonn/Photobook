<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>제품 관리</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/common_style.css"/>' />
</head>
<body>
	<c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>

	<div class="main-content">
		<div class="header">
			<h2>제품 관리</h2>
		</div>
		<div class="card">
			<div class="card-header">제품 검색</div>
			<div class="card-body">
				<form method="get" action="<c:url value='/admin/products/search'/>">
					<div class="form-group">
						<input type="text" id="search-input" name="keyword" placeholder="상품ID 검색">
					</div>
					<button type="submit" class="btn btn-primary">검색</button>
				</form>
			</div>
		</div>
		<div class="card">
			 <div class="card-header">제품 목록
        	<form method="get" action="<c:url value='/admin/product/add'/>" style="float: right;">
            <button class="btn btn-primary">제품 추가</button>
        </form>
    </div>
			<div class="card-body">
				<table class="table">
					<thead>
						<tr>
							<th>번호</th>
							<th>이미지</th>
							<th>상품코드</th>
							<th>가격</th>
							<th>사이즈</th>
							<th colspan="2">편집</th>
						</tr>
					</thead>

					<tbody>
						<c:set var="index" value="1"/>
						<c:forEach var="entry" items="${ProductMap}" varStatus="status">
							<tr>
								<td>${index}
									<c:set var="index" value="${index + 1}"/>
								</td>
								<td><img width = "200em" src="<c:url value="${entry['imgSrc']}"/>"/></td>
								<td>${entry['albumVo'].albumId}</td>
								<td>${entry['inventoryVo'].albumPrice}</td>
								<td>${entry['albumVo'].albumSize}</td>
								<td>
								<a class="btn btn-primary" href="<c:url value="/admin/product/productEdit?albumId=${entry['albumVo'].albumId}"/>">
										수정
								</a>
								<a class="btn btn-primary" href="<c:url value="/admin/product/delete?albumId=${entry['albumVo'].albumId}"/>">
										삭제
								</a></td>
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
