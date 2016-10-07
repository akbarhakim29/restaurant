package com.bymankind.restaurant.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.AdminActivity;
import com.bymankind.restaurant.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_menu);

        final EditText etIDMenu = (EditText) findViewById(R.id.etIDMenu);
        final EditText etName = (EditText) findViewById(R.id.etIDEmployee);
        final EditText etPrice =  (EditText) findViewById(R.id.etPrice);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);

        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        int id_menu = intent.getIntExtra("id_menu",-1);
        String nama =  intent.getStringExtra("name");
        int harga = intent.getIntExtra("price",-1);
        String deskripsi = intent.getStringExtra("description");

        etIDMenu.setText(id_menu + "");
        etName.setText(nama);
        etPrice.setText(harga + "");
        etDescription.setText(deskripsi);

        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_menu = Integer.parseInt(etIDMenu.getText().toString());
                final String name = etName.getText().toString();
                final int price = Integer.parseInt(etPrice.getText().toString());
                final String description = etDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailMenu.this,"Menu updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailMenu.this, AdminActivity.class);
                                DetailMenu.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailMenu.this,"Menu not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateMenuRequest updateMenuRequest = new UpdateMenuRequest(id_menu,name,price,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailMenu.this);
                queue.add(updateMenuRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_menu = Integer.parseInt(etIDMenu.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailMenu.this,"Menu deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailMenu.this, AdminActivity.class);
                                DetailMenu.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailMenu.this,"Menu not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteMenuRequest deleteMenuRequest = new DeleteMenuRequest(id_menu,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailMenu.this);
                queue.add(deleteMenuRequest);
            }
        });

    }
}
