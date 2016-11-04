package com.bymankind.restaurant.Order;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class DetailOrderRequest extends StringRequest{
    private final static String DETAIL_ORDER_REQUEST_URL = "http://192.168.100.4/restoserver/api/getOrder";
    private Map<String, String> params;

    public DetailOrderRequest(String id_transaction, Response.Listener<String> listener){
        super(Request.Method.POST , DETAIL_ORDER_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_transaction",id_transaction);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
