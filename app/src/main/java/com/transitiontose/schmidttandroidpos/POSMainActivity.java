package com.transitiontose.schmidttandroidpos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class POSMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posmain);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, foods);
        AutoCompleteTextView tv1 = (AutoCompleteTextView) findViewById(R.id.itemField);
        tv1.setAdapter(adapter);
    }

    static final String[] foods = new String[] {
            "Apple", "Banana", "Cheeseburger", "Donut", "Eclair", "Fries",
            "Garlic Bread", "Hot Dog", "Ice Cream", "Jambalaya", "Kale", "Lamb Chops",
            "Mushrooms", "Nuts", "Omelette", "Panini", "Quiche", "Rice", "Sub Sandwich", "Trout",
            "Ugli", "Vegetables", "Waffles", "Xigua", "Yams", "Zucchini"
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_posmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
