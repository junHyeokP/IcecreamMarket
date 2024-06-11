package icecream.model;

import java.util.ArrayList;

public class Cart {
private ArrayList<CartItem> itemList = new ArrayList<>();
	
	public boolean isEmpty() {
		return itemList.isEmpty();
	}

	public ArrayList<CartItem> getItemList() {
		return itemList;
	}

	public int getNumItems() {
		return itemList.size();
	}

	public String getItemInfo(int index) {
		return itemList.get(index).toString();
	}
	
	public void addItem(Icecream icecream) {
		
		CartItem item = getCartItem(icecream);
		if (item == null) {
			itemList.add(new CartItem(icecream));
		}
		else {
			item.addQuantity(1);
		}
	}

	private CartItem getCartItem(Icecream icecream) {
		
		for (CartItem item : itemList) {
			if (item.getIcecream() == icecream) return item;
		}
		
		return null;
	}
	
	private CartItem getCartItem(String name) {
		
		for (CartItem item : itemList) {
			if (item.icecream.getName().equals(name)) return item;
		}
		return null;
	}
	
	public void deleteItem(Icecream icecream) {
		itemList.remove(icecream);
	}

	public void resetCart() {
		this.itemList.clear();
	}

	public boolean isValidItem(String name) {
		for (CartItem item : itemList) {
			if (item.icecream.getName().equals(name)) return true;
		}
		return false;
	}
	
	public void deleteItem(String name) {
		CartItem item = getCartItem(name);
		itemList.remove(item);
	}

	public void updateQuantity(String name, int quantity) {
		// 목록에서 아이스크림 이름 삭제
		if (quantity == 0) deleteItem(name);
		else {
			CartItem item = getCartItem(name);
			item.setQuantity(quantity);
		}
		
	}
	
	public int getTotalPrice() {
		int totalPrice = 0;
		for (CartItem item : itemList) {
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}

}
