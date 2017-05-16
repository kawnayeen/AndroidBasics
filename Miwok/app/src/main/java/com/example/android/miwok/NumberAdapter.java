package com.example.android.miwok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private List<Word> values;

    NumberAdapter(List<Word> values) {
        this.values = values;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView englishText;
        TextView miwokText;

        NumberViewHolder(View itemView) {
            super(itemView);
            englishText = (TextView) itemView.findViewById(R.id.englishText);
            miwokText = (TextView) itemView.findViewById(R.id.miwokText);
        }

        void bind(Word word) {
            englishText.setText(word.getEnglishTranslation());
            miwokText.setText(word.getMiwokTranslation());
        }
    }
}
