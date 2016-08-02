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

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

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
                Toast.makeText(ReadMenuMakanan.this,"id = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){

                                int id_makanan = jsonResponse.getInt("id_makanan");
                                String nama = jsonResponse.getString("nama");
                                String harga = jsonResponse.getString("harga");
                                String deskripsi = jsonResponse.getString("deskripsi");

                                Intent detailMenuIntent = new Intent(ReadMenuMakanan.this, DetailMenuMakanan.class);
                                detailMenuIntent.putExtra("id", id_makanan);
                                detailMenuIntent.putExtra("nama", nama);
                                detailMenuIntent.putExtra("harga", harga);
                                detailMenuIntent.putExtra("deskripsi", deskripsi);

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
                DetailMenuMakananRequest detailMenuMakananRequest = new DetailMenuMakananRequest(id,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ReadMenuMakanan.this);
                queue.add(detailMenuMakananRequest);
            }
        });
    }


}
