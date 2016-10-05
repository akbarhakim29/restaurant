package com.bymankind.restaurant.Employee;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

/**
 * Created by Server-Panduit on 10/5/2016.
 */

public class CustomListEmployee extends ArrayAdapter<String>{
    public static String[] id_employee;
    public static String[] name;
    public static String[] position;

    private Activity context;

    public CustomListEmployee(Activity context, String[] id_employee, String[] name, String[] position) {
        super(context, R.layout.list_employee_detail, id_employee);
        this.context = context;
        this.id_employee = id_employee;
        this.name = name;
        this.position = position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_employee_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tv_IDEmployee);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_name);
        TextView textViewPosition = (TextView) listViewItem.findViewById(R.id.tv_position);

        textViewID.setText(id_employee[pos]);
        textViewName.setText(name[pos]);
        textViewPosition.setText(position[pos]);

        return listViewItem;
    }

}
