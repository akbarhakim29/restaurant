package com.bymankind.customer;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PesanMejaActivity extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.9/restoserver/api/getAllMenu";
    public static final String JSON_MEJA_URL = "http://192.168.100.9/restoserver/api/getAllTable";
    private ListView listView;
    private  Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_meja);

        listView =  (ListView) findViewById(R.id.lvMenuMakanan);
        spinner = (Spinner) findViewById(R.id.spinnerMeja) ;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                        Toast.makeText(PesanMejaActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        PesanMejaParseJSON pj = new PesanMejaParseJSON(json);
        pj.parseJSON();
        final PesanMejaCustomList cl = new PesanMejaCustomList(this, PesanMejaParseJSON.id_makanan,PesanMejaParseJSON.nama,PesanMejaParseJSON.harga,PesanMejaParseJSON.deskripsi);
        listView.setAdapter(cl);
    }

}
