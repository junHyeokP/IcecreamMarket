package icecream.model;

public class Icecream {
	
	private int price;
	private String taste;
	private String name;

	Icecream(String name, String taste, int price) {
		
		this.name = name;
		this.taste = taste;
		this.price = price;
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
