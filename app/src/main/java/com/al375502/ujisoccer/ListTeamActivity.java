package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        final ListTeamActivity thisView = this;

        listView = findViewById(R.id.listView);

        final ListTeamPresenter presenter = new ListTeamPresenter(this, Model.getInstance(getApplicationContext()));
        presenter.GetStandingsAndTeams(league_id);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String teamSelectedS = "";
                Team teamSelected;
                for(int i = 0; i < teamInStandings.size(); i++)
                {
                    if(teamInStandings.get(i).position == position+1){
                        teamSelectedS = teamInStandings.get(i).name;
                    }
                }

                for(int i = 0; i < teams.size(); i++)
                {
                    //Log.d("Equipo","deberia de salir pero solo lo pensé");
                    if(teamSelectedS.equals(teams.get(i).name))
                    {
                        teamSelected = teams.get(i);
                        TeamDialog teamDialog = new TeamDialog(teamSelected, thisView);
                        teamDialog.show(getSupportFragmentManager(),"team dialog");
                    }
                }

                //
            }
        });
    }

    //EL PROBLEMA ERA EL FillDataBaseWithTeams del Model, porque por alguna razon, el año de fundación de algunos equipos era null, y yo intentaba pillar entero y petaba jaja xd

    public void FillListView(ArrayList<TeamInStanding> response) {
        teamInStandings = response;
        adapter = new MyAdapter(this, response);
        listView.setAdapter(adapter);
    }

    public void PopUpTeam(ArrayList<Team> allteams) {
        teams = allteams;
    }

    public void GetIntoWebsite(String url)
    {
        /*WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl(teamWeb);*/
        try {
            Uri webpage = Uri.parse(url);
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    
}
