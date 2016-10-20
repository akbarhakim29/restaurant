package com.bymankind.restaurant.TypesOfMenu;

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

public class CreateTypesofmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_typesofmenu);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        final Button buttonCreate = (Button) findViewById(R.id.btnCreate);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = etName.getText().toString();
                final String description = etDescription.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(CreateTypesofmenu.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreateTypesofmenu.this, AdminActivity.class);
                                CreateTypesofmenu.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateTypesofmenu.this);
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
                CreateTypesofmenuRequest createTypesofmenuRequest = new CreateTypesofmenuRequest(name,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateTypesofmenu.this);
                queue.add(createTypesofmenuRequest);
            }
        });
    }
}
