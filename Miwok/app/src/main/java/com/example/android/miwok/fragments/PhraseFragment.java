package com.example.android.miwok.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.example.android.miwok.R;
import com.example.android.miwok.model.Word;
import com.example.android.miwok.viewmodel.PhrasesViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends WordFragment {

    PhrasesViewModel phrasesViewModel;

    public PhraseFragment() {
    }

    @Override
    void prepareWords() {
        colorId = R.color.category_phrases;
        phrasesViewModel = ViewModelProviders.of(this).get(PhrasesViewModel.class);
        phrasesViewModel.getPhraseLiveData().observe(this, this::prepareWordList);
    }
}
