package com.bymankind.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bymankind.restaurant.Chef.ListStatusCooked;

public class ChefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);

        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        Button btnListOrder = (Button) findViewById(R.id.btnListOrder);

        Intent intent = getIntent();
        String username =  intent.getStringExtra("username");
        tvUsername.setText(username);

        btnListOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chefIntent = new Intent(ChefActivity.this, ListStatusCooked.class);
                ChefActivity.this.startActivity(chefIntent);
            }
        });
    }
}
