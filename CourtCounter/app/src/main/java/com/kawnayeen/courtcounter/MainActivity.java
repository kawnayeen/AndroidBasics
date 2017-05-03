package com.kawnayeen.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private int totalScore = 0;

    @BindView(R.id.score)
    TextView scoreText;
    @BindView(R.id.three_points)
    Button scoreThreePoints;
    @BindView(R.id.two_points)
    Button scoreTwoPoints;
    @BindView(R.id.free_throw)
    Button scoreFreeThrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        scoreThreePoints.setOnClickListener(v -> {
            totalScore += 3;
            updateScoreText();
        });

        scoreTwoPoints.setOnClickListener(v -> {
            totalScore += 2;
            updateScoreText();
        });

        scoreFreeThrow.setOnClickListener(v -> {
            totalScore++;
            updateScoreText();
        });
    }

    private void updateScoreText() {
        scoreText.setText(String.valueOf(totalScore));
    }
}
