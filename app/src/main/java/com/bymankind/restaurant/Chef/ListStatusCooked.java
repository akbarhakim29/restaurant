package com.bymankind.restaurant.Chef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.Customer.DetailCustomer;
import com.bymankind.restaurant.Customer.ListCustomer;
import com.bymankind.restaurant.LoginActivity;
import com.bymankind.restaurant.LoginRequest;
import com.bymankind.restaurant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 11/14/2016.
 */

public class ListStatusCooked extends AppCompatActivity{
    public static final String JSON_URL = "http://192.168.100.5/restoserver/api/getAllCooked";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listView =  (ListView) findViewById(R.id.lvAll);
        sendRequest();
    }

    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListStatusCooked.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONStatusCooked pj = new ParseJSONStatusCooked(json);
        pj.parseJSON();
        final CustomListStatusCooked cl = new CustomListStatusCooked(this, ParseJSONStatusCooked.id_transaction, ParseJSONStatusCooked.name, ParseJSONStatusCooked.id_table, ParseJSONStatusCooked.menu, ParseJSONStatusCooked.quantity);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String id_transaction =  cl.getItem(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ListStatusCooked.this);
                builder.setMessage("do you have completed this process ?")
                        .setTitle("COOKING PROCCESS!")
                        .setIcon(R.mipmap.ic_resto)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int d) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                                            JSONObject jo = jsonChildObject.getJSONObject(0);
                                            int code = jsonResponse.getInt("code");

                                            if (code==200){

                                                int id_customer = jo.getInt("id_customer");
                                                int id_transaction = jo.getInt("id_transaction");
                                                int id_table = jo.getInt("id_table");
                                                int id_menu = jo.getInt("id_menu");
                                                String name = jo.getString("name");

                                            }
                                            else {
                                                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ListStatusCooked.this);
                                                builder.setMessage("nothing data")
                                                        .setNegativeButton("Retry",null)
                                                        .create()
                                                        .show();
                                            }
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                UpdateCheckedRequest updateCheckedRequest = new UpdateCheckedRequest(id_transaction,id_customer,id_table,id_menu,timeOrderCooked,responseListener);
                                RequestQueue queue = Volley.newRequestQueue(ListStatusCooked.this);
                                queue.add(updateCheckedRequest);

                                Toast t = Toast.makeText(getApplicationContext(), "You Choose 'YES' button", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        })
                        .setNegativeButton("NOT YET", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int d) {
                                Toast t = Toast.makeText(getApplicationContext(), "You Choose 'NOT YET' button", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
