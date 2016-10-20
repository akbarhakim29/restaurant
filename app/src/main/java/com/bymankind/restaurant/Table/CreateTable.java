package com.bymankind.restaurant.Table;

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
import com.bymankind.restaurant.Position.CreatePosition;
import com.bymankind.restaurant.Position.CreatePositionRequest;
import com.bymankind.restaurant.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_table);

        final EditText etIDTable = (EditText) findViewById(R.id.etIDTable);
        final EditText etIDStatus = (EditText) findViewById(R.id.etIDStatus);
        final Button buttonCreate = (Button) findViewById(R.id.btnCreate);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int id_table = Integer.parseInt(etIDTable.getText().toString());
                final int id_status = Integer.parseInt(etIDStatus.getText().toString());

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(CreateTable.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreateTable.this, AdminActivity.class);
                                CreateTable.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateTable.this);
                                builder.setMessage("Data not inserted")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CreateTableRequest createTableRequest = new CreateTableRequest(id_table,id_status,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateTable.this);
                queue.add(createTableRequest);
            }
        });
    }
}
