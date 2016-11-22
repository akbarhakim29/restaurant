package com.bymankind.restaurant.Chef;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 11/14/2016.
 */

public class UpdateCheckedRequest extends StringRequest{
    private final static String UPDATE_ORDER_REQUEST_URL = "http://192.168.100.9/restoserver/api/changeStatustoChecked";
    private Map<String, String> params;

    public UpdateCheckedRequest(String id_order, Response.Listener<String> listener) {
        super(Request.Method.POST, UPDATE_ORDER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_order", id_order);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
