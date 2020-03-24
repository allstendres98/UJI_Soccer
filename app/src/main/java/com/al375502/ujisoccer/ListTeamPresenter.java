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
        model.updateStandings(league,new Response.Listener<ArrayList<TeamInStanding>>() {
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

    public void GetTeams(final int league_id){
        model.getTeams(new Response.Listener<ArrayList<Team>>() {
            @Override
            public void onResponse(ArrayList<Team> response) {
                onTeamsAvailable(response, league_id);
            }
        });
    }

    private void onTeamsAvailable(ArrayList<Team> teams, int league_id){
        if (teams.size() == 0) {
            model.updateTeams(league_id, new Response.Listener<ArrayList<Team>>() {
                @Override
                public void onResponse(ArrayList<Team> teams) {
                    ArrayList<String> names = new ArrayList<>();
                    for (Team team:teams
                    ) {
                        names.add(team.name);
                    }

                    //put in in VIEW
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
