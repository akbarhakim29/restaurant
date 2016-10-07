package com.bymankind.restaurant.Inventory;

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

public class DetailInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_inventory);

        final EditText etIDInventory = (EditText) findViewById(R.id.etIDInventory);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etAmount =  (EditText) findViewById(R.id.etAmount);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);

        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        int id_inventory = intent.getIntExtra("id_inventory",-1);
        String name =  intent.getStringExtra("name");
        int amount = intent.getIntExtra("amount",-1);
        String description = intent.getStringExtra("description");

        etIDInventory.setText(id_inventory + "");
        etName.setText(name);
        etAmount.setText(amount + "");
        etDescription.setText(description);

        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_inventory = Integer.parseInt(etIDInventory.getText().toString());
                final String name = etName.getText().toString();
                final int amount = Integer.parseInt(etAmount.getText().toString());
                final String description = etDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailInventory.this,"Inventory updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailInventory.this, AdminActivity.class);
                                DetailInventory.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailInventory.this,"Inventory not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest(id_inventory,name,amount,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailInventory.this);
                queue.add(updateInventoryRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_inventory = Integer.parseInt(etIDInventory.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailInventory.this,"Inventory deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailInventory.this, AdminActivity.class);
                                DetailInventory.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailInventory.this,"Inventory not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteInventoryRequest deleteInventoryRequest = new DeleteInventoryRequest(id_inventory,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailInventory.this);
                queue.add(deleteInventoryRequest);
            }
        });
    }
}
