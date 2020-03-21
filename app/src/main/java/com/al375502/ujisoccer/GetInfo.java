package com.al375502.ujisoccer;

import android.os.Parcel;
import android.os.Parcelable;

public class GetInfo implements Parcelable {
    Model model;
    int leagueId;

    public GetInfo(Model model, int league) {
        this.model = model;
        this.leagueId = league;
    }

    protected GetInfo(Parcel in) {
        leagueId = in.readInt();
    }

    public Model getModel() {
        return model;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public static final Creator<GetInfo> CREATOR = new Creator<GetInfo>() {
        @Override
        public GetInfo createFromParcel(Parcel in) {
            return new GetInfo(in);
        }

        @Override
        public GetInfo[] newArray(int size) {
            return new GetInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(leagueId);
    }
}
