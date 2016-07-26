package com.bymankind.restaurant.posisi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bymankind.restaurant.R;

public class ReadPosisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_posisi);

        ListView lvPosisi =  (ListView) findViewById(R.id.lvPosisi);



    }
}
