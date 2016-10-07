package com.bymankind.restaurant.TypesOfMenu;

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

public class DetailTypesofmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_typesofmenu);

        final EditText etID = (EditText) findViewById(R.id.etIDToM);
        final EditText etName = (EditText) findViewById(R.id.etName);

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        int id_types_of_menu = intent.getIntExtra("id_types_of_menu",-1);
        String name =  intent.getStringExtra("name");


        etID.setText(id_types_of_menu + "");
        etName.setText(name);

        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_types_of_menu = Integer.parseInt(etID.getText().toString());
                final String name = etName.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailTypesofmenu.this,"Types of Menu updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailTypesofmenu.this, AdminActivity.class);
                                DetailTypesofmenu.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailTypesofmenu.this,"Types of Menu not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateTypesofmenuRequest updateTypesofmenuRequest= new UpdateTypesofmenuRequest(id_types_of_menu,name,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailTypesofmenu.this);
                queue.add(updateTypesofmenuRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_types_of_menu = Integer.parseInt(etID.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailTypesofmenu.this,"Types of Menu deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailTypesofmenu.this, AdminActivity.class);
                                DetailTypesofmenu.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailTypesofmenu.this,"Types of Menu not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteTypesofmenuRequest deleteTypesofmenuRequest= new DeleteTypesofmenuRequest(id_types_of_menu,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailTypesofmenu.this);
                queue.add(deleteTypesofmenuRequest);
            }
        });
    }
}
