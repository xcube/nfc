package org.xcube.nfc;


import java.math.BigDecimal;
import java.util.Properties;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.handler.NfcTagHandler;
import org.xcube.nfc.handler.NfcTagHandlerImpl;
import org.xcube.nfc.handler.TagField;
import org.xcube.nfc.service.ItemInfoService;
import org.xcube.nfc.service.ItemInfoServiceImpl;

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
    NfcTagHandler tagHandler = new NfcTagHandlerImpl();

    private Properties tagData = new Properties();
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        itemInfoService.getItem(null);
        super.onCreate(savedInstanceState);
        setMainView();
        resolveIntent(getIntent());
        Item item = getItem();
        addItem(item);
    }

    private Item getItem() {
		
		 if(!tagData.isEmpty()) {
	        	
	        	String type = tagData.getProperty(TagField.TYPE.getKey());
	        	String upc = tagData.getProperty(TagField.UPC.getKey());
	        	String price = tagData.getProperty(TagField.PRICE.getKey());
	        	if(null == upc || price == null) {
	        		return null;
	        	}
	        	
	        	try {
	        		Item item = itemInfoService.getItem(upc);
					return item;
				} catch (NumberFormatException e) {
					Log.e(getClass().getName(), e.getMessage());
				}
		 }
		
		return null;
	}

	public void addItem(Item item) {

		if(null != item) {
	        TableRow itemsTableRow = (TableRow) findViewById(R.id.items);
	        	
	        itemsTableRow.addView(getTextView(item.getUpc()));
	       	itemsTableRow.addView(getTextView(item.getName()));
	       	itemsTableRow.addView(getTextView(String.valueOf(item.getCalories())));
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
            tagData = tagHandler.readTag(rawMsgs);
            
        } else {
            Log.e(TAG, "Unknown intent " + intent);
            finish();
            return;
        }
    }
}