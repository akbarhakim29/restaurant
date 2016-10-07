package com.bymankind.restaurant.Position;

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

public class CreatePosition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_position);

        final EditText etPosition = (EditText) findViewById(R.id.etPosition);
        final EditText etSalary = (EditText) findViewById(R.id.etSalary);
        final Button buttonCreate = (Button) findViewById(R.id.btnCreate);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = etPosition.getText().toString();
                final int salary = Integer.parseInt(etSalary.getText().toString());

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(CreatePosition.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreatePosition.this, AdminActivity.class);
                                CreatePosition.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreatePosition.this);
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
                CreatePositionRequest createPositionRequest = new CreatePositionRequest(name,salary,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreatePosition.this);
                queue.add(createPositionRequest);
            }
        });
    }
}
