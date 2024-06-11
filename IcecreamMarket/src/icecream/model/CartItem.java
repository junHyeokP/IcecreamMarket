package icecream.model;

public class CartItem {
	Icecream icecream;
	int quantity;
	
	public CartItem(Icecream icecream) {
		this.icecream = icecream;
		this.quantity = 1;
	}
	
	public Icecream getIcecream() {
		return icecream;
	}
	public void setIcecream(Icecream Icecream) {
		this.icecream = icecream;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public int getPrice() {
		return quantity * icecream.getPrice();
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	@Override
	public String toString() {
		return icecream.getName() + ", " + icecream.getTaste() + ", " +  quantity + "개, " + getPrice() + "원";
	}
	
	
	
}