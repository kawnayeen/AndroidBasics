package com.example.android.quakereport;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.quakereport.model.QuakeInfos;

public class EarthquakeViewModel extends AndroidViewModel {
    private EarthquakeRepository earthquakeRepository;
    private LiveData<QuakeInfos> quakeInfoLiveData;

    public EarthquakeViewModel(@NonNull Application application) {
        super(application);
        earthquakeRepository = new EarthquakeRepository();
        quakeInfoLiveData = earthquakeRepository.getQuakeInfo();
    }

    public LiveData<QuakeInfos> getQuakeInfoLiveData() {
        return quakeInfoLiveData;
    }
}
