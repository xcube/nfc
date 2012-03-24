package org.xcube.nfc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class XcubeNFCActivity extends Activity {

    private static final String COUNT_LABEL = "count";
    private static final String ITEM_LABEL = "item";
    private static final String CALORIES_LABEL = "calories";
    private static final String PRICE_LABEL = "price";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView();
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