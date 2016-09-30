package com.bymankind.restaurant.posisi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class CustomList extends ArrayAdapter<String>{
    public static String[] id_position;
    public static String[] name;
    public static String[] salary;

    private Activity context;

    public CustomList(Activity context, String[] id_position, String[] name, String[] salary) {
        super(context, R.layout.read_posisi_list, id_position);
        this.context = context;
        this.id_position = id_position;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.read_posisi_list, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.tvId_posisi);
        TextView textViewPosisi = (TextView) listViewItem.findViewById(R.id.tvPosisi);
        TextView textViewPassword = (TextView) listViewItem.findViewById(R.id.tvPassword);

        textViewId.setText(id_position[position]);
        textViewPosisi.setText(name[position]);
        textViewPassword.setText(salary[position]);

        return listViewItem;
    }

}
