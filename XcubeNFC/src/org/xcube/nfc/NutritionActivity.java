package org.xcube.nfc;

import org.xcube.nfc.domain.NutritionTotals;
import org.xcube.nfc.service.NutritionService;
import org.xcube.nfc.service.NutritionServiceImpl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NutritionActivity extends Activity {

    private static final String MAIN_LABEL = "Nutrition Info";
    private NutritionService nutritionService = new NutritionServiceImpl();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition);
    }
    @Override
    public void onResume() {
        super.onResume();
        ((ViewGroup) findViewById(R.id.main_table)).removeAllViews();
        setMainView();
    }
    private void setMainView() {


        TableLayout mainTable = (TableLayout) findViewById(R.id.main_table);

        NutritionTotals nutritionTotals = nutritionService.getTotal();
        mainTable.addView(getTableRow("Calories", nutritionTotals.getCalories().toString()));
        mainTable.addView(getTableRow("Carbohydrates", nutritionTotals.getCarbohydrate().toString()));
        mainTable.addView(getTableRow("Fat", nutritionTotals.getFat().toString()));
        mainTable.addView(getTableRow("Fibre", nutritionTotals.getFibre().toString()));
        mainTable.addView(getTableRow("Protein", nutritionTotals.getProtein().toString()));
        mainTable.addView(getTableRow("Salt", nutritionTotals.getSalt().toString()));
    }

    private TableRow getTableRow(String key, String value) {
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(3, 3, 0, 3);
        TextView textKey = new TextView(this);
        textKey.setTextColor(Color.BLACK);
        textKey.setText(key);
        textKey.setTextSize(20);
        TextView textValue = new TextView(this);
        textValue.setGravity(Gravity.RIGHT);
        textValue.setTextSize(20);
        textValue.setText(value);
        textValue.setTextColor(Color.BLACK);
        tableRow.addView(textKey);
        tableRow.addView(textValue);
        
        return tableRow;
    }
}
