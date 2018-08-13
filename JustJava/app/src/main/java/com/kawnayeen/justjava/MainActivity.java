package com.kawnayeen.justjava;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.quantity_text_view)
    TextView quantityTv;
    @BindView(R.id.price_text_view)
    TextView priceTv;
    @BindView(R.id.order_button)
    Button orderBtn;
    @BindView(R.id.increment)
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
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        numberOfCoffees = 2;
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderBtn.setOnClickListener(v -> submitOrder());
        incrementBtn.setOnClickListener(this::incrementOrder);
        decrementBtn.setOnClickListener(this::decrementOrder);
        whippedCream.setOnClickListener(this::updateQuantityAndPrice);
        chocolate.setOnClickListener(this::updateQuantityAndPrice);
        customerName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                hideKeyboard(v);
        });
        orderViewModel.errorStream().observe(this, this::showToastMessage);
        orderViewModel.priceStream().observe(this, this::displayPrice);
    }

    private void incrementOrder(View v) {
        orderViewModel.incrementOrder();
        if (numberOfCoffees < 100) {
            numberOfCoffees++;
            updateQuantityAndPrice(v);
        }
    }

    private void decrementOrder(View v) {
        orderViewModel.decrementOrder();
        if (numberOfCoffees > 1) {
            numberOfCoffees--;
            updateQuantityAndPrice(v);
        }
    }

    private void showToastMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void submitOrder() {
        String name = customerName.getText().toString();
        String str = "";
        str += getString(R.string.name) + " : " + name;
        if (whippedCream.isChecked())
            str += "\nAdd " + getString(R.string.whipped_cream);
        if (chocolate.isChecked())
            str += "\nAdd " + getString(R.string.chocolate);
        str += "\n" + getString(R.string.quantity) + " : " + numberOfCoffees;
        str += "\n" + getString(R.string.total) + " : " + NumberFormat.getCurrencyInstance().format(calculatePrice());
        str += "\n" + getString(R.string.thank_you);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ordering Coffee for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, str);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void updateQuantityAndPrice(View v) {
        orderViewModel.setCreamChecked(whippedCream.isChecked());
        orderViewModel.setChocolateChecked(chocolate.isChecked());
        setQuantityText(orderViewModel.getNumberOfCoffees());
        hideKeyboard(v);
    }

    private int calculatePrice() {
        return orderViewModel.calculatePrice();
    }

    private void setQuantityText(int quantity) {
        quantityTv.setText(String.valueOf(quantity));
    }

    private void displayPrice(String message) {
        priceTv.setText(message);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
