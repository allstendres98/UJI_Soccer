package com.al375502.ujisoccer.database;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Team.class,League.class},version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract DAO getDao();
}
