package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.quakereport.model.EarthQuakeInfo;
import com.example.android.quakereport.model.QuakeInfo;
import com.example.android.quakereport.model.QuakeInfos;
import com.example.android.quakereport.networking.RestClient;
import com.example.android.quakereport.networking.USGSService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakeActivity extends AppCompatActivity {

    @BindView(R.id.rvEarthQuake)
    RecyclerView earthQuakeViews;
    EarthQuakeAdapter earthQuakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ButterKnife.bind(this);

        earthQuakeAdapter = new EarthQuakeAdapter(Arrays.asList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        earthQuakeViews.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(earthQuakeViews.getContext(), layoutManager.getOrientation());
        earthQuakeViews.addItemDecoration(decoration);
        earthQuakeViews.setAdapter(earthQuakeAdapter);

        USGSService usgsService = RestClient.getUsgsService();
        Call<QuakeInfos> quakeInfosCall = usgsService.getQuakeInfos();
        quakeInfosCall.enqueue(new Callback<QuakeInfos>() {
            @Override
            public void onResponse(Call<QuakeInfos> call, Response<QuakeInfos> response) {
                if (response.isSuccessful()) {
                    //Log.i("kamarul", "success " + response.body().quakeInfos.length);
                    List<QuakeInfo> quakeInfos = Arrays.asList(response.body().quakeInfos);
                    List<EarthQuakeInfo> infos = new ArrayList<>();
                    for (QuakeInfo quakeInfo : quakeInfos) {
                        infos.add(new EarthQuakeInfo(quakeInfo.properties.getMagnitude(),
                                quakeInfo.properties.getLocation(), quakeInfo.properties.getTime()
                                , quakeInfo.properties.getDetailsUrl()));
                    }
                    earthQuakeAdapter.setValues(infos);
                    earthQuakeAdapter.notifyDataSetChanged();
                } else {
                    Log.i("kamarul", "fail at on response");
                }
            }

            @Override
            public void onFailure(Call<QuakeInfos> call, Throwable t) {
                Log.i("kamarul", t.toString());
            }
        });
    }
}
