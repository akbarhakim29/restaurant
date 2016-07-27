package com.bymankind.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bymankind.restaurant.posisi.CreatePosisiActivity;
import com.bymankind.restaurant.posisi.DeletePosisiActivity;
import com.bymankind.restaurant.posisi.ReadPosisiActivity;
import com.bymankind.restaurant.posisi.UpdatePosisiActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final TextView createPosisiLink = (TextView) findViewById(R.id.tvCreatePosisi);
        final TextView readPosisiLink = (TextView) findViewById(R.id.tvReadPosisi);
        final TextView updatePosisiLink = (TextView) findViewById(R.id.tvUpdatePosisi);
        final TextView deletePosisiLink = (TextView) findViewById(R.id.tvDeletePosisi);

        final TextView createMenuMakananLink = (TextView) findViewById(R.id.tvCreateMenuMakanan);
        final TextView readMenuMakananLink = (TextView) findViewById(R.id.tvReadMenuMakanan);
        final TextView updateMenuMakananLink = (TextView) findViewById(R.id.tvUpdateMenuMakanan);
        final TextView deleteMenuMakananLink = (TextView) findViewById(R.id.tvDeleteMenuMakanan);

        final TextView createMejaLink = (TextView) findViewById(R.id.tvCreateMeja);
        final TextView readMejaLink = (TextView) findViewById(R.id.tvReadMeja);
        final TextView deleteMejaLink = (TextView) findViewById(R.id.tvDeleteMeja);

        createPosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createPosisiIntent = new Intent(AdminActivity.this , CreatePosisiActivity.class);
                startActivity(createPosisiIntent);
            }
        });

        readPosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readPosisiIntent = new Intent(AdminActivity.this , ReadPosisiActivity.class);
                startActivity(readPosisiIntent);
            }
        });

        updatePosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updatePosisiIntent = new Intent(AdminActivity.this , UpdatePosisiActivity.class);
                startActivity(updatePosisiIntent);

            }
        });

        deletePosisiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deletePosisiIntent = new Intent(AdminActivity.this , DeletePosisiActivity.class);
                startActivity(deletePosisiIntent);
            }
        });


    }
}
