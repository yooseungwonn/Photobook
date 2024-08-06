<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8">
<title>제작</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/photobook_style.css"/>'>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

	<c:import url="/WEB-INF/views/users/includes/users_header.jsp"></c:import>

	<main>
		<section class="photobook-container">
			<h2>포토북 제작</h2>
			<c:if test="${not empty error}">
    <p style="color: red;">${error}
    </c:if>
			<form action="<c:url value='/users/create_photobook'/>" method="post">
				<div class="form-group">
					<label for="material">커버 재질:</label> <select id="material"
						name="material">
						<option value="LINEN">린넨</option>
						<option value="LEATHER">가죽</option>
						<option value="HARD">하드</option>
						<option value="SOFT">소프트</option>
					</select>
				</div>
				<div class="form-group">
					<label for="albumSize">사이즈:</label> <select id="albumSize"
						name="albumSize">
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="B">B</option>
					</select>
				</div>
				<div class="form-group">
					<label for="color">색상:</label> <select id="color" name="color">
						<option value="GRAY">회색</option>
						<option value="BLUE">파란색</option>
						<option value="BROWN">갈색</option>
					</select>
				</div>
				<div class="form-group">
					<label for="quantity">수량:</label> <input type="number"
						id="oQuantity" name="oQuantity" min="1" max="100" value="1" required>
				</div>
				<div class="form-group">
                    <label for="price">가격:</label>
                    <span id="price">0</span>원
                </div>
				<div class="form-group">
					<button type="submit">만들기</button>
				</div>
			</form>
		</section>

        <script>
        document.addEventListener('DOMContentLoaded', function() {
            const material = document.getElementById('material');
            const albumSize = document.getElementById('albumSize');
            const color = document.getElementById('color');
            const quantity = document.getElementById('oQuantity');
            const priceDisplay = document.getElementById('price');

            const prices = {
                LINEN: { S: 15000, M: 20000 },
                LEATHER: { S: 25000, M: 35000, B: 50000 },
                HARD: { S: 20000, M: 30000, B: 40000 },
                SOFT: { S: 10000, M: 15000 }
            };

            function updateOptions() {
                const materialValue = material.value;
                albumSize.innerHTML = '';
                Object.keys(prices[materialValue]).forEach(s => {
                    const option = document.createElement('option');
                    option.value = s;
                    option.textContent = s;
                    albumSize.appendChild(option);
                });

                color.innerHTML = '';
                const colors = materialValue === 'LEATHER' ? ['GRAY', 'BROWN'] : ['GRAY', 'BLUE', 'BROWN'];
                colors.forEach(c => {
                    const option = document.createElement('option');
                    option.value = c;
                    option.textContent = c === 'GRAY' ? '회색' : (c === 'BLUE' ? '파란색' : '갈색');
                    color.appendChild(option);
                });
                
                calculatePrice();
            }

            function calculatePrice() {
                const materialValue = material.value;
                const sizeValue = albumSize.value;
                const basePrice = prices[materialValue][sizeValue];
                const totalPrice = basePrice * parseInt(quantity.value);
                priceDisplay.textContent = totalPrice.toLocaleString();
            }

            material.addEventListener('change', updateOptions);
            albumSize.addEventListener('change', calculatePrice);
            color.addEventListener('change', calculatePrice);
            quantity.addEventListener('input', calculatePrice);

            updateOptions();
        });
        </script>
         <c:if test="${not empty error}">
            <script>
                alert('${error}');
            </script>
        </c:if>
    </main>

    <c:import url="/WEB-INF/views/users/includes/users_footer.jsp"></c:import>
</body>
</html>