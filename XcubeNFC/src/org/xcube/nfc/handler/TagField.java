package org.xcube.nfc.handler;

public enum TagField {
	TYPE("type"), UPC("upc"), PRICE("price");
	
	private String key;
	
	private TagField(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}
