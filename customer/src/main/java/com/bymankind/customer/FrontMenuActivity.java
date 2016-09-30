package com.bymankind.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_menu);

        Button buttonLihatMeja =  (Button) findViewById(R.id.buttonLihatMeja);
        Button buttonLihatStatus = (Button) findViewById(R.id.buttonLihatStatus);
        Button buttonPesanMeja = (Button) findViewById(R.id.buttonPesanMeja);

        buttonLihatMeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihatMejaIntent = new Intent(FrontMenuActivity.this, CheckMejaActivity.class);
                startActivity(lihatMejaIntent);
            }
        });

        buttonLihatStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihatStatusIntent = new Intent(FrontMenuActivity.this, CheckStatusPesananActivity.class);
                startActivity(lihatStatusIntent);
            }
        });

        buttonPesanMeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesanMejaIntent = new Intent(FrontMenuActivity.this, PesanMejaActivity.class);
                startActivity(pesanMejaIntent);
            }
        });
    }
}
