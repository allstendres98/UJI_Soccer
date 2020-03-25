package com.al375502.ujisoccer.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM league ORDER BY name")
    List<League> allLeagues();

    @Query("SELECT * FROM team WHERE league_id = :id ORDER BY name")
    List<Team> allTeamsInALeague(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLeague(List<League> leagues);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeams(List<Team> teams);
}
