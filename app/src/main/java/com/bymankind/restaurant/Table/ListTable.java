package com.bymankind.restaurant.Table;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class ListTable extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.37/restoserver/api/getAllTable";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listView =  (ListView) findViewById(R.id.lvAll);
        sendRequest();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTableIntent = new Intent(ListTable.this, CreateTable.class);
                startActivity(createTableIntent);
            }
        });
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
                        Toast.makeText(ListTable.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONTable pj = new ParseJSONTable(json);
        pj.parseJSON();
        final CustomListTable cl = new CustomListTable(this, ParseJSONTable.id_table,ParseJSONTable.description);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListTable.this,"id table = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_table =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_table = jo.getInt("id_table");
                                int id_status = jo.getInt("id_status");
                                String description = jo.getString("description");

                                Intent detailTableIntent = new Intent(ListTable.this, DetailTable.class);
                                detailTableIntent.putExtra("id_table", id_table);
                                detailTableIntent.putExtra("id_status", id_status);
                                detailTableIntent.putExtra("description", description);

                                ListTable.this.startActivity(detailTableIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListTable.this);
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
                DetailTableRequest detailEmployeeRequest = new DetailTableRequest(id_table,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListTable.this);
                queue.add(detailEmployeeRequest);
            }
        });
    }
}
