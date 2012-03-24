package org.xcube.nfc;


import java.util.Map.Entry;
import org.xcube.nfc.domain.Item;
import org.xcube.nfc.service.ItemInfoService;
import org.xcube.nfc.service.ItemInfoServiceImpl;
import java.util.Properties;

import org.xcube.nfc.handler.NfcTagHandler;
import org.xcube.nfc.handler.NfcTagHandlerImpl;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TableRow;
import android.widget.TextView;

public class XcubeNFCActivity extends Activity {

    private static final String TAG = "ViewTag";
    private static final String COUNT_LABEL = "";
    private static final String ITEM_LABEL = "item";
    private static final String CALORIES_LABEL = "calories";
    private static final String PRICE_LABEL = "price";

    private ItemInfoService itemInfoService = new ItemInfoServiceImpl();
    private Properties tagData = new Properties();
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        itemInfoService.getItem(null);
        super.onCreate(savedInstanceState);
        setMainView();
        resolveIntent(getIntent());
        addItem(null);
    }

    public void addItem(Item item) {

        TableRow itemsTableRow = (TableRow) findViewById(R.id.items);

        /*
        itemsTableRow.addView(getTextView("1"));
        itemsTableRow.addView(getTextView("beef"));
        itemsTableRow.addView(getTextView("300"));
        itemsTableRow.addView(getTextView("2.99"));
        */
        
        if(!tagData.isEmpty()) {
        	for(Entry<Object,Object> data: tagData.entrySet()) {
        		String key = (String)data.getKey();
        		String value = (String)data.getValue();
        		itemsTableRow.addView(getTextView(key));
        		itemsTableRow.addView(getTextView(value));
        		
        	}
        }
    }

    public TextView getTextView(String text) {

        TextView textView = new TextView(this);
        textView.setText(text);
        return textView;
    }

    private void setMainView() {

        setContentView(R.layout.main);

        TextView countLabel = (TextView) findViewById(R.id.heading_count_label);
        countLabel.setText(COUNT_LABEL);

        TextView itemLabel = (TextView) findViewById(R.id.heading_item_label);
        itemLabel.setText(ITEM_LABEL);

        TextView caloriesLabel = (TextView) findViewById(R.id.heading_calories_label);
        caloriesLabel.setText(CALORIES_LABEL);

        TextView priceLabel = (TextView) findViewById(R.id.heading_price_label);
        priceLabel.setText(PRICE_LABEL);
    }
    
    void resolveIntent(Intent intent) {
        // Parse the intent
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            // When a tag is discovered we send it to the service to be save. We
            // include a PendingIntent for the service to call back onto. This
            // will cause this activity to be restarted with onNewIntent(). At
            // that time we read it from the database and view it.
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NfcTagHandler tagHandler = new NfcTagHandlerImpl();
            tagData = tagHandler.readTag(rawMsgs);
            /*
            Intent sendData = new Intent(this, FakeTagsActivity.class);
            sendData.putExtra("info", COUNT_LABEL.getText().toString);
            startActivity(sendData);
            */
        } else {
            Log.e(TAG, "Unknown intent " + intent);
            finish();
            return;
        }
    }
}