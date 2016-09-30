package com.bymankind.customer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Server-Panduit on 8/4/2016.
 */
public class CheckMejaCustomList extends ArrayAdapter<String>{
    public static String[] id_table;
    public static String[] description;

    private Activity context;

    public CheckMejaCustomList(Activity context, String[] id_table, String[] description) {
        super(context, R.layout.list_item_check_meja, id_table);
        this.context = context;
        this.id_table = id_table;
        this.description = description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_check_meja, null, true);
        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.tvTitleNomer);
        TextView textViewAvailable = (TextView) listViewItem.findViewById(R.id.tvAvailable);

        textViewNama.setText(id_table[position]);
        textViewAvailable.setText(description[position]);

        return listViewItem;
    }
}
