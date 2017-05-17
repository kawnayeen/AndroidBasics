package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Developed by : kawnayeen
 * Creation Date : 5/17/17
 */
public abstract class WordActivity extends AppCompatActivity {
    @BindView(R.id.rvNumber)
    RecyclerView numberRecyclerView;

    List<Word> words;
    int colorId;

    abstract void populateWords();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        ButterKnife.bind(this);
        populateWords();
        WordAdapter wordAdapter = new WordAdapter(words, colorId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(numberRecyclerView.getContext(), layoutManager.getOrientation());
        numberRecyclerView.addItemDecoration(decoration);
        numberRecyclerView.setHasFixedSize(true);
        numberRecyclerView.setAdapter(wordAdapter);
    }
}
