package com.al375502.ujisoccer;

import android.media.MediaSync;
import android.net.sip.SipSession;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.al375502.ujisoccer.database.League;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    MainActivity view;
    Model model;

    public MainPresenter(MainActivity view, Model model) {
        this.view = view;
        this.model = model;
        prueba();


    }

    public void prueba(){
        model.getLeagues(new Response.Listener<List<League>>() {
            @Override
            public void onResponse(List<League> response) {
                onLeagueAvailable(response);
            }
        });
    }

    private void onLeagueAvailable(List<League> leagues){
        if (leagues.size() == 0) {
            model.updateLeagues(new Response.Listener<List<League>>() {
                @Override
                public void onResponse(List<League> leagues) {
                    view.FillSpinner(leagues);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }


    }
}
