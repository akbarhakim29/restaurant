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

public class UpdatePosisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_posisi);

        final EditText etOldPosisi = (EditText) findViewById(R.id.etPosisi);
        final EditText etNewPosisi = (EditText) findViewById(R.id.etNewPosisi);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String oldPosisi = etOldPosisi.getText().toString();
                final String newPosisi = etNewPosisi.getText().toString();
                final String newPassword = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success){
                                Toast.makeText(UpdatePosisiActivity.this,"data updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(UpdatePosisiActivity.this, AdminActivity.class);
                                UpdatePosisiActivity.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePosisiActivity.this);
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
                UpdatePosisiRequest updatePosisiRequest = new UpdatePosisiRequest(oldPosisi,newPosisi,newPassword,responseListener);
                RequestQueue queue = Volley.newRequestQueue(UpdatePosisiActivity.this);
                queue.add(updatePosisiRequest);
            }
        });
    }
}
