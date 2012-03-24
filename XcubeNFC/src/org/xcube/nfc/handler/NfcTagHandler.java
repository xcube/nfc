package org.xcube.nfc.handler;

import java.util.Properties;

import android.os.Parcelable;

public interface NfcTagHandler {

	public Properties readTag(Parcelable[] rawMessages);
	
	public void writeTag();
}
