package com.example.android.miwok.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.example.android.miwok.R;
import com.example.android.miwok.model.Word;
import com.example.android.miwok.viewmodel.ColorViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends WordFragment {

    ColorViewModel colorViewModel;

    public ColorFragment() {
    }

    @Override
    void prepareWords() {
        colorId = R.color.category_colors;
        colorViewModel = ViewModelProviders.of(this).get(ColorViewModel.class);
        colorViewModel.getColorData().observe(this, this::prepareWordList);
    }
}
