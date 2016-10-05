package com.bymankind.restaurant.menuMakanan;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

public class ReadMenuMakanan extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.14/restoserver/api/getAllMenu";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_menu_makanan);

        listView =  (ListView) findViewById(R.id.lvMenuMakanan);
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
                        Toast.makeText(ReadMenuMakanan.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONReadMenu pj = new ParseJSONReadMenu(json);
        pj.parseJSON();
        final CustomListMenu cl = new CustomListMenu(this, ParseJSONReadMenu.id_menu,ParseJSONReadMenu.name,ParseJSONReadMenu.price,ParseJSONReadMenu.description);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReadMenuMakanan.this,"id menu = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_menu =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_menu = jo.getInt("id_menu");
                                String name = jo.getString("name");
                                int price = jo.getInt("price");
                                String description = jo.getString("description");

                                Intent detailMenuIntent = new Intent(ReadMenuMakanan.this, DetailMenuMakanan.class);
                                detailMenuIntent.putExtra("id_menu", id_menu);
                                detailMenuIntent.putExtra("name", name);
                                detailMenuIntent.putExtra("price", price);
                                detailMenuIntent.putExtra("description", description);

                                ReadMenuMakanan.this.startActivity(detailMenuIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ReadMenuMakanan.this);
                                builder.setMessage("nothing data")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DetailMenuMakananRequest detailMenuMakananRequest = new DetailMenuMakananRequest(id_menu,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ReadMenuMakanan.this);
                queue.add(detailMenuMakananRequest);
            }
        });
    }


}
