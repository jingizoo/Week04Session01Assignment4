package com.jalaj.firstapp.autosearchproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jalaj.firstapp.autosearchproject.R;

import java.util.ArrayList;

/**
 * Created by jalajmehta on 7/16/16.
 */

public class DatabaseDisplayAdapter extends BaseAdapter {

    ArrayList<String> arrayList;
    LayoutInflater layoutInflater;

    public DatabaseDisplayAdapter(Context ctx, ArrayList<String> arrayList)
    {
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.layout_txtview_liner,parent,false);
        TextView textView = (TextView)convertView.findViewById(R.id.textView);
        textView.setText((String)arrayList.get(position));

        return convertView;
    }
}
