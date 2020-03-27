package com.al375502.ujisoccer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.al375502.ujisoccer.database.Squad;
import com.al375502.ujisoccer.database.TeamInStanding;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterForSquad extends BaseAdapter {
    Context context;
    ArrayList<ArrayAdapter<String>> squad;

    public MyAdapterForSquad(Context context, ArrayList<ArrayAdapter<String>> squad) {
        this.context = context;
        this.squad = squad;
    }

    @Override
    public int getCount() {
        return 5;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_squad,parent,false);

        TextView pos  = (TextView) convertView.findViewById(R.id.position);
        ListView list = (ListView) convertView.findViewById(R.id.listofposition);
        String role = "";
        switch(position){
            case 0: role = "COACH"; break;
            case 1: role = "GOALKEEPER"; break;
            case 2: role = "DEFENDER"; break;
            case 3: role = "MIDFILEDER"; break;
            case 4: role = "ATTACKER"; break;
            default: break;
        }

        pos.setText(role);
        list.setAdapter(squad.get(position));

        return convertView;
    }
}
