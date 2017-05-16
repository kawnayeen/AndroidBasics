/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.numbers)
    Button numbersBtn;
    @BindView(R.id.family)
    Button familyBtn;
    @BindView(R.id.colors)
    Button colorsBtn;
    @BindView(R.id.phrases)
    Button phrasesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        numbersBtn.setOnClickListener(v -> startActivity(new Intent(this, NumbersActivity.class)));
        familyBtn.setOnClickListener(v -> startActivity(new Intent(this, FamilyMembersActivity.class)));
        colorsBtn.setOnClickListener(v -> startActivity(new Intent(this, ColorsActivity.class)));
        phrasesBtn.setOnClickListener(v -> startActivity(new Intent(this, PhrasesActivity.class)));
    }
}
