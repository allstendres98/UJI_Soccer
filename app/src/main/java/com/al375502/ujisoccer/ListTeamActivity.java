package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.al375502.ujisoccer.database.Team;
import com.al375502.ujisoccer.database.TeamInStanding;

import java.util.ArrayList;

public class ListTeamActivity extends AppCompatActivity {

    public static final String LEAGUE = "League";
    ListView listView;
    MyAdapter adapter;
    ArrayList<Team> teams;
    ArrayList<TeamInStanding> teamInStandings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_team);
        Intent intent = getIntent();
        int league_id = intent.getIntExtra(LEAGUE,2021);

        listView = findViewById(R.id.listView);

        final ListTeamPresenter presenter = new ListTeamPresenter(this, Model.getInstance(getApplicationContext()));
        presenter.GetStandingsAndTeams(league_id);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String teamSelected = "";
                for(int i = 0; i < teamInStandings.size(); i++)
                {
                    if(teamInStandings.get(i).position == position+1){
                        teamSelected = teamInStandings.get(i).name;
                    }
                }

                Log.d("Equipo",teamSelected);
            }
        });*/
    }

    public void FillListView(ArrayList<TeamInStanding> response) {
        teamInStandings = response;
        adapter = new MyAdapter(this, response);
        listView.setAdapter(adapter);
    }

    public void PopUpTeam(ArrayList<Team> allteams) {
        teams = allteams;
    }
    
}
