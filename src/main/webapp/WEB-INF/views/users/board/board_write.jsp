<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
	
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>글 작성</title>
    <link rel="stylesheet" href="<c:url value='/css/board_style.css' />">
	  <script src="<c:url value='/javascript/users/user.js'/>"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
	<c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>
	<main>
	<section id="board">
    <h1>글 작성</h1>
    
    <!-- 게시글을 나타낼 수 있는 vo 작성  -->
    <div class="main-content">
	    <form method="POST" action="<c:url value="/users/board/write"/>" id="boardWrite" >
	    	<label for="title">글 제목</label>
	   		<input type="text" id="title" name="title" required>
	   		<br>
	   		<label for="content">글 내용</label>
	   		<textarea id="content" name="content" rows="5" cols="90">문의사항은 여기 적어주세요</textarea>
	    </form>
	   </div>
    <div class="board-actions">
    <p><a href="<c:url value="/users/board/write"/>" onclick="boardWrite(event)">등록하기</a></p>
    <p><a href="<c:url value="/users/boardList"/>" onclick="boardDelete(event)">돌아가기</a> </p>
    </div>
    </section>
    </main>
    <c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</html>