package com.bymankind.restaurant.Chef;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

import java.util.List;

/**
 * Created by Server-Panduit on 11/14/2016.
 */

public class CustomListStatusCooked extends ArrayAdapter<String> {
    public static String[] id_order;
    public static String[] name;
    public static String[] id_table;
    public static String[] menu;
    public static String[] quantity;

    private Activity context;

    public CustomListStatusCooked(Activity context, String[] id_order,String[] name,String[] id_table, String[] menu, String[] quantity) {
        super(context, R.layout.list_status_cooked, id_order);
        this.context = context;
        this.id_order = id_order;
        this.name = name;
        this.id_table = id_table;
        this.menu = menu;
        this.quantity = quantity;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_status_cooked, parent, true);
        }
            TextView textViewID = (TextView) convertView.findViewById(R.id.tvIDTransaction);
            TextView textViewName = (TextView) convertView.findViewById(R.id.tvCustomer);
            TextView textViewTable = (TextView) convertView.findViewById(R.id.tvIDTable);
            TextView textViewMenu = (TextView) convertView.findViewById(R.id.tvMenu);
            TextView textViewQuantity = (TextView) convertView.findViewById(R.id.tvQuantity);

            textViewID.setText(id_order[pos]);
            textViewName.setText(name[pos]);
            textViewTable.setText(id_table[pos]);
            textViewMenu.setText(menu[pos]);
            textViewQuantity.setText(quantity[pos]);

        return convertView;
    }

}
