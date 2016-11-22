package com.bymankind.restaurant.Order;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class CustomListOrder extends ArrayAdapter<String>{
    public static String[] id_order;
    public static String[] name;
    public static String[] id_table;
    public static String[] description;
    public static String[] menu;

    private Activity context;

    public CustomListOrder(Activity context, String[] id_order, String[] name,String[] id_table, String[] description,String[] menu) {
        super(context, R.layout.list_order_detail, id_order);
        this.context = context;
        this.id_order = id_order;
        this.name = name;
        this.id_table = id_table;
        this.description = description;
        this.menu = menu;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_order_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDTransaction);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvCustomer);
        TextView textViewIDTable = (TextView) listViewItem.findViewById(R.id.tvIDTable);
        TextView textViewStatus = (TextView) listViewItem.findViewById(R.id.tvStatus);
        TextView textViewMenu = (TextView) listViewItem.findViewById(R.id.tvMenu);

        textViewID.setText(id_order[pos]);
        textViewName.setText(name[pos]);
        textViewIDTable.setText(id_table[pos]);
        textViewStatus.setText(description[pos]);
        textViewMenu.setText(menu[pos]);

        return listViewItem;
    }
}
