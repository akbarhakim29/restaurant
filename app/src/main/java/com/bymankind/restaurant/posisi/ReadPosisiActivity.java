package com.bymankind.restaurant.posisi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.R;

public class ReadPosisiActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String JSON_URL = "http://192.168.1.173/login/sql_restaurant.php?operasi=read_posisi";
    private ListView listView;
    private Button buttonGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_posisi);

        buttonGet = (Button) findViewById(R.id.button);
        buttonGet.setOnClickListener(this);
        listView =  (ListView) findViewById(R.id.lvReadPosisi);

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
                        Toast.makeText(ReadPosisiActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONReadPosisi pj = new ParseJSONReadPosisi(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSONReadPosisi.id,ParseJSONReadPosisi.posisi,ParseJSONReadPosisi.password);
        listView.setAdapter(cl);
    }

    @Override
    public void onClick(View v) {
        sendRequest();
    }
}
