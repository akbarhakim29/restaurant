package com.bymankind.restaurant;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bymankind.restaurant.Employee.ListEmployee;
import com.bymankind.restaurant.menuMakanan.ReadMenuMakanan;


public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final ImageView positionLink = (ImageView) findViewById(R.id.btn_position);
        final ImageView menuLink = (ImageView) findViewById(R.id.btn_menu);
        final ImageView employeeLink = (ImageView) findViewById(R.id.btn_employee);
        final ImageView komLink = (ImageView) findViewById(R.id.btn_kindofmenu);
        final ImageView customerLink = (ImageView) findViewById(R.id.btn_customer);
        final ImageView tableLink = (ImageView) findViewById(R.id.btn_table);
        final ImageView orderLink = (ImageView) findViewById(R.id.btn_order);
        final ImageView inventoryLink = (ImageView) findViewById(R.id.btn_inventory);

        employeeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeIntent = new Intent(AdminActivity.this, ListEmployee.class);
                startActivity(employeeIntent);
            }
        });

        menuLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(AdminActivity.this, ReadMenuMakanan.class);
                startActivity(menuIntent);
            }
        });


    }
}
