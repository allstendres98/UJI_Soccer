package com.al375502.ujisoccer;

import android.media.MediaSync;
import android.net.sip.SipSession;
import android.util.Log;
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
        model.getLeagues(new Response.Listener<ArrayList<League> >() {
            @Override
            public void onResponse(ArrayList<League>  response) {
                onLeagueAvailable(response);
            }
        });
    }

    private void onLeagueAvailable(ArrayList<League> leagues){
        if (leagues.size() == 0) {
            model.updateLeagues(new Response.Listener<ArrayList<League>>() {
                @Override
                public void onResponse(ArrayList<League> leagues) {
                    //view.FillSpinner(leagues);
                    Log.d("qwer", "onResponse: " + leagues);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }


    }
}
