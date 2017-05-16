package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumbersActivity extends AppCompatActivity {

    @BindView(R.id.rvNumber)
    RecyclerView numberRecyclerView;

    List<String> words = new ArrayList<>(
            Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        ButterKnife.bind(this);

        NumberAdapter numberAdapter = new NumberAdapter(words);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberRecyclerView.setLayoutManager(layoutManager);
        numberRecyclerView.setHasFixedSize(true);
        numberRecyclerView.setAdapter(numberAdapter);
    }
}
