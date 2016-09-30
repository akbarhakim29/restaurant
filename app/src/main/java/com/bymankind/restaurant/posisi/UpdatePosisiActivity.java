package com.bymankind.restaurant.posisi;

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

public class UpdatePosisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_posisi);

        final EditText etID = (EditText) findViewById(R.id.etPosition);
        final EditText etNewPosition = (EditText) findViewById(R.id.etNewPosition);
        final EditText etNewSalary = (EditText) findViewById(R.id.etSalary);
        final Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_position = Integer.parseInt(etID.getText().toString());
                final String newPosition = etNewPosition.getText().toString();
                final int newSalary = Integer.parseInt(etNewSalary.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(UpdatePosisiActivity.this,"Position updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(UpdatePosisiActivity.this, AdminActivity.class);
                                UpdatePosisiActivity.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(UpdatePosisiActivity.this,"position not updated" ,Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdatePosisiRequest updatePosisiRequest = new UpdatePosisiRequest(id_position,newPosition,newSalary,responseListener);
                RequestQueue queue = Volley.newRequestQueue(UpdatePosisiActivity.this);
                queue.add(updatePosisiRequest);
            }
        });
    }
}
