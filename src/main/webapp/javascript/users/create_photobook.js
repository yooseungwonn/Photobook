window.addEventListener("load", event => {
    const mainCanvas = document.getElementById('mainCanvas');
    const ctx = mainCanvas.getContext('2d');
    const stickerUpload = document.getElementById('stickerUpload');
    const stickerPreview = document.getElementById('stickerPreview');
    const stickerGallery = document.getElementById('stickerGallery');
    const addCanvasButton = document.getElementById('addCanvasButton');
    const canvasGallery = document.getElementById('canvasGallery');
    const createButton = document.getElementById('createButton');
    const confirmationPopup = document.getElementById('confirmationPopup');
    const overlay = document.getElementById('overlay');
    const cancelOrderButton = document.getElementById('cancelOrder');
    const confirmOrderButton = document.getElementById('confirmOrder');
    const matarial = document.getElementById('material');
    const color = document.getElementById('color');
    const albumSize = document.getElementById('albumSize');
    const oQuantity = document.getElementById('oQuantity');
	const albumId = document.getElementById('albumId');
	const userId = document.getElementById('userId');

    let canvases = [];
    let currentCanvasIndex = 0;
    let backgroundImage = null;
    let stickers = [];
    let activeSticker = null;
    let stickerId = 0;

    mainCanvas.width = 400;
    mainCanvas.height = 600;

    function createNewCanvas() {
        const newCanvas = {
            backgroundImage: null,
            stickers: [],
            dataURL: null
        };
        canvases.push(newCanvas);
        currentCanvasIndex = canvases.length - 1;
        stickers = [];
        activeSticker = null;
        mainCanvas.addEventListener('mousedown', startAction);
        mainCanvas.addEventListener('mousemove', performAction);
        mainCanvas.addEventListener('mouseup', stopAction);
        mainCanvas.addEventListener('mouseleave', stopAction);
        loadBackgroundImage();
        updateCanvas();
        updateCanvasGallery();
    }

    function isPointInSticker(x, y, sticker) {
        return x >= sticker.x && x <= sticker.x + sticker.width &&
               y >= sticker.y && y <= sticker.y + sticker.height;
    }
    
    function isPointInResizeHandle(x, y, sticker) {
        const handleSize = 10;
        const handleX = sticker.x + sticker.width - handleSize / 2;
        const handleY = sticker.y + sticker.height - handleSize / 2;
        return x >= handleX && x <= handleX + handleSize &&
               y >= handleY && y <= handleY + handleSize;
    }
    
    function isPointInRotateHandle(x, y, sticker) {
        const handleRadius = 5;
        const handleX = sticker.x + (sticker.width / 2);
        const handleY = (sticker.y - 20);
        return Math.sqrt((x - handleX) ** 2 + (y - handleY) ** 2) <= handleRadius;
    }
    
    function isPointInConfirmButton(x, y, sticker) {
        const buttonSize = 20;
        const buttonX = sticker.x + sticker.width + 10;
        const buttonY = sticker.y - 10;
        return x >= buttonX && x <= buttonX + buttonSize &&
               y >= buttonY && y <= buttonY + buttonSize;
    }

    function loadBackgroundImage() {
        const img = new Image();
        img.onload = function() {
            backgroundImage = img;
            canvases[currentCanvasIndex].backgroundImage = img;
            drawCanvas();
        }
        img.src = 'background_image1.jpg'; // 실제 이미지 경로로 수정 필요
    }

    function updateCanvas() {
        backgroundImage = canvases[currentCanvasIndex].backgroundImage;
        stickers = canvases[currentCanvasIndex].stickers || [];
        drawCanvas();
        updateStickerGallery();

        // 각 스티커에 대해 이벤트 리스너 재설정
        stickers.forEach(sticker => {
        enableStickerControls(sticker);
    });
    }

    function drawCanvas() {
        ctx.clearRect(0, 0, mainCanvas.width, mainCanvas.height);

        if (backgroundImage) {
            ctx.drawImage(backgroundImage, 0, 0, mainCanvas.width, mainCanvas.height);
        } else {
            ctx.fillStyle = '#f0f0f0';
            ctx.fillRect(0, 0, mainCanvas.width, mainCanvas.height);
        }
    
        // 활성 스티커를 제외한 모든 스티커 그리기
        stickers.filter(s => s !== activeSticker).forEach(drawSticker);
        
        // 활성 스티커를 마지막에 그리기
        if (activeSticker) {
            drawSticker(activeSticker);
        }
        
        canvases[currentCanvasIndex].dataURL = mainCanvas.toDataURL();
        updateCanvasGallery();
    }

    function updateCanvasGallery() {
        canvasGallery.innerHTML = '';
        canvases.forEach((canvas, index) => {
            const thumbnail = document.createElement('img');
            thumbnail.src = canvas.dataURL || 'placeholder.png';
            thumbnail.classList.add('canvasThumbnail');
            thumbnail.addEventListener('click', () => {
                currentCanvasIndex = index;
                updateCanvas();
            });
            canvasGallery.appendChild(thumbnail);
        });
    }

    function updateStickerGallery() {
        stickerGallery.innerHTML = '';
        stickers.forEach(sticker => {
            const thumbnail = document.createElement('img');
            thumbnail.src = sticker.img.src;
            thumbnail.classList.add('stickerThumbnail');
            thumbnail.dataset.stickerId = sticker.id;
            thumbnail.addEventListener('click', () => {
                activateSticker(sticker);
            });
            stickerGallery.appendChild(thumbnail);
        });
        if (activeSticker) {
            showStickerPreview(activeSticker);
        }
    }

    function showStickerPreview(sticker) {
        stickerPreview.innerHTML = '';
        const previewImg = sticker.img.cloneNode();
        previewImg.style.width = '100%';
        previewImg.style.height = '100%';
        stickerPreview.appendChild(previewImg);
    }

    stickerUpload.addEventListener('change', function(e) {
        const files = e.target.files;
        for (let file of files) {
            const reader = new FileReader();
            reader.onload = function(event) {
                const img = new Image();
                img.onload = function() {
                    addStickerToCanvas(img);
                }
                img.src = event.target.result;
            }
            reader.readAsDataURL(file);
        }
    });

    function addStickerToCanvas(img) {
        const sticker = {
            id: stickerId++,
            img: img,
            x: mainCanvas.width / 2 - 50,
            y: mainCanvas.height / 2 - 50,
            width: 100,
            height: 100,
            rotation: 0,
            isFixed: false
        };
        stickers.push(sticker);
        canvases[currentCanvasIndex].stickers = stickers;
        activateSticker(sticker);
        updateStickerGallery();
        drawCanvas();
    }

    function activateSticker(sticker) {
        activeSticker = sticker;
        showStickerPreview(sticker);
        enableStickerControls(sticker);
        drawCanvas();
    }

    function drawSticker(sticker) {
        ctx.save();
        ctx.translate(sticker.x + sticker.width / 2, sticker.y + sticker.height / 2);
        ctx.rotate(sticker.rotation);
        ctx.drawImage(sticker.img, -sticker.width / 2, -sticker.height / 2, sticker.width, sticker.height);
        
        if (sticker === activeSticker && !sticker.isFixed) {
            ctx.strokeStyle = 'blue';
            ctx.lineWidth = 2;
            ctx.strokeRect(-sticker.width / 2, -sticker.height / 2, sticker.width, sticker.height);
            
            ctx.fillStyle = 'blue';
            ctx.fillRect(sticker.width / 2 - 5, sticker.height / 2 - 5, 10, 10);
            
            ctx.beginPath();
            ctx.arc(0, -sticker.height / 2 - 20, 5, 0, Math.PI * 2);
            ctx.fill();

            ctx.fillStyle = 'green';
            ctx.fillRect(sticker.width / 2 + 10, -sticker.height / 2 - 10, 20, 20);
            ctx.fillStyle = 'white';
            ctx.font = '16px Arial';
            ctx.fillText('✓', sticker.width / 2 + 13, -sticker.height / 2 + 5);
        }
        
        ctx.restore();
    }

    function enableStickerControls(sticker) {
         // 이 함수는 이제 각 스티커에 대한 클릭 이벤트만 처리합니다
        mainCanvas.addEventListener('mousedown', (e) => {
            const rect = mainCanvas.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;

            if (isPointInSticker(x, y, sticker)) {
                activateSticker(sticker);
            }
        });
    }
       
    let isDragging = false;
    let isResizing = false;
    let isRotating = false;
    let startX, startY, startWidth, startHeight, startAngle;

    function startAction(e) {
        if (!activeSticker || activeSticker.isFixed) return;

        const rect = mainCanvas.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;

        if (isPointInConfirmButton(x, y, activeSticker)) {
            activeSticker.isFixed = true;
            activeSticker = null;
            drawCanvas();
            return;
        }
    
        if (isPointInRotateHandle(x, y, activeSticker)) {
            isRotating = true;
            const centerX = activeSticker.x + activeSticker.width / 2;
            const centerY = activeSticker.y + activeSticker.height / 2;
            startAngle = Math.atan2(y - centerY, x - centerX) - activeSticker.rotation;
        } else if (isPointInResizeHandle(x, y, activeSticker)) {
            isResizing = true;
            startWidth = activeSticker.width;
            startHeight = activeSticker.height;
            startX = x;
            startY = y;
        } else if (isPointInSticker(x, y, activeSticker)) {
            isDragging = true;
            startX = x - activeSticker.x;
            startY = y - activeSticker.y;
        } else {
            activeSticker = null;
        }
        drawCanvas();
    }

    function performAction(e) {
        if (!activeSticker || activeSticker.isFixed) return;

        const rect = mainCanvas.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;

        if (isDragging) {
            activeSticker.x = x - startX;
            activeSticker.y = y - startY;
        } else if (isResizing) {
            const dx = x - startX;
            const dy = y - startY;
            const ratio = startWidth / startHeight;
            activeSticker.width = Math.max(20, startWidth + dx);
            activeSticker.height = activeSticker.width / ratio;
        } else if (isRotating) {
            const centerX = activeSticker.x + activeSticker.width / 2;
            const centerY = activeSticker.y + activeSticker.height / 2;
            const angle = Math.atan2(y - centerY, x - centerX);
            activeSticker.rotation = angle - startAngle;
        }

        if (isDragging || isResizing || isRotating) {
            drawCanvas();
        }
    }

    function stopAction() {
        isDragging = isResizing = isRotating = false;
    }

    function sendCanvasesToServer() {
        const formData = new FormData();
        formData.append('albumId', albumId.value);
        formData.append('userId', userId.value);

        canvases.forEach((canvas, index) => {
            // 캔버스의 데이터 URL을 Blob으로 변환
            fetch(canvas.dataURL)
                .then(res => res.blob())
                .then(blob => {
                    // Blob을 File 객체로 변환
                    const file = new File([blob], `canvas_${index + 1}.jpg`, { type: 'image/jpeg' });
                    
                    // FormData에 파일 추가
                    formData.append('canvasFiles', file);
                });
        });

        // 모든 캔버스가 처리된 후 서버로 전송
        setTimeout(() => {
            fetch('/Photobook/users/photobookOrder', {
                method: 'POST',
                body: formData
            })
            .then(response => {
				return response.text();
			})
            .then(html => {
			    // 서버로부터 받은 HTML을 페이지에 삽입하거나 처리합니다
			    document.body.innerHTML = html;  // 예시: 전체 페이지를 새로운 HTML로 교체
			})
			.catch((error) => {
			    console.error('Error:', error);
			    alert('전송 중 오류가 발생했습니다.');
			});
        }, 1000); // 모든 캔버스 처리를 위해 1초 대기
    }

    mainCanvas.addEventListener('mousedown', startAction);
    mainCanvas.addEventListener('mousemove', performAction);
    mainCanvas.addEventListener('mouseup', stopAction);
    mainCanvas.addEventListener('mouseleave', stopAction);

    addCanvasButton.addEventListener('click', createNewCanvas);

    createNewCanvas();
    
    createButton.addEventListener('click', function() {
            const orderDetails = document.getElementById('orderDetails');
            orderDetails.innerHTML = `
                <p>커버 재질: ${matarial.value}</p>
                <p>사이즈: ${albumSize.value}</p>
                <p>색상: ${color.value}</p>
                <p>주문 수량: ${oQuantity.value}개</p>`;
            confirmationPopup.style.display = 'block';
            overlay.style.display = 'block';
   });
   
    // 취소 버튼 클릭 시 팝업 닫기
    cancelOrderButton.addEventListener('click', function() {
        confirmationPopup.style.display = 'none';
        overlay.style.display = 'none';
    });
    
    confirmOrderButton.addEventListener('click', function() {
        sendCanvasesToServer();
    });
});