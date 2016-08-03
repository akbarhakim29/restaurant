package com.bymankind.restaurant.menuMakanan;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Objects;

public class CreateMenuMakanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_makanan);

        final EditText etIDMakanan = (EditText) findViewById(R.id.etIDMakanan);
        final EditText etNamaMakanan = (EditText) findViewById(R.id.etNamaMakanan);
        final EditText etHargaMakanan = (EditText) findViewById(R.id.etHargaMakanan);
        final EditText etDeskripsi = (EditText) findViewById(R.id.etDeskripsiMakanan);
        final EditText etToken = (EditText) findViewById(R.id.etToken);
        final Button buttonCreated = (Button) findViewById(R.id.buttonCreateMakanan);

        String token = "f31294ac0a9255aa810e2cd9639485260ff9b3c1";
        etToken.setText(token);

        buttonCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int id_makanan = Integer.parseInt(etIDMakanan.getText().toString());
                final String nama = etNamaMakanan.getText().toString();
                final int harga = Integer.parseInt(etHargaMakanan.getText().toString());
                final String deskripsi = etDeskripsi.getText().toString();
                final String token = etToken.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("response");

                            String status = jsonObject1.getString("status");

                            if (status.equals("success")){
                                Toast.makeText(CreateMenuMakanan.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreateMenuMakanan.this, AdminActivity.class);
                                CreateMenuMakanan.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateMenuMakanan.this);
                                builder.setMessage("insert data failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CreateMenuMakananRequest createMenuMakananRequest = new CreateMenuMakananRequest(token,id_makanan,nama,harga,deskripsi,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateMenuMakanan.this);
                queue.add(createMenuMakananRequest);
            }
        });
    }
}
