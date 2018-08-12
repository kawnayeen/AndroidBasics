package com.kawnayeen.justjava;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * Created by kawnayeen on 8/12/18.
 */
public class OrderViewModel extends AndroidViewModel {
    private int numberOfCoffees;
    private static final int BASE_PRICE = 5;
    private static final int WHIPPED_CREAM_PRICE = 1;
    private static final int CHOCOLATE_PRICE = 2;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        numberOfCoffees = 2;
    }
}
