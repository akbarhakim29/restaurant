package com.bymankind.restaurant.Position;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/6/2016.
 */

public class DetailPositionRequest extends StringRequest{
    private final static String DETAIL_POSITION_REQUEST_URL = "http://192.168.100.8/restoserver/api/getPosition";
    private Map<String, String> params;

    public DetailPositionRequest(String id_position, Response.Listener<String> listener){
        super(Method.POST , DETAIL_POSITION_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_position",id_position);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
