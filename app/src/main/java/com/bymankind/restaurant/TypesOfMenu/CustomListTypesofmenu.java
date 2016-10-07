package com.bymankind.restaurant.TypesOfMenu;

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

public class CustomListTypesofmenu extends ArrayAdapter<String> {
    public static String[] id_types_of_menu;
    public static String[] name;

    private Activity context;

    public CustomListTypesofmenu(Activity context, String[] id_types_of_menu, String[] name) {
        super(context, R.layout.list_typesofmenu_detail, id_types_of_menu);
        this.context = context;
        this.id_types_of_menu = id_types_of_menu;
        this.name = name;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_typesofmenu_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDToM);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvName);

        textViewID.setText(id_types_of_menu[pos]);
        textViewName.setText(name[pos]);

        return listViewItem;
    }
}
