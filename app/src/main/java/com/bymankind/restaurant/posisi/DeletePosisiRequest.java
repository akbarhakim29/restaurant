package com.bymankind.restaurant.posisi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/22/2016.
 */
public class DeletePosisiRequest extends StringRequest {
    private final static String LOGIN_REQUEST_URL = "http://192.168.1.173/login/sql_restaurant.php?operasi=delete_posisi";
    private Map<String, String> params;

    public DeletePosisiRequest(String posisi, Response.Listener<String> listener){
        super(Method.POST , LOGIN_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("posisi",posisi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
