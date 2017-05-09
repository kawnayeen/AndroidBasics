package com.kawnayeen.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kawnayeen.justjava.R.id.increment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.quantity_text_view)
    TextView quantityTv;
    @BindView(R.id.price_text_view)
    TextView priceTv;
    @BindView(R.id.order_button)
    Button orderBtn;
    @BindView(increment)
    Button incrementBtn;
    @BindView(R.id.decrement)
    Button decrementBtn;
    @BindView(R.id.whipped_cream_topping)
    CheckBox whippedCream;
    @BindView(R.id.chocolate_topping)
    CheckBox chocolate;
    @BindView(R.id.et_customer_name)
    EditText customerName;

    private int numberOfCoffees;
    private static final int BASE_PRICE = 5;
    private static final int WHIPPED_CREAM_PRICE = 1;
    private static final int CHOCOLATE_PRICE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        numberOfCoffees = 2;
        orderBtn.setOnClickListener(v -> submitOrder());
        incrementBtn.setOnClickListener(this::incrementOrder);
        decrementBtn.setOnClickListener(this::decrementOrder);
        whippedCream.setOnClickListener(this::updateQuantityAndPrice);
        chocolate.setOnClickListener(this::updateQuantityAndPrice);
        customerName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                hideKeyboard(v);
        });
    }

    private void incrementOrder(View v) {
        numberOfCoffees++;
        updateQuantityAndPrice(v);
    }

    private void decrementOrder(View v) {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            updateQuantityAndPrice(v);
        }
    }

    private void submitOrder() {
        String name = customerName.getText().toString();
        String str = "";
        str += "Name : " + name;
        if (whippedCream.isChecked())
            str += "\nAdd whipped cream";
        if (chocolate.isChecked())
            str += "\nAdd chocolate";
        str += "\nQuantity : " + numberOfCoffees;
        str += "\nTotal :" + NumberFormat.getCurrencyInstance().format(calculatePrice());
        str += "\nThank you!";
        displayMessage(str);
    }

    private void updateQuantityAndPrice(View v) {
        setQuantityText(numberOfCoffees);
        displayMessage(NumberFormat.getCurrencyInstance().format(calculatePrice()));
        hideKeyboard(v);
    }

    private int calculatePrice() {
        return numberOfCoffees * getPricePerCup();
    }

    private int getPricePerCup() {
        int price = BASE_PRICE;
        if (whippedCream.isChecked())
            price += WHIPPED_CREAM_PRICE;
        if (chocolate.isChecked())
            price += CHOCOLATE_PRICE;
        return price;
    }

    private void setQuantityText(int quantity) {
        quantityTv.setText(String.valueOf(quantity));
    }

    private void displayMessage(String message) {
        priceTv.setText(message);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
