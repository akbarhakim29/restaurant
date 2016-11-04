package com.bymankind.restaurant.Menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.BitmapLruCache;
import com.bymankind.restaurant.R;
/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class CustomListMenu extends ArrayAdapter<String>{
    public static String[] id_menu;
    public static String[] name;
    public static String[] price;
    public static String[] picture;
    public static String[] description;
    private Bitmap[] bitmaps;

    private Activity context;/*
    ImageLoader.ImageCache imageCache = new BitmapLruCache();
    ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(context), imageCache);*/

    public CustomListMenu(Activity context, String[] id_menu, String[] name, String[] price, Bitmap[] bitmaps, String[] description) {
        super(context, R.layout.list_menu_detail, id_menu);

        this.context = context;
        this.id_menu = id_menu;
        this.name = name;
        this.price = price;
        this.bitmaps = bitmaps;
        this.description = description;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_menu_detail, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivMenu);
        TextView textViewID = (TextView) convertView.findViewById(R.id.tvIDMenu);
        TextView textViewName = (TextView) convertView.findViewById(R.id.tvName);
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.tvPrice);

        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[pos],100,50,false));
        textViewID.setText(id_menu[pos]);
        textViewName.setText(name[pos]);
        textViewPrice.setText(price[pos]);

        return convertView;
    }

}
