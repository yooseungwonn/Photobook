

	// 대시 보드 각 미처리 관련 링크

	function dashBoard1(event) {
		event.preventDefault();
		const adminConfirmed = confirm("주문 관리로 이동하시겠습니까?");
		if (adminConfirmed) {
			window.location.href = event.currentTarget.href;
		}
	}

	function dashBoard2(event) {
		event.preventDefault();
		const adminConfirmed = confirm("배송 관리로 이동하시겠습니까?");
		if (adminConfirmed) {
			window.location.href = event.currentTarget.href;
		}
	}

	function dashBoard3(event) {
		event.preventDefault();
		const adminConfirmed = confirm("환불 관리로 이동하시겠습니까?");
		if (adminConfirmed) {
			window.location.href = event.currentTarget.href;
		}
	}

	function dashBoard4(event) {
		event.preventDefault();
		const adminConfirmed = confirm("문의 관리로 이동하시겠습니까?");
		if (adminConfirmed) {
			window.location.href = event.currentTarget.href;
		}
	}

	// 고객 관리 수정 버튼 누를때 (post 이전 처리)
	function userModify(event) {
		event.preventDefault();
		const adminConfirmed = confirm("고객 정보를 수정하시겠습니까?");
		if (adminConfirmed) {
			document.getElementById('updateForm').submit();
		}
	}

	// 제품 관리 수정 버튼 누를때 (post 이전 처리)
	function productModify(event) {
		event.preventDefault();
		const adminConfirmed = confirm("제품 정보를 수정하시겠습니까?");
		if (adminConfirmed) {
			document.getElementById('form1').submit();
		}
	}

	// 배송 생성 버튼 눌렀을때
	function createShipment(event) {
		event.preventDefault();
		const adminConfirmed = confirm("해당 상품을 배송하시겠습니까?");
		if (adminConfirmed) {
			document.getElementById('createShipment').submit();
		}
	}

	// 환불 생성 버튼 눌렀을때
	function createRefund(event) {
		event.preventDefault();
		const adminConfirmed = confirm("해당 상품을 환불처리하시겠습니까?");
		if (adminConfirmed) {
			document.getElementById('createRefund').submit();
		}
	}

// 환불 완료
function adminRefund(event) {
		event.preventDefault();
		const adminConfirmed = confirm("환불 완료 처리하시겠습니까?");
		if (adminConfirmed) {
			document.getElementById('refundComplete').submit();
		}
	}
// 환불 취소
function refundCancel(event) {
		event.preventDefault();
		const adminConfirmed = confirm("환불을 취소하시겠습니까?");
		if (adminConfirmed) {
			document.getElementById('refundCancel').submit();
		}
	}


// 상품 삭제
function deleteProduct(event) {
		event.preventDefault();
		const adminConfirmed = confirm("수정 하시겠습니까?");
		if (adminConfirmed) {
			window.location.href = event.currentTarget.href;
		}
	}




