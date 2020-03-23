package com.al375502.ujisoccer.database;
public class TeamInStanding {

    public int position;
    public String name;
    public int playedGames;
    public int won;
    public int draw;
    public int lost;
    public int points;
    public int goalsFor;
    public int goalsAgainst;


    public TeamInStanding(int position, String name, int playedGames, int won, int draw, int lost, int points, int goalsFor, int goalsAgainst) {
        this.position = position;
        this.name = name;
        this.playedGames = playedGames;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

}
