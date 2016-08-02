package com.bymankind.restaurant.posisi;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class CreatePosisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_posisi);

        final EditText etPosisi = (EditText) findViewById(R.id.etPosisi);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etIDPosisi = (EditText) findViewById(R.id.etIDPosisi);
        final Button buttonCreated = (Button) findViewById(R.id.buttonCreate);

        buttonCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = Integer.parseInt(etIDPosisi.getText().toString());
                final String posisi = etPosisi.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success){
                                Toast.makeText(CreatePosisiActivity.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreatePosisiActivity.this, AdminActivity.class);
                                CreatePosisiActivity.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreatePosisiActivity.this);
                                builder.setMessage("login failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CreatePosisiRequest createPosisiRequest = new CreatePosisiRequest(id,posisi,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreatePosisiActivity.this);
                queue.add(createPosisiRequest);
            }
        });
    }
}
