package com.al375502.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import org.json.*;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this, Model.getInstance(getApplicationContext()));

        spinner = findViewById(R.id.spinner);
        String x = "Ricardo te voy a pushear esto, vale?";

        String url = "https://api.football-data.org/v2/competitions?plan=TIER_ONE";

        JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener ){
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Auth-Token", "ec3c7112c4d840a6bfddc19172a19ce3");
                return headers;
            }
        };


    }
}
