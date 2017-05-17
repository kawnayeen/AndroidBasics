package com.example.android.miwok;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
public class Word {
    private String miwokTranslation;
    private String englishTranslation;
    private int imgResourceId;
    private int audioResourceId;
    private static int IMG_NOT_DEFINE = -1;

    public Word(String miwokTranslation, String englishTranslation, int imgResourceId, int audioResourceId) {
        this.miwokTranslation = englishTranslation;
        this.englishTranslation = miwokTranslation;
        this.imgResourceId = imgResourceId;
        this.audioResourceId = audioResourceId;
    }

    public Word(String englishTranslation, String miwokTranslation, int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.englishTranslation = englishTranslation;
        this.imgResourceId = IMG_NOT_DEFINE;
        this.audioResourceId = audioResourceId;
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

    public int getAudioResourceId() {
        return audioResourceId;
    }
}
