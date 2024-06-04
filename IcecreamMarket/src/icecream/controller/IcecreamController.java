package icecream.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import icecream.view.IcecreamViewer;

public class IcecreamController {
	
	private int menuNum;
	
	public int inputNum()throws IOException { // 아이스크림 메뉴 번호입력
		IcecreamViewer view = new IcecreamViewer();
		
		try {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		this.menuNum = Integer.parseInt(bf.readLine());
		return menuNum;
		} catch (Exception e) {
			view.showMessage("잘못된 입력입니다.");
		}
		return -1;
	}
	
	private void selectMenu(int menuNum)throws IOException { // menu 입력, 0을 입력하기 전까지 view와 controller속 메서드를 왕복
		IcecreamViewer view = new IcecreamViewer();
		
		do {
		
			switch(menuNum) {
			case 1 : view.IcecreamList();
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 5 : view.icecreamSelection(); break;
			case 0 : view.showMessage("종료합니다."); break;
			default : view.showMessage("없는 메뉴거나 잘못된 입력입니다."); break;
			}
			
		}while(menuNum != 0);
	}

	public void chosenIce()throws IOException { // 아이스크림 메뉴 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String cream = bf.readLine();
		selectIcecreamVar(cream);
	}

	private void selectIcecreamVar(String cream) { // 메인메뉴 스위치 1번 >> view.icecreamSelection >> choosenIce() >> selectIcecreamVar 순서대로 호출됨
		IcecreamViewer view = new IcecreamViewer();
		if (cream.contains("1") || cream.contains("단품")) {
			view.showMessage("단품으로 주문하셨습니다.");
		}
		else if (cream.contains("2") || cream.contains("믹스")) {
			view.showMessage("믹스로 주문하셨습니다.");
		} else {
			view.showMessage("없는 메뉴거나 잘못된 입력입니다.");
		}
		
	}

}
