package com.bymankind.restaurant.Inventory;

import android.content.Intent;
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

public class ListInventory extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.8/restoserver/api/getAllInventory";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_inventory);

        listView =  (ListView) findViewById(R.id.lvInventory);
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
                        Toast.makeText(ListInventory.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONInventory pj = new ParseJSONInventory(json);
        pj.parseJSON();
        final CustomListInventory cl = new CustomListInventory(this, ParseJSONInventory.id_inventory, ParseJSONInventory.name, ParseJSONInventory.amount, ParseJSONInventory.description);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListInventory.this,"id Inventory = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_inventory =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_inventory = jo.getInt("id_inventory");
                                String name = jo.getString("name");
                                int amount = jo.getInt("amount");
                                String description = jo.getString("description");

                                Intent detailInventoryIntent = new Intent(ListInventory.this, DetailInventory.class);
                                detailInventoryIntent.putExtra("id_inventory", id_inventory);
                                detailInventoryIntent.putExtra("name", name);
                                detailInventoryIntent.putExtra("amount", amount);
                                detailInventoryIntent.putExtra("description", description);

                                ListInventory.this.startActivity(detailInventoryIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListInventory.this);
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
                DetailInventoryRequest detailInventoryRequest = new DetailInventoryRequest(id_inventory,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListInventory.this);
                queue.add(detailInventoryRequest);
            }
        });
    }
}
