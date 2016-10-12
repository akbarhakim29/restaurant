package com.bymankind.restaurant.Customer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bymankind.restaurant.R;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class CustomListCustomer extends ArrayAdapter<String>{
    public static String[] id_customer;
    public static String[] name;
    public static String[] description;

    private Activity context;

    public CustomListCustomer(Activity context, String[] id_customer,String[] name,String[] description) {
        super(context, R.layout.list_customer_detail, id_customer);
        this.context = context;
        this.id_customer = id_customer;
        this.name = name;
        this.description = description;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_customer_detail, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvIDCustomer);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tvName);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.tvDescription);

        textViewID.setText(id_customer[pos]);
        textViewName.setText(name[pos]);
        textViewDescription.setText(description[pos]);

        return listViewItem;
    }
}
