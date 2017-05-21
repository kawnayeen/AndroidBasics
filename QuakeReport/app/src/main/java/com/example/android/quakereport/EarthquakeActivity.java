package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarthquakeActivity extends AppCompatActivity {

    @BindView(R.id.rvEarthQuake)
    RecyclerView earthQuakeViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ButterKnife.bind(this);

        ArrayList<EarthQuakeInfo> earthquakes = new ArrayList<>();
        earthquakes.add(new EarthQuakeInfo(5.6, "San Francisco", "21th May, 2017"));
        earthquakes.add(new EarthQuakeInfo(7.2, "California", "14th May, 2017"));
        earthquakes.add(new EarthQuakeInfo(8.1, "New Jersey", "24th May, 2017"));
        earthquakes.add(new EarthQuakeInfo(3.6, "Dhaka", "21th May, 2017"));
        earthquakes.add(new EarthQuakeInfo(4.6, "Khulna", "27th May, 2017"));
        earthquakes.add(new EarthQuakeInfo(3.3, "Chittagong", "20th May, 2017"));


        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(earthquakes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        earthQuakeViews.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(earthQuakeViews.getContext(), layoutManager.getOrientation());
        earthQuakeViews.addItemDecoration(decoration);
        earthQuakeViews.setHasFixedSize(true);
        earthQuakeViews.setAdapter(earthQuakeAdapter);
    }
}
