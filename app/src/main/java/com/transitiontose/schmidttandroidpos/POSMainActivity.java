package com.transitiontose.schmidttandroidpos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.os.*;
import android.app.*;

public class POSMainActivity extends Activity {

    private TextView totalTextView;
    private TextView summaryTextView;
    private AutoCompleteTextView itemField;
    private EditText quantityField;
    private EditText unitPriceField;
    private Button newOrderButton;
    private Button newItemButton;
    private Button totalButton;
    private ButtonClickListener btnClick;
    static final String[] foods = new String[] {
            "Angelhair Pasta", "Bacon", "Cheeseburger", "Donut", "Eclair", "Fries",
            "Garlic Bread", "Hot Dog", "Ice Cream", "Jambalaya", "Kale", "Lamb Chops",
            "Mushrooms", "Nuts", "Omelette", "Panini", "Quiche", "Rice", "Sub Sandwich",
            "Trout", "Ugli", "Vegetables", "Waffles", "Xigua", "Yams", "Zucchini"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posmain);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, foods);
        AutoCompleteTextView tv1 = (AutoCompleteTextView) findViewById(R.id.itemField);
        tv1.setAdapter(adapter);

        totalTextView = (TextView) findViewById(R.id.totalTextView);
        summaryTextView = (TextView) findViewById(R.id.summaryTextView);
        itemField = (AutoCompleteTextView) findViewById(R.id.itemField);
        quantityField = (EditText) findViewById(R.id.quantityField);
        unitPriceField = (EditText) findViewById(R.id.unitPriceField);
        newOrderButton = (Button) findViewById(R.id.newOrderButton);
        newItemButton = (Button) findViewById(R.id.newItemButton);
        totalButton = (Button) findViewById(R.id.totalButton);
        btnClick = new ButtonClickListener();

        int[] idList = { R.id.newOrderButton, R.id.newItemButton, R.id.totalButton };

        for (int id: idList) {
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick);
        }
    }

    private class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.newOrderButton:
                    unitPriceField.setText("0.00");
                    quantityField.setText("1");
                    itemField.setText("");
                    summaryTextView.setText("");
                    totalTextView.setText("$0.00");
                    break;
                case R.id.newItemButton:
                    unitPriceField.setText("0.00");
                    quantityField.setText("1");
                    itemField.setText("");
                    break;
                case R.id.totalButton:
                    // update summary
                    String currentText = summaryTextView.getText().toString();
                    summaryTextView.setText(currentText + "\n" + itemField.getText() + " x" + quantityField.getText());

                    // update total
                    String total = totalTextView.getText().toString();
                    total = total.substring(1);
                    Double doubleTotal = Double.parseDouble(total);
                    int quantity = Integer.parseInt(quantityField.getText().toString());
                    double price = Double.parseDouble(unitPriceField.getText().toString());
                    doubleTotal = (quantity * price) + doubleTotal;
                    totalTextView.setText("$" + doubleTotal.toString());
                    break;
            }
        }
    }
}
