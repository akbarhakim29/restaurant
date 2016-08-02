package com.bymankind.restaurant.menuMakanan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.bymankind.restaurant.AdminActivity;
import com.bymankind.restaurant.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailMenuMakanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_makanan);

        final EditText etIDMakanan = (EditText) findViewById(R.id.etIdMakanan);
        final EditText etNamaMakanan = (EditText) findViewById(R.id.etNamaMakanan);
        final EditText etHargaMakanan =  (EditText) findViewById(R.id.etHargaMakanan);
        final EditText etDeskripsiMakanan = (EditText) findViewById(R.id.etDeskripsiMakanan);

        final Button buttonUpdate = (Button) findViewById(R.id.buttonUpdateMakanan);
        final Button buttonDelete = (Button) findViewById(R.id.buttonDeleteMakanan);

        Intent intent = getIntent();
        int id_makanan = intent.getIntExtra("id",-1);
        String nama =  intent.getStringExtra("nama");
        String harga = intent.getStringExtra("harga");
        String deskripsi = intent.getStringExtra("deskripsi");

        etIDMakanan.setText(id_makanan + "");
        etNamaMakanan.setText(nama);
        etHargaMakanan.setText(harga);
        etDeskripsiMakanan.setText(deskripsi);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idMakanan = etIDMakanan.getText().toString();
                final String namaMakanan = etNamaMakanan.getText().toString();
                final String hargaMakanan = etHargaMakanan.getText().toString();
                final String deskripsiMakanan = etDeskripsiMakanan.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success){
                                Toast.makeText(DetailMenuMakanan.this,"Menu updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailMenuMakanan.this, AdminActivity.class);
                                DetailMenuMakanan.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailMenuMakanan.this,"Menu not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
