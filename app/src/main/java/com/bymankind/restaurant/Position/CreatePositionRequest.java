package com.bymankind.restaurant.Position;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/21/2016.
 */
public class CreatePositionRequest extends StringRequest{
    private final static String CREATE_POSITION_REQUEST_URL = "http://192.168.100.30/restoserver/api/insertPosition";
    private Map<String, String> params;

    public CreatePositionRequest(String name, int salary, Response.Listener<String> listener){
        super(Method.POST , CREATE_POSITION_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("salary",salary+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
