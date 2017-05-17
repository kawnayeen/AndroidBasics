package com.example.android.miwok;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
public class Word {
    private String miwokTranslation;
    private String englishTranslation;
    private int imgResourceId;

    public Word(String miwokTranslation, String englishTranslation, int imgResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.englishTranslation = englishTranslation;
        this.imgResourceId = imgResourceId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }
}
