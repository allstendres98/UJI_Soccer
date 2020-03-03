package com.al375502.ujisoccer.database;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DAO {
    @Query("SELECT * FROM league ORDER BY name")
    League[] allLeagues();

    @Query("SELECT * FROM team WHERE league_id == :id ORDER BY name")
    Team[] allTeamsInALeague(int id);
}
