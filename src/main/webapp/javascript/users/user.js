document.addEventListener('DOMContentLoaded', function () {
    window.existingValues = {
        userName: document.getElementById('userName').value,
        password: document.getElementById('password').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        address: document.getElementById('address').value
    };
});

// 사용자 게시판 수정 전
function boardModify(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("게시글을 수정하시겠습니까?");
    if (userConfirmed) {
       document.getElementById('boardModify').submit();
    }
}

// 게시글 수정중 취소 버튼
function boardCancel(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("게시글 수정을 취소하시겠습니까? 기존 양식은 삭제됩니다.");
    if (userConfirmed) {
        window.location.href = event.currentTarget.href; 
    }
}

// 사용자 게시판 글 작성
function boardWrite(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("게시글을 등록하시겠습니까?");
    if (userConfirmed) {
       document.getElementById('boardWrite').submit();
    }
}

// 사용자 게시판 글 삭제
function boardDelete(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("게시글을 삭제하시겠습니까? 기존 양식은 삭제됩니다.");
    if (userConfirmed) {
        window.location.href = event.currentTarget.href; 
    }
}

function boardDelete2(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("게시글을 삭제하시겠습니까?");
    if (userConfirmed) {
        window.location.href = event.currentTarget.href; 
    }
}

// 주문 창 최종 알람
function orderConfirm(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("주문이 완료되었습니다.");
    const popup = document.getElementById('confirmationPopup');
    popup.style.display = 'none';
}

function profileUpdate(event) {
    event.preventDefault(); 

    const userName = document.getElementById('userName').value;
    const password = document.getElementById('password').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const address = document.getElementById('address').value;

    if (userName === window.existingValues.userName &&
        password === window.existingValues.password &&
        phoneNumber === window.existingValues.phoneNumber &&
        address === window.existingValues.address) {

        alert("다시 확인해주세요");
        return;
    }

    const userConfirmed = confirm("프로필을 업데이트하시겠습니까?");
    
    if (userConfirmed) {
        const form = document.getElementById('profileUpdate');
        form.submit();
    }
}




// 사용자 로그아웃
function logOut(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("로그아웃 하시겠습니까?");
    if (userConfirmed) {
        window.location.href = event.currentTarget.href; 
    }
}

// 관리자 페이지로 이동시
function goAdmin(event) {
    event.preventDefault(); 
    const userConfirmed = confirm("관리자 페이지로 이동하시겠습니까?");
    if (userConfirmed) {
        window.location.href = event.currentTarget.href; 
    }
}