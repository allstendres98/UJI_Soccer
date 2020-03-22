package com.al375502.ujisoccer.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "TeamInStanding"/*, foreignKeys = @ForeignKey(entity = League.class, parentColumns = "id", childColumns = "league_id")*/)
public class TeamInStanding implements Parcelable {
    @PrimaryKey
    public int position;

    @ColumnInfo(name = "Name")
    public String name;

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

    @ColumnInfo(name = "PlayedGames")
    public int playedGames;

    @ColumnInfo(name = "Won")
    public int won;

    @ColumnInfo(name = "Draw")
    public int draw;

    @ColumnInfo(name = "Lost")
    public int lost;

    @ColumnInfo(name = "Points")
    public int points;

    @ColumnInfo(name = "GoalsFor")
    public int goalsFor;

    @ColumnInfo(name = "GoalsAgainst")
    public int goalsAgainst;


    protected TeamInStanding(Parcel in) {
        position = in.readInt();
        name = in.readString();
        playedGames = in.readInt();
        won = in.readInt();
        draw = in.readInt();
        lost = in.readInt();
        points = in.readInt();
        goalsFor = in.readInt();
        goalsAgainst = in.readInt();
    }

    public static final Creator<TeamInStanding> CREATOR = new Creator<TeamInStanding>() {
        @Override
        public TeamInStanding createFromParcel(Parcel in) {
            return new TeamInStanding(in);
        }

        @Override
        public TeamInStanding[] newArray(int size) {
            return new TeamInStanding[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
        dest.writeString(name);
        dest.writeInt(playedGames);
        dest.writeInt(won);
        dest.writeInt(draw);
        dest.writeInt(lost);
        dest.writeInt(points);
        dest.writeInt(goalsFor);
        dest.writeInt(goalsAgainst);
    }
}
