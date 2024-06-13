package icecreammarket;

import java.io.IOException;

import icecream.controller.IcecreamController;
import icecream.model.Cart;
import icecream.model.IcecreamFridge;
import icecream.view.IcecreamViewer;

public class IcecreamMarket {
	
	public static void main(String[] args) throws IOException {
		
		// model (냉장고)
		IcecreamFridge fridge = new IcecreamFridge();
		Cart cart = new Cart();
		
		// view (뷰어)
		IcecreamViewer view = new IcecreamViewer();
		
		// controller (컨트롤러)
		IcecreamController cntr = new IcecreamController(fridge, cart, view);
		cntr.enterIcecreamMarket();
	}
	
}
