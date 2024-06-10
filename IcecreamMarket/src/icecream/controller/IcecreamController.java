package icecream.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import icecream.view.IcecreamViewer;

public class IcecreamController {
	
	private int menuNum;
	IcecreamViewer view;
	
	public IcecreamController(IcecreamViewer view) {
		this.view = view;
	}
	
	public void inputNum()throws IOException { // 아이스크림 메뉴 번호입력
		
		if (menuNum == -1) {
			menuNum = 0;
			return;
		}
		
		try {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		menuNum = Integer.parseInt(bf.readLine());
		selectMenu();
		} catch (Exception e) {
			view.showMessage("잘못된 입력입니다.");
			inputNum();
		}
		
	}
	
	private void selectMenu()throws IOException { // menu 입력, 0을 입력하기 전까지 mainmenu와  selectMenu메서드를 반복
		
		do {
		
			switch(menuNum) {
			case 1 : view.IcecreamList(); goBack(); break;
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 5 : view.icecreamSelection(); chosenIce(); break;
			case 0 : view.showMessage("종료를 원하시면 yes / 혹은 아무키나 입력해주세요.");
					 areYouSure();
					 break;
			default : view.showMessage("없는 메뉴거나 잘못된 입력입니다."); break;
			}
			
			inputNum();
			
		}while(menuNum != 0);
		
		
		view.showMessage("종료합니다");
	}
	
	

	private void goBack()throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		view.showMenu();
		String str = bf.readLine();
		
	}

	private int areYouSure()throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String sure = bf.readLine();
		
		if (sure.equals("yes")) return menuNum = -1;
		
		else return menuNum;
	} 

	private void chosenIce()throws IOException { // 아이스크림 메뉴 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String cream = bf.readLine();
		selectIcecreamVar(cream);
	}

	private void selectIcecreamVar(String cream)throws IOException { // 메인메뉴 스위치 1번 >> view.icecreamSelection >> choosenIce() >> selectIcecreamVar 순서대로 호출됨
		
		if (cream.contains("1") || cream.equals("단품")) {
			view.showMessage("\n단품으로 주문하셨습니다.");
			return;
		}
		else if (cream.contains("2") || cream.equals("믹스")) {
			view.showMessage("\n믹스로 주문하셨습니다.");
			return;
		} else {
			view.showMessage("\n없는 메뉴거나 잘못된 입력입니다.");
			chosenIce();
		}
		
	}

}
