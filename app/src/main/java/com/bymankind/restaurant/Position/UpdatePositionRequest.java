package com.bymankind.restaurant.Position;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/22/2016.
 */
public class UpdatePositionRequest extends StringRequest {
    private final static String UPDATE_POSITION_REQUEST_URL = "http://192.168.100.30/restoserver/api/updatePosition";
    private Map<String, String> params;

    public UpdatePositionRequest(int id_position, String name, int salary, Response.Listener<String> listener){
        super(Method.POST , UPDATE_POSITION_REQUEST_URL, listener , null);
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
