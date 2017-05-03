package com.kawnayeen.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        orderBtn.setOnClickListener(v -> {
            setQuantityText(2);
            setDisplayPrice(2 * 5);
        });
    }

    private void setQuantityText(int quantity) {
        quantityTv.setText("" + quantity);
    }

    private void setDisplayPrice(int number) {
        priceTv.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
