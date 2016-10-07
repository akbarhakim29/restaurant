package com.bymankind.restaurant.Inventory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class CustomListInventory extends ArrayAdapter<String>{
    public static String[] id_inventory;
    public static String[] name;
    public static String[] amount;
    public static String[] description;

    private Activity context;

    public CustomListInventory(Activity context, String[] id_inventory, String[] name, String[] amount, String[] description) {
        super(context, R.layout.list_inventory_detail, id_inventory);
        this.context = context;
        this.id_inventory = id_inventory;
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_inventory_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDInventory);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvName);
        TextView textViewAmount = (TextView) listViewItem.findViewById(R.id.tvAmount);

        textViewID.setText(id_inventory[pos]);
        textViewName.setText(name[pos]);
        textViewAmount.setText(amount[pos]);

        return listViewItem;
    }

}
