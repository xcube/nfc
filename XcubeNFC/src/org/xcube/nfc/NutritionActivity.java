package org.xcube.nfc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NutritionActivity extends Activity {

    private static final String MAIN_LABEL = "Nutrition Info";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView();
    }

    private void setMainView() {

        setContentView(R.layout.nutrition);
        TextView countLabel = (TextView) findViewById(R.id.main_label);
        countLabel.setText(MAIN_LABEL);
    }
}
