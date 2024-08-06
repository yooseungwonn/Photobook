/**
 * - 설명 - 
 * 사용자:메뉴버튼(views/users/users_index.jsp)을 위해 사용하는 javascript입니다.
 */

let horizontalUnderLine = document.getElementById("horizontal-underline");
let horizontalMenus = document.querySelectorAll("nav ul li a");

horizontalMenus.forEach((menu)=>
	menu.addEventListener("click",(e)=> horizontalIndicator(e))
);

function horizontalIndicator(e) {
	horizontalUnderLine.style.left = e.currentTarget.offsetLeft+"px";
	horizontalUnderLine.style.width = e.currentTarget.offsetWidth+"px";
	horizontalUnderLine.style.top = e.currentTarget.offsetTop+e.currentTarget.offsetHeight+"px";
	
}