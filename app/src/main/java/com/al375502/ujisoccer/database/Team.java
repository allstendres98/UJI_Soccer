package com.al375502.ujisoccer.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Team", foreignKeys = @ForeignKey(entity = League.class, parentColumns = "id", childColumns = "league_id"))
public class Team implements Parcelable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Short Name")
    public String shortName;

    @ColumnInfo(name = "Stadium")
    public String stadium;

    @ColumnInfo(name = "Colours")
    public String colours;

    @ColumnInfo(name = "Website")
    public String website;

    @ColumnInfo(name = "Year of Foundation")
    public int yearFoundation;

    @ColumnInfo(name = "league_id")
    public int league_id;

    protected Team(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortName = in.readString();
        stadium = in.readString();
        colours = in.readString();
        website = in.readString();
        yearFoundation = in.readInt();
        league_id = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(stadium);
        dest.writeString(colours);
        dest.writeString(website);
        dest.writeInt(yearFoundation);
        dest.writeInt(league_id);
    }
}
