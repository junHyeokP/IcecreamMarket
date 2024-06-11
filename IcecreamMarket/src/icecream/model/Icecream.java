package icecream.model;

public class Icecream {
	
	private int price;
	private String taste;
	private String name;

	Icecream(int price, String taste, String name) {
		this.price = price;
		this.taste = taste;
		this.name = name;
	}

	public String getTaste() {
	   
	    return this.taste;
	}

	public String getName() {
		
		return this.name;
	}


	public int getPrice() {
		
		return this.price;
	}
	@Override
	public String toString() {
		return name + ", " + taste + ", " + price + "원";
	}

}
