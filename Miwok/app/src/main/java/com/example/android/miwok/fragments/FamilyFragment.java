package com.example.android.miwok.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.example.android.miwok.R;
import com.example.android.miwok.model.Word;
import com.example.android.miwok.viewmodel.FamilyViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends WordFragment {

    FamilyViewModel familyViewModel;

    public FamilyFragment() {
    }

    @Override
    void prepareWords() {
        colorId = R.color.category_family;
        familyViewModel = ViewModelProviders.of(this).get(FamilyViewModel.class);
        familyViewModel.getFamilyLiveData().observe(this, this::prepareWordList);
    }
}
