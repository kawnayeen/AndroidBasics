package com.example.android.miwok.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.android.miwok.R;
import com.example.android.miwok.model.Word;

import java.util.ArrayList;
import java.util.List;

public class ColorViewModel extends AndroidViewModel {
    MutableLiveData<List<Word>> colorWordLiveData;

    public ColorViewModel(@NonNull Application application) {
        super(application);
        populateColorLiveData();
    }

    private void populateColorLiveData() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colorWordLiveData = new MutableLiveData<>();
        colorWordLiveData.setValue(words);
    }

    public LiveData<List<Word>> getColorData() {
        return colorWordLiveData;
    }
}
