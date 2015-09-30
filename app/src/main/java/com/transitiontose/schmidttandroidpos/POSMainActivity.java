package com.transitiontose.schmidttandroidpos;

import android.app.*;
import android.content.Context;
import android.os.*;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.widget.AdapterView.*;

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
    static final Double[] foodPrices = new Double[] {
            15.00, 4.00, 6.00, 3.00, 3.00, 2.00,
            3.00, 2.50, 1.00, 17.00, 3.00, 19.00,
            2.00, 1.00, 7.00, 7.00, 4.00, 3.00, 5.00,
            16.00, 3.00, 4.00, 8.00, 2.00, 2.00, 2.00
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

        // new stuff
        itemField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(itemField.getWindowToken(), 0);

                    String textUserEntered = itemField.getText().toString();
                    for (int i = 0; i < foods.length; i++) {
                        if (foods[i].equals(textUserEntered)) {
                            unitPriceField.setText(foodPrices[i].toString());
                            handled = true;
                            return handled;
                        }
                    }
                }
                return handled;
            }
        });
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
