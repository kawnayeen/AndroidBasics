package com.example.android.miwok;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
public class Word {
    private String miwokTranslation;
    private String englishTranslation;

    public Word(String miwokTranslation, String englishTranslation) {
        this.miwokTranslation = miwokTranslation;
        this.englishTranslation = englishTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }
}
