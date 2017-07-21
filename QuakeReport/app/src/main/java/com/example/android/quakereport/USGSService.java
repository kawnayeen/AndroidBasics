package com.example.android.quakereport;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Developed by : kawnayeen
 * Creation Date : 7/20/17
 */
public interface USGSService {
    @GET("fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10")
    Call<QuakeInfos> getQuakeInfos();
}
