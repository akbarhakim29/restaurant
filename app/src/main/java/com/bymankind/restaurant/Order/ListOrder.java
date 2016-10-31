package com.bymankind.restaurant.Order;

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

public class ListOrder extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.30/restoserver/api/getAllOrder";
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
                        Toast.makeText(ListOrder.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONOrder pj = new ParseJSONOrder(json);
        pj.parseJSON();
        final CustomListOrder cl = new CustomListOrder(this, ParseJSONOrder.id_transaction,ParseJSONOrder.name,ParseJSONOrder.id_table,ParseJSONOrder.description,ParseJSONOrder.menu);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListOrder.this,"id Transaction = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_transaction =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_transaction = jo.getInt("id_transaction");
                                int id_customer = jo.getInt("id_customer");
                                int id_table = jo.getInt("id_table");
                                String name = jo.getString("name");
                                int id_menu = jo.getInt("id_menu");
                                int quantity = jo.getInt("quantity");
                                int id_order_status = jo.getInt("id_order_status");
                                String description = jo.getString("description");
                                String date = jo.getString("date");
                                String timeOrderPlaced = jo.getString("timeOrderPlaced");
                                String timeOrderCooked = jo.getString("timeOrderCooked");
                                String timeOrderChecked = jo.getString("timeOrderChecked");
                                String timeOrderAccepted = jo.getString("timeOrderAccepted");
                                String timePaid = jo.getString("timePaid");


                                Intent detailOrderIntent = new Intent(ListOrder.this, DetailOrder.class);
                                detailOrderIntent.putExtra("id_transaction", id_transaction);
                                detailOrderIntent.putExtra("id_customer", id_customer);
                                detailOrderIntent.putExtra("name", name);
                                detailOrderIntent.putExtra("id_table", id_table);
                                detailOrderIntent.putExtra("id_menu", id_menu);
                                detailOrderIntent.putExtra("quantity", quantity);
                                detailOrderIntent.putExtra("id_order_status", id_order_status);
                                detailOrderIntent.putExtra("description", description);
                                detailOrderIntent.putExtra("date", date);
                                detailOrderIntent.putExtra("timeOrderPlaced", timeOrderPlaced);
                                detailOrderIntent.putExtra("timeOrderCooked", timeOrderCooked);
                                detailOrderIntent.putExtra("timeOrderChecked", timeOrderChecked);
                                detailOrderIntent.putExtra("timeOrderAccepted", timeOrderAccepted);
                                detailOrderIntent.putExtra("timePaid", timePaid);

                                ListOrder.this.startActivity(detailOrderIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListOrder.this);
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
                DetailOrderRequest detailOrderRequest = new DetailOrderRequest(id_transaction,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListOrder.this);
                queue.add(detailOrderRequest);
            }
        });
    }
}

