<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
	
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/board_modify.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- TODO: 글 제목과 연결 
    	<title>${title}</title>
    	-->
    <title>글 내용</title>
    <!-- 여기 변경 -->

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
   	<p>답글</p>
    <table>
    <tr>
			<td>${commentVo.commentId}</td>
			<td>${commentVo.userName} 님에게</td>
			<td>${commentVo.content}</td>
			<td>${commentVo.comDate}</td>
		</tr>
	</table> 
    <hr>
	   
	   
    </div>    
    
    <p><a href="<c:url value="/users/boardList"/>">돌아가기</a></p>
 
    <c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</section>
	</main>
</body>

</html>