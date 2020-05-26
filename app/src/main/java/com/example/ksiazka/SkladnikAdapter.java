package com.example.ksiazka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SkladnikAdapter extends ArrayAdapter<Skladnik> {

    private Context ctx;
    int resource;

    public SkladnikAdapter(Context context, int resource, ArrayList<Skladnik> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).nazwa;
        String count = getItem(position).ilosc;

        Skladnik skladnik = new Skladnik(name, count);
        LayoutInflater inflater = LayoutInflater.from(ctx);
        convertView = inflater.inflate(resource, parent, false);

        TextView nameViewName = (TextView) convertView.findViewById(R.id.textView1);
        TextView countViewName = (TextView) convertView.findViewById(R.id.textView2);
        nameViewName.setText(name);
        countViewName.setText(count);

        return convertView;
    }
}
