package com.bymankind.restaurant.Customer;

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

public class DetailCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_customer);

        final EditText etID = (EditText) findViewById(R.id.etIDCustomer);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etIDStatus = (EditText) findViewById(R.id.etMember);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);
        final EditText etTelephone = (EditText) findViewById(R.id.etTelephone);

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        int id_customer = intent.getIntExtra("id_customer",-1);
        int  id_customer_status = intent.getIntExtra("id_customer_status",-1);
        String name =  intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String telephone = intent.getStringExtra("telephone");
        String description = intent.getStringExtra("description");


        etID.setText(id_customer + "");
        etIDStatus.setText(id_customer_status+"");
        etName.setText(name);
        etAddress.setText(address);
        etTelephone.setText(telephone);


        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_customer = Integer.parseInt(etID.getText().toString());
                final int id_customer_status = Integer.parseInt(etIDStatus.getText().toString());
                final String name = etName.getText().toString();
                final String address = etAddress.getText().toString();
                final String telephone = etTelephone.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailCustomer.this,"Customer updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailCustomer.this, AdminActivity.class);
                                DetailCustomer.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailCustomer.this,"Customer not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateCustomerRequest updateCustomerRequest= new UpdateCustomerRequest(id_customer,id_customer_status,name,address,telephone,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailCustomer.this);
                queue.add(updateCustomerRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_customer = Integer.parseInt(etID.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailCustomer.this,"Customer deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailCustomer.this, AdminActivity.class);
                                DetailCustomer.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailCustomer.this,"Cusstomer not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteCustomerRequest deleteCustomerRequest= new DeleteCustomerRequest(id_customer,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailCustomer.this);
                queue.add(deleteCustomerRequest);
            }
        });
    }
}
