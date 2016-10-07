package com.bymankind.restaurant.Table;

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
import com.bymankind.restaurant.Employee.DeleteEmployeeRequest;
import com.bymankind.restaurant.Employee.DetailEmployee;
import com.bymankind.restaurant.Employee.UpdateEmployeeRequest;
import com.bymankind.restaurant.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_table);

        final EditText etID = (EditText) findViewById(R.id.etIDTable);
        final EditText etIDStatus = (EditText) findViewById(R.id.etIDStatus);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        int id_table = intent.getIntExtra("id_table",-1);
        int id_status =  intent.getIntExtra("id_status",-1);
        String description = intent.getStringExtra("description");


        etID.setText(id_table + "");
        etIDStatus.setText(id_status + "");
        etDescription.setText(description);


        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_table = Integer.parseInt(etID.getText().toString());
                final int id_status = Integer.parseInt(etIDStatus.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailTable.this,"Table updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailTable.this, AdminActivity.class);
                                DetailTable.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailTable.this,"Table not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateTableRequest updateTableRequest= new UpdateTableRequest(id_table,id_status,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailTable.this);
                queue.add(updateTableRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_table = Integer.parseInt(etID.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailTable.this,"Table deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailTable.this, AdminActivity.class);
                                DetailTable.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailTable.this,"Table not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteTableRequest deleteTableRequest= new DeleteTableRequest(id_table,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailTable.this);
                queue.add(deleteTableRequest);
            }
        });


    }
}
