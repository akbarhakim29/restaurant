package com.bymankind.restaurant.menuMakanan;

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

public class UpdateMenuMakanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu_makanan);

        final EditText etOldMenu = (EditText) findViewById(R.id.etOldMenu);
        final EditText etNewMenu = (EditText) findViewById(R.id.etNewMenu);
        final EditText etNewPrice = (EditText) findViewById(R.id.etNewPrice);
        final EditText etNewDescription = (EditText) findViewById(R.id.etNewDescription);
        final Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        etOldMenu.setText(nama);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String oldMenu = etOldMenu.getText().toString();
                final String newMenu = etNewMenu.getText().toString();
                final String newPrice = etNewPrice.getText().toString();
                final String newDescription = etNewDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success){
                                Toast.makeText(UpdateMenuMakanan.this,"Menu updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(UpdateMenuMakanan.this, AdminActivity.class);
                                UpdateMenuMakanan.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(UpdateMenuMakanan.this,"Menu not updated" ,Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateMenuMakananRequest updateMenuMakananRequest = new UpdateMenuMakananRequest(oldMenu,newMenu,newPrice,newDescription,responseListener);
                RequestQueue queue = Volley.newRequestQueue(UpdateMenuMakanan.this);
                queue.add(updateMenuMakananRequest);
            }
        });
    }
}
