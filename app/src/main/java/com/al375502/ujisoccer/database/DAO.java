package com.al375502.ujisoccer.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM league ORDER BY name")
    League[] allLeagues();

    @Query("SELECT * FROM team WHERE league_id == :id ORDER BY name")
    Team[] allTeamsInALeague(int id);

    @Insert
    void insertLeague(List<League> leagues);

    @Insert
    void insertTeams(List<Team> teams);
}
