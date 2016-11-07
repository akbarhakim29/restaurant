package com.bymankind.restaurant.Customer;

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

public class ListCustomer extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.37/restoserver/api/getAllCustomer";
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
                        Toast.makeText(ListCustomer.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONCustomer pj = new ParseJSONCustomer(json);
        pj.parseJSON();
        final CustomListCustomer cl = new CustomListCustomer(this, ParseJSONCustomer.id_customer, ParseJSONCustomer.name, ParseJSONCustomer.description);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListCustomer.this,"id customer = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_customer =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_customer = jo.getInt("id_customer");
                                int id_customer_status = jo.getInt("id_customer_status");
                                String name = jo.getString("name");
                                String address = jo.getString("address");
                                String telephone = jo.getString("telephone");
                                String description = jo.getString("description");

                                Intent detailMenuIntent = new Intent(ListCustomer.this, DetailCustomer.class);
                                detailMenuIntent.putExtra("id_customer", id_customer);
                                detailMenuIntent.putExtra("id_customer_status", id_customer_status);
                                detailMenuIntent.putExtra("name", name);
                                detailMenuIntent.putExtra("address", address);
                                detailMenuIntent.putExtra("telephone", telephone);
                                detailMenuIntent.putExtra("description", description);

                                ListCustomer.this.startActivity(detailMenuIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListCustomer.this);
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
                DetailCustomerRequest detailCustomerRequest = new DetailCustomerRequest(id_customer,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListCustomer.this);
                queue.add(detailCustomerRequest);
            }
        });
    }
}
