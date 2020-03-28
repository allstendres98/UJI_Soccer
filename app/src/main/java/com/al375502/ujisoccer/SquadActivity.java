package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.al375502.ujisoccer.database.Squad;

import java.util.ArrayList;

public class SquadActivity extends AppCompatActivity {

    public static final String TEAM = "Team";

    ListView ch, df, gk, mf, att;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);

        Intent intent = getIntent();
        int team_id = intent.getIntExtra(TEAM,61);

        SquadPresenter presenter = new SquadPresenter(this, Model.getInstance(getApplicationContext()), team_id);
        ch  = findViewById(R.id.squads);


    }

    public void FillSquadListView(ArrayList<Squad> squad) {
        ArrayList<String> coach       = new ArrayList<String>();
        ArrayList<String> goalkeepers = new ArrayList<String>();
        ArrayList<String> defenders   = new ArrayList<String>();
        ArrayList<String> midfielders = new ArrayList<String>();
        ArrayList<String> attackers   = new ArrayList<String>();
        for(int i = 0; i < squad.size(); i++) {
            switch (squad.get(i).position) {
                case "Coach":
                    coach.add(squad.get(i).name);
                    break;
                case "Goalkeeper":
                    goalkeepers.add(squad.get(i).name);
                    break;
                case "Defender":
                    defenders.add(squad.get(i).name);
                    break;
                case "Midfielder":
                    midfielders.add(squad.get(i).name);
                    break;
                case "Attacker":
                    attackers.add(squad.get(i).name);
                    break;
                default:
                    break;
            }
        }

        ArrayList<String> aux = new ArrayList<>();
        aux.add("COACH");
        for (String a: coach) {
            aux.add(a);
        }
        aux.add("GOALKEEPER");
        for (String a: goalkeepers) {
            aux.add(a);
        }
        aux.add("DEFENDER");
        for (String a: defenders) {
            aux.add(a);
        }
        aux.add("MIDFIELDER");
        for (String a: midfielders) {
            aux.add(a);
        }
        aux.add("ATTACKER");
        for (String a: attackers) {
            aux.add(a);
        }
        //My custom adapter receive a list of all the positions in order with his header at the begining of each one
        MyAdaptersquad adapter = new MyAdaptersquad(this, aux);

        ch.setAdapter(adapter);
    }
}
