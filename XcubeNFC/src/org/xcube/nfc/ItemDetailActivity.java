package org.xcube.nfc;

import org.xcube.nfc.domain.Item;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailActivity extends Activity {

	public static final String ITEM_KEY = "item";
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.iteminfo);
		Item item = (Item) getIntent().getExtras().get(ITEM_KEY);
		setItem(item);
	}
	
	public void setItem(Item item) {
		TextView title = (TextView) findViewById(R.id.item_name);
		title.setText(item.getInfo().getName());
		
		
		
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
}
