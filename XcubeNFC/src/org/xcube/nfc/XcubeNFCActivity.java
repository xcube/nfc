package org.xcube.nfc;


import java.math.BigDecimal;
import java.util.Properties;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.domain.ItemInfo;
import org.xcube.nfc.domain.ItemWithQuantity;
import org.xcube.nfc.handler.NfcTagHandler;
import org.xcube.nfc.handler.NfcTagHandlerImpl;
import org.xcube.nfc.handler.TagField;
import org.xcube.nfc.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import org.xcube.nfc.service.BasketService;
import org.xcube.nfc.service.BasketServiceImpl;
import org.xcube.nfc.service.FridgeService;
import org.xcube.nfc.service.FridgeServiceImpl;
import org.xcube.nfc.service.ItemInfoService;
import org.xcube.nfc.service.ItemInfoServiceImpl;
import org.xcube.nfc.util.LayoutUtil;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class XcubeNFCActivity extends Activity {
	
	private static final String BASKET_ROW_TAG = "basket_row";
	private static final String TAG = "ViewTag";
    private static final String COUNT_LABEL = "Amount";
    private static final String ITEM_LABEL = "Item";
    private static final String CALORIES_LABEL = "Kcal";
    private static final String PRICE_LABEL = "Price";

    private FridgeService fridgeService = new FridgeServiceImpl();
    private BasketService basketService = new BasketServiceImpl();
    private ItemInfoService itemInfoService = new ItemInfoServiceImpl();
    private NfcTagHandler tagHandler = new NfcTagHandlerImpl();

    private NfcAdapter nfcAdapter;
    private Properties tagData = new Properties();

    private IntentFilter[] intentFilters;
    private String[][] tagTechLists;
    private PendingIntent pendingIntent;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureForegroundDispatch();
//        clearTableRows();
        setMainView();
        processIntent(getIntent());
        
    }

	private void processIntent(Intent intent) {
		clearTableRows();
        setMainView();
		if(resolveIntent(intent)) {
            Item itemInTag = getItem(getItemInfo());
            if(null != itemInTag) {
            	basketService.addItemToBasket(itemInTag);
            }
//            LayoutUtil.clearTaggedChildren(
//            		(ViewGroup)findViewById(R.id.basketTable), BASKET_ROW_TAG);
            
		}
		System.out.println("======= Elements in basket : "+basketService.getItems().size());
        for(ItemWithQuantity item : basketService.getItems()) {
        	addItemToView(item);
        }
	}

	private void configureForegroundDispatch() {
		pendingIntent = PendingIntent.getActivity(
        	    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        configureIntentFilter();
        tagTechLists = new String[][] { new String[] { NfcF.class.getName() } };
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
	}
    
    public void configureIntentFilter() {
    	IntentFilter intentFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
    	try {
            intentFilter.addDataType("text/*");    
        }
        catch (MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
       intentFilters = new IntentFilter[] {intentFilter, };

    }

    private Item getItem(ItemInfo itemInfo) {
    	if(!tagData.isEmpty()) {
    		Item item = new Item(itemInfo);
        	String price = tagData.getProperty(TagField.PRICE.getKey());
        	item.setPrice(getPrice(price));
        	return item;
    	}
    	return null;
	}

	private ItemInfo getItemInfo() {
		
		 if(!tagData.isEmpty()) {
	        	
	        	String upc = tagData.getProperty(TagField.UPC.getKey());
	        	if(null == upc) {
	        		return null;
	        	}
	        	
	        	try {
	        		return itemInfoService.getItemInfo(upc);
				} catch (NumberFormatException e) {
					Log.e(getClass().getName(), e.getMessage());
				}
		 }
		
		return null;
	}

	private BigDecimal getPrice(String price) {	
		price = (null == price || price.isEmpty()) ? "0" : price;
		return new BigDecimal(price);
	}

	public void addItemToView(ItemWithQuantity item) {

		if(null != item) {
	        TableLayout table = (TableLayout) findViewById(R.id.basketTable);
	        TableRow row = new TableRow(this);
	        row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	        row.setBackgroundColor(Color.parseColor("#EDE8EA"));
	        row.setAlpha(.85f);
	        row.setTag(BASKET_ROW_TAG);
	        row.addView(getTextView("#"+item.getQuantity()));
	       	row.addView(getTextView(item.getItem().getName()));
	       	row.addView(getTextView(item.getItem().getInfo().getPer100g().getCalories()));
	       	row.addView(getTextView("\u00A3"+item.getItem().getPrice().divide(BigDecimal.valueOf(100))));
	       	row.addView(getButton());
	       	table.addView(row);
		}
    }

    private View getImageView(String imageUrl) {
		
    	ImageView imageView = new ImageView(this);
    	UrlImageViewHelper.setUrlDrawable(imageView, imageUrl);
    	imageView.setMaxHeight(100);
		return imageView;
	}
    
    private Button getButton() {
    	Button button = new Button(this);
    	button.setText("-");
    	return button;
    }

	public TextView getTextView(String text) {

        TextView textView = new TextView(this);
        textView.setTextColor(Color.BLACK);
        textView.setText(text);
        return textView;
    }

    private void setMainView() {

        setContentView(R.layout.main);

        TextView countLabel = (TextView) findViewById(R.id.heading_count_label);
        countLabel.setText(COUNT_LABEL);
        countLabel.setTextSize(18);

        TextView itemLabel = (TextView) findViewById(R.id.heading_item_label);
        itemLabel.setTextSize(18);
        itemLabel.setText(ITEM_LABEL);

        TextView caloriesLabel = (TextView) findViewById(R.id.heading_calories_label);
        caloriesLabel.setText(CALORIES_LABEL);
        caloriesLabel.setTextSize(18);

        TextView priceLabel = (TextView) findViewById(R.id.heading_price_label);
        priceLabel.setText(PRICE_LABEL);
        priceLabel.setTextSize(18);
        
        final Intent fridgeIntent = new Intent(this,
                FridgeActivity.class);
        
        Button checkoutButton = (Button)findViewById(R.id.basket_checkout);
        checkoutButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 if (event.getAction() == MotionEvent.ACTION_UP) {
	                    // do something here when the element is clicked
	                    fridgeService.checkout(basketService.getBasket());
	                    basketService.clearBasket();
	                    startActivity(fridgeIntent);
	                }

	                // true if the event was handled
	                // and should not be given further down to other views.
	                // if no other child view of this should get the event then return false
	                return true;
			}
		});
    }
    
    boolean resolveIntent(Intent intent) {
        // Parse the intent
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            // When a tag is discovered we send it to the service to be save. We
            // include a PendingIntent for the service to call back onto. This
            // will cause this activity to be restarted with onNewIntent(). At
            // that time we read it from the database and view it.
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            tagData = tagHandler.readTag(rawMsgs);
            return true;
        } 
        return false;
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if(null != nfcAdapter) {
    		nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, tagTechLists);
    	}
//    	clearTableRows();
//    	setMainView();
    	processIntent(getIntent());
    }

	private void clearTableRows() {
		ViewGroup basketTable = (ViewGroup)findViewById(R.id.basketTable);
    	basketTable.removeAllViews();
	}
    
    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
//    	clearTableRows();
//    	setMainView();
    	processIntent(intent);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	if(null != nfcAdapter) {
    		nfcAdapter.disableForegroundDispatch(this);
    	}
    }
}