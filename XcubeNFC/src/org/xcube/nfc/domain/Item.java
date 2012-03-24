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
		if(null != info) {
			return info.getUpc();
		}
		return "00000000";
	}
	
	public String getName() {
		if(null != info) {
			return info.getName();
		}
		return "No Name Set";
	}
	
	public String getImageUrl() {
		if(null != info) {
			return info.getImg();
		}
		return "";
	}
}
