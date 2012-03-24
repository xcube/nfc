package org.xcube.nfc.handler;

import java.util.List;
import java.util.Properties;

import org.xcube.nfc.NdefMessageParser;
import org.xcube.nfc.record.ParsedNdefRecord;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.Parcelable;

public class NfcTagHandlerImpl implements NfcTagHandler {

	@Override
	public Properties readTag(Parcelable[] rawMsgs) {
            // When a tag is discovered we send it to the service to be save. We
            // include a PendingIntent for the service to call back onto. This
            // will cause this activity to be restarted with onNewIntent(). At
            // that time we read it from the database and view it.
            NdefMessage[] msgs;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            } else {
                // Unknown tag type
                byte[] empty = new byte[] {};
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                msgs = new NdefMessage[] {msg};
            }
            Properties properties = new Properties();
            for(NdefMessage message : msgs) {
            	List<ParsedNdefRecord> parsedRecords = NdefMessageParser.parse(message);
            	for(ParsedNdefRecord parsedRecord : parsedRecords) {
            		properties.putAll(parsedRecord.getRecordData());
            	}
            }
            return properties;
	}

	@Override
	public void writeTag() {
		// TODO Auto-generated method stub

	}

}
