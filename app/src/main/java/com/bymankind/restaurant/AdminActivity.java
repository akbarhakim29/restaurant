package com.bymankind.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bymankind.restaurant.Employee.ListEmployee;
import com.bymankind.restaurant.Inventory.ListInventory;
import com.bymankind.restaurant.Menu.ListMenu;
import com.bymankind.restaurant.Position.ListPosition;
import com.bymankind.restaurant.Table.ListTable;
import com.bymankind.restaurant.TypesOfMenu.ListTypesofmenu;


public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final ImageView positionLink = (ImageView) findViewById(R.id.btn_position);
        final ImageView menuLink = (ImageView) findViewById(R.id.btn_menu);
        final ImageView employeeLink = (ImageView) findViewById(R.id.btn_employee);
        final ImageView tomLink = (ImageView) findViewById(R.id.btn_typesofmenu);
        final ImageView customerLink = (ImageView) findViewById(R.id.btn_customer);
        final ImageView tableLink = (ImageView) findViewById(R.id.btn_table);
        final ImageView orderLink = (ImageView) findViewById(R.id.btn_order);
        final ImageView inventoryLink = (ImageView) findViewById(R.id.btn_inventory);

        positionLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent positionIntent = new Intent(AdminActivity.this, ListPosition.class);
                startActivity(positionIntent);
            }
        });

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
                Intent menuIntent = new Intent(AdminActivity.this, ListMenu.class);
                startActivity(menuIntent);
            }
        });

        tomLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tomIntent = new Intent(AdminActivity.this, ListTypesofmenu.class);
                startActivity(tomIntent);
            }
        });

        tableLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tableIntent = new Intent(AdminActivity.this, ListTable.class);
                startActivity(tableIntent);
            }
        });

        inventoryLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inventoryIntent = new Intent(AdminActivity.this, ListInventory.class);
                startActivity(inventoryIntent);
            }
        });


    }
}
