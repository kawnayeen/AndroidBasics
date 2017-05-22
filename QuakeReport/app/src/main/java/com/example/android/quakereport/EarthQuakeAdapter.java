package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
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
public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeViewHolder> implements ItemClickListener {
    private List<EarthQuakeInfo> values;
    private Context context;

    public EarthQuakeAdapter(List<EarthQuakeInfo> values) {
        this.values = values;
    }

    @Override
    public EarthQuakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForListItem = R.layout.earthquake_event;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new EarthQuakeViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(EarthQuakeViewHolder holder, int position) {
        holder.bind(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public void itemClicked(int position) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kawnayeen.github.io"));
        context.startActivity(browserIntent);
    }

    class EarthQuakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

        ItemClickListener itemClickListener;

        public EarthQuakeViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.itemClickListener = itemClickListener;
        }

        void bind(EarthQuakeInfo earthQuakeInfo) {
            this.cityName.setText(earthQuakeInfo.getCity());
            this.distanceFromCity.setText(earthQuakeInfo.getDistanceFromCity());
            this.magnitude.setText(earthQuakeInfo.getFormattedMagnitude());
            this.date.setText(earthQuakeInfo.getDateToDisplay());
            this.time.setText(earthQuakeInfo.getTimeToDisplay());
            GradientDrawable magnitudeDrawable = (GradientDrawable) magnitude.getBackground();
            magnitudeDrawable.setColor(getMagnitudeColor(earthQuakeInfo.getMagnitude()));
        }

        @Override
        public void onClick(View v) {
            itemClickListener.itemClicked(getAdapterPosition());
        }

        private int getMagnitudeColor(double mag) {
            int magnitudeColorResourceId;
            int magnitudeFloor = (int) Math.floor(mag);
            switch (magnitudeFloor) {
                case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId = R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId = R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId = R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId = R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId = R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId = R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId = R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId = R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;
            }
            return ContextCompat.getColor(magnitude.getContext(), magnitudeColorResourceId);
        }
    }
}

interface ItemClickListener {
    void itemClicked(int position);
}
