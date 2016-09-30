package com.bymankind.customer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Server-Panduit on 8/8/2016.
 */
public class PesanMejaCustomList extends ArrayAdapter<String>{
    public static String[] id_makanan;
    public static String[] nama;
    public static String[] harga;
    public static String[] deskripsi;

    private Activity context;

    public PesanMejaCustomList(Activity context, String[] id_makanan, String[] nama, String[] harga, String[] deskripsi) {
        super(context, R.layout.list_item_pesan_meja, id_makanan);
        this.context = context;
        this.id_makanan = id_makanan;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_pesan_meja, null, true);
        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.tvNamaMakanan);
        TextView textViewHarga = (TextView) listViewItem.findViewById(R.id.tvHargaMakanan);
        TextView textViewDeskripsi = (TextView) listViewItem.findViewById(R.id.tvDeskripsiMakanan);

        textViewNama.setText(nama[position]);
        textViewHarga.setText(harga[position]);
        textViewDeskripsi.setText(deskripsi[position]);

        return listViewItem;
    }
}
