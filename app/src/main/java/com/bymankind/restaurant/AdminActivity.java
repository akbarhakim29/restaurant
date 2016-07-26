package com.bymankind.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.posisi.CreatePosisiActivity;
import com.bymankind.restaurant.posisi.DeletePosisiActivity;
import com.bymankind.restaurant.posisi.ReadPosisiActivity;
import com.bymankind.restaurant.posisi.ReadPosisiRequest;
import com.bymankind.restaurant.posisi.UpdatePosisiActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final TextView createPosisiLink = (TextView) findViewById(R.id.tvCreatePosisi);
        final TextView readPosisiLink = (TextView) findViewById(R.id.tvReadPosisi);
        final TextView updatePosisiLink = (TextView) findViewById(R.id.tvUpdatePosisi);
        final TextView deletePosisiLink = (TextView) findViewById(R.id.tvDeletePosisi);

        final TextView createMenuMakananLink = (TextView) findViewById(R.id.tvCreateMenuMakanan);
        final TextView readMenuMakananLink = (TextView) findViewById(R.id.tvReadMenuMakanan);
        final TextView updateMenuMakananLink = (TextView) findViewById(R.id.tvUpdateMenuMakanan);
        final TextView deleteMenuMakananLink = (TextView) findViewById(R.id.tvDeleteMenuMakanan);

        final TextView createMejaLink = (TextView) findViewById(R.id.tvCreateMeja);
        final TextView readMejaLink = (TextView) findViewById(R.id.tvReadMeja);
        final TextView deleteMejaLink = (TextView) findViewById(R.id.tvDeleteMeja);

        createPosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createPosisiIntent = new Intent(AdminActivity.this , CreatePosisiActivity.class);
                startActivity(createPosisiIntent);
            }
        });

        readPosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("data");
                            boolean success = jsonResponse.getBoolean("success");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject json_data =  jsonArray.getJSONObject(i);
                                String posisi[i] = json_data.getString("posisi");
                                String password[i] = json_data.getString("password");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ReadPosisiRequest readPosisiRequest = new ReadPosisiRequest(posisi,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(AdminActivity.this);
                queue.add(readPosisiRequest);
                /*Intent readPosisiIntent = new Intent(AdminActivity.this , ReadPosisiActivity.class);
                startActivity(readPosisiIntent);*/
            }
        });

        updatePosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updatePosisiIntent = new Intent(AdminActivity.this , UpdatePosisiActivity.class);
                startActivity(updatePosisiIntent);

            }
        });

        deletePosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deletePosisiIntent = new Intent(AdminActivity.this , DeletePosisiActivity.class);
                startActivity(deletePosisiIntent);
            }
        });


    }
}
