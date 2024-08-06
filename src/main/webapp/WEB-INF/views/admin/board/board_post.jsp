<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
	
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- TODO: 글 제목과 연결 
    	<title>${title}</title>
    	-->
    <title>글 내용 페이지</title>
    <!-- 여기 변경 -->
    
  	<link type="text/css" 
		rel="stylesheet" 
		href='<c:url value="/css/board_modify.css"/>'>
</head>

<body>
	<c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>
	<main>
	<section id="board">
	<div class="main-content">
	<!-- postVo 요청 혹은 각 데이터를 요청 -->
	
	<h2>제목 : ${vo['boardVo'].title}</h2>
    <p>글번호 : ${vo['boardVo'].boardId}</p>
    <p>작성자 : ${vo['usersVo'].userName}</p>
    <p>내용 : ${vo['boardVo'].content}</p>
    
    <!-- 댓글 기능 지원 -->
    <hr>
   	<p>댓글</p>
    <table>
		<tr>
			<td>${commentVo.commentId}</td>
			<td>${commentVo.userName} 님에게</td>
			<td>${commentVo.content}</td>
			<td>${commentVo.comDate}</td>
		</tr>
	</table> 
    <hr>
    <c:if test="${!hasComment}">
	    <form method="POST" action="<c:url value="/admin/comment/write"/>" >
	    <input type="hidden" name="boardId" value="${vo['boardVo'].boardId }">
	   		<label for="content">관리자 코멘트</label>
	   		<textarea id="content" name="content" rows="5" cols="100">댓글은 여기 적어주세요</textarea>
	        <button type="submit">작성하기</button>
	    </form>
	   </c:if>
	   <c:if test="${hasComment }">
	   	<p>이미 댓글을 작성했습니다.</p>
	   </c:if>
    </div>    
    <div class="board-actions">
    <p><a href="<c:url value="/admin/boardList"/>">돌아가기</a></p><br>
    <p><a href="<c:url value="/admin/cs"/>">관리자 페이지로</a></p>
 	</div>
    <c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
	</section>
	</main>
</body>

</html>
