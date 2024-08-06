<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>게시판</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/board_modify.css"/>'>
</head>

<body>
<c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>
	<main>
		<section id="board">
			<h2>게시판</h2>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>상태</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${postList}" var="postMap" varStatus="status">
						<tr>
							<td>${postMap['boardVo'].boardId}</td>
							<td><a
								href="<c:url value="/admin/board/post/${postMap['boardVo'].userId}/${postMap['boardVo'].boardId }"/>">${postMap['boardVo'].title}</a></td>
							<td>${postMap['usersVo'].userName}</td>
							<td>${postMap['boardVo'].regDate}</td>
							<td>${postMap['boardVo'].status}</td>
							<td>
								<c:if test="${not empty authUser }">
									<c:if test="${authUser.userId == postMap['boardVo'].userId || authUser.role.equals('A') }">
									
										<a href="<c:url value="/admin/board/${postMap['boardVo'].userId}/${postMap['boardVo'].boardId }/delete"/>">삭제</a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="board-actions">
			<p>
				<a href="<c:url value="/admin/board/write"/>">글쓰기</a>
				<p><a href="<c:url value="/admin/cs"/>">관리자 페이지로</a></p>
			</p>
			</div>
		</section>
	</main>
	<c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
</body>
</html>