package com.bymankind.restaurant.Order;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class DeleteOrderRequest extends StringRequest {
    private final static String DELETE_ORDER_REQUEST_URL = "http://192.168.100.5/restoserver/api/deleteOrder";
    private Map<String, String> params;

    public DeleteOrderRequest(int id_transaction, Response.Listener<String> listener){
        super(Request.Method.POST , DELETE_ORDER_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_transaction",id_transaction+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
