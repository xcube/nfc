package org.xcube.nfc;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.domain.ItemWithQuantity;
import org.xcube.nfc.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import org.xcube.nfc.service.FridgeService;
import org.xcube.nfc.service.FridgeServiceImpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends Activity implements View.OnClickListener {

	public static final String ITEM_KEY = "item";
	public static final String BACK_KEY = "fromScreen";
	public static final String FROM_FRIDGE = "fridge";
	public static final String FROM_BASKET = "basket";
	
	private FridgeService fridgeService = new FridgeServiceImpl();
	
	private String from = FROM_BASKET;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.iteminfo);
		Button btn = (Button) findViewById(R.id.iteminfo_back);
		btn.setOnClickListener(this);
		
		String upc = getIntent().getExtras().getString(ITEM_KEY);
		ItemWithQuantity iq = fridgeService.getItem(upc);
		if (null != iq) {
			setItem(iq.getItem());
		}
		from = getIntent().getExtras().getString(BACK_KEY);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		String upc = getIntent().getExtras().getString(ITEM_KEY);
		ItemWithQuantity iq = fridgeService.getItem(upc);
		if (null != iq) {
			setItem(iq.getItem());
		}
		from = getIntent().getExtras().getString(BACK_KEY);
	}
	
	public void setItem(Item item) {
		TextView title = (TextView) findViewById(R.id.item_name);
		title.setText(item.getInfo().getName());
		
		ImageView image = (ImageView) findViewById(R.id.item_image);
		UrlImageViewHelper.setUrlDrawable(image, item.getImageUrl());
		
		if (null != item.getInfo().getPer100g()) {
			TextView calories = (TextView) findViewById(R.id.iteminfo_calories);
			calories.setText(item.getInfo().getPer100g().getCalories());

			TextView protein = (TextView) findViewById(R.id.iteminfo_protein);
			protein.setText(item.getInfo().getPer100g().getProtein());

			TextView carbs = (TextView) findViewById(R.id.iteminfo_carbs);
			carbs.setText(item.getInfo().getPer100g().getCarbohydrate());

			TextView fat = (TextView) findViewById(R.id.iteminfo_fat);
			fat.setText(item.getInfo().getPer100g().getFat());

			TextView fibre = (TextView) findViewById(R.id.iteminfo_fibre);
			fibre.setText(item.getInfo().getPer100g().getFibre());

			TextView salt = (TextView) findViewById(R.id.iteminfo_salt);
			salt.setText(item.getInfo().getPer100g().getSalt());
		}
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
