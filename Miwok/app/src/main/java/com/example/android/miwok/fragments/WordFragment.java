package com.example.android.miwok.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.miwok.R;
import com.example.android.miwok.WordAdapter;
import com.example.android.miwok.model.Word;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class WordFragment extends Fragment {
    @BindView(R.id.rvNumber)
    RecyclerView numberRecyclerView;

    List<Word> words;
    int colorId;
    private WordAdapter wordAdapter;

    abstract void populateWords();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false);
        ButterKnife.bind(this, rootView);
        populateWords();
        wordAdapter = new WordAdapter(words, colorId, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        numberRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(numberRecyclerView.getContext(), layoutManager.getOrientation());
        numberRecyclerView.addItemDecoration(decoration);
        numberRecyclerView.setHasFixedSize(true);
        numberRecyclerView.setAdapter(wordAdapter);
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        wordAdapter.cleanUpPlayer();
    }
}
