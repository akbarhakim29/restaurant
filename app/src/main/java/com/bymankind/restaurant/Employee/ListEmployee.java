package com.bymankind.restaurant.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class ListEmployee extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.14/restoserver/api/getAllEmployee";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_employee);

        listView =  (ListView) findViewById(R.id.lvEmployee);
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
                        Toast.makeText(ListEmployee.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONEmployee pj = new ParseJSONEmployee(json);
        pj.parseJSON();
        final CustomListEmployee cl = new CustomListEmployee(this, ParseJSONEmployee.id_employee,ParseJSONEmployee.name,ParseJSONEmployee.position);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListEmployee.this,"id employee = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_employee =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_employee = jo.getInt("id_employee");
                                String name = jo.getString("name");
                                int position = jo.getInt("position");

                                Intent detailEmployeeIntent = new Intent(ListEmployee.this, DetailEmployee.class);
                                detailEmployeeIntent.putExtra("id_employee", id_employee);
                                detailEmployeeIntent.putExtra("name", name);
                                detailEmployeeIntent.putExtra("position", position);

                                ListEmployee.this.startActivity(detailEmployeeIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListEmployee.this);
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
                DetailEmployeeRequest detailEmployeeRequest = new DetailEmployeeRequest(id_employee,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListEmployee.this);
                queue.add(detailEmployeeRequest);
            }
        });
    }

}
