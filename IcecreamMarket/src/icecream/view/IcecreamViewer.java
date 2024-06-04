package icecream.view;

import java.io.IOException;

import icecream.controller.IcecreamController;

public class IcecreamViewer {
	
	static StringBuilder sb = new StringBuilder();
	String message;
	
	public void welcome() {
		showMessage("온라인 아이스크림 가게에 오신걸 환영합니다, 원하시는 메뉴의 번호를 입력해주세요.");
		
	}
	
	public void mainMenu()throws IOException { // 아이스크림 메뉴 출력
		IcecreamController control = new IcecreamController();
		
		sb.append("===<메뉴 목록>===\n" +
				  "1. 아이스크림 목록\n" +
				  "2. 장바구니 목록 보기\n" +
				  "3. 장바구니 목록 내용 삭제\n" +
				  "4. 아이스크림 수량 변경\n" +
				  "5. 아이스크림 주문\n" +
				  "0. 종료\n").append("번호 입력 : ");
		
		System.out.print(sb);
		
		control.inputNum();
	}
	
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	//IcecreamController >> view.icecreamSelection
	public void icecreamSelection() throws IOException {
		IcecreamController iceControl = new IcecreamController();  
		sb.append("\n===<아이스크림>=== \n").append("원하시는 아이스크림 유형의 이름이나 번호를 입력하세요 (예 : 믹스 / 단품) : \n").append("1.아이스크림 단품 \n2.아이스크림 믹스\n>> ");
		System.out.print(sb + "/n");
		
		iceControl.chosenIce();
	}

	public void IcecreamList() {
		sb.append("\n===<아이스크림 메뉴>===\n" +
				  "1. 메로나\n" +
				  "2. 돼지바\n" +
				  "3. 누가바\n");
		
		System.out.println(sb);
		
	}

	
	
}
