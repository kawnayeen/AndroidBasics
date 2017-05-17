package com.example.android.miwok;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
public class Word {
    private String miwokTranslation;
    private String englishTranslation;
    private int imgResourceId;
    private static int IMG_NOT_DEFINE = -1;

    public Word(String miwokTranslation, String englishTranslation, int imgResourceId) {
        this.miwokTranslation = englishTranslation;
        this.englishTranslation = miwokTranslation;
        this.imgResourceId = imgResourceId;
    }

    public Word(String englishTranslation, String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
        this.englishTranslation = englishTranslation;
        this.imgResourceId = IMG_NOT_DEFINE;
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

    public boolean isImageAvailable() {
        return imgResourceId != IMG_NOT_DEFINE;
    }
}
