package com.bymankind.restaurant.Table;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class DeleteTableRequest extends StringRequest{
    private final static String DELETE_TABLE_REQUEST_URL = "http://192.168.100.5/restoserver/api/deleteTable";
    private Map<String, String> params;

    public DeleteTableRequest(int id_table, Response.Listener<String> listener){
        super(Request.Method.POST , DELETE_TABLE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_table",id_table+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
