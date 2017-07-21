package com.example.android.quakereport.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed by : kawnayeen
 * Creation Date : 7/21/17
 */
public class RestClient {
    private static final String BASE_URL = "http://earthquake.usgs.gov/";
    private static USGSService usgsService = null;

    public static USGSService getUsgsService() {
        if (usgsService == null)
            constructUsgsService();
        return usgsService;
    }

    private static void constructUsgsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usgsService = retrofit.create(USGSService.class);
    }
}
