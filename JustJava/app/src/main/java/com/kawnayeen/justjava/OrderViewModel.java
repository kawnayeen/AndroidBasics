package com.kawnayeen.justjava;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.text.NumberFormat;

/**
 * Created by kawnayeen on 8/12/18.
 */
public class OrderViewModel extends AndroidViewModel {
    private int numberOfCoffees;
    private boolean creamChecked;
    private boolean chocolateChecked;
    private static final int BASE_PRICE = 5;
    private static final int WHIPPED_CREAM_PRICE = 1;
    private static final int CHOCOLATE_PRICE = 2;
    private static final String MAX_ORDER_LIMIT_ERROR = "You can't have more than 100 coffee";
    private static final String MIN_ORDER_LIMIT_ERROR = "You can't have less than 1 coffee";
    private MutableLiveData<String> errorListener;
    private MutableLiveData<String> priceListener;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        numberOfCoffees = 2;
        errorListener = new MutableLiveData<>();
        priceListener = new MutableLiveData<>();
    }

    private int getPricePerCup() {
        return BASE_PRICE
                + (chocolateChecked ? CHOCOLATE_PRICE : 0)
                + (creamChecked ? WHIPPED_CREAM_PRICE : 0);
    }

    public void incrementOrder() {
        if (numberOfCoffees < 100) {
            numberOfCoffees++;
            updatePrice();
        } else
            errorListener.setValue(MAX_ORDER_LIMIT_ERROR);
    }

    public void decrementOrder() {
        if (numberOfCoffees > 1) {
            numberOfCoffees--;
            updatePrice();
        } else
            errorListener.setValue(MIN_ORDER_LIMIT_ERROR);
    }

    public LiveData<String> errorStream() {
        return errorListener;
    }

    public LiveData<String> priceStream() {
        return priceListener;
    }

    public int getNumberOfCoffees() {
        return numberOfCoffees;
    }

    public void setCreamChecked(boolean creamChecked) {
        this.creamChecked = creamChecked;
        updatePrice();
    }

    public void setChocolateChecked(boolean chocolateChecked) {
        this.chocolateChecked = chocolateChecked;
        updatePrice();
    }

    private void updatePrice() {
        priceListener.setValue(NumberFormat.getCurrencyInstance().format(calculatePrice()));
    }

    public int calculatePrice() {
        return numberOfCoffees * getPricePerCup();
    }
}
