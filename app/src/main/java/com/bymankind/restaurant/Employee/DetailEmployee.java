package com.bymankind.restaurant.Employee;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.AdminActivity;
import com.bymankind.restaurant.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class DetailEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_employee);

        final EditText etID = (EditText) findViewById(R.id.etIDEmployee);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etBirthPlace = (EditText) findViewById(R.id.etBirthPlace);
        final EditText etBirthDay = (EditText) findViewById(R.id.etBirthDay);
        final EditText etIDPosition = (EditText) findViewById(R.id.etPosition);
        final EditText etContractStart = (EditText) findViewById(R.id.etContractStart);
        final EditText etContractEnd = (EditText) findViewById(R.id.etContractEnd);

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        int id_employee = intent.getIntExtra("id_employee",-1);
        String name =  intent.getStringExtra("name");
        String birthPlace = intent.getStringExtra("birthPlace");
        int  id_position = intent.getIntExtra("id_position",-1);
        String birthDay = intent.getStringExtra("birthDay");
        String contractStart = intent.getStringExtra("contractStart");
        String contractEnd = intent.getStringExtra("contractEnd");


        etID.setText(id_employee + "");
        etName.setText(name);
        etBirthPlace.setText(birthPlace);
        etBirthDay.setText(birthDay);
        etIDPosition.setText(id_position + "");
        etContractStart.setText(contractStart);
        etContractEnd.setText(contractEnd);

        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear  = mcurrentDate.get(Calendar.YEAR);
        final int mMonth = mcurrentDate.get(Calendar.MONTH);
        final int mDay   = mcurrentDate.get(Calendar.DAY_OF_MONTH);


        etBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker=new DatePickerDialog(DetailEmployee.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        etBirthDay.setText(selectedyear+"-"+selectedmonth+"-"+selectedday);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        etContractStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker=new DatePickerDialog(DetailEmployee.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        etContractStart.setText(selectedyear+"-"+selectedmonth+"-"+selectedday);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        etContractEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker=new DatePickerDialog(DetailEmployee.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        etContractEnd.setText(selectedyear+"-"+selectedmonth+"-"+selectedday);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_employee = Integer.parseInt(etID.getText().toString());
                final String name = etName.getText().toString();
                final String birthPlace = etBirthPlace.getText().toString();
                final String birthDay = etBirthDay.getText().toString();
                final int position = Integer.parseInt(etIDPosition.getText().toString());
                final String contractStart = etContractStart.getText().toString();
                final String contractEnd = etContractEnd.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailEmployee.this,"Employee updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailEmployee.this, AdminActivity.class);
                                DetailEmployee.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailEmployee.this,"Employee not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateEmployeeRequest updateEmployeeRequest= new UpdateEmployeeRequest(id_employee,name,birthPlace,birthDay,position,contractStart,contractEnd,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailEmployee.this);
                queue.add(updateEmployeeRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_employee = Integer.parseInt(etID.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailEmployee.this,"Employee deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailEmployee.this, AdminActivity.class);
                                DetailEmployee.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailEmployee.this,"Employee not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteEmployeeRequest deleteEmployeeRequest= new DeleteEmployeeRequest(id_employee,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailEmployee.this);
                queue.add(deleteEmployeeRequest);
            }
        });

    }
}
