package com.example.android.quakereport;

import android.arch.lifecycle.ViewModelProviders;
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
    EarthquakeViewModel earthquakeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ButterKnife.bind(this);
        earthquakeViewModel = ViewModelProviders.of(this).get(EarthquakeViewModel.class);
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
        earthquakeViewModel.getQuakeInfoLiveData().observe(this, quakeInfo -> {
            List<QuakeInfo> quakeInfoList = Arrays.asList(quakeInfo.quakeInfos);
            List<EarthQuakeInfo> earthQuakeInfoList = new ArrayList<>();
            for (QuakeInfo info : quakeInfoList) {
                earthQuakeInfoList.add(new EarthQuakeInfo(info.properties.getMagnitude(),
                        info.properties.getLocation(), info.properties.getTime()
                        , info.properties.getDetailsUrl()));
            }
            pbHolder.setVisibility(View.GONE);
            if (!earthQuakeInfoList.isEmpty()) {
                earthQuakeViews.setVisibility(View.VISIBLE);
                earthQuakeAdapter.setValues(earthQuakeInfoList);
                earthQuakeAdapter.notifyDataSetChanged();
            } else {
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }
}
