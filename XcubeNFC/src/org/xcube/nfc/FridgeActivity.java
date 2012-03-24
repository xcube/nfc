package org.xcube.nfc;

import java.util.List;

import org.xcube.nfc.domain.ItemInfo;
import org.xcube.nfc.domain.ItemWithQuantity;
import org.xcube.nfc.service.FridgeService;
import org.xcube.nfc.service.FridgeServiceImpl;
import org.xcube.nfc.service.ItemInfoService;
import org.xcube.nfc.service.ItemInfoServiceImpl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FridgeActivity extends Activity {

    private FridgeService fridgeService = new FridgeServiceImpl();
    private ItemInfoService itemInfoService = new ItemInfoServiceImpl();

    private static final String COUNT_LABEL = "";
    private static final String ITEM_LABEL = "item";
    private static final String CALORIES_LABEL = "calories";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setMainView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setItems();
    }

    private void setMainView() {

        setContentView(R.layout.fridge);

        TextView countLabel = (TextView) findViewById(R.id.heading_count_label);
        countLabel.setText(COUNT_LABEL);

        TextView itemLabel = (TextView) findViewById(R.id.heading_item_label);
        itemLabel.setText(ITEM_LABEL);

        TextView caloriesLabel = (TextView) findViewById(R.id.heading_calories_label);
        caloriesLabel.setText(CALORIES_LABEL);
    }

    private void setItems() {

        List<ItemWithQuantity> items = fridgeService.getFridge().getItems();

        TableLayout mainTable = (TableLayout) findViewById(R.id.main_table);
        for (ItemWithQuantity item : items) {
            mainTable.addView(getItemRow(item));
        }
    }

    public TableRow getItemRow(ItemWithQuantity item) {

        TableRow tableRow = new TableRow(this);
        int quantity = item.getQuantity();
        tableRow.addView(getTextView(Integer.toString(quantity)));
        ItemInfo itemInfo = itemInfoService.getItemInfo(item.getItem().getUpc());
        tableRow.addView(getTextView(itemInfo.getName()));
        tableRow.addView(getTextView(itemInfo.getPer100g().getCalories()));

        return tableRow;
    }

    public TextView getTextView(String text) {

        TextView textView = new TextView(this);
        textView.setText(text);
        return textView;
    }
}
