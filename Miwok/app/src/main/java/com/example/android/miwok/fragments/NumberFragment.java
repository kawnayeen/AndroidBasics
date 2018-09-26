package com.example.android.miwok.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.example.android.miwok.R;
import com.example.android.miwok.model.Word;
import com.example.android.miwok.viewmodel.NumberViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragment extends WordFragment {

    NumberViewModel numberViewModel;

    public NumberFragment() {
    }

    @Override
    void prepareWords() {
        colorId = R.color.category_numbers;
        numberViewModel = ViewModelProviders.of(this).get(NumberViewModel.class);
        numberViewModel.getNumberLiveData().observe(this, this::prepareWordList);
    }
}
