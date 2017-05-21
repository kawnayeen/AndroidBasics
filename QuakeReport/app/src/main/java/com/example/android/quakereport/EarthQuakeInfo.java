package com.example.android.quakereport;

/**
 * Created by kawnayeen on 5/21/17.
 */
public class EarthQuakeInfo {
    private double magnitude;
    private String cityName;
    private String date;

    public EarthQuakeInfo(double magnitude, String cityName, String date) {
        this.magnitude = magnitude;
        this.cityName = cityName;
        this.date = date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return date;
    }
}
