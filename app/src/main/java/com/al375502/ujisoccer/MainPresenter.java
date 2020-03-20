package com.al375502.ujisoccer;

import android.net.sip.SipSession;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.al375502.ujisoccer.database.League;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class MainPresenter {
    MainActivity view;
    Model model;

    public MainPresenter(MainActivity view, Model model) {
        this.view = view;
        this.model = model;
        prueba();


    }

    public void prueba(){
        model.getLeagues(new Response.Listener<League[]>() {
            @Override
            public void onResponse(League[] response) {
                onLeagueAvailable(response, this);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void onLeagueAvailable(League[] leagues, final Response.Listener<League[]> listener){
        if (leagues == null) {
            model.updateLeagues(listener);
        }
        else
            view.FillSpinner(leagues);
    }
}
