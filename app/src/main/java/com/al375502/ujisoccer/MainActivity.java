package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.*;

import com.al375502.ujisoccer.database.League;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public Spinner spinner;
    public ArrayList<League> Leagues;
    public TextView country, start, end;
    public Button standingsButton;
    int leagueId;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, Model.getInstance(getApplicationContext()));

        spinner = findViewById(R.id.spinner);
        country = findViewById(R.id.country);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        standingsButton = findViewById(R.id.standingsButton);
        standingsButton.setEnabled(false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String leagueName = parentView.getSelectedItem().toString();
                for(int i = 0; i < Leagues.size(); i++)
                {
                    if(Leagues.get(i).name == leagueName)
                    {
                        leagueId = Leagues.get(i).id;
                        standingsButton.setEnabled(true);
                        country.setText(Leagues.get(i).country);
                        start.setText(Leagues.get(i).start);
                        end.setText(Leagues.get(i).end+"");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });

        standingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity();
            }
        });

    }

    public void ChangeActivity(){
        Intent intent = new Intent(this, ListTeamActivity.class);
        intent.putExtra(ListTeamActivity.LEAGUE, leagueId);
        startActivity(intent);
    }
    public void FillSpinner(ArrayList<String> leagues){
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                leagues);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    /*
    public GetInfo CreateInfo(){
        GetInfo getinfo = new GetInfo(Model.getInstance(getApplicationContext()), leagueId);
        return getinfo;
    }*/
}
