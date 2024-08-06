<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" href="<c:url value='/css/board_style.css' />">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
								href="<c:url value="/users/board/post/${postMap['boardVo'].userId}/${postMap['boardVo'].boardId }"/>">${postMap['boardVo'].title}</a></td>
							<td>${postMap['usersVo'].userName}</td>
							<td>${postMap['boardVo'].regDate}</td>
							<td>${postMap['boardVo'].status}</td>
							<td class="action-links">
							<c:if test="${not empty authUser }">
									<c:if test="${authUser.userId == postMap['boardVo'].userId}">
										<a
											href="<c:url value="/users/board/${postMap['boardVo'].userId}/${postMap['boardVo'].boardId }/modify"/>">수정</a>
										<a
											href="<c:url value="/users/board/${postMap['boardVo'].userId}/${postMap['boardVo'].boardId }/delete"/>" onclick="boardDelete2(event)">삭제</a>
									</c:if>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="board-actions">
			<p>
				<a href="<c:url value="/users/board/write"/>" >글쓰기</a>
				
				
			</p>
			<p>
				<a href="<c:url value="/users/board/mine"/>">내가 쓴 글 보기</a>
			</p>
		</div>
		</section>
	</main>
	<c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>