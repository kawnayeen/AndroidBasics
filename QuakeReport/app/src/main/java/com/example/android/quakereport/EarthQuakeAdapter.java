package com.example.android.quakereport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kawnayeen on 5/20/17.
 */
public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeViewHolder> {
    private List<String> values;

    public EarthQuakeAdapter(List<String> values) {
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
        TextView cityName;

        public EarthQuakeViewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.cityName);
        }

        void bind(String cityName) {
            this.cityName.setText(cityName);
        }
    }
}
