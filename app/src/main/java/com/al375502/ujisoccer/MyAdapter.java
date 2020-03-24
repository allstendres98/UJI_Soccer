package com.al375502.ujisoccer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.al375502.ujisoccer.database.TeamInStanding;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<TeamInStanding> arr;

    public MyAdapter(Context context, ArrayList<TeamInStanding> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.rowdesign,parent,false);

        TextView posT  = (TextView) convertView.findViewById(R.id.posT);
        TextView teamT = (TextView) convertView.findViewById(R.id.teamT);
        TextView plT   = (TextView) convertView.findViewById(R.id.plT);
        TextView wT    = (TextView) convertView.findViewById(R.id.wT);
        TextView dT    = (TextView) convertView.findViewById(R.id.dT);
        TextView lT    = (TextView) convertView.findViewById(R.id.lT);
        TextView ptT   = (TextView) convertView.findViewById(R.id.ptT);
        TextView gfT   = (TextView) convertView.findViewById(R.id.gfT);
        TextView gaT   = (TextView) convertView.findViewById(R.id.gaT);

        posT.setText(""  + arr.get(position).position);
        teamT.setText("" + arr.get(position).name);
        plT.setText(""   + arr.get(position).playedGames);
        wT.setText(""    + arr.get(position).won);
        dT.setText(""    + arr.get(position).draw);
        lT.setText(""    + arr.get(position).lost);
        ptT.setText(""   + arr.get(position).points);
        gfT.setText(""   + arr.get(position).goalsFor);
        gaT.setText(""   + arr.get(position).goalsAgainst);

        return convertView;
    }
}
