package com.al375502.ujisoccer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.al375502.ujisoccer.database.TeamInStanding;

import java.util.ArrayList;
import java.util.List;

public class MyAdaptersquad extends BaseAdapter {
    Context context;
    ArrayList<String> arr;

    public MyAdaptersquad(Context context, ArrayList<String> arr) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.squadlsit,parent,false);

        TextView text  = (TextView) convertView.findViewById(R.id.squadmovida);
        text.setText(arr.get(position));
        ArrayList<String> positions = new ArrayList<String>();
        positions.add("COACH");
        positions.add("GOALKEEPER");
        positions.add("DEFENDER");
        positions.add("MIDFIELDER");
        positions.add("ATTACKER");

        if(positions.contains(arr.get(position))){
            text.setBackgroundColor(Color.parseColor("#000000"));
            text.setTextColor(Color.parseColor("#ffffff"));
            text.setTextSize(text.getTextSize() - text.getTextSize()/3f);
        }
        return convertView;
    }
}
