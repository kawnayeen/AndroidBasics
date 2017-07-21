package com.example.android.quakereport;

import com.google.gson.annotations.SerializedName;

/**
 * Developed by : kawnayeen
 * Creation Date : 7/20/17
 */
public class QuakeInfos {
    @SerializedName("features")
    QuakeInfo[] quakeInfos;
}
