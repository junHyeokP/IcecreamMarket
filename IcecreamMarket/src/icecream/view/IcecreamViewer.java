package icecream.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import icecream.model.Cart;
import icecream.model.Customer;
import icecream.model.IcecreamCup;
import icecream.model.IcecreamFridge;

public class IcecreamViewer {

	
	public void welcome() {
		showMessage("온라인 아이스크림 가게에 오신걸 환영합니다.");
	}
	
	public int menuInput (String[] menuList) { // 아이스크림 메뉴 출력
		
		displayMenu(menuList);
		
		int menu;
		
		do {
			System.out.print(">> 메뉴 선택 (번호 입력) : ");
			menu = inputNumberWithValidation();
			if (menu < 0 || menu >= menuList.length)
				System.out.println("0부터 " + (menuList.length - 1) + "까지의 숫자를 입력해주세요.");
		} while(menu < 0 || menu >= menuList.length);
		return menu;
	}
	
	private int inputNumberWithValidation() {
		Scanner sc = new Scanner(System.in);
		try {
			int number = sc.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 : ");
			return inputNumberWithValidation();
		}
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
	
	//IcecreamController >> view.icecreamSelection
	public void icecreamSelection()  { // 7번 order 선택시 출력
		 
		System.out.print("\n===<아이스크림>=== \n "
				+ "원하시는 아이스크림 유형의 이름이나 번호를 입력하세요 (예 : 믹스 / 단품) : \n"
				+ "1.아이스크림 단품 \n2.아이스크림 믹스\n>> ");
		
	}
	// 메뉴 출력
		private void displayMenu(String[] menuList) {
			System.out.println("=========================================");
			for (int i = 1; i < menuList.length; i++) {
				System.out.println(menuList[i]);
			}
			System.out.println(menuList[0]);
			System.out.println("=========================================");
		}

		// 아이스크림 목록 보여주기
		public void displayIcecreamInfo(IcecreamFridge fridge) {
			for (int i = 0; i < fridge.getNumIcecreams(); i++) {
				String icecreamInfo = fridge.getIcecreamInfo(i);
				System.out.println(icecreamInfo);
			}
		}

		// 장바구니 보여주기
		public void displayCart(Cart cart) {
			if (cart.isEmpty()) {
				System.out.println(">> 장바구니가 비어 있습니다.");
				return;
			}
				
			for (int i = 0; i < cart.getNumItems(); i++) {
				System.out.println(cart.getItemInfo(i));	
			}
			System.out.println("총 금액 : " + cart.getTotalPrice() + "원");
		}

		// fridge에 있는 아이스크림을 이름으로 선택하기
		public String selectIcecreamName(IcecreamFridge fridge) {
				
			String name;
			boolean result;
			do {
				name = inputString("추가할 아이스크림의 이름를 입력하세요 : ");
				result = fridge.isValidIcecream(name);
				if (!result)
					System.out.print("잘못된 아이스크림 이름입니다.");
			} while (!result);
					
			return name;
		}

		// cart에 있는 아이스크림을 이름으로 선택하기
		public String selectName(Cart cart) {
			
			String name;
			boolean result;
			do {
				name = inputString(" 아이스크림 이름 입력 : ");
				result = cart.isValidItem(name);
				if (!result)
					showMessage("잘못된 아이스크림 이름입니다.");
			} while (!result);
			
			return name;
		}

		
		
		// 아이스크림 수량 입력 받기
		public int inputQuantity(int min, int max) {
			int number;
			do {
				number = readNumber(">> 수량 입력 (" + min + " ~ " + max + "): ");
				if (number < min || number > max)
					System.out.println("잘못된 수량입니다.");
			} while (number < min || number > max);
			
			return number;
		}
		
		// 배송 정보 입력 받기 - 주소와 이메일주소가 없는 경우
			public void inputDeliveryInfo(Customer customer) {
				if (customer.getEmail() == null) {
					Scanner input = new Scanner(System.in);
					System.out.println("배송을 위하여 이메일 주소와 배송받을 곳의 주소를 입력받습니다.");
					System.out.print(">> 이메일 : ");
					customer.setEmail(input.nextLine());
					System.out.print(">> 주소 : ");
					customer.setAddress(input.nextLine());
					}
				}

		// 고객 정보 입력 받기
		public void inputCustomerInfo(Customer customer) {
			Scanner input = new Scanner(System.in);
			String phone = "";
			String name = "";
			do {
			System.out.println("온라인 아이스크림 마켓을 이용하시려면 이름과 전화번호를 입력하세요.");
			System.out.print(">> 이름 : ");
			name = input.nextLine();
			customer.setName(name);
			System.out.print(">> 전화번호 : ");
			phone = input.nextLine();
			customer.setPhone(phone);
			if (name.equals("") || phone.equals("")) showMessage("공백은 입력불가입니다.");
			} while(name.equals("") || phone.equals(""));
		}

		
		
		public void displayOrder(Cart cart, Customer customer) {
			
			System.out.println();
			// 장바구니 보여주기
			System.out.println("***** 주문할 아이스크림 ******");
			displayCart(cart);
			
			// 배송 정보 보여주기 - 고객 이름, 전화번호, 주소, 이메일주소
			System.out.println("***** 배송 정보 ******");
			System.out.println(">> 이름 : " + customer.getName());
			System.out.println(">> 전화번호 : " + customer.getPhone());
			System.out.println(">> 이메일 : " + customer.getEmail());
			System.out.println(">> 주소 : " + customer.getAddress());
			System.out.println();

		}
		
		/////////////////////  공용 모듈 ////////////////////////
		
		// 입력된 문자열을 입력했을 때만 true를 반환하는 confirm용
		public boolean askConfirm(String message, String yes, String no) {
			
			System.out.print(message);
			
			Scanner input = new Scanner(System.in);
			String ask = input.nextLine();
			if (ask.equals(yes)) {
				return true;
			}
	
			else if (ask.equals(no)) {
				showMessage("취소하여 메뉴로 다시 돌아갑니다.");
				return false;
			}
			
			else return false;
		}
		
		// 숫자 입력 받기 (숫자가 아닌 문자를 넣으면 예외 처리하고 다시 입력받기)
		public int readNumber(String message) {
			if (message != null || !message.equals(""))
				System.out.print(message);
			
			Scanner input = new Scanner(System.in);
			try {
				int number = input.nextInt();
				return number;
			} catch (Exception e) {
				System.out.print("숫자를 입력하세요 :");
				return readNumber(message);
			}
		}
		
		
		// 문자열 입력 받기
		public String inputString(String msg) {
			Scanner in = new Scanner(System.in);
			System.out.print(msg);
			return in.nextLine();
		}

		public void showTaste() {
			System.out.println("초콜릿 / 바나나 / 바닐라 / 딸기 / 망고 / 커피 맛 중 아무거나 1개 이상 적어주세요.");
		}
		
		public String inputTaste() { // 믹스 선택 시 아이스크림컵에 담을 맛들을 입력
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String cream = null;
			
			try {
			showMessage("컵에 여러가지 맛들을 층층이 쌓습니다. 넣을 맛들을 입력해주세요. >>");
			cream = bf.readLine();
			
			return cream;
			
			} catch (IOException e) {
				showMessage("잘못된 입력입니다.");
				return cream;
				}
			
		}

		public boolean askCorn_firm(String message, String yes, String no) {
			// 콘 추가 여부를 묻는 메서드
			System.out.print(message);
			
			Scanner input = new Scanner(System.in);
			String ask;
			do {
				ask = input.nextLine();
				if (ask.equals(yes)) {
					return true;
				}
	
				else if (ask.equals(no)) {
					return false;
				}
			
				else showMessage("잘못된 입력입니다, 콘을 추가하시려면 yes / 취소는 no를 입력해주세요.");
				
			} while (ask != yes || ask != no);
			
			return false;
		}

		public String[] displayAdminCheck() {
			Scanner sc = new Scanner(System.in);
			
			showMessage("관리자 모드로 들어가시려면 관리자 전용 ID와 PWD를 입력하셔야 합니다. >>");
			
			String[] idpwdArr = new String[2];
			System.out.print("ID >>");
			idpwdArr[0] = sc.nextLine();
			System.out.print("Password >>");
			idpwdArr[1] = sc.nextLine();
			
			return idpwdArr;
			
		}
}