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
        ch  = findViewById(R.id.coach);
        gk  = findViewById(R.id.gk);
        df  = findViewById(R.id.df);
        mf  = findViewById(R.id.mf);
        att = findViewById(R.id.att);

    }

    public void FillSquadListView(ArrayList<Squad> squad) {
        ArrayList<String> coach       = new ArrayList<String>();
        ArrayList<String> goalkeepers = new ArrayList<String>();
        ArrayList<String> defenders   = new ArrayList<String>();
        ArrayList<String> midfielders = new ArrayList<String>();
        ArrayList<String> attackers   = new ArrayList<String>();
        for(int i = 0; i < squad.size(); i++){
            switch(squad.get(i).position) {
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
            //RELLENAR LISTVIEWS
            ArrayAdapter<String> chArrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    coach );
            ArrayAdapter<String> gkArrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    goalkeepers );
            ArrayAdapter<String> dfArrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    defenders );
            ArrayAdapter<String> mfArrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    midfielders );
            ArrayAdapter<String> attArrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    attackers );

            ch.setAdapter(chArrayAdapter);
            gk.setAdapter(gkArrayAdapter);
            df.setAdapter(dfArrayAdapter);
            mf.setAdapter(mfArrayAdapter);
            att.setAdapter(attArrayAdapter);
        }

    }
}
