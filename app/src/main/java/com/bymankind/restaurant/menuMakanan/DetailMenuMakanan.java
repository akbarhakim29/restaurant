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

public class DetailMenuMakanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_makanan);

        final EditText etIDMakanan = (EditText) findViewById(R.id.etIDMenu);
        final EditText etNamaMakanan = (EditText) findViewById(R.id.etIDEmployee);
        final EditText etHargaMakanan =  (EditText) findViewById(R.id.etPrice);
        final EditText etDeskripsiMakanan = (EditText) findViewById(R.id.etDescription);

        final Button buttonUpdate = (Button) findViewById(R.id.buttonUpdateMakanan);
        final Button buttonDelete = (Button) findViewById(R.id.buttonDeleteMakanan);

        Intent intent = getIntent();
        int id_menu = intent.getIntExtra("id_menu",-1);
        String nama =  intent.getStringExtra("name");
        int harga = intent.getIntExtra("price",-1);
        String deskripsi = intent.getStringExtra("description");

        etIDMakanan.setText(id_menu + "");
        etNamaMakanan.setText(nama);
        etHargaMakanan.setText(harga + "");
        etDeskripsiMakanan.setText(deskripsi);

        // action update
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int idMakanan = Integer.parseInt(etIDMakanan.getText().toString());
                final String namaMakanan = etNamaMakanan.getText().toString();
                final int hargaMakanan = Integer.parseInt(etHargaMakanan.getText().toString());
                final String deskripsiMakanan = etDeskripsiMakanan.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
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
                UpdateMenuMakananRequest updateMenuMakananRequest= new UpdateMenuMakananRequest(idMakanan,namaMakanan,hargaMakanan,deskripsiMakanan,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailMenuMakanan.this);
                queue.add(updateMenuMakananRequest);
            }
        });

        // action delete
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int idMakanan = Integer.parseInt(etIDMakanan.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailMenuMakanan.this,"Menu deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailMenuMakanan.this, AdminActivity.class);
                                DetailMenuMakanan.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailMenuMakanan.this,"Menu not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteMenuMakananRequest deleteMenuMakananRequest= new DeleteMenuMakananRequest(idMakanan,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailMenuMakanan.this);
                queue.add(deleteMenuMakananRequest);
            }
        });

    }
}
