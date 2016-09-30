package com.bymankind.restaurant.posisi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/22/2016.
 */
public class DeletePosisiRequest extends StringRequest {
    private final static String DELETE_REQUEST_URL = "http://192.168.100.9/restoserver/api/deletePosition";
    private Map<String, String> params;

    public DeletePosisiRequest(int id_position, Response.Listener<String> listener){
        super(Method.POST , DELETE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_position",id_position+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
