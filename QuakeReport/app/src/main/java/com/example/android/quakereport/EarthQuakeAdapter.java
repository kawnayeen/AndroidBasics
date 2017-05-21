package com.example.android.quakereport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kawnayeen on 5/20/17.
 */
public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeViewHolder> {
    private List<EarthQuakeInfo> values;

    public EarthQuakeAdapter(List<EarthQuakeInfo> values) {
        this.values = values;
    }

    @Override
    public EarthQuakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.earthquake_event;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new EarthQuakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthQuakeViewHolder holder, int position) {
        holder.bind(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class EarthQuakeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.magnitude)
        TextView magnitude;
        @BindView(R.id.cityName)
        TextView cityName;
        @BindView(R.id.distanceFromCity)
        TextView distanceFromCity;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;

        public EarthQuakeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(EarthQuakeInfo earthQuakeInfo) {
            this.cityName.setText(earthQuakeInfo.getCity());
            this.distanceFromCity.setText(earthQuakeInfo.getDistanceFromCity());
            this.magnitude.setText(earthQuakeInfo.getMagnitude() + "");
            this.date.setText(earthQuakeInfo.getDateToDisplay());
            this.time.setText(earthQuakeInfo.getTimeToDisplay());
        }
    }
}
