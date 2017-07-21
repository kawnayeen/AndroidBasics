package com.example.android.quakereport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Developed by : kawnayeen
 * Creation Date : 7/20/17
 */
public class Properties {
    @SerializedName("mag")
    private double magnitude;
    @SerializedName("place")
    private String location;
    @SerializedName("time")
    private long time;
    @SerializedName("url")
    private String detailsUrl;

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }
}
