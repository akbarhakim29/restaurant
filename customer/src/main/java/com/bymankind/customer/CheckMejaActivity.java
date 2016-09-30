package com.bymankind.customer;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckMejaActivity extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.9/restoserver/api/getAllTable";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_meja);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitleNomer);
        listView =  (ListView) findViewById(R.id.lvLihatMeja);
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
                        Toast.makeText(CheckMejaActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        CheckMejaParseJSON pj = new CheckMejaParseJSON(json);
        pj.parseJSON();
        final CheckMejaCustomList cl = new CheckMejaCustomList(this, CheckMejaParseJSON.id_table,CheckMejaParseJSON.description);
        listView.setAdapter(cl);
    }
}
