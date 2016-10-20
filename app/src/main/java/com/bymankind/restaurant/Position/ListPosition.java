package com.bymankind.restaurant.Position;

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

public class ListPosition extends AppCompatActivity{
    public static final String JSON_URL = "http://192.168.100.8/restoserver/api/getAllPosition";
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
                Intent createPositionIntent = new Intent(ListPosition.this, CreatePosition.class);
                startActivity(createPositionIntent);
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
                        Toast.makeText(ListPosition.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONPosition pj = new ParseJSONPosition(json);
        pj.parseJSON();
        final CustomListPosition cl = new CustomListPosition(this, ParseJSONPosition.id_position, ParseJSONPosition.name, ParseJSONPosition.salary);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListPosition.this,"id position = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_position =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_position = jo.getInt("id_position");
                                String name = jo.getString("name");
                                int salary = jo.getInt("salary");

                                Intent detailPositionIntent = new Intent(ListPosition.this, DetailPosition.class);
                                detailPositionIntent.putExtra("id_position", id_position);
                                detailPositionIntent.putExtra("name", name);
                                detailPositionIntent.putExtra("salary", salary);

                                ListPosition.this.startActivity(detailPositionIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListPosition.this);
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
                DetailPositionRequest detailPositionRequest = new DetailPositionRequest(id_position,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListPosition.this);
                queue.add(detailPositionRequest);
            }
        });

    }
}
