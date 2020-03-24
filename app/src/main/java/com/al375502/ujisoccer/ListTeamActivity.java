package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.al375502.ujisoccer.database.TeamInStanding;

import java.util.ArrayList;

public class ListTeamActivity extends AppCompatActivity {

    public static final String LEAGUE = "League";
    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_team);
        Intent intent = getIntent();
        int league_id = intent.getIntExtra(LEAGUE,2021);

        listView = findViewById(R.id.listView);
        //standingsList = new ArrayList<>();

        final ListTeamPresenter presenter = new ListTeamPresenter(this, Model.getInstance(getApplicationContext()));
        presenter.GetStandings(league_id);
        //listView.setAdapter(adapter);

    }

    public void FillListView(ArrayList<TeamInStanding> response) {
        //funciono. setText("position: " +response.get(0).position+ " nombre: " + response.get(0).name + " playedGames: " + response.get(0).playedGames);
        adapter = new MyAdapter(this, response);
        listView.setAdapter(adapter);

    }
}
