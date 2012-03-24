package org.xcube.nfc.record;

public class SmartPosterData {

	private UriRecord uriRecord;
	private TextRecord title;
	
	public SmartPosterData(UriRecord uriRecord, TextRecord title) {
		this.uriRecord = uriRecord;
		this.title = title;
	}

	protected UriRecord getUriRecord() {
		return uriRecord;
	}

	protected void setUriRecord(UriRecord uriRecord) {
		this.uriRecord = uriRecord;
	}

	protected TextRecord getTitle() {
		return title;
	}

	protected void setTitle(TextRecord title) {
		this.title = title;
	}
	
	
}
