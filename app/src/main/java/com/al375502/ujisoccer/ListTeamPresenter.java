package com.al375502.ujisoccer;

import android.widget.Toast;

import com.al375502.ujisoccer.database.League;
import com.al375502.ujisoccer.database.Team;
import com.al375502.ujisoccer.database.TeamInStanding;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class ListTeamPresenter {
    ListTeamActivity view;
    Model model;

    public ListTeamPresenter(ListTeamActivity view, Model model) {
        this.view = view;
        this.model = model;
        //this.league = getInfo.getLeagueId();
        //setLeague();
        //GetTeams();
    }

    public void GetStandings(int league) {
        model.updateTeams(league,new Response.Listener<ArrayList<TeamInStanding>>() {
            @Override
            public void onResponse(ArrayList<TeamInStanding> response) {
                view.FillListView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processError(error.getMessage());
            }
        });
    }

    public void GetTeams(){
        model.getTeams(new Response.Listener<ArrayList<Team>>() {
            @Override
            public void onResponse(ArrayList<Team> response) {
                //onLeagueAvailable(response);
            }
        });
    }

    private void onTeamsAvailable(ArrayList<Team> teams){


    }

    public void processError(String e) {
        Toast toast = Toast.makeText(view ,e,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
