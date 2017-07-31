package com.example.android.quakereport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

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
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.pbHolder)
    FrameLayout pbHolder;
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

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            fetchEarthQuakeData();
            pbHolder.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText("No Network Available");
        }
    }

    private void fetchEarthQuakeData() {
        USGSService usgsService = RestClient.getUsgsService();
        Call<QuakeInfos> quakeInfosCall = usgsService.getQuakeInfos();
        quakeInfosCall.enqueue(new Callback<QuakeInfos>() {
            @Override
            public void onResponse(Call<QuakeInfos> call, Response<QuakeInfos> response) {
                if (response.isSuccessful()) {
                    List<QuakeInfo> quakeInfos = Arrays.asList(response.body().quakeInfos);
                    List<EarthQuakeInfo> infos = new ArrayList<>();
                    for (QuakeInfo quakeInfo : quakeInfos) {
                        infos.add(new EarthQuakeInfo(quakeInfo.properties.getMagnitude(),
                                quakeInfo.properties.getLocation(), quakeInfo.properties.getTime()
                                , quakeInfo.properties.getDetailsUrl()));
                    }
                    pbHolder.setVisibility(View.GONE);
                    if (!infos.isEmpty()) {
                        earthQuakeViews.setVisibility(View.VISIBLE);
                        earthQuakeAdapter.setValues(infos);
                        earthQuakeAdapter.notifyDataSetChanged();
                    } else {
                        emptyView.setVisibility(View.VISIBLE);
                    }
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
