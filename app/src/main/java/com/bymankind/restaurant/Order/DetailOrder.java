package com.bymankind.restaurant.Order;

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

import org.json.JSONException;
import org.json.JSONObject;

public class DetailOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order);

        final EditText etID = (EditText) findViewById(R.id.etIDTransaction);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etIDTable = (EditText) findViewById(R.id.etIDTable);
        final EditText etIDMenu = (EditText) findViewById(R.id.etIDMenu);
        final EditText etQuantity = (EditText) findViewById(R.id.etQuantity);
        final EditText etIDOrderStatus = (EditText) findViewById(R.id.etIDOrderStatus);
        final EditText etDate = (EditText) findViewById(R.id.etDate);
        final EditText etTimeOrderPlaced = (EditText) findViewById(R.id.etTimeOrderPlaced);
        final EditText etTimeOrderCooked = (EditText) findViewById(R.id.etTimeOrderCooked);
        final EditText etTimeOrderChecked = (EditText) findViewById(R.id.etTimeOrderChecked);
        final EditText etTimeOrderAccepted = (EditText) findViewById(R.id.etTimeOrderAccepted);
        final EditText etTimePaid = (EditText) findViewById(R.id.etTimePaid);

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        int id_transaction = intent.getIntExtra("id_transaction",-1);
        int  id_customer = intent.getIntExtra("id_customer",-1);
        String name = intent.getStringExtra("name");
        int id_table = intent.getIntExtra("id_table",-1);
        int  id_menu = intent.getIntExtra("id_menu",-1);
        int  quantity = intent.getIntExtra("quantity",-1);
        int  id_order_status = intent.getIntExtra("id_order_status",-1);
        String description =  intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String timeOrderPlaced = intent.getStringExtra("timeOrderPlaced");
        String timeOrderCooked = intent.getStringExtra("timeOrderCooked");
        String timeOrderChecked = intent.getStringExtra("timeOrderChecked");
        String timeOrderAccepted = intent.getStringExtra("timeOrderAccepted");
        String timePaid = intent.getStringExtra("timePaid");

        etID.setText(id_transaction + "");
        etName.setText(name);
        etIDTable.setText(id_table+"");
        etIDMenu.setText(id_menu+"");
        etQuantity.setText(quantity+"");
        etIDOrderStatus.setText(id_order_status+"");
        etDate.setText(date);
        etTimeOrderPlaced.setText(timeOrderPlaced);
        etTimeOrderCooked.setText(timeOrderCooked);
        etTimeOrderChecked.setText(timeOrderChecked);
        etTimeOrderAccepted.setText(timeOrderAccepted);
        etTimePaid.setText(timePaid);

        // action update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_transaction = Integer.parseInt(etID.getText().toString());
                final int id_table = Integer.parseInt(etIDTable.getText().toString());
                final int id_menu = Integer.parseInt(etIDMenu.getText().toString());
                final int quantity = Integer.parseInt(etQuantity.getText().toString());
                final int id_order_status = Integer.parseInt(etIDOrderStatus.getText().toString());
                final String date = etDate.getText().toString();
                final String timeOrderPlaced = etTimeOrderPlaced.getText().toString();
                final String timeOrderCooked = etTimeOrderCooked.getText().toString();
                final String timeOrderChecked = etTimeOrderChecked.getText().toString();
                final String timeOrderAccepted = etTimeOrderAccepted.getText().toString();
                final String timePaid = etTimePaid.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailOrder.this,"Order updated" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailOrder.this, AdminActivity.class);
                                DetailOrder.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailOrder.this,"Order not updated" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UpdateOrderRequest updateOrderRequest= new UpdateOrderRequest(id_transaction,id_table,id_menu,quantity,id_order_status,date,timeOrderPlaced,timeOrderCooked,timeOrderChecked,timeOrderAccepted,timePaid,responseListener);
                RequestQueue queue = Volley.newRequestQueue(DetailOrder.this);
                queue.add(updateOrderRequest);
            }
        });

        // action delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_transaction = Integer.parseInt(etID.getText().toString());
                Response.Listener<String> deleteResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code==200){
                                Toast.makeText(DetailOrder.this,"Order deleted" ,Toast.LENGTH_LONG).show();
                                Intent successIntent = new Intent(DetailOrder.this, AdminActivity.class);
                                DetailOrder.this.startActivity(successIntent);
                            }
                            else{
                                Toast.makeText(DetailOrder.this,"Order not deleted" ,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteOrderRequest deleteOrderRequest= new DeleteOrderRequest(id_transaction,deleteResponse);
                RequestQueue queue = Volley.newRequestQueue(DetailOrder.this);
                queue.add(deleteOrderRequest);
            }
        });
    }
}
