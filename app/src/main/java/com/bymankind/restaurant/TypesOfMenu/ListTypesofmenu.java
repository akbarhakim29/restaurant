package com.bymankind.restaurant.TypesOfMenu;

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
import com.bymankind.restaurant.Employee.CustomListEmployee;
import com.bymankind.restaurant.Employee.DetailEmployee;
import com.bymankind.restaurant.Employee.DetailEmployeeRequest;
import com.bymankind.restaurant.Employee.ListEmployee;
import com.bymankind.restaurant.Employee.ParseJSONEmployee;
import com.bymankind.restaurant.Position.CreatePosition;
import com.bymankind.restaurant.Position.ListPosition;
import com.bymankind.restaurant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListTypesofmenu extends AppCompatActivity {
    public static final String JSON_URL = "http://192.168.100.5/restoserver/api/getAllToM";
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
                Intent createToMIntent = new Intent(ListTypesofmenu.this, CreateTypesofmenu.class);
                startActivity(createToMIntent);
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
                        Toast.makeText(ListTypesofmenu.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONTypesofmenu pj = new ParseJSONTypesofmenu(json);
        pj.parseJSON();
        final CustomListTypesofmenu cl = new CustomListTypesofmenu(this, ParseJSONTypesofmenu.id_types_of_menu,ParseJSONTypesofmenu.name);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListTypesofmenu.this,"id Types of Menu = "+cl.getItem(i),Toast.LENGTH_SHORT).show();
                final String id_types_of_menu =  cl.getItem(i);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonChildObject = jsonResponse.getJSONArray("data");
                            JSONObject jo = jsonChildObject.getJSONObject(0);
                            int code = jsonResponse.getInt("code");

                            if (code==200){

                                int id_types_of_menu = jo.getInt("id_types_of_menu");
                                String name = jo.getString("name");

                                Intent detailTypesofmenuIntent = new Intent(ListTypesofmenu.this, DetailTypesofmenu.class);
                                detailTypesofmenuIntent.putExtra("id_types_of_menu", id_types_of_menu);
                                detailTypesofmenuIntent.putExtra("name", name);

                                ListTypesofmenu.this.startActivity(detailTypesofmenuIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ListTypesofmenu.this);
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
                DetailTypesofmenuRequest detailTypesofmenuRequest = new DetailTypesofmenuRequest(id_types_of_menu,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ListTypesofmenu.this);
                queue.add(detailTypesofmenuRequest);
            }
        });
    }
}
