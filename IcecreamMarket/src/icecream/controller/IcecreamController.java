package icecream.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import icecream.model.Cart;
import icecream.model.Customer;
import icecream.model.Icecream;
import icecream.model.IcecreamCup;
import icecream.model.IcecreamFridge;
import icecream.view.IcecreamViewer;

public class IcecreamController {
	
	IcecreamViewer view;
	
	Cart iCart;
	
	IcecreamCup icup;
	Icecream icecream; // model에서 import해서 가져오기
	IcecreamFridge fridge; // storage 역할
	
	Customer iCustomer;
	
	String[] menuList = {
			"0. 종료",
			"1. 아이스크림 종류 보기",
			"2. 장바구니 보기", 
			"3. 장바구니에 아이스크림 추가",
			"4. 장바구니 아이스크림 삭제",
			"5. 장바구니 아이스크림 수량 변경",
			"6. 장바구니 비우기",
			"7. 주문"
	};
	
	public IcecreamController(IcecreamFridge fridge, Cart cart, IcecreamViewer view) {
		this.view = view;
		this.fridge = fridge;
		this.iCart = cart;
	}

	public void enterIcecreamMarket() {
		
		welcome();
		registerCustomerInfo();
	    
		int menu;
		
		do {
			menu = view.menuInput(menuList);
			
			
			switch (menu) {
			case 1 -> viewIcecreamInfo(); 
			case 2 -> viewCart();
			case 3 -> addIcecream2Cart();
			case 4 -> deleteIcecreamInCart();
			case 5 -> updateIcecreamInCart();
			case 6 -> resetCart();
			case 7 -> order();
			case 0 -> end();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while(menu != 0);
	}
	
	private void welcome() {
		view.welcome();
	}
	
	private void addIcecream2Cart() {
		
		String ice = selectIcecream(); // 아이스크림 유형 선택
		if (ice.equals("믹스")) {
			view.showTaste();
			String cream = choseTaste();
			icup = icecreamMix(cream); // 컵에 아이스크림 맛들을 넣기
			String name = icup.getTaste(); // 컵에 담긴 맛들을 name에 저장
			iCart.addItem(fridge.getIcecreamCupByName(name, icup)); // 장바구니에 담기
		}	
		else if (ice.equals("단품")) {
		view.displayIcecreamInfo(fridge);
		String name = view.selectIcecreamName(fridge);
		iCart.addItem(fridge.getIcecreamByName(name));
		view.showMessage("장바구니에 아이스크림을 추가하였습니다.");
		}
	}

	private String selectIcecream() { // 1. 유형 선택
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
		view.showMessage("아이스크림 유형을 선택해주세요 (단품 혹은 믹스 입력) >> ");
		return bf.readLine();
		} catch (IOException e) {
			view.showMessage("잘못된 입력이오니 다시 입력해주세요.");
			return selectIcecream();
		}
	}
	
	private String choseTaste() { // 믹스 선택 시 아이스크림컵에 담을 맛들을 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
		view.showMessage("컵에 여러가지 맛들을 층층이 쌓습니다. 넣을 맛들을 입력해주세요. >>");
		String cream = bf.readLine();

		return cream;
		
		} catch (IOException e) {
			view.showMessage("잘못된 입력입니다.");
			return choseTaste();
		}
		
	}
	
	private IcecreamCup icecreamMix(String cream) { // 콘 추가 여부 묻기
		
		IcecreamCup icup = new IcecreamCup(cream);
		boolean corn = false;
		
		if (icecreamCorn()) {// 콘 추가 여부 묻기
			corn = true;
			icup.setCorn(corn); 
			view.showMessage("콘 추가 + 믹스 주문하셨습니다.");
			return icup;
		}
	
		else { 
		view.showMessage("믹스 주문하셨습니다.");	
		return icup;
		}
	}


	private boolean icecreamCorn() {
		return view.askConfirm("컵에 콘 추가 : yes 입력, 취소 : 아무키나 누르세요.", "yes");
	}
	
	
	
	private void registerCustomerInfo() {
		iCustomer = new Customer();	
		view.inputCustomerInfo(iCustomer);
	}

	// Icecream Fridge에서 아이스크림 삭제
	private void deleteIcecreamInStorage() {
		if (!fridge.isEmpty()) {
			 view.showMessage("냉장고에 아이스크림이 없습니다.");
			 return;
		} 
		
			// 아이스크림 창고 보여주기
			viewIcecreamInfo();
			//아이스크림 이름 입력 받기
			String name = view.selectIcecreamName(fridge);
			if (view.askConfirm(">> 해당 아이스크림를 삭제하려면 yes를 입력하세요 : ", "yes")) {
			   	// 해당 아이스크림의 cartItem 삭제
				fridge.deleteItem(name);
				view.showMessage(">> 해당 아이스크림를 삭제했습니다.");
				}
		}


	private void viewIcecreamInfo() {
		view.displayIcecreamInfo(fridge);
	}


	
	private void addIcecream2Fridge() {
		view.showMessage("새로운 아이스크림을 추가합니다.");
		
		// 아이스크림 정보로 Icecream 인스턴스 만들어서 fridge에 추가
		fridge.addIcecream(view.inputString("아이스크림 이름 : "), view.inputString("맛 : "), view.readNumber("가격 : "));
		
	}

	private void end() {
		view.showMessage("IcecreamMarket을 종료합니다.");
	}
	private void order() {
		
		//배송 정보 추가
		addDeliveryInfo();
		//구매 정보 보기 : 장바구니 내역, 배송 정보
		displayOrderInfo();
		//주문할건지 다시 확인
		if (view.askConfirm("정말 주문하시겠습니까 ? yes 아님 no를 입력해주세요.", "yes")) {
			//주문 처리
			view.displayOrder(iCart, iCustomer);
			iCart.resetCart();
		}
	}

	private void addDeliveryInfo() {
		view.inputDeliveryInfo(iCustomer);
	}

	private void displayOrderInfo() {
		view.displayOrder(iCart, iCustomer);
	}

	private void updateIcecreamInCart() {
		// 장바구니 보여주기
		
		if (!iCart.isEmpty()) {
			//아이스크림 이름 입력 받기
			String name = view.selectName(iCart);
			// 수량 입력 받기
			int quantity = view.inputQuantity(0, fridge.getMaxQuantity());
			// 아이스크림 이름에 해당하는 cartItem 가져와서 cartItem quantity set 수량 수정
			iCart.updateQuantity(name, quantity);
		} else if (iCart.isEmpty()) {
			view.showMessage("장바구니가 비어있습니다.");
		}
	}

	private void deleteIcecreamInCart() {
		// 장바구니 보여주기
		view.displayCart(iCart);
		if (!iCart.isEmpty()) {
		//아이스크림 이름 입력 받기
			String name = view.selectName(iCart);
			if (view.askConfirm(">> 해당 아이스크림를 삭제하려면 yes를 입력하세요 : ", "yes")) {
		// 해당 아이스크림 이름이 담긴 cartItem 삭제
				iCart.deleteItem(name);
				view.showMessage(">> 해당 아이스크림을 삭제했습니다.");
			}
		}
	}

	private void resetCart() {
		view.displayCart(iCart);
		
		if (!iCart.isEmpty()) {
			if (view.askConfirm("장바구니를 비우시려면 yes를 입력하세요", "yes")) {
				iCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}
		
	}


	private void viewCart() {
		view.displayCart(iCart);
	}

	

}
