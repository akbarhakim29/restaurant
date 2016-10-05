package com.bymankind.restaurant.Employee;

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
import com.bymankind.restaurant.menuMakanan.DeleteMenuMakananRequest;
import com.bymankind.restaurant.menuMakanan.DetailMenuMakanan;
import com.bymankind.restaurant.menuMakanan.UpdateMenuMakananRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class DetailEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_employee);

        final EditText etID = (EditText) findViewById(R.id.etIDEmployee);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etBirthPlace = (EditText) findViewById(R.id.etBirthPlace);
        final EditText etBirthDay = (EditText) findViewById(R.id.etBirthDay);
        final EditText etPosition = (EditText) findViewById(R.id.etPosition);
        final EditText etContractStart = (EditText) findViewById(R.id.etContractStart);
        final EditText etContractEnd = (EditText) findViewById(R.id.etContractEnd);

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        int id_employee = intent.getIntExtra("id_employee",-1);
        String name =  intent.getStringExtra("name");
        String birthPlace = intent.getStringExtra("birthPlace");
        String birthDay = intent.getStringExtra("birthDay");
        String contractStart = intent.getStringExtra("contractStart");
        String contractEnd = intent.getStringExtra("contractEnd");


        etID.setText(id_employee + "");
        etName.setText(name);
        etBirthPlace.setText(birthPlace);
        etBirthDay.setText(birthDay);
        etContractStart.setText(contractStart);
        etContractEnd.setText(contractEnd);


        // action update
       /* btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_employee = Integer.parseInt(etID.getText().toString());
                final String name = etName.getText().toString();
                final String birthPlace = etBirthPlace.getText().toString();
                final Date birthDay = etBirthDay.getText().toString();
                final Date contractStart = etContractStart.getText().toString();
                final Date contractEnd = etContractEnd.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailEmployee.this,"Menu updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailEmployee.this, AdminActivity.class);
                                DetailEmployee.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailMenuMakanan.this,"Menu not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateMenuMakananRequest updateMenuMakananRequest= new UpdateMenuMakananRequest(idMakanan,namaMakanan,hargaMakanan,deskripsiMakanan,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailMenuMakanan.this);
                queue.add(updateMenuMakananRequest);
            }
        });

        // action delete
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int idMakanan = Integer.parseInt(etIDMakanan.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailMenuMakanan.this,"Menu deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailMenuMakanan.this, AdminActivity.class);
                                DetailMenuMakanan.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailMenuMakanan.this,"Menu not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteMenuMakananRequest deleteMenuMakananRequest= new DeleteMenuMakananRequest(idMakanan,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailMenuMakanan.this);
                queue.add(deleteMenuMakananRequest);
            }
        });*/

    }
}
