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

public class NumberViewModel extends AndroidViewModel {
    MutableLiveData<List<Word>> numberWordLiveData;

    public NumberViewModel(@NonNull Application application) {
        super(application);
        populateNumberWords();
    }

    private void populateNumberWords() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));
        numberWordLiveData = new MutableLiveData<>();
        numberWordLiveData.setValue(words);
    }

    public LiveData<List<Word>> getNumberLiveData() {
        return numberWordLiveData;
    }
}
