package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kawnayeen on 5/21/17.
 */
public class EarthQuakeInfo {
    private double magnitude;
    private String formattedMagnitude;
    private String city;
    private String distanceFromCity;
    private Date date;
    private String dateToDisplay;
    private String timeToDisplay;
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
    private static DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
    private static String LOCATION_SPLITTER = " of ";

    public EarthQuakeInfo(double magnitude, String cityName, long date) {
        this.magnitude = magnitude;

        if (cityName.contains(LOCATION_SPLITTER)) {
            String[] locations = cityName.split(LOCATION_SPLITTER);
            city = locations[1];
            distanceFromCity = locations[0] + " of";
        } else {
            city = cityName;
            distanceFromCity = "Near of";
        }

        this.date = new Date(date);
        dateToDisplay = dateFormatter.format(this.date);
        timeToDisplay = timeFormatter.format(this.date);
        formattedMagnitude = magnitudeFormatter.format(magnitude);
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public String getDateToDisplay() {
        return dateToDisplay;
    }

    public String getTimeToDisplay() {
        return timeToDisplay;
    }

    public String getDistanceFromCity() {
        return distanceFromCity;
    }

    public String getFormattedMagnitude() {
        return formattedMagnitude;
    }
}
