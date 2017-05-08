package com.kawnayeen.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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

    private int numberOfCoffees;
    private String name = "Kawnayeen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        numberOfCoffees = 2;
        orderBtn.setOnClickListener(v -> submitOrder());
        incrementBtn.setOnClickListener(v -> incrementOrder());
        decrementBtn.setOnClickListener(v -> decrementOrder());
    }

    private void incrementOrder() {
        numberOfCoffees++;
        updateQuantityAndPrice();
    }

    private void decrementOrder() {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            updateQuantityAndPrice();
        }
    }

    private void submitOrder() {
        String str = "";
        str += "Name : "+name;
        str += "\nQuantity : "+numberOfCoffees;
        str += "\nTotal :" + NumberFormat.getCurrencyInstance().format(calculatePrice());
        str += "\nThank you!";
        displayMessage(str);
    }

    private void updateQuantityAndPrice() {
        setQuantityText(numberOfCoffees);
        displayMessage(NumberFormat.getCurrencyInstance().format(calculatePrice()));
    }

    private int calculatePrice() {
        return numberOfCoffees * 5;
    }

    private void setQuantityText(int quantity) {
        quantityTv.setText(String.valueOf(quantity));
    }

    private void displayMessage(String message) {
        priceTv.setText(message);
    }
}
