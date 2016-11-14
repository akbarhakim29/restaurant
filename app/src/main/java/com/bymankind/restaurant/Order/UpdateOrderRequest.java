package com.bymankind.restaurant.Order;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class UpdateOrderRequest extends StringRequest{
    private final static String UPDATE_ORDER_REQUEST_URL = "http://192.168.100.5/restoserver/api/updateOrder";
    private Map<String, String> params;

    public UpdateOrderRequest(int id_transaction,int id_table,int id_menu,int quantity,int id_order_status,
                              String date,String timeOrderPlaced,String timeOrderCooked,String timeOrderChecked,
                              String timeOrderAccepted,String timePaid, Response.Listener<String> listener) {
        super(Request.Method.POST, UPDATE_ORDER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_transaction", id_transaction + "");
        params.put("id_table", id_table + "");
        params.put("id_menu", id_menu+"");
        params.put("quantity", quantity+"");
        params.put("id_order_status", id_order_status+"");
        params.put("date", date);
        params.put("timeOrderPlaced", timeOrderPlaced);
        params.put("timeOrderCooked", timeOrderCooked);
        params.put("timeOrderChecked", timeOrderChecked);
        params.put("timeOrderAccepted", timeOrderAccepted);
        params.put("timePaid", timePaid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
