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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Item)) {
            return false;
        }
        Item other = (Item) obj;
        if (info == null) {
            if (other.info != null) {
                return false;
            }
        } else if (!info.equals(other.info)) {
            return false;
        }
        return true;
    }
}
