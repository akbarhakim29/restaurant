package com.bymankind.restaurant.menuMakanan;

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
        super(context, R.layout.read_menu_makanan_list, id_menu);
        this.context = context;
        this.id_menu = id_menu;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.read_menu_makanan_list, null, true);
        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.tvName);
        TextView textViewHarga = (TextView) listViewItem.findViewById(R.id.tvPrice);

        textViewNama.setText(name[position]);
        textViewHarga.setText(price[position]);

        return listViewItem;
    }

}
