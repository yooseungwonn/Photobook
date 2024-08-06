<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>제품 추가</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href='<c:url value="/css/common_style.css"/>' />
    <script type="text/javascript" 
		src='<c:url value="/javascript/admin/productAdd.js"/>'>
	</script>
</head>
<body>
    <c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>

    <div class="main-content">
        <div class="header">
            <h2>제품 추가</h2>
        </div>
        <div class="card">
            <div class="card-header">제품 추가</div>
            <div class="card-body">
                <form method="post" action="<c:url value='/admin/product/add'/>"
                	enctype="Multipart/form-data">
                    <div class="form-group">
                        <label for="albumId">상품코드</label>
                        <input type="text" id="albumId" name="albumId" placeholder="A000" required>
                    </div>
                    <div class="form-group">
                        <label for="albumPrice">가격</label>
                        <input type="number" id="albumPrice" name="albumPrice" placeholder="가격 입력" min="0" required>
                    </div>
                    <div class="form-group">
                        <label for="material">재질</label>
                        <input type="text" id="material" name="material" placeholder="재질 입력" required>
                    </div>
                    <div class="form-group">
                        <label for="color">색깔</label>
                        <input type="text" id="color" name="color" placeholder="컬러 입력" required>
                    </div>
                    <div class="form-group">
                        <label for="albumSize">사이즈</label>
                        <input type="text" id="albumSize" name="albumSize" placeholder="S/M/B" required>
                    </div>
                    <div class="form-group">
                        <label for="fileUploader">이미지 업로드</label>
                        <input type="file" id="fileUploader" name="fileUploader"/>
                        <div class="preview-container">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">추가</button>
                </form>
            </div>
        </div>
    </div>
	
    <c:import url="/WEB-INF/views/admin/includes/admin_footer.jsp"></c:import>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
