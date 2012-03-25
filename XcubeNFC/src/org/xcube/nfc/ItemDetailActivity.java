package org.xcube.nfc;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends Activity implements View.OnClickListener {

	public static final String ITEM_KEY = "item";
	public static final String BACK_KEY = "fromScreen";
	public static final String FROM_FRIDGE = "fridge";
	public static final String FROM_BASKET = "basket";
	
	private String from = FROM_BASKET;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.iteminfo);
		Item item = (Item) getIntent().getExtras().get(ITEM_KEY);
		from = getIntent().getExtras().getString(BACK_KEY);
		setItem(item);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Item item = (Item) getIntent().getExtras().get(ITEM_KEY);
		from = getIntent().getExtras().getString(BACK_KEY);
		setItem(item);
	}
	
	public void setItem(Item item) {
		TextView title = (TextView) findViewById(R.id.item_name);
		title.setText(item.getInfo().getName());
		
		ImageView image = (ImageView) findViewById(R.id.item_image);
		UrlImageViewHelper.setUrlDrawable(image, item.getImageUrl());
		
		TextView calories = (TextView) findViewById(R.id.iteminfo_calories);
		calories.setText(item.getInfo().getName());
		
		TextView protein = (TextView) findViewById(R.id.iteminfo_protein);
		protein.setText(item.getInfo().getName());
		
		TextView carbs = (TextView) findViewById(R.id.iteminfo_carbs);
		carbs.setText(item.getInfo().getName());
		
		TextView fat = (TextView) findViewById(R.id.iteminfo_fat);
		fat.setText(item.getInfo().getName());
		
		TextView fibre = (TextView) findViewById(R.id.iteminfo_fibre);
		fibre.setText(item.getInfo().getName());
		
		TextView salt = (TextView) findViewById(R.id.iteminfo_salt);
		salt.setText(item.getInfo().getName());
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		if (FROM_FRIDGE.equals(from)) {
			intent = new Intent(this, FridgeActivity.class);
		} else {
			intent = new Intent(this, XcubeNFCActivity.class);
		}
		startActivity(intent);
	}
}
