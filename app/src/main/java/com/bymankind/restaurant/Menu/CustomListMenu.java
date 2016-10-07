package com.bymankind.restaurant.Menu;

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
public class CustomListMenu extends ArrayAdapter<String>{
    public static String[] id_menu;
    public static String[] name;
    public static String[] price;
    public static String[] description;

    private Activity context;

    public CustomListMenu(Activity context, String[] id_menu, String[] name, String[] price, String[] description) {
        super(context, R.layout.list_menu_detail, id_menu);
        this.context = context;
        this.id_menu = id_menu;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_menu_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDMenu);
        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.tvName);
        TextView textViewHarga = (TextView) listViewItem.findViewById(R.id.tvPrice);

        textViewID.setText(id_menu[position]);
        textViewNama.setText(name[position]);
        textViewHarga.setText(price[position]);

        return listViewItem;
    }

}
