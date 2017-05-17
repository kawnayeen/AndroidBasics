package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> values;
    private int colorResourceId;

    WordAdapter(List<Word> values, int colorResourceId) {
        this.values = values;
        this.colorResourceId = colorResourceId;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.word_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.bind(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        TextView englishText;
        TextView miwokText;
        ImageView image;

        WordViewHolder(View itemView) {
            super(itemView);
            englishText = (TextView) itemView.findViewById(R.id.englishText);
            miwokText = (TextView) itemView.findViewById(R.id.miwokText);
            image = (ImageView) itemView.findViewById(R.id.numberImage);
            itemView.findViewById(R.id.textContainer)
                    .setBackgroundColor(ContextCompat.getColor(itemView.getContext(), colorResourceId));
        }

        void bind(Word word) {
            englishText.setText(word.getEnglishTranslation());
            miwokText.setText(word.getMiwokTranslation());
            if (word.isImageAvailable())
                image.setImageResource(word.getImgResourceId());
            else
                image.setVisibility(View.GONE);
        }
    }
}
