package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    abstract void populateWords();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        ButterKnife.bind(this);
        populateWords();
        NumberAdapter numberAdapter = new NumberAdapter(words);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberRecyclerView.setLayoutManager(layoutManager);
        numberRecyclerView.setHasFixedSize(true);
        numberRecyclerView.setAdapter(numberAdapter);
    }
}
