package com.bymankind.restaurant.Employee;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class CreateEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_employee);

        final EditText etIDPosition = (EditText) findViewById(R.id.etIDPosition);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etContractStart = (EditText) findViewById(R.id.etContractStart);
        final EditText etContractEnd = (EditText) findViewById(R.id.etContractEnd);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etBirthPlace = (EditText) findViewById(R.id.etBirthPlace);
        final EditText etBirthDay = (EditText) findViewById(R.id.etBirthDay);
        final EditText etLastEducation = (EditText) findViewById(R.id.etLastEducation);

        final Button buttonCreated = (Button) findViewById(R.id.btnCreate);

        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear  = mcurrentDate.get(Calendar.YEAR);
        final int mMonth = mcurrentDate.get(Calendar.MONTH);
        final int mDay   = mcurrentDate.get(Calendar.DAY_OF_MONTH);


        etBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker=new DatePickerDialog(CreateEmployee.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog mDatePicker=new DatePickerDialog(CreateEmployee.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog mDatePicker=new DatePickerDialog(CreateEmployee.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        etContractEnd.setText(selectedyear+"-"+selectedmonth+"-"+selectedday);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        buttonCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int id_position = Integer.parseInt(etIDPosition.getText().toString());
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String contractStart = etContractStart.getText().toString();
                final String contractEnd = etContractEnd.getText().toString();
                final String birthPlace = etBirthPlace.getText().toString();
                final String birthDay = etBirthDay.getText().toString();
                final String lastEducation = etLastEducation.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code==200){
                                Toast.makeText(CreateEmployee.this,"data inserted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(CreateEmployee.this, AdminActivity.class);
                                CreateEmployee.this.startActivity(successIntent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEmployee.this);
                                builder.setMessage("insert data failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest(name, birthPlace, birthDay, lastEducation, id_position, username, password, contractStart, contractEnd,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateEmployee.this);
                queue.add(createEmployeeRequest);
            }
        });

    }
}
