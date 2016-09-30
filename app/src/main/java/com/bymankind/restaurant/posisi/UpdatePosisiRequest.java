package com.bymankind.restaurant.posisi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/22/2016.
 */
public class UpdatePosisiRequest extends StringRequest {
    private final static String UPDATE_REQUEST_URL = "http://192.168.100.9/restoserver/api/updatePosition";
    private Map<String, String> params;

    public UpdatePosisiRequest(int id_position, String name, int salary, Response.Listener<String> listener){
        super(Method.POST , UPDATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_position",id_position+"");
        params.put("name",name);
        params.put("salary",salary+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
