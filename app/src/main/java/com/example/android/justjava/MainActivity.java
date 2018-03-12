package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if(quantity<10) {
            quantity++;
            displayQuantity(quantity);
        }
        else{
            CharSequence text = "You cannot order more than 10 coffees.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if(quantity>1) {
            quantity--;
            displayQuantity(quantity);
        }
        else{
            CharSequence text = "You cannot order less than 1 coffee.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param numberOfCups of coffee
     */
    private void displayQuantity(int numberOfCups) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCups);
    }

    /**
     * This method calculates the total price.
     * @param hasWhipped is true if the customer requests whipped cream
     * @param hasChocolate is true if the customer requests chocolate
     * @return total price
     */
    private int calculatePrice(boolean hasWhipped, boolean hasChocolate){
        int pricePerCup = 5;
        if (hasWhipped){
            pricePerCup += 1;
        }
        if (hasChocolate){
            pricePerCup += 2;
        }
        return quantity * pricePerCup;
    }

    /**
     * This method creates a String that shows the order display.
     * @param name is customer name
     * @param price of the order
     * @param hasWhippedCream indicates if the user wants whipped cream
     * @param hasChocolate indicates if the user wants chocolate
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate){
        String orderSummary = getString(R.string.name_summary, name);
        orderSummary += "\n" + getString(R.string.whipped_summary) + hasWhippedCream;
        orderSummary += "\n" + getString(R.string.chocolate_summary) + hasChocolate;
        orderSummary += "\n" + getString(R.string.quantity_summary) + quantity;
        orderSummary += "\n" + getString(R.string.total) + price;
        orderSummary += "\n" + getString(R.string.thank_you);
        return orderSummary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkBoxWhipped = (CheckBox) findViewById(R.id.whipped_cream_check);
        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.chocolate_check);
        EditText nameTextView = (EditText) findViewById(R.id.name);
        String name = nameTextView.getText().toString();
        boolean hasWhipped = checkBoxWhipped.isChecked();
        boolean hasChocolate = checkBoxChocolate.isChecked();
        int totalPrice = calculatePrice(hasWhipped, hasChocolate);
        String priceMessage = createOrderSummary(name, totalPrice, hasWhipped, hasChocolate);
        displayMessage(priceMessage);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    /**
     * This method displays the given text on the screen.
     * @param message to display
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
