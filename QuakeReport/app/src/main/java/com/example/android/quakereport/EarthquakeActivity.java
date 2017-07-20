package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarthquakeActivity extends AppCompatActivity {

    @BindView(R.id.rvEarthQuake)
    RecyclerView earthQuakeViews;
    EarthQuakeAdapter earthQuakeAdapter;

    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ButterKnife.bind(this);

        //ArrayList<EarthQuakeInfo> earthquakes = QueryUtils.extractEarthquakes("");
        earthQuakeAdapter = new EarthQuakeAdapter(Arrays.asList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        earthQuakeViews.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(earthQuakeViews.getContext(), layoutManager.getOrientation());
        earthQuakeViews.addItemDecoration(decoration);
        //earthQuakeViews.setHasFixedSize(true);
        earthQuakeViews.setAdapter(earthQuakeAdapter);
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthQuakeInfo>> {

        @Override
        protected List<EarthQuakeInfo> doInBackground(String... params) {
            if (params.length < 1 || params[0] == null) {
                return null;
            }

            return QueryUtils.fetchEarthquakeData(params[0]);
        }

        @Override
        protected void onPostExecute(List<EarthQuakeInfo> earthQuakeInfos) {
            super.onPostExecute(earthQuakeInfos);
            if (earthQuakeInfos != null && !earthQuakeInfos.isEmpty()) {
                earthQuakeAdapter.setValues(earthQuakeInfos);
                earthQuakeAdapter.notifyDataSetChanged();
            }
        }
    }
}
