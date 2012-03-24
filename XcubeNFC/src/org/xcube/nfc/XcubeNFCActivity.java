package org.xcube.nfc;

import org.xcube.nfc.domain.Item;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

public class XcubeNFCActivity extends Activity {

    private static final String COUNT_LABEL = "";
    private static final String ITEM_LABEL = "item";
    private static final String CALORIES_LABEL = "calories";
    private static final String PRICE_LABEL = "price";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView();
        addItem(null);
    }

    public void addItem(Item item) {

        TableRow itemsTableRow = (TableRow) findViewById(R.id.items);

        itemsTableRow.addView(getTextView("1"));
        itemsTableRow.addView(getTextView("beef"));
        itemsTableRow.addView(getTextView("300"));
        itemsTableRow.addView(getTextView("2.99"));
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
}