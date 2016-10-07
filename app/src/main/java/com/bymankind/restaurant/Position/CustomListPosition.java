package com.bymankind.restaurant.Position;

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
public class CustomListPosition extends ArrayAdapter<String>{
    public static String[] id_position;
    public static String[] name;
    public static String[] salary;

    private Activity context;

    public CustomListPosition(Activity context, String[] id_position, String[] name, String[] salary) {
        super(context, R.layout.list_position_detail, id_position);
        this.context = context;
        this.id_position = id_position;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_position_detail, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.tvIDPosition);
        TextView textViewPosition = (TextView) listViewItem.findViewById(R.id.tvPosition);
        TextView textViewPassword = (TextView) listViewItem.findViewById(R.id.tvPassword);

        textViewId.setText(id_position[position]);
        textViewPosition.setText(name[position]);
        textViewPassword.setText(salary[position]);

        return listViewItem;
    }

}
