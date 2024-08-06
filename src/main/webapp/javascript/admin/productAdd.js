/**
 * - 설명 - 
 * 관리자:배송 상세 페이지(views/admin/product/productAdd.jsp)에서 사용하는 javascript입니다.
 */

 window.addEventListener("load", event => {
	let fileUploader = document.getElementById("fileUploader");
	let previewContainer = document.querySelector(".preview-container");
	 
	fileUploader.addEventListener('change', function(event) {
            previewContainer.innerHTML = ''; // 기존 미리보기 삭제
            const files = event.target.files;
            for (let i = 0; i < files.length; i++) {
                const file = files[i];
                const reader = new FileReader();
                reader.onload = function(e) {
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.style.width = '200px'; // 미리보기 이미지 크기 조정
                    img.style.height = 'auto';
                    previewContainer.appendChild(img);
                }
                reader.readAsDataURL(file);
            }
    });
 });