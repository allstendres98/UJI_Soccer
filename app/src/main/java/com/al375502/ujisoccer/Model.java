package com.al375502.ujisoccer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.al375502.ujisoccer.database.DAO;
import com.al375502.ujisoccer.database.Database;
import com.al375502.ujisoccer.database.League;
import com.al375502.ujisoccer.database.Squad;
import com.al375502.ujisoccer.database.Team;
import com.al375502.ujisoccer.database.TeamInStanding;
import com.android.volley.*;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Model {
    DAO dao;
    public static final String url = "https://api.football-data.org/v2/competitions";
    public static final String competitions = "?plan=TIER_ONE";
    public static final String standings = "/standings";
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
    public void getLeagues(final Listener<ArrayList<League>> leagueresponse){
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
            ArrayList<Integer> desiredLeagues = new ArrayList<Integer>(Arrays.asList(2021, 2015, 2002, 2019, 2003, 2017, 2014));


            for(int i = 0; i < array.length(); i++){
                boolean isIn = false;
                JSONObject extractedleague = array.getJSONObject(i);

                int id = extractedleague.getInt("id");
                for(int j=0; j < desiredLeagues.size(); j++)
                {
                    if(id == desiredLeagues.get(j)) isIn = true;
                }
                if(isIn) {
                    String name              = extractedleague.getString("name");
                    JSONObject area          = extractedleague.getJSONObject("area");
                    String countryName       = area.getString("name");
                    JSONObject currentSeason = extractedleague.getJSONObject("currentSeason");
                    String startDate         = currentSeason.getString("startDate");
                    String endDate           = currentSeason.getString("endDate");

                    leagues.add(new League(id, name, countryName, startDate, endDate));
                }
            }

            insertLeaguesInDao(leagues, listener );
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

    public void getTeams(final int league_id,final Listener<ArrayList<Team>> teamresponse) {
        new AsyncTask<Void, Void, ArrayList<Team>>(){
            @Override
            protected ArrayList<Team> doInBackground(Void... voids) {
                return new ArrayList<>(dao.allTeamsInALeague(league_id));
            }
            @Override
            protected void onPostExecute(ArrayList<Team> teams) {
                teamresponse.onResponse(teams);
            }
        }.execute();
    }

    public void updateTeams(int league_id, final Listener<ArrayList<Team>> listener, Response.ErrorListener errorListener) {

        JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, url+"/"+league_id+teams, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                FillDataBaseWithTeams(response, listener);
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

    private void FillDataBaseWithTeams(JSONObject response, final Listener<ArrayList<Team>> listener){
        ArrayList<Team> teams = new ArrayList<>();

        try{
            JSONArray teamstable = response.getJSONArray("teams");
            JSONObject competition = response.getJSONObject("competition");
            for(int i = 0; i < teamstable.length(); i++){
                JSONObject extractedteam = teamstable.getJSONObject(i);

                int id            = extractedteam.getInt("id");
                String name       = extractedteam.isNull("name")?       "Unkown" : extractedteam.getString("name");
                String shortName  = extractedteam.isNull("shortName")?  "Unkown" : extractedteam.getString("shortName");
                String stadium    = extractedteam.isNull("venue")?      "Unkown" : extractedteam.getString("venue");
                String colors     = extractedteam.isNull("clubColors")? "Unkown" : extractedteam.getString("clubColors");
                String website    = extractedteam.isNull("website")?    "Unkown" : extractedteam.getString("website");
                int founded       = extractedteam.isNull("founded")?  0 : extractedteam.getInt("founded");
                int league_id     = competition.getInt("id");
                Log.d("Prueba", "FUNDACION del : "+name+", " + founded);

                teams.add(new Team(id, name, shortName, stadium, colors, website, founded, league_id)); //REVISAAAAR Y COMPROBAR el league_id
            }
            insertTeamsInDao(teams, listener);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    private void insertTeamsInDao(final ArrayList<Team> teams, final Listener<ArrayList<Team>> listener){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void[] voids) {
                dao.insertTeams(teams);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listener.onResponse(teams);
            }
        }.execute();
    }


    public void updateStandings(int actualLeague,final Listener<ArrayList<TeamInStanding>> listener, Response.ErrorListener errorListener) {

        JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, url+"/"+actualLeague+standings, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GetTeamInStandingArray(response, listener);
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

    private void GetTeamInStandingArray(JSONObject response, final Listener<ArrayList<TeamInStanding>> listener){

        ArrayList<TeamInStanding> standings = new ArrayList<>();

        try{
            JSONArray array = response.getJSONArray("standings");
            JSONObject standing = array.getJSONObject(0);
            JSONArray  table = standing.getJSONArray("table");

            for(int i = 0; i < table.length(); i++){
                JSONObject teamInStanding = table.getJSONObject(i);
                JSONObject team = teamInStanding.getJSONObject("team");

                String name      = team.getString("name");
                int position     = teamInStanding.getInt("position");
                int playedGames  = teamInStanding.getInt("playedGames");
                int won          = teamInStanding.getInt("won");
                int draw         = teamInStanding.getInt("draw");
                int lost         = teamInStanding.getInt("lost");
                int points       = teamInStanding.getInt("points");
                int goalsFor     = teamInStanding.getInt("goalsFor");
                int goalsAgainst = teamInStanding.getInt("goalsAgainst");


                standings.add(new TeamInStanding(position, name, playedGames, won, draw, lost, points, goalsFor, goalsAgainst));
            }

            listener.onResponse(standings);
        }
        catch (JSONException e)
        {

        }
    }

    public void updateSquad(int actualTeam, final Listener<ArrayList<Squad>> listener, Response.ErrorListener errorListener) {

        JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.football-data.org/v2/teams/"+actualTeam, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GetTeamSquad(response, listener);
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

    private void GetTeamSquad(JSONObject response, final Listener<ArrayList<Squad>> listener){

        ArrayList<Squad> squads = new ArrayList<>();

        try{
            JSONArray array = response.getJSONArray("squad");

            for(int i = 0; i < array.length(); i++){
                JSONObject human = array.getJSONObject(i);

                String name     = human.getString("name");
                String position = human.isNull("position")? "Coach" : human.getString("position");
                String role     = human.getString("role");

                squads.add(new Squad(role, position, name));
            }
            listener.onResponse(squads);
        }
        catch (JSONException e)
        {

        }
    }
}
