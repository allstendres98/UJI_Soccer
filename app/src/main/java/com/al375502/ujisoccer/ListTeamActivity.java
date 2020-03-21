package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ListTeamActivity extends AppCompatActivity {

    public static final String LEAGUE = "League";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_team);

        Intent intent = getIntent();
        GetInfo info = intent.getParcelableExtra(LEAGUE);

        final ListTeamPresenter presenter = new ListTeamPresenter(this, info);
    }
}
