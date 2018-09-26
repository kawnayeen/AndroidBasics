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

public class FamilyViewModel extends AndroidViewModel {
    MutableLiveData<List<Word>> familyWordLiveData;

    public FamilyViewModel(@NonNull Application application) {
        super(application);
        populateFamilyWord();
    }

    private void populateFamilyWord() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));
        familyWordLiveData = new MutableLiveData<>();
        familyWordLiveData.setValue(words);
    }

    public LiveData<List<Word>> getFamilyLiveData() {
        return familyWordLiveData;
    }
}
