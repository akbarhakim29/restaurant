package com.bymankind.restaurant.Menu;

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

public class CreateMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_menu);

        final EditText etID_Types_of_Menu = (EditText) findViewById(R.id.etIDToM);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPrice = (EditText) findViewById(R.id.etPrice);
        final EditText etPicture = (EditText) findViewById(R.id.etPicture);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        final Button buttonCreated = (Button) findViewById(R.id.btnCreate);

        buttonCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int id_types_of_menu = Integer.parseInt(etID_Types_of_Menu.getText().toString());
                final String name = etName.getText().toString();
                final int price = Integer.parseInt(etPrice.getText().toString());
                final String description = etDescription.getText().toString();
                final String picture = etPicture.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(CreateMenu.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreateMenu.this, AdminActivity.class);
                                CreateMenu.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateMenu.this);
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
                CreateMenuRequest createMenuRequest = new CreateMenuRequest(id_types_of_menu,name,price,picture,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateMenu.this);
                queue.add(createMenuRequest);
            }
        });
    }
}
