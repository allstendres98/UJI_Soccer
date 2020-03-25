package com.al375502.ujisoccer;

import android.widget.Toast;

import com.al375502.ujisoccer.database.Squad;
import com.al375502.ujisoccer.database.Team;
import com.al375502.ujisoccer.database.TeamInStanding;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class SquadPresenter {
    SquadActivity view;
    Model model;

    public SquadPresenter(SquadActivity view, Model model, int team_id) {
        this.view  = view;
        this.model = model;
        GetSquad(team_id);
    }

    public void GetSquad(final int team_id) {
        model.updateSquad(team_id, new Response.Listener<ArrayList<Squad>>() {
            @Override
            public void onResponse(ArrayList<Squad> response) {
                view.FillSquadListView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processError(error.getMessage());
            }
        });

    }

    public void processError(String e) {
        Toast toast = Toast.makeText(view ,e,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
