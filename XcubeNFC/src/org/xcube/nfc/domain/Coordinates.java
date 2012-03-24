package org.xcube.nfc.domain;

public class Coordinates {
	private long latitude;
	private long longitude;
	
	public Coordinates(String commaSeparatedCoordinates) {
		String[] coordinates = commaSeparatedCoordinates.split(",");
		this.latitude = Long.parseLong(coordinates[0]);
		this.longitude = Long.parseLong(coordinates[1]);
	}
	
	public Coordinates(long latitude, long longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	protected long getLatitude() {
		return latitude;
	}

	protected void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	protected long getLongitude() {
		return longitude;
	}

	protected void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	
	
}
