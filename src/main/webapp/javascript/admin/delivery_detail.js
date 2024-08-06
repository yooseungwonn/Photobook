/**
 * - 설명 - 
 * 관리자:배송 상세 페이지(views/admin/delivery_detail.jsp)에서 사용하는 javascript입니다.
 */

window.addEventListener("load", event => {
	// 클래스 이름이 "edit-btn"인 모든 태그에 
	// 누르면 사촌의 disable 상태를 변경 할 수 있게 설정
	let edit = document.querySelectorAll(".edit-btn");
	edit.forEach((element) => {
		element.addEventListener("click", event => {
			editAble(event);
		})
	});
	
	// 배송 상태의 초깃값을 정해주는 코드
	let deliveryStatusSelector = document.getElementById("delivery_status");
	let deliveryStatusSelectedValue = deliveryStatusSelector.getAttribute("data-default");
	selectorFunction(deliveryStatusSelector, deliveryStatusSelectedValue);
	
	// 앨범 id의 초깃값을 정해주는 코드
	let albumSelector = document.getElementById("albumVo");
	let albumSelectedValue = albumSelector.getAttribute("data-default");
	selectorFunction(albumSelector, albumSelectedValue);
	
	// 앨범 재질의 초깃값을 정해주는 코드
	let albumMaterialSelector = document.getElementById("material");
	let albumMaterialSelectedValue = albumMaterialSelector.getAttribute("data-default");
	selectorFunction(albumMaterialSelector, albumMaterialSelectedValue);

	// 앨범 색상의 초깃값을 정해주는 코드
	let albumColorSelector = document.getElementById("color");
	let albumColorSelectedValue = albumColorSelector.getAttribute("data-default");
	selectorFunction(albumColorSelector, albumColorSelectedValue);
	
	// 앨범 사이즈의 초깃값을 정해주는 코드
	let albumSizeSelector = document.getElementById("albumSize");
	let albumSizeSelectedValue = albumSizeSelector.getAttribute("data-default");
	selectorFunction(albumSizeSelector, albumSizeSelectedValue);
});

/**  수정 버튼을 누르면, 
 * <Input> 태그의 부모의 이전 형제의 첫 사촌을 선택해서,
 * disabled를 풀어주고 
 * 한번 더 누르면 diabled를 걸어주는 함수
 * 
 * disabled가 걸린 태그는 값을 쓸 수가 없다. 
 * 그래서 disabled한 태그(Node)의 다음 형제를 만들어서
 * hidden을 부여해서 값을 쓰겠끔함
 * event : 해당 함수를 적용할 수정 버튼
 */
function editAble(event){	
	let target = event.target;
	let Node = target.parentElement.previousElementSibling.firstElementChild;
	let valueSave = Node.nextElementSibling;
	
	if(target.innerText == "수정")
	{		
		Node.disabled= false;
		target.innerText = "확인";
	} else {
		valueSave.value = Node.value;
		Node.disabled= true;
		target.innerText = "수정";
	}
}

/** 컨트롤러에서 넘겨준 값과, 
 * jsp의 value가 일치하는 것으로 기초 selected가
 * 변하게 해주는 메서드
 * 
 * selector : 메서드를 적용할 <select>태그의 id
 * selectedValue : 컨트롤러에서 가져온 값 [예: ${map[key].value}]
 */ 
function selectorFunction(selector, selectedValue){	
	for(let index = 0; index < selector.options.length; index++)
	{
		if(selectedValue == selector.options[index].value) {
			selector.options[index].selected = true;
			break;
		}
	}
}



