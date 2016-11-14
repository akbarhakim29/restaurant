package com.bymankind.restaurant.Chef;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

/**
 * Created by Server-Panduit on 11/14/2016.
 */

public class CustomListStatusCooked extends ArrayAdapter<String> {
    public static String[] id_transaction;
    public static String[] name;
    public static String[] id_table;
    public static String[] menu;
    public static String[] quantity;

    private Activity context;

    public CustomListStatusCooked(Activity context, String[] id_transaction,String[] name,String[] id_table, String[] menu, String[] quantity) {
        super(context, R.layout.list_status_cooked, id_transaction);
        this.context = context;
        this.id_transaction = id_transaction;
        this.name = name;
        this.id_table = id_table;
        this.menu = menu;
        this.quantity = quantity;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_status_cooked, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDTransaction);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvCustomer);
        TextView textViewTable = (TextView) listViewItem.findViewById(R.id.tvIDTable);
        TextView textViewMenu = (TextView) listViewItem.findViewById(R.id.tvMenu);
        TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.tvQuantity);

        textViewID.setText(id_transaction[pos]);
        textViewName.setText(name[pos]);
        textViewTable.setText(id_table[pos]);
        textViewMenu.setText(menu[pos]);
        textViewQuantity.setText(quantity[pos]);

        return listViewItem;
    }

}
