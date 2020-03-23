package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.al375502.ujisoccer.database.TeamInStanding;

import java.util.ArrayList;

public class ListTeamActivity extends AppCompatActivity {

    public static final String LEAGUE = "League";
    TextView funciono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_team);
        Intent intent = getIntent();
        int league_id = intent.getIntExtra(LEAGUE,2021);

        funciono = findViewById(R.id.textView);

        final ListTeamPresenter presenter = new ListTeamPresenter(this, Model.getInstance(getApplicationContext()));
        presenter.GetStandings(league_id);

    }

    public void FillListView(ArrayList<TeamInStanding> response) {
        funciono. setText("position: " +response.get(0).position+ " nombre: " + response.get(0).name + " playedGames: " + response.get(0).playedGames);
    }
}
