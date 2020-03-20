package com.al375502.ujisoccer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.al375502.ujisoccer.database.DAO;
import com.al375502.ujisoccer.database.Database;
import com.al375502.ujisoccer.database.League;
import com.android.volley.*;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.ErrorListener;


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
        dao = dataBase.getDao();
        queue = Volley.newRequestQueue(context);
    }

    public static Model getInstance(Context context) {
        if (model == null){
            model = new Model(context);
        }
        return model;
    }


    public void getLeagues(final Listener<ArrayList<League> > leagueresponse){
        new AsyncTask<Void, Void, ArrayList<League>>(){
            @Override
            protected ArrayList<League> doInBackground(Void... voids) {
                return new ArrayList<>(dao.allLeagues());
            }
            @Override
            protected void onPostExecute(ArrayList<League> league) {
               leagueresponse.onResponse(league);
            }
        }.execute();
    }

    public void updateLeagues(final Listener<ArrayList<League>> listener, final Response.ErrorListener errorListener){

        JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, url+competitions, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                FillDataBaseWithLeagues(response, listener);
            }
        }, errorListener){
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Auth-Token", "ec3c7112c4d840a6bfddc19172a19ce3");
                return headers;
            }
        };
        queue.add(ObjectRequest);
    }

    private void FillDataBaseWithLeagues(JSONObject response, final Listener<ArrayList<League>> listener){

        ArrayList<League> leagues = new ArrayList<>();

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

            insertLeaguesInDao(leagues, listener );
            //tryagain.onResponse(dao.allLeagues());
        }
        catch (JSONException e)
        {

        }
    }

    private void insertLeaguesInDao(final ArrayList<League> leagues, final Listener<ArrayList<League>> listener){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void[] voids) {
                dao.insertLeague(leagues);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listener.onResponse(leagues);
            }
        }.execute();
    }

}
