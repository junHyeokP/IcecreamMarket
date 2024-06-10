package icecreammarket;

import java.io.IOException;

import icecream.controller.IcecreamController;
import icecream.view.IcecreamViewer;

public class IcecreamMarket {
	
	public static void main(String[] args) throws IOException {
		
		IcecreamViewer view = new IcecreamViewer();
		view.welcome();
		view.showMenu();
		
		IcecreamController cntr = new IcecreamController(view);
		cntr.inputNum();
	}
	
}
