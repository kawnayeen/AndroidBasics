package com.example.android.quakereport;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.android.quakereport.model.QuakeInfos;
import com.example.android.quakereport.networking.RestClient;
import com.example.android.quakereport.networking.USGSService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakeRepository {
    private USGSService usgsService;

    public EarthquakeRepository() {
        usgsService = RestClient.getUsgsService();
    }

    public LiveData<QuakeInfos> getQuakeInfo() {
        MutableLiveData<QuakeInfos> data = new MutableLiveData<>();
        Call<QuakeInfos> quakeInfoCall = usgsService.getQuakeInfos();
        quakeInfoCall.enqueue(new Callback<QuakeInfos>() {
            @Override
            public void onResponse(Call<QuakeInfos> call, Response<QuakeInfos> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Log.i("kamarul", "fail at on response");
                }
            }

            @Override
            public void onFailure(Call<QuakeInfos> call, Throwable t) {
                Log.i("kamarul", t.toString());
            }
        });
        return data;
    }
}
