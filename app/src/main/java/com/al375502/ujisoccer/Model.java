package com.al375502.ujisoccer;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.al375502.ujisoccer.database.DAO;
import com.al375502.ujisoccer.database.Database;
import com.al375502.ujisoccer.database.League;
import com.android.volley.*;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Model {
    DAO dao;
    public static final String url = "https://api.football-data.org/v2/competitions";
    public static final String competitions = "?plan=TIER_ONE";
    public static final String standings = "/standing";
    public static final String teams = "/teams";

    private RequestQueue queue;

    private static  Model model;

    private Model(Context context){
        Database dataBase = Room.databaseBuilder(context, Database.class, "DataBase").build();
    }

    public static Model getInstance(Context context) {
        if (model == null){
            model = new Model(context);
        }
        return model;
    }


    public void getLeagues( final Listener<League[]> leagueresponse){
        new AsyncTask<Void, Void, League[]>(){
            @Override
            protected League[] doInBackground(Void... voids) {
                return dao.allLeagues();
            }

            @Override
            protected void onPostExecute(League[] league) {
               leagueresponse.onResponse(league);
            }
        }.execute();
    }

    public List<League> updateLeagues(){

        List<League> Leagues;
        JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                FillDataBaseWithLeagues(response);
            }
        }, errorListener){
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Auth-Token", "ec3c7112c4d840a6bfddc19172a19ce3");
                return headers;
            }
        };
        return ;
    }

    public void FillDataBaseWithLeagues(JSONObject response){

        List<League> leagues = new ArrayList<>();

        try{
            JSONArray array = response.getJSONArray("competitions");

            for(int i = 0; i < array.length(); i++){
                JSONObject extractedleague = array.getJSONObject(i);

                int id = extractedleague.getInt("id");
                String name = extractedleague.getString("name");
                JSONObject area = extractedleague.getJSONObject("area");
                String countryName = area.getString("name");
                JSONObject currentSeason = extractedleague.getJSONObject("currentSeason");
                String startDate = currentSeason.getString("startDate");
                String endDate = currentSeason.getString("endDate");

                leagues.add(new League(id, name, countryName, startDate, endDate));

            }

            dao.insertLeague(leagues);
        }
        catch (JSONException e)
        {

        }


    }
}
