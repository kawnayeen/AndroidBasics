package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/16/17
 */
class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> implements ItemClickListener {

    private List<Word> values;
    private int colorResourceId;
    private Context context;
    private MediaPlayer player;
    private AudioManager audioManager;
    private OnAudioFocusChangeListener onAudioFocusChangeListener =
            this::audioFocusChanged;

    WordAdapter(List<Word> values, int colorResourceId, Context context) {
        this.values = values;
        this.colorResourceId = colorResourceId;
        this.context = context;
        this.player = null;
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.word_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new WordViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.bind(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public void itemClicked(int position) {
        cleanUpPlayer();
        int result = audioManager.requestAudioFocus(
                onAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            player = MediaPlayer.create(context, values.get(position).getAudioResourceId());
            player.start();
            player.setOnCompletionListener(mp -> cleanUpPlayer());
        }
    }

    public void cleanUpPlayer() {
        if (player != null) {
            player.release();
            player = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    private void audioFocusChanged(int focusChange) {
        if (player == null)
            return;
        if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AUDIOFOCUS_LOSS_TRANSIENT) {
            player.pause();
            player.seekTo(0);
        } else if (focusChange == AUDIOFOCUS_LOSS) {
            cleanUpPlayer();
        } else if (focusChange == AUDIOFOCUS_GAIN) {
            player.start();
        }
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView englishText;
        TextView miwokText;
        ImageView image;
        ItemClickListener itemClickListener;

        WordViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;
            englishText = (TextView) itemView.findViewById(R.id.englishText);
            miwokText = (TextView) itemView.findViewById(R.id.miwokText);
            image = (ImageView) itemView.findViewById(R.id.numberImage);
            itemView.setOnClickListener(this);
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

        @Override
        public void onClick(View v) {
            itemClickListener.itemClicked(getAdapterPosition());
        }
    }
}

interface ItemClickListener {
    void itemClicked(int position);
}
