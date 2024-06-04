package icecreammarket;

import java.io.IOException;

import icecream.view.IcecreamViewer;

public class IcecreamMarket {
	
	public static void main(String[] args) throws IOException {
		
		IcecreamViewer view = new IcecreamViewer();
		view.welcome();
		view.mainMenu();
	}
	
}
