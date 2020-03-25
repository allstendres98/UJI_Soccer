package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.al375502.ujisoccer.database.Squad;

import java.util.ArrayList;

public class SquadActivity extends AppCompatActivity {

    public static final String TEAM = "Team";
    ArrayList<Squad> squad;
    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);

        Intent intent = getIntent();
        int team_id = intent.getIntExtra(TEAM,61);

        SquadPresenter presenter = new SquadPresenter(this, Model.getInstance(getApplicationContext()), team_id);

        prueba = findViewById(R.id.textView3);
    }

    public void FillSquadListView(ArrayList<Squad> response) {
        squad = response;
        prueba.setText(squad.get(0).name+" "+squad.get(0).position+" "+ squad.get(0).role );
    }
}
