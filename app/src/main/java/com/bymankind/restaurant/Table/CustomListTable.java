package com.bymankind.restaurant.Table;

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

public class CustomListTable extends ArrayAdapter<String>{
    public static String[] id_table;
    public static String[] description;

    private Activity context;

    public CustomListTable(Activity context, String[] id_table, String[] description) {
        super(context, R.layout.list_table_detail, id_table);
        this.context = context;
        this.id_table = id_table;
        this.description = description;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_table_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDTable);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.tvDescription);

        textViewID.setText(id_table[pos]);
        textViewDescription.setText(description[pos]);

        return listViewItem;
    }
}
