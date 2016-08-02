package com.bymankind.restaurant.menuMakanan;

import android.app.Activity;
import android.content.Intent;
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
    public static String[] id_makanan;
    public static String[] nama;
    public static String[] harga;
    public static String[] deskripsi;

    private Activity context;

    public CustomListMenu(Activity context, String[] id_makanan, String[] nama, String[] harga, String[] deskripsi) {
        super(context, R.layout.read_menu_makanan_list, id_makanan);
        this.context = context;
        this.id_makanan = id_makanan;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.read_menu_makanan_list, null, true);
        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.tvNamaMakanan);
        TextView textViewHarga = (TextView) listViewItem.findViewById(R.id.tvHargaMakanan);

        textViewNama.setText(nama[position]);
        textViewHarga.setText(harga[position]);

        return listViewItem;
    }

}
