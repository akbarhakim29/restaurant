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

public class DeletePosisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_posisi);

        final EditText etDelete = (EditText) findViewById(R.id.etDelete);
        final Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_position = Integer.parseInt(etDelete.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(DeletePosisiActivity.this,"data deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DeletePosisiActivity.this, AdminActivity.class);
                                DeletePosisiActivity.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(DeletePosisiActivity.this);
                                builder.setMessage("no data in database")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeletePosisiRequest deletePosisiRequest = new DeletePosisiRequest(id_position,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DeletePosisiActivity.this);
                queue.add(deletePosisiRequest);
            }
        });
    }
}
