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

    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderBtn.setOnClickListener(v -> submitOrder());
        incrementBtn.setOnClickListener(v -> orderViewModel.incrementOrder());
        decrementBtn.setOnClickListener(v -> orderViewModel.decrementOrder());
        whippedCream.setOnClickListener(v -> orderViewModel.setCreamChecked(whippedCream.isChecked()));
        chocolate.setOnClickListener(v -> orderViewModel.setChocolateChecked(chocolate.isChecked()));
        customerName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                hideKeyboard(v);
        });
        orderViewModel.errorStream().observe(this, this::showToastMessage);
        orderViewModel.priceStream().observe(this, this::displayPrice);
        orderViewModel.quantityStream().observe(this, this::setQuantityText);
    }

    private void showToastMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void submitOrder() {
        String name = customerName.getText().toString();
        Email email = orderViewModel.getEmail(name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, email.getSubject());
        intent.putExtra(Intent.EXTRA_TEXT, email.getBody());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void setQuantityText(int quantity) {
        quantityTv.setText(String.valueOf(quantity));
    }

    private void displayPrice(String message) {
        priceTv.setText(message);
        hideKeyboard(priceTv);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
