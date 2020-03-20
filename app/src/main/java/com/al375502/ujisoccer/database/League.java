package com.al375502.ujisoccer.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "League")
public class League implements Comparable<League>, Parcelable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Country")
    public String country;

    @ColumnInfo(name = "Start")
    public String start;

    @ColumnInfo(name = "End")
    public String end;

    public League(int id, String name, String country, String start, String end) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.start = start;
        this.end = end;
    }

    protected League(Parcel in) {
        id = in.readInt();
        name = in.readString();
        country = in.readString();
        start = in.readString();
        end = in.readString();
    }

    public static final Creator<League> CREATOR = new Creator<League>() {
        @Override
        public League createFromParcel(Parcel in) {
            return new League(in);
        }

        @Override
        public League[] newArray(int size) {
            return new League[size];
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
        dest.writeString(country);
        dest.writeString(start);
        dest.writeString(end);
    }

    @Override
    public int compareTo(League o) {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
