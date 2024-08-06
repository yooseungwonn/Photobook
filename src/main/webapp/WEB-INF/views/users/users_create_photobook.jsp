<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
    <title>사진 업로드 및 미리보기</title>
    <link rel="stylesheet" href='<c:url value="/css/new_create_photobook.css"/>'>
    <script type="text/javascript" 
        src='<c:url value="/javascript/users/create_photobook.js"/>'>
    </script>
</head>
<body>
	<c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>

	<h1>제작</h1>

	<div class="selected-options">
		<h2>선택된 옵션</h2>
		<p>커버 재질: ${param.material}</p>
		<p>사이즈: ${param.albumSize}</p>
		<p>색상: ${param.color}</p>
	</div>
	<input type="hidden" id="material" value="${param.material}"/>
	<input type="hidden" id="albumSize" value="${param.albumSize}"/>
	<input type="hidden" id="color" value="${param.color}"/>
	<input type="hidden" id="oQuantity" value="${param.oQuantity}"/>

    <div id="container">
        <div id="canvasArea">
          <h2>포토북 제작</h2>
          <h3>현재 캔버스</h3>
          <canvas id="mainCanvas"></canvas> 
        </div>
    	<div id="canvasPreview">
       		<button id="addCanvasButton">새 캔버스 추가</button>
  			<h3>추가된 캔버스</h3>	
  			<div id="canvasGallery"></div>
        </div>>
        <div id="stickerArea">
          <h3>파일 선택</h3>
          <input type="file" id="stickerUpload" accept=".jpg, .jpeg" multiple>
          <h3>추가된 사진</h3>
          <div id="stickerGallery"></div>
          <div id="stickerPreview"></div>
        </div>
    </div>

  <button type="button" id="createButton" class="create-button" form="orderForm">제작 완료</button>
      
  <input type="hidden" id="albumId" name="albumId" value="${sessionScope.albumId}">
  <input type="hidden" id="userId" name="userId" value="${sessionScope.authUser.userId}">    
      
   <div class="overlay" id="overlay"></div>
	<div class="popup" id="confirmationPopup">
		<h2>주문 정보 확인</h2>
		<div id="orderDetails"></div>
		<div class="popup-buttons">
			<button id="cancelOrder">취소</button>
			<button id="confirmOrder" onclick="orderConfirm(event)">확인</button>
		</div>
	</div>


</body>

</html>

