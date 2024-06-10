package icecream.view;

import java.io.IOException;  
import icecream.controller.IcecreamController;

public class IcecreamViewer {
	
	
	String message;
	
	public void welcome() {
		showMessage("온라인 아이스크림 가게에 오신걸 환영합니다, 원하시는 메뉴의 번호를 입력해주세요.");
	}
	
	public void showMenu() { // 아이스크림 메뉴 출력
		
		System.out.println("===<메뉴 목록>===\n" +
				  "1. 아이스크림 목록\n" +
				  "2. 장바구니 목록 보기\n" +
				  "3. 장바구니 목록 내용 삭제\n" +
				  "4. 아이스크림 수량 변경\n" +
				  "5. 아이스크림 주문\n" +
				  "0. 종료\n 번호 입력 : ");
	}
	
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	//IcecreamController >> view.icecreamSelection
	public void icecreamSelection()  { // 5번 선택시 출력
		 
		System.out.print("\n===<아이스크림>=== \n "
				+ "원하시는 아이스크림 유형의 이름이나 번호를 입력하세요 (예 : 믹스 / 단품) : \n"
				+ "1.아이스크림 단품 \n2.아이스크림 믹스\n>> ");
		
	}

	public void IcecreamList() {
		
		System.out.println("\n===<아이스크림 메뉴>===\n" +
				  "1. 메로나\n" +
				  "2. 돼지바\n" +
				  "3. 누가바\n" +
				  "뒤로 가시려면 b를 입력하시고, 다른 메뉴로 가시려면 메인 메뉴에 명시된 번호들 중 하나를 입력해주세요 >>.");
		
		
	}

}