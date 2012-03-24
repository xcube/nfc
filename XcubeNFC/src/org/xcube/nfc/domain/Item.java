package org.xcube.nfc.domain;

import java.math.BigDecimal;

public class Item {

	BigDecimal price;
	Coordinates coordinates;
	ItemInfo info = new ItemInfo();
	
	public Item(ItemInfo itemInfo) {
	    if (null != itemInfo) {
	        this.info = itemInfo;
	    }
	}

	public BigDecimal getPrice() {
		return price;
	} 

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public ItemInfo getInfo() {
		return info;
	}

	public void setInfo(ItemInfo info) {
		this.info = info;
	}
	
	public String getUpc() {
		return info.getUpc();
	}
	
	public String getName() {
		return info.getName();
	}
}
