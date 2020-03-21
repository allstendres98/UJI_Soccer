package com.al375502.ujisoccer;

import android.widget.Toast;

import com.al375502.ujisoccer.database.League;
import com.al375502.ujisoccer.database.Team;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class ListTeamPresenter {
    ListTeamActivity view;
    Model model;
    int league;

    public ListTeamPresenter(ListTeamActivity view, GetInfo getInfo) {
        this.view = view;
        this.model = getInfo.getModel();
        this.league = getInfo.getLeagueId();
        setLeague();
        GetTeams();
    }
    public void setLeague(){
        model.actualLeague = league;
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
        if (teams.size() == 0) {
            model.updateLeagues(new Response.Listener<ArrayList<Team>>() {
                @Override
                public void onResponse(ArrayList<Team> teams) {
                    ArrayList<String> names = new ArrayList<>();
                    for (Team team:teams
                    ) {
                        names.add(team.);
                    }
                    //view.FillSpinner(names);
                    //view.Leagues = leagues;
                    //Log.d("qwer", "onResponse: " + leagues);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    processError(error.getMessage());
                }
            });
        }


    }

    public void processError(String e) {
        Toast toast = Toast.makeText(view ,e,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
