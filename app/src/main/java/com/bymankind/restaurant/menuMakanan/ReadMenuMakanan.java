package com.bymankind.restaurant.menuMakanan;

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

public class ReadMenuMakanan extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.4/login/sql_restaurant.php?operasi=menu_makanan";
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
        final CustomListMenu cl = new CustomListMenu(this, ParseJSONReadMenu.id_makanan,ParseJSONReadMenu.nama,ParseJSONReadMenu.harga,ParseJSONReadMenu.deskripsi);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReadMenuMakanan.this,cl.getItem(i),Toast.LENGTH_SHORT).show();
            }
        });
    }


}
