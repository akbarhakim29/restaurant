package com.bymankind.restaurant.Position;

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

public class DetailPosition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_position);

        final EditText etIDPosition = (EditText) findViewById(R.id.etPosition);
        final EditText etName = (EditText) findViewById(R.id.etIDEmployee);
        final EditText etSalary =  (EditText) findViewById(R.id.etSalary);

        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        final int id_position = intent.getIntExtra("id_position",-1);
        String name =  intent.getStringExtra("name");
        int salary = intent.getIntExtra("salary",-1);

        etIDPosition.setText(id_position + "");
        etName.setText(name);
        etSalary.setText(salary + "");

        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_position = Integer.parseInt(etIDPosition.getText().toString());
                final String name = etName.getText().toString();
                final int salary = Integer.parseInt(etSalary.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailPosition.this,"Position updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailPosition.this, AdminActivity.class);
                                DetailPosition.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailPosition.this,"Position not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdatePositionRequest updatePositionRequest = new UpdatePositionRequest(id_position,name,salary,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailPosition.this);
                queue.add(updatePositionRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_position = Integer.parseInt(etIDPosition.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailPosition.this,"Position deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailPosition.this, AdminActivity.class);
                                DetailPosition.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailPosition.this,"Position not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeletePositionRequest deletePositionRequest = new DeletePositionRequest(id_position,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailPosition.this);
                queue.add(deletePositionRequest);
            }
        });
    }
}
