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

        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");

        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(earthquakes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        earthQuakeViews.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(earthQuakeViews.getContext(), layoutManager.getOrientation());
        earthQuakeViews.addItemDecoration(decoration);
        earthQuakeViews.setHasFixedSize(true);
        earthQuakeViews.setAdapter(earthQuakeAdapter);
    }
}
