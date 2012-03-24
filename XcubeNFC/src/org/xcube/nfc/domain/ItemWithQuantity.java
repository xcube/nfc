package org.xcube.nfc.domain;

public class ItemWithQuantity {
	private Item item = null;
	private int quantity = 0;
	
	public ItemWithQuantity(Item item) {
		this.item = item;
		this.quantity = 1;
	}
	
	public ItemWithQuantity(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public int incQuantity() {
		return ++quantity;
	}
	
	public int decQuantity() {
		return --quantity;
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
