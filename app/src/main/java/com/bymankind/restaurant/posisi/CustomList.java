package com.bymankind.restaurant.posisi;

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
public class CustomList extends ArrayAdapter<String>{
    public static String[] id_admin;
    public static String[] posisi;
    public static String[] password;

    private Activity context;

    public CustomList(Activity context, String[] id_admin, String[] posisi, String[] password) {
        super(context, R.layout.read_posisi_list, id_admin);
        this.context = context;
        this.id_admin = id_admin;
        this.posisi = posisi;
        this.password = password;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.read_posisi_list, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.tvId_posisi);
        TextView textViewPosisi = (TextView) listViewItem.findViewById(R.id.tvPosisi);
        TextView textViewPassword = (TextView) listViewItem.findViewById(R.id.tvPassword);

        textViewId.setText(id_admin[position]);
        textViewPosisi.setText(posisi[position]);
        textViewPassword.setText(password[position]);

        return listViewItem;
    }

}
