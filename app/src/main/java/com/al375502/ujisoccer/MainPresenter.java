package com.al375502.ujisoccer;

import android.net.sip.SipSession;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.al375502.ujisoccer.database.League;
import com.android.volley.Response;

import java.util.ArrayList;

public class MainPresenter {
    MainActivity view;
    Model model;

    public MainPresenter(MainActivity view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void fillSpiner(final Spinner spinner) {
        model.getLeagues(new Response.Listener<League[]>(){
            @Override
            public void onResponse(League[] response) {
                if(response == null){
                    model.updateLeagues();
                }else{
                    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(view,
                            android.R.layout.simple_spinner_dropdown_item,
                            response);
                     spinner.setAdapter(spinnerArrayAdapter);
                }
            }
        });
    }
}
