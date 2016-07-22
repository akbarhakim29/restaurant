package com.bymankind.restaurant;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String posisi = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            int id_posisi =jsonResponse.getInt("id_posisi");
                            String posisi = jsonResponse.getString("posisi");

                            if (success && id_posisi==1){
                                Intent kokiIntent = new Intent(LoginActivity.this, KokiActivity.class);
                                kokiIntent.putExtra("posisi", posisi);
                                startActivity(kokiIntent);
                            }
                            else if (success && id_posisi==2){
                                Intent pramusajiIntent = new Intent(LoginActivity.this, PramusajiActivity.class);
                                pramusajiIntent.putExtra("posisi",posisi);
                                LoginActivity.this.startActivity(pramusajiIntent);
                            }
                            else if (success && id_posisi==3){
                                Intent kasirIntent = new Intent(LoginActivity.this, KasirActivity.class);
                                kasirIntent.putExtra("posisi",posisi);
                                LoginActivity.this.startActivity(kasirIntent);
                            }
                            else if (success && id_posisi==4){
                                Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                adminIntent.putExtra("posisi",posisi);
                                LoginActivity.this.startActivity(adminIntent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
                LoginRequest loginRequest = new LoginRequest(posisi,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });
    }
}
